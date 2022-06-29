package controller;

import java.sql.Connection;
import factory.ConnectionFactory;
import model.ReservasModelo;
import dao.ReservasDAO;

public class ReservasController {

    private ReservasDAO reservasDAO;

    public ReservasController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.reservasDAO = new ReservasDAO(connection);
    }

    public void salvar(ReservasModelo reservasModelo) {
        this.reservasDAO.salvar(reservasModelo);
    }

    // public void deletar(Integer id) {
    // this.reservasDAO.deletar(id);
    // }

    // public List<Produto> listar() {
    // return this.reservasDAO.listar();
    // }

    // public void alterar(String nome, String descricao, Integer id) {
    // this.reservasDAO.alterar(nome, descricao, id);
    // }
}
