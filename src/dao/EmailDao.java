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
import javax.swing.JOptionPane;
import modelo.Acessorio;
import modelo.Email;

/**
 *
 * @author Tony
 */
public class EmailDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Email email) {

        String sql = "INSERT INTO email (nome, email) VALUES (?,?)";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email.getNome());
            stm.setString(2, email.getEmail());
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

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean update(Email email) {

        String sql = "UPDATE email SET nome = ?, email = ? WHERE id = ?";
        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, email.getNome());
            stm.setString(2, email.getEmail());
            stm.setInt(3, email.getId());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar email Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int id) {
        String sql = "DELETE FROM email WHERE id= ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir email Dao. " + e);
            return false;
        }
    }

    //Busca por nome
    public Email buscaPorNome(String nome) {

        Email email = null;

        String sql = "SELECT * FROM email WHERE nome = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, nome);

            rs = stm.executeQuery();

            if (rs.next()) {
                email = new Email(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            }

            //fechando as conexões;
            con.close();
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao email dao.");
        }
        return email;

    }

    // Busca por email
    public Email buscaPorEmail(String texto) {

        Email email = null;

        String sql = "SELECT * FROM email WHERE email = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, texto);

            rs = stm.executeQuery();

            if (rs.next()) {
                email = new Email(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            }

            //fechando as conexões;
            con.close();
            stm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar email dao.");
        }
        return email;

    }

}
