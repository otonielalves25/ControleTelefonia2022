/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Acessorio;
import modelo.Emprestimo;

/**
 *
 * @author Tony
 */
public class EmprestimoDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Emprestimo emprestimo) {

        String sql = "INSERT INTO emprestimo (situacao, dataEmprestimo, dataDevolucao, funcionario_id, usuario_id, observacao, celular_id, chip_id) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, emprestimo.getSituacao());
            stm.setDate(2, (Date) emprestimo.getDataEmprestimo());
            stm.setDate(3, (Date) emprestimo.getDataDevolucao());
            stm.setInt(4, emprestimo.getFuncionario().getIdFuncionario());
            stm.setInt(5, emprestimo.getUsuario().getIdUsuario());
            stm.setString(6, emprestimo.getObservacao());
            stm.setInt(7, emprestimo.getCelular().getIdCelular());
            stm.setInt(8, emprestimo.getChip().getIdChip());
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
    public boolean update(Emprestimo emprestimo) {

        String sql = "UPDATE emprestimo SET situacao=?, dataEmprestimo=?, dataDevolucao=?, funcionario_id=?, usuario_id=?, observacao=?, celular_id=?, chip_id=? where idEmprestimo = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, emprestimo.getSituacao());
            stm.setDate(2, (Date) emprestimo.getDataEmprestimo());
            stm.setDate(3, (Date) emprestimo.getDataDevolucao());
            stm.setInt(4, emprestimo.getFuncionario().getIdFuncionario());
            stm.setInt(5, emprestimo.getUsuario().getIdUsuario());
            stm.setString(6, emprestimo.getObservacao());
            stm.setInt(7, emprestimo.getCelular().getIdCelular());
            stm.setInt(8, emprestimo.getChip().getIdChip());
            stm.setInt(9, emprestimo.getIdEmprestimo());

            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar emprestimo Dao. " + ex);
            return false;
        }
    }
    // ------------ALTERAR CADASTRA  --------------------------------------    

    public boolean updateDevolver(Emprestimo emprestimo) {

        String sql = "UPDATE emprestimo SET situacao=?, dataDevolucao=?, observacaoDevolucao = ? where idEmprestimo = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, emprestimo.getSituacao());
            stm.setDate(2, (Date) emprestimo.getDataDevolucao());
            stm.setString(3, emprestimo.getObservacao());
            stm.setString(4, emprestimo.getObservacaoDevolucao());
            stm.setInt(5, emprestimo.getIdEmprestimo());

            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar emprestimo Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean deleteUmItem(int codigo) {
        String sql = "DELETE FROM emprestimo WHERE idEmprestimo= ?";

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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir emprestimo Dao. " + e);
            return false;
        }
    }

//    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
//    public Acessorio retornaPorID(int codigo) {
//
//        String sql = "SELECT * FROM emprestimo_acessorio WHERE idAcessorio = ?";
//        Acessorio acessorio = null;
//        try {
//            con = conexao.ConexaoSqLite.getConnection();
//            stm = con.prepareStatement(sql);
//            stm.setInt(1, codigo);
//            rs = stm.executeQuery();
//            if (rs != null) {
//                while (rs.next()) {
//                    acessorio = new Acessorio();
//                    acessorio.setIdAcessorio(rs.getInt("idAcessorio"));
//                    acessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
//                }
//            }
//            //fechando as conexões
//            con.close();
//            stm.close();
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao Consultar acessorio DAO. " + ex);
//        }
//
//        return acessorio;
//    }
//
//    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
//    public ArrayList<Acessorio> getListagemPorEmprestimo(int emprestimo_id) {
//
//        ArrayList<Acessorio> Listagem = new ArrayList<>();
//        String sql = "SELECT * FROM emprestimo_acessorio WHERE categoria_id LIKE ? ORDER BY nomeAcessorio";
//        Acessorio acessorio;
//
//        try {
//            con = conexao.ConexaoSqLite.getConnection();
//            stm = con.prepareStatement(sql);
//            stm.setInt(1, emprestimo_id);
//            rs = stm.executeQuery();
//            while (rs.next()) {
//
//                acessorio = new Acessorio();
//                acessorio.setIdAcessorio(rs.getInt("idAcessorio"));
//                acessorio.setNomeAcessorio(rs.getString("nomeAcessorio"));
//                Listagem.add(acessorio);
//            }
//            //fechando as conexões
//            con.close();
//            stm.close();
//
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro ao buscar todos usuários DAO. " + ex);
//        }
//        return Listagem;
//    }
}
