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
import modelo.Empresa;

/**
 *
 * @author Tony
 */
public class EmpresaDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Empresa empresa) {

        String sql = "INSERT INTO empresa (nomeEmpresa) VALUES (?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, empresa.getNomeEmpresa());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar empresa Dao. " + ex);
            return false;
        }

    }

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean update(Empresa empresa) {

        String sql = "UPDATE empresa SET nomeEmpresa =? where idEmpresa = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, empresa.getNomeEmpresa());
            stm.setInt(2, empresa.getIdEmpresa());

            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar empresa Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int codigo) {
        String sql = "DELETE from empresa where idEmpresa = ?";

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
            JOptionPane.showMessageDialog(null, "Empresa não pode ser excluído,  tem funcionário cadastrado. ");
            return false;
        }

    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Empresa retornaPorID(int codigo) {

        String sql = "SELECT * FROM empresa WHERE idEmpresa = ?";
        Empresa empresa = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    empresa = new Empresa();
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar empresa DAO. " + ex);
        }

        return empresa;
    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Empresa retornaPorNome(String procura) {

        String sql = "SELECT * FROM empresa WHERE nomeEmpresa = ?";
        Empresa empresa = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, procura);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    empresa = new Empresa();
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));

                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar empresa DAO. " + ex);
        }

        return empresa;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Empresa> getListagemLike(String busca) {

        ArrayList<Empresa> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM empresa WHERE nomeEmpresa LIKE ? ORDER BY nomeEmpresa";
        Empresa empresa;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
            rs = stm.executeQuery();
            while (rs.next()) {

                empresa = new Empresa();
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                Listagem.add(empresa);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos empresa DAO. " + ex);
        }
        return Listagem;
    }

}
