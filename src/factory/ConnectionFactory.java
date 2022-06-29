package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

    public DataSource dataSource;
    private String username_db = "alura"; // "alura"
    private String password_db = "pass123"; // "pass123"

    public ConnectionFactory() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/alura_hotel?useTimezone=true&serverTimezone=UTC");
        comboPooledDataSource.setUser(username_db);
        comboPooledDataSource.setPassword(password_db);
        this.dataSource = comboPooledDataSource;
    }

    public Connection recuperarConexao() {
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(String user, String password_user) {
        if (user.equals(username_db) && password_user.equals(password_db)) {
            return true;
        } else {
            return false;
        }
    }
}
