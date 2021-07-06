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
import modelo.Categoria;
import modelo.Marca;

/**
 *
 * @author Tony
 */
public class MarcaDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Marca marca) {

        String sql = "INSERT INTO marca (marca, categoria_id) VALUES (?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, marca.getMarca());
            stm.setInt(2, marca.getCategoria().getIdCategoria());
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
    public boolean update(Marca marca) {

        String sql = "UPDATE marca SET marca =?, categoria_id = ? where idMarca = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, marca.getMarca());
            stm.setInt(2, marca.getCategoria().getIdCategoria());
            stm.setInt(3, marca.getIdMarca());
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
        String sql = "DELETE from marca where idMarca = ?";

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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Tipo Dao. " + e);
            return false;
        }

    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Marca retornaPorID(int codigo) {

        String sql = "SELECT * FROM marca WHERE idMarca = ?";
        Marca marca = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    marca = new Marca();
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    Categoria categoria = new CategoriaDao().retornaPorID(rs.getInt("categoria_id"));
                    marca.setCategoria(categoria);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Categoria DAO. " + ex);
        }

        return marca;
    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Marca retornaPorNome(String procura) {

        String sql = "SELECT * FROM marca WHERE marca = ?";
        Marca marca = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, procura);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    marca = new Marca();
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    Categoria categoria = new CategoriaDao().retornaPorID(rs.getInt("categoria_id"));
                    marca.setCategoria(categoria);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Categoria DAO. " + ex);
        }

        return marca;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Marca> getListagemLike(String busca) {

        ArrayList<Marca> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM marca WHERE marca LIKE ? ORDER BY marca";
        Marca marca = null;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
            rs = stm.executeQuery();
            while (rs.next()) {

                marca = new Marca();
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                Categoria categoria = new CategoriaDao().retornaPorID(rs.getInt("categoria_id"));
                marca.setCategoria(categoria);
                Listagem.add(marca);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos usuários DAO. " + ex);
        }
        return Listagem;
    }
        //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Marca> getListagemPorCategoria(int categoria_id) {

        ArrayList<Marca> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM marca WHERE categoria_id = ? ORDER BY marca";
        Marca marca = null;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, categoria_id);
            rs = stm.executeQuery();
            while (rs.next()) {

                marca = new Marca();
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                Categoria categoria = new CategoriaDao().retornaPorID(rs.getInt("categoria_id"));
                marca.setCategoria(categoria);
                Listagem.add(marca);
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
