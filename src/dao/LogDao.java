/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.LogsSistema;
import modelo.Session;

/**
 *
 * @author Tony
 */
public class LogDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    //INSERINDO NOVO CADASTRO **************************************************    
    public void insert(String atividade) {

        String sql = "INSERT INTO logsistema (data, atividade, usuario) VALUES (?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, sdf.format(new Date()));
            stm.setString(2, atividade);
            stm.setString(3, Session.getNome());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {

        }

    }

    // LISTA LOG //////////////////////////////////////////////////////////////
    public ArrayList<LogsSistema> listagemLogs(String atividade, int  limite) {
        ArrayList<LogsSistema> listagem = new ArrayList<>();

        String sql = "SELECT * FROM logsistema WHERE atividade LIKE ? LIMIT " + limite;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + atividade + "%");

            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    LogsSistema logo = new LogsSistema();
                    logo.setIdLog(rs.getInt("idLog"));
                    logo.setData(rs.getString("data"));
                    logo.setAtividade(rs.getString("atividade"));
                    logo.setUsuario(rs.getString("usuario"));

                    listagem.add(logo);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao DAO. " + ex);
        }

        return listagem;
    }
    
    
    // LISTA POR USUARIO //////////////////////////////////////////////////////////////
    public ArrayList<LogsSistema> listagemLogsPorUsuario(String usuario, int  limite){
        ArrayList<LogsSistema> listagem = new ArrayList<>();

        String sql = "SELECT * FROM logsistema WHERE usuario LIKE ? LIMIT " + limite;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + usuario + "%");

            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    LogsSistema logo = new LogsSistema();
                    logo.setIdLog(rs.getInt("idLog"));
                    logo.setData(rs.getString("data"));
                    logo.setAtividade(rs.getString("atividade"));
                    logo.setUsuario(rs.getString("usuario"));

                    listagem.add(logo);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao DAO. " + ex);
        }

        return listagem;
    }

    
        // LISTA POR DATA //////////////////////////////////////////////////////////////
    public ArrayList<LogsSistema> listagemLogsPorData(String data, int  limite){
        ArrayList<LogsSistema> listagem = new ArrayList<>();

        String sql = "SELECT * FROM logsistema WHERE data LIKE ? LIMIT " + limite;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + data + "%");

            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    LogsSistema logo = new LogsSistema();
                    logo.setIdLog(rs.getInt("idLog"));
                    logo.setData(rs.getString("data"));
                    logo.setAtividade(rs.getString("atividade"));
                    logo.setUsuario(rs.getString("usuario"));

                    listagem.add(logo);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao DAO. " + ex);
        }

        return listagem;
    }
}
