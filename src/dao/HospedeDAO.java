package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HospedeModelo;

public class HospedeDAO {
    private Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(HospedeModelo hospedeModelo) {
        try {
            String sql = "INSERT INTO hospedes (Nome, Sobrenome, DataNascimento, Nacionalidade, Telefone, IdReserva) VALUES (?,?,?,?,?,?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, hospedeModelo.getNome());
                pstm.setString(2, hospedeModelo.getSobrenome());
                pstm.setString(3, hospedeModelo.getDataNascimento());
                pstm.setString(4, hospedeModelo.getNacionalidade());
                pstm.setString(5, hospedeModelo.getTelefone());
                pstm.setInt(6, hospedeModelo.getIdReservas());

                pstm.execute();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int idHospede) {
        try {
            String sql = "DELETE FROM hospedes WHERE IdHospede = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, idHospede);
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(HospedeModelo hospedeModelo) {
        try {
            String sql = "UPDATE hospedes SET Nome = ?, Sobrenome = ?, DataNascimento = ?, Nacionalidade = ?, Telefone = ? WHERE IdReserva = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, hospedeModelo.getNome());
                pstm.setString(2, hospedeModelo.getSobrenome());
                pstm.setString(3, hospedeModelo.getDataNascimento());
                pstm.setString(4, hospedeModelo.getNacionalidade());
                pstm.setString(5, hospedeModelo.getTelefone());
                pstm.setInt(6, hospedeModelo.getIdReservas());

                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HospedeModelo> listar() {
        List<HospedeModelo> hospede = new ArrayList<>();
        try {
            String sql = "SELECT IdHospede, Nome, Sobrenome, DataNascimento, Nacionalidade, Telefone, IdReserva FROM hospedes";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                ResultSetEmHospede(hospede, pstm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hospede;
    }

    public List<HospedeModelo> buscar(String nome) {
        List<HospedeModelo> hospede = new ArrayList<>();
        try {
            String sql = "SELECT * FROM hospedes WHERE Nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, nome);
                pstm.execute();

                ResultSetEmHospede(hospede, pstm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hospede;
    }

    public void ResultSetEmHospede(List<HospedeModelo> hospede, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                HospedeModelo hospedeModelo = new HospedeModelo();
                hospedeModelo.setIdHospede(rst.getInt("IdHospede"));
                hospedeModelo.setNome(rst.getString("Nome"));
                hospedeModelo.setSobrenome(rst.getString("Sobrenome"));
                hospedeModelo.setDataNascimento(rst.getString("DataNascimento"));
                hospedeModelo.setNacionalidade(rst.getString("Nacionalidade"));
                hospedeModelo.setTelefone(rst.getString("Telefone"));
                hospedeModelo.setIdReservas(rst.getInt("IdReserva"));
                hospede.add(hospedeModelo);
            }
        }
    }

}
