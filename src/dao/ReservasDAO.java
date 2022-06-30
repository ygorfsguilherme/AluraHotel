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
			String sql = "INSERT INTO reservas (DataCheckin, DataCheckout, FormaPagamento, ValorHospedagem) VALUES (?,?,?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {

				pstm.setString(1, reservasModelo.getDataCheckin());
				pstm.setString(2, reservasModelo.getDataCheckout());
				pstm.setString(3, reservasModelo.getFormaPagamento());
				pstm.setInt(4, reservasModelo.getValor());

				pstm.execute();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ReservasModelo> listar() {
		List<ReservasModelo> reservas = new ArrayList<>();
		try {
			String sql = "SELECT IdReserva, DataCheckin, DataCheckout, FormaPagamento, ValorHospedagem FROM reservas";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();

				ResultSetEmReservas(reservas, pstm);
			}
			return reservas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void ResultSetEmReservas(List<ReservasModelo> reservas, PreparedStatement pstm)
			throws SQLException {

		try (ResultSet rst = pstm.getResultSet()) {
			while (rst.next()) {
				ReservasModelo reserva = new ReservasModelo(
						rst.getString("DataCheckin"),
						rst.getString("DataCheckout"),
						rst.getString("FormaPagamento"),
						rst.getInt("ValorHospedagem"),
						rst.getInt("IdReserva"));
				reservas.add(reserva);
			}
		}
	}

	public void deletar(Integer id) {
		try (PreparedStatement stm = connection.prepareStatement("DELETE FROM reservas WHERE IdReserva = ?")) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void alterar(String data_checkin, String data_checkout, String forma_pagamento, Integer valor_hosp,
			Integer id) {
		try (PreparedStatement stm = connection.prepareStatement(
				"UPDATE reservas r SET r.DataCheckin = ?, r.DataCheckout = ?, r.FormaPagamento = ?, r.ValorHospedagem, = ? WHERE ID = ?")) {
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
