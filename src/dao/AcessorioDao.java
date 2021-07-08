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

/**
 *
 * @author Tony
 */
public class AcessorioDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Acessorio acessorio) {

        String sql = "INSERT INTO emprestimo_acessorio (nomeAcessorio, emprestimo_id) VALUES (?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, acessorio.getNomeAcessorio());
            stm.setInt(2, acessorio.getEmprestimo().getIdEmprestimo());
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
    public boolean update(Acessorio acessorio) {

        String sql = "UPDATE emprestimo_acessorio set nomeAcessorio = ?, emprestimo_id = ? where idAcessorio = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, acessorio.getNomeAcessorio());
            stm.setInt(2, acessorio.getEmprestimo().getIdEmprestimo());
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

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean deleteUmItem(int codigo) {
        String sql = "DELETE from emprestimo_acessorio where idAcessorio= ?";

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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir acessorio Dao. " + e);
            return false;
        }
    }

    //-----------DELETAR POR EMPRESTIMO -----------------------------------------------
    public boolean deletePorIdEmprestimo(int codigo) {
        String sql = "DELETE from emprestimo_acessorio where emprestimo_id = ?";

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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir acessorio Dao. " + e);
            return false;
        }
    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Acessorio retornaPorID(int codigo) {

        String sql = "SELECT * FROM emprestimo_acessorio WHERE idAcessorio = ?";
        Acessorio acessorio = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    acessorio = new Acessorio();
                    acessorio.setIdAcessorio(rs.getInt("idAcessorio"));
                    acessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar acessorio DAO. " + ex);
        }

        return acessorio;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Acessorio> getListagemPorEmprestimo(int emprestimo_id) {

        ArrayList<Acessorio> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo_acessorio WHERE emprestimo_id = ? ORDER BY nomeAcessorio";
        Acessorio acessorio;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, emprestimo_id);
            rs = stm.executeQuery();
            while (rs.next()) {

                acessorio = new Acessorio();
                acessorio.setIdAcessorio(rs.getInt("idAcessorio"));
                acessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
                Listagem.add(acessorio);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos acessoriao DAO. " + ex);
        }
        return Listagem;
    }
}
