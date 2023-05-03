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
import modelo.Usuario;

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

        String sql = "INSERT INTO logsistema (data, atividade, usuario_id) VALUES (?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, sdf.format(new Date()));
            stm.setString(2, atividade);
            stm.setInt(3, Session.getIdUsuario());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastra logs do usuário. " + ex);
        }

    }

    //INSERINDO NOVO CADASTRO **************************************************    
    public void excluir(int idLog) {

        String sql = "DELETE FROM logsistema WHERE idLog = ?";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, idLog);
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir logs. " + ex);
        }

    }

    // LISTA LOG //////////////////////////////////////////////////////////////
    public ArrayList<LogsSistema> listagemLogs(String atividade, int limite) {
        ArrayList<LogsSistema> listagem = new ArrayList<>();

        String sql = "SELECT * FROM logsistema INNER JOIN usuario ON logsistema.usuario_id = usuario.idUsuario "
                + "WHERE logsistema.atividade LIKE ? ORDER BY idLog DESC LIMIT " + limite;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + atividade + "%");

            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    LogsSistema logo = new LogsSistema();
                    Usuario usuario = new Usuario();
                    logo.setIdLog(rs.getInt("idLog"));
                    logo.setData(rs.getString("data"));
                    logo.setAtividade(rs.getString("atividade"));
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    logo.setUsuario(usuario);

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
    public ArrayList<LogsSistema> listagemLogsPorUsuario(String usuarioProcurado, int limite) {
        ArrayList<LogsSistema> listagem = new ArrayList<>();

        String sql = "SELECT * FROM logsistema INNER JOIN usuario ON logsistema.usuario_id = usuario.idUsuario "
                + "WHERE usuario.nome LIKE ? ORDER BY idLog DESC LIMIT " + limite;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + usuarioProcurado + "%");

            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    LogsSistema logo = new LogsSistema();
                    Usuario usuario = new Usuario();
                    logo.setIdLog(rs.getInt("idLog"));
                    logo.setData(rs.getString("data"));
                    logo.setAtividade(rs.getString("atividade"));
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    logo.setUsuario(usuario);

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
    public ArrayList<LogsSistema> listagemLogsPorData(String data, int limite) {
        ArrayList<LogsSistema> listagem = new ArrayList<>();

        String sql = "SELECT * FROM logsistema INNER JOIN usuario ON logsistema.usuario_id = usuario.idUsuario "
                + "WHERE logsistema.data LIKE ? ORDER BY idLog DESC LIMIT " + limite;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + data + "%");

            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    LogsSistema logo = new LogsSistema();
                    Usuario usuario = new Usuario();
                    logo.setIdLog(rs.getInt("idLog"));
                    logo.setData(rs.getString("data"));
                    logo.setAtividade(rs.getString("atividade"));
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    logo.setUsuario(usuario);

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
