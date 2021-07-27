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
import javax.swing.JOptionPane;
import modelo.Logo;

/**
 *
 * @author Tony
 */
public class LogoDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Logo logo) {

        String sql = "INSERT INTO logo (imagem1,imagem2,imagem3,imagem4) VALUES (?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBytes(1, logo.getImagem1());
            stm.setBytes(2, logo.getImagem2());
            stm.setBytes(3, logo.getImagem3());
            stm.setBytes(4, logo.getImagem4());

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
    public boolean update(Logo logo) {

        String sql = "UPDATE logo set imagem1=?,imagem2=?,imagem3=?,imagem4=? where idImagem = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setBytes(1, logo.getImagem1());
            stm.setBytes(2, logo.getImagem2());
            stm.setBytes(3, logo.getImagem3());
            stm.setBytes(4, logo.getImagem4());
            stm.setInt(5, logo.getIdImagem());
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

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Logo getListagem() {

        String sql = "SELECT * FROM logo";
        Logo logo = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    logo = new Logo(rs.getInt("idImagem"), rs.getBytes("imagem1"), rs.getBytes("imagem2"),
                            rs.getBytes("imagem3"), rs.getBytes("imagem4"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar acessorio DAO. " + ex);
        }

        return logo;
    }

}
