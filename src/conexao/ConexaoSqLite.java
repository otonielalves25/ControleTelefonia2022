package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoSqLite {

    private static Connection conexaoMysql;

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
    // CONEXÃO SQLITE LOCAL ///////////////////////////////////////////////////
    // CONEXÃO MYSQ SERVIDOR CELEPAR ///////////////////////////////////////////
    //CONECTAR NA DETRAN AQUI SERVIDOR ANDRÉ DE PAULA OU MARCELO ANEMMAM DA CELEPAR   
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    // CONEXÃO COM SERVIDOR
    private static final String URL = "jdbc:mysql://10.50.0.19/bd_telefonia";
    private static final String ROOT = "detran_cgi";
    private static final String SENHA = "Detr@n2020";

    // CONEXÃO COM SERVIDOR LOCALO
//    private static final String URL = "jdbc:mysql://localhost/bd_telefonia";
//    private static final String ROOT = "root";
//    private static final String SENHA = "";
    
    public static Connection getConnection() {

        try {
            //Conexta com o banco de dados
            Class.forName(DRIVER);
            conexaoMysql = DriverManager.getConnection(URL, ROOT, SENHA);

        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de Dados " + ex);
        }
        //Retorna uma conexão
        return conexaoMysql;
    }

    // CONEXÃO MYSQ SERVIDOR CELEPAR ///////////////////////////////////////////
}
