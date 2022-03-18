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
import modelo.Localidade;

/**
 *
 * @author Tony
 */
public class LocalidadeDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Localidade localidade) {

        String sql = "INSERT INTO localidade (nomeLocalidade, tipoLocalidade) VALUES (?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, localidade.getNomeLocalidade());
            stm.setString(2, localidade.getTipoLocalidade());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Catedoria Dao. " + ex);
            return false;
        }

    }

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean update(Localidade localidade) {

        String sql = "UPDATE localidade set nomeLocalidade=?, tipoLocalidade=? where idLocalidade = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, localidade.getNomeLocalidade());
            stm.setString(2, localidade.getTipoLocalidade());
            stm.setInt(3, localidade.getIdLocalidade());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Categoria Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int codigo) {
        String sql = "DELETE from localidade where idLocalidade= ?";

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
            JOptionPane.showMessageDialog(null, "Localidade não pode ser excluído,  tem funcionário cadastrado. ");
            return false;
        }

    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Localidade retornaPorID(int codigo) {

        String sql = "SELECT * FROM localidade WHERE idLocalidade = ?";
        Localidade localidade = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    localidade = new Localidade();
                    localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                    localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                    localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Categoria DAO. " + ex);
        }

        return localidade;
    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Localidade retornaPorNome(String procura) {

        String sql = "SELECT * FROM localidade WHERE nomeLocalidade = ?";
        Localidade localidade = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, procura);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    localidade = new Localidade();
                    localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                    localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                    localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Categoria DAO. " + ex);
        }

        return localidade;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Localidade> getListagemLike(String busca) {

        ArrayList<Localidade> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM localidade WHERE nomeLocalidade LIKE ? ORDER BY nomeLocalidade";
        Localidade localidade;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
            rs = stm.executeQuery();
            while (rs.next()) {

                localidade = new Localidade();
                localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));
                Listagem.add(localidade);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos usuários DAO. " + ex);
        }
        return Listagem;
    }

}
