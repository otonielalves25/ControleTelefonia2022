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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 *
 * @author Tony
 */
public class UsuarioDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, login, senha, status, previlegio) values(?,?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getLogin());
            stm.setString(3, usuario.getSenha());
            stm.setString(4, usuario.getStatus());
            stm.setString(5, usuario.getPrevilegio());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar  Dao. " + ex);
            return false;
        }

    }

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean update(Usuario usuario) {

        String sql = "UPDATE usuario set nome=?,login=?,senha=?,status=?, previlegio=? where idUsuario = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getLogin());
            stm.setString(3, usuario.getSenha());
            stm.setString(4, usuario.getStatus());
            stm.setString(5, usuario.getPrevilegio());
            stm.setInt(6, usuario.getIdUsuario());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int codigo) {
        String sql = "UPDATE usuario SET status = 'Inativo' where idUsuario = ?";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            stm.executeUpdate();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Dao. " + e);
            return false;
        }

    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Usuario getPorID(int codigo) {

        String sql = "SELECT * FROM usuario WHERE idUsuario = ? AND status = 'Ativo'";
        Usuario usuario = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setStatus(rs.getString("status"));
                    usuario.setPrevilegio(rs.getString("previlegio"));

                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar DAO. " + ex);
        }

        return usuario;
    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Usuario retornaPorNome(String procura) {

        String sql = "SELECT * FROM usuario WHERE nome = ? AND status = 'Ativo'";
        Usuario usuario = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, procura);        
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setStatus(rs.getString("status"));
                    usuario.setPrevilegio(rs.getString("previlegio"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar DAO. " + ex);
        }

        return usuario;
    }
    
        //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public List<Usuario> getListagem() {

        String sql = "SELECT * FROM usuario WHERE status = 'Ativo' ORDER BY nome";
        List<Usuario> lista = new ArrayList<>();
        Usuario usuario = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
  
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setStatus(rs.getString("status"));
                    usuario.setPrevilegio(rs.getString("previlegio"));
                    lista.add(usuario);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao DAO. " + ex);
        }

        return lista;
    }
    
        
        //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public List<Usuario> getListagemAtivos() {

        String sql = "SELECT * FROM usuario WHERE status = 'Ativo' ORDER BY nome";
        List<Usuario> lista = new ArrayList<>();
        Usuario usuario = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
  
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setStatus(rs.getString("status"));
                    usuario.setPrevilegio(rs.getString("previlegio"));
                    lista.add(usuario);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao DAO. " + ex);
        }

        return lista;
    }
    
        //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Usuario validarLogin(String login, String senha) {

        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ? AND status = 'Ativo'";
        Usuario usuario = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, login);        
            stm.setString(2, senha);        
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idUsuario"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setStatus(rs.getString("status"));
                    usuario.setPrevilegio(rs.getString("previlegio"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar DAO. " + ex);
        }

        return usuario;
    }
    
    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean trocarSenha(String senha, int idUsuario) {

        String sql = "UPDATE usuario set senha=? where idUsuario = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);        
            stm.setString(1, senha);         
            stm.setInt(2, idUsuario);
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Dao. " + ex);
            return false;
        }
    }


}
