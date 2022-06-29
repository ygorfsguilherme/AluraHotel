package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import model.Categoria;
import model.ReservasModelo;

public class ReservasDAO {

	private Connection connection;

	public ReservasDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(ReservasModelo reservasModelo) {
		try {
			String sql = "INSERT INTO reservas (data_checkin, data_checkout, forma_pagamento, valor_hosp) VALUES (?,?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, reservasModelo.getDataCheckin());
				pstm.setString(2, reservasModelo.getDataCheckout());
				pstm.setString(3, reservasModelo.getFormaPagamento());
				pstm.setInt(4, reservasModelo.getValor());

				pstm.execute();

				// try (ResultSet rst = pstm.getGeneratedKeys()) {
				// while (rst.next()) {
				// reserva.setId(rst.getInt(1));
				// }
				// }
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ReservasModelo> listar() {
		List<ReservasModelo> reservas = new ArrayList<>();
		try {
			String sql = "SELECT id, data_checkin, data_checkout, forma_pagamento, valor_hosp FROM reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				trasformarResultSetEmProduto(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void trasformarResultSetEmProduto(List<ReservasModelo> reservas, PreparedStatement pstm)
			throws SQLException {

		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				ReservasModelo produto = new ReservasModelo(
						rst.getString("data_checkin"),
						rst.getString("data_checkout"),
						rst.getString("forma_pagamento"),
						rst.getInt("valor_hosp"),
						rst.getInt("id"));
				reservas.add(produto);
			}
		}
	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE ID = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(String data_checkin, String data_checkout, String forma_pagamento, Integer valor_hosp,
			Integer id) {
		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE reservas r SET r.data_checkin = ?, r.data_checkout = ?, r.forma_pagamento = ?, r.valor_hosp = ? WHERE ID = ?")) {
			stm.setString(1, data_checkin);
			stm.setString(2, data_checkout);
			stm.setString(3, forma_pagamento);
			stm.setInt(4, valor_hosp);
			stm.setInt(5, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
