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
import modelo.Celular;
import modelo.Marca;

/**
 *
 * @author Tony
 */
public class CelularDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Celular celular) {

        String sql = "INSERT INTO celular (serie, imei1, imei2, marca_id, status, observacao) values(?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, celular.getSerie());
            stm.setString(2, celular.getImei1());
            stm.setString(3, celular.getImei2());
            stm.setInt(4, celular.getMarca().getIdMarca());
            stm.setString(5, celular.getStatus());
            stm.setString(6, celular.getObservacao());

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
    public boolean update(Celular celular) {

        String sql = "UPDATE celular set serie=?, imei1=?,imei2=?, marca_id=?, status=?, observacao=? where idCelular = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, celular.getSerie());
            stm.setString(2, celular.getImei1());
            stm.setString(3, celular.getImei2());
            stm.setInt(4, celular.getMarca().getIdMarca());
            stm.setString(5, celular.getStatus());
            stm.setString(6, celular.getObservacao());
            stm.setInt(7, celular.getIdCelular());
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
    public boolean delete(int codigo) {
        String sql = "DELETE from celular where idcelular= ?";

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
            JOptionPane.showMessageDialog(null, "Erro ao Excluir Dao. " + e);
            return false;
        }

    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Celular getPorID(int codigo) {

        String sql = "SELECT * FROM celular "
                + "JOIN marca ON  celular.marca_id = marca.idMarca "
                + "JOIN categoria ON  marca.categoria_id = categoria.idCategoria "
                + "WHERE idcelular = ?";
        Celular celular = null;
        Marca marca = null;
        Categoria categoria = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    celular = new Celular();
                    categoria = new Categoria();
                    marca = new Marca();

                    celular.setIdCelular(rs.getInt("idCelular"));
                    celular.setImei1(rs.getString("imei1"));
                    celular.setImei2(rs.getString("imei2"));
                    celular.setSerie(rs.getString("serie"));
                    celular.setStatus(rs.getString("status"));
                    celular.setObservacao(rs.getString("observacao"));
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setCategoria(rs.getString("categoria"));
                    marca.setCategoria(categoria);
                    celular.setMarca(marca);

                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar DAO. " + ex);
        }

        return celular;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Celular> getListagemLike(String busca) {

        ArrayList<Celular> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM celular "
                + "JOIN marca ON  celular.marca_id = marca.idMarca "
                + "JOIN categoria ON  marca.categoria_id = categoria.idCategoria "
                + "WHERE serie LIKE ? OR imei1 LIKE ? OR imei2 LIKE ?";
        Celular celular = null;
        Marca marca = null;
        Categoria categoria = null;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
            stm.setString(2, "%" + busca + "%");
            stm.setString(3, "%" + busca + "%");
            rs = stm.executeQuery();
            while (rs.next()) {

                celular = new Celular();
                categoria = new Categoria();
                marca = new Marca();

                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                celular.setImei2(rs.getString("imei2"));
                celular.setSerie(rs.getString("serie"));
                celular.setStatus(rs.getString("status"));
                celular.setObservacao(rs.getString("observacao"));
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setCategoria(rs.getString("categoria"));
                marca.setCategoria(categoria);
                celular.setMarca(marca);
                Listagem.add(celular);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos DAO. " + ex);
        }
        return Listagem;
    }

        //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Celular verificarSeCadastrado(String serie, String imei) {

        String sql = "SELECT * FROM celular "
                + "JOIN marca ON  celular.marca_id = marca.idMarca "
                + "JOIN categoria ON  marca.categoria_id = categoria.idCategoria "
                + "WHERE imei1 = ? OR serie = ?";
        Celular celular = null;
        Marca marca = null;
        Categoria categoria = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, imei);
            stm.setString(2, serie);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    celular = new Celular();
                    categoria = new Categoria();
                    marca = new Marca();

                    celular.setIdCelular(rs.getInt("idCelular"));
                    celular.setImei1(rs.getString("imei1"));
                    celular.setImei2(rs.getString("imei2"));
                    celular.setSerie(rs.getString("serie"));
                    celular.setStatus(rs.getString("status"));
                    celular.setObservacao(rs.getString("observacao"));
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setCategoria(rs.getString("categoria"));
                    marca.setCategoria(categoria);
                    celular.setMarca(marca);

                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar DAO. " + ex);
        }

        return celular;
    }
}
