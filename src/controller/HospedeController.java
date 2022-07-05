package controller;

import java.sql.Connection;
import java.util.List;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import model.HospedeModelo;

public class HospedeController {
    private HospedeDAO hospedeDAO;

    public HospedeController() {
        Connection connection = new ConnectionFactory().recuperarConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }

    public void salvar(HospedeModelo hospedeDAO) {
        this.hospedeDAO.salvar(hospedeDAO);
    }

    public List<HospedeModelo> listar() {
        return this.hospedeDAO.listar();
    }

    public void deletar(Integer id) {
        this.hospedeDAO.deletar(id);
    }

    public void alterar(HospedeModelo hospedeModelo) {
        this.hospedeDAO.alterar(hospedeModelo);
    }

    public List<HospedeModelo> buscar(String nome) {
        return this.hospedeDAO.buscar(nome);
    }

}
