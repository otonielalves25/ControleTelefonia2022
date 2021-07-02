package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoSqLite {

    private static Connection conexao;
    private static final String CAMINHO = "jdbc:sqlite:bd/bd_telefonia.db";
    private static final String DRIVER = "org.sqlite.JDBC";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(CAMINHO);
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de Dados " + ex);
        }

        return conexao;
    }

}
