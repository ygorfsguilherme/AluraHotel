package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
