package controller;

import java.sql.Connection;
import java.util.List;

import dao.ReservasDAO;
import factory.ConnectionFactory;
import model.ReservasModelo;

public class ReservasController {

    private ReservasDAO reservasDAO;

    public ReservasController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.reservasDAO = new ReservasDAO(connection);
    }

    public void salvar(ReservasModelo reservasModelo) {
        this.reservasDAO.salvar(reservasModelo);
    }

    public List<ReservasModelo> listar() {
        return this.reservasDAO.listar();
    }

    public void deletar(Integer id) {
        this.reservasDAO.deletar(id);
    }

    public void alterar(String data_checkin, String data_checkout, String forma_pagamento, Integer valor_hosp,
            Integer id) {
        this.reservasDAO.alterar(data_checkin, data_checkout, forma_pagamento, valor_hosp, id);
    }
}
