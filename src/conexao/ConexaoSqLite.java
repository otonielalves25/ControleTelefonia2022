package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoSqLite {

//     //CONEXÃO SQLITE LOCAL ///////////////////////////////////////////////////
//    private static Connection conexao;
//    private static final String CAMINHO = "jdbc:sqlite:bd/bd_telefonia.db";
//    private static final String DRIVER = "org.sqlite.JDBC";
//
//    public static Connection getConnection() {
//
//        try {
//            Class.forName(DRIVER);
//            conexao = DriverManager.getConnection(CAMINHO);
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de Dados " + ex);
//        }
//
//        return conexao;
//    }
    // CONEXÃO FIM SQLITE LOCAL /////////////////////////////////////////////////// 
    //CONECTAR NA DETRAN AQUI SERVIDOR ANDRÉ DE PAULA OU MARCELO ANEMMAM DA CELEPAR 
//    private static Connection conexaoMysql;
//    private static final String DRIVER = "com.mysql.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://10.50.0.19/bd_telefonia";
//    private static final String ROOT = "detran_cgi";
//    private static final String SENHA = "Detr@n2020";
//
//    public static Connection getConnection() {
//
//        try {
//    
//            Class.forName(DRIVER);
//            conexaoMysql = DriverManager.getConnection(URL, ROOT, SENHA);
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de Dados: Erro -> " + ex);
//        }
//
//        return conexaoMysql;
//    }
    // FIM CONEXÃO  MYSQ SERVIDOR CELEPAR ///////////////////////////////////////////
    // *************************************************************************** //
    //INICIO CONEXÃO  MYSQ SERVIDOR LOCALHOST ///////////////////////////////////////
    private static Connection conexaoMysql;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/bd_telefonia";
    private static final String ROOT = "root";
    private static final String SENHA = "";

    public static Connection getConnection() {

        try {

            Class.forName(DRIVER);
            conexaoMysql = DriverManager.getConnection(URL, ROOT, SENHA);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de Dados: Erro -> " + ex);
        }

        return conexaoMysql;
    }

    //FIM CONEXÃO  MYSQ SERVIDOR LOCALHOST ///////////////////////////////////////
}
