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
import modelo.Cargo;
import modelo.MotivoEmprestimo;

/**
 *
 * @author Tony
 */
public class MotivoEmprestimoDao {

    private Connection con;
    private PreparedStatement stm = null;
    private ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(MotivoEmprestimo motivo) {

        String sql = "INSERT INTO motivoemprestimo (motivo) VALUES (?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, motivo.getMotivoEmprestimo());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Cargo Dao. " + ex);
            return false;
        }

    }

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean update(MotivoEmprestimo motivo) {

        String sql = "UPDATE motivoemprestimo set motivo = ? where idmotivoEmprestimo = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, motivo.getMotivoEmprestimo());
            stm.setInt(2, motivo.getIdMotivoEmprestimo());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar cargo Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int codigo) {
        String sql = "DELETE from motivoemprestimo where idmotivoEmprestimo= ?";

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
            JOptionPane.showMessageDialog(null, "Cargo não pode ser excluído,  tem funcionário cadastrado. ");
            return false;
        }

    }

    //----------- RETORNA TODOS ------------------------------------------------------------
    public ArrayList<MotivoEmprestimo> getListagem() {

        ArrayList<MotivoEmprestimo> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM motivoemprestimo ORDER BY motivo";
        MotivoEmprestimo motivo;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {

                motivo = new MotivoEmprestimo();
                motivo.setIdMotivoEmprestimo(rs.getInt("idmotivoEmprestimo"));
                motivo.setMotivoEmprestimo(rs.getString("motivo"));
                Listagem.add(motivo);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos cargos DAO. " + ex);
        }
        return Listagem;
    }

}
