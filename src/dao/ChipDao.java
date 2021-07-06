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
import modelo.Chip;


/**
 *
 * @author Tony
 */
public class ChipDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Chip chip) {

        String sql = "INSERT INTO chip (numeroLinha,telefonia,dados,status,observacao,codigoChip) values(?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, chip.getNumeroLinha());
            stm.setBoolean(2, chip.isIsTelefonia());
            stm.setBoolean(3, chip.isIsDado());
            stm.setString(4, chip.getStatus());
            stm.setString(5, chip.getObservacao());
            stm.setString(6, chip.getCodigoChip());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar clip Dao. " + ex);
            return false;
        }

    }

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean update(Chip chip) {

        String sql = "UPDATE chip set numeroLinha=?, telefonia=?, dados=?,status=?,observacao=?, codigoChip=? where idChip = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, chip.getNumeroLinha());
            stm.setBoolean(2, chip.isIsTelefonia());
            stm.setBoolean(3, chip.isIsDado());
            stm.setString(4, chip.getStatus());
            stm.setString(5, chip.getObservacao());
            stm.setString(6, chip.getCodigoChip());
            stm.setInt(7, chip.getIdChip());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar clip Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int codigo) {
        String sql = "DELETE from chip where idChip= ?";

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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir clip Dao. " + e);
            return false;
        }

    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Chip getPorID(int codigo) {

        String sql = "SELECT * FROM Chip WHERE idChip = ?";
        Chip chip = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    chip = new Chip();
                    chip.setIdChip(rs.getInt("idChip"));
                    chip.setCodigoChip(rs.getString("codigoChip"));
                    chip.setNumeroLinha(rs.getString("numeroLinha"));
                    chip.setIsTelefonia(rs.getBoolean("telefonia"));
                    chip.setIsDado(rs.getBoolean("dados"));
                    chip.setStatus(rs.getString("status"));
                    chip.setObservacao(rs.getString("observacao"));

                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar clip DAO. " + ex);
        }

        return chip;
    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Chip retornaPorNome(String procura) {

        String sql = "SELECT * FROM Chip WHERE codigoChip = ? OR numeroLinha = ? ";
        Chip chip = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, procura);
            stm.setString(2, procura);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    chip = new Chip();
                    chip.setIdChip(rs.getInt("idChip"));
                    chip.setCodigoChip(rs.getString("codigoChip"));
                    chip.setNumeroLinha(rs.getString("numeroLinha"));
                    chip.setIsTelefonia(rs.getBoolean("telefonia"));
                    chip.setIsDado(rs.getBoolean("dados"));
                    chip.setStatus(rs.getString("status"));
                    chip.setObservacao(rs.getString("observacao"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar clip DAO. " + ex);
        }

        return chip;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Chip> getListagemLike(String busca) {

        ArrayList<Chip> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM chip WHERE numeroLinha LIKE ? OR codigoChip LIKE ? ORDER BY numeroLinha";
        Chip chip;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
            stm.setString(2, "%" + busca + "%");
            rs = stm.executeQuery();
            while (rs.next()) {

                chip = new Chip();
                chip.setIdChip(rs.getInt("idChip"));
                chip.setCodigoChip(rs.getString("codigoChip"));
                chip.setNumeroLinha(rs.getString("numeroLinha"));
                chip.setIsTelefonia(rs.getBoolean("telefonia"));
                chip.setIsDado(rs.getBoolean("dados"));
                chip.setStatus(rs.getString("status"));
                chip.setObservacao(rs.getString("observacao"));
                Listagem.add(chip);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos clip DAO. " + ex);
        }
        return Listagem;
    }

}
