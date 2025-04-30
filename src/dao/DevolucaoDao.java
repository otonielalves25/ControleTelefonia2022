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
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Devolucao;

/**
 *
 * @author Tony
 */
public class DevolucaoDao {

    private Connection con;
    private PreparedStatement stm = null;
    private ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Devolucao devolucao) {

        String sql = "INSERT INTO devolucao (situacao, emprestimo_id, chip_id, celular_id, formaEnvia, dataEnvio, usuario_id) VALUES (?,?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, devolucao.getSituacao());
            stm.setInt(2, devolucao.getEmprestimo().getIdEmprestimo());
            stm.setInt(3, devolucao.getChip() == null ? Types.INTEGER : devolucao.getChip().getIdChip());
            stm.setInt(4, devolucao.getCelular() == null ? Types.INTEGER : devolucao.getCelular().getIdCelular());
            stm.setString(5, devolucao.getFormaEnvio());
            stm.setString(6, devolucao.getDataEnvio());
            stm.setInt(7, devolucao.getUsuario().getIdUsuario());

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
    public boolean update(Devolucao devolucao) {

        String sql = "UPDATE devolucao SET situacao = ?, emprestimo_id = ?, chip_id = ?, celular_id = ?, formaEnvia = ?, dataEnvio = ?, usuario_id = ?) VALUES (?,?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, devolucao.getSituacao());
            stm.setInt(2, devolucao.getEmprestimo().getIdEmprestimo());
            stm.setInt(3, devolucao.getChip() == null ? Types.INTEGER : devolucao.getChip().getIdChip());
            stm.setInt(4, devolucao.getCelular() == null ? Types.INTEGER : devolucao.getCelular().getIdCelular());
            stm.setString(5, devolucao.getFormaEnvio());
            stm.setString(6, devolucao.getDataEnvio());
            stm.setInt(7, devolucao.getUsuario().getIdUsuario());

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

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean marcarDevolvido(int id, int usuario_id, String situacao) {

        String sql = "UPDATE devolucao set situacao = ?, usuario_id = ? where id = ?";
        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, situacao);
            stm.setInt(2, usuario_id);
            stm.setInt(3, id);
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

        String sql = "DELETE from devolucao where id = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
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
    public ArrayList<Devolucao> getListagem() {

        ArrayList<Devolucao> listagem = new ArrayList<>();
        String sql = "SELECT * FROM devolucao ORDER BY data";
        Devolucao devolucao;

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                devolucao = new Devolucao();

                listagem.add(devolucao);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos cargos DAO. " + ex);
        }
        return listagem;
    }

}
