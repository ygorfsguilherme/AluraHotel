package control;

import java.sql.*;

public class ConexaoDB {
    public static Connection conector(String username, char[] password) {
        java.sql.Connection conexao = null;
        String drive = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/alura_hotel";
        String user = username; // "ygor"
        String password_user = new String(password); // "P@ssw0rd"

        try {
            Class.forName(drive);
            conexao = DriverManager.getConnection(url, user, password_user);
            return conexao;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }
}
