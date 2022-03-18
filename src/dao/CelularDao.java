/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import utilidade.SqlGlobal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.Celular;
import modelo.Empresa;
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

        String sql = "INSERT INTO celular (serie, imei1, imei2, marca_id, status, observacao, empresa_id, estadoBem, caixa, carregador, manual, adaptador, foneOuvido, capinha, patrimonio) "
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, celular.getSerie());
            stm.setString(2, celular.getImei1());
            stm.setString(3, celular.getImei2());
            stm.setInt(4, celular.getMarca().getIdMarca());
            stm.setString(5, celular.getStatus());
            stm.setString(6, celular.getObservacao());
            stm.setInt(7, celular.getEmpresa().getIdEmpresa());
            stm.setString(8, celular.getEstadoBem());
            stm.setBoolean(9, celular.isCaixa());
            stm.setBoolean(10, celular.isCarregador());
            stm.setBoolean(11, celular.isManual());
            stm.setBoolean(12, celular.isAdaptador());
            stm.setBoolean(13, celular.isFoneOuvido());
            stm.setBoolean(14, celular.isCapinha());
            stm.setString(15, celular.getPatrimonio());

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

        String sql = "UPDATE celular set serie=?, imei1=?,imei2=?, marca_id=?, status=?, observacao=?, empresa_id=?, estadoBem=?, "
                + "caixa=?, carregador=?, manual=?, adaptador=?, foneOuvido=?, capinha = ?, patrimonio=? where idCelular = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, celular.getSerie());
            stm.setString(2, celular.getImei1());
            stm.setString(3, celular.getImei2());
            stm.setInt(4, celular.getMarca().getIdMarca());
            stm.setString(5, celular.getStatus());
            stm.setString(6, celular.getObservacao());
            stm.setInt(7, celular.getEmpresa().getIdEmpresa());
            stm.setString(8, celular.getEstadoBem());
            stm.setBoolean(9, celular.isCaixa());
            stm.setBoolean(10, celular.isCarregador());
            stm.setBoolean(11, celular.isManual());
            stm.setBoolean(12, celular.isAdaptador());
            stm.setBoolean(13, celular.isFoneOuvido());
               stm.setBoolean(14, celular.isCapinha());
                stm.setString(15, celular.getPatrimonio());
            stm.setInt(16, celular.getIdCelular());
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
    public boolean updateStatus(Celular celular) {

        String sql = "UPDATE celular set status=?, estadoBem=?, observacao=? where idCelular = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, celular.getStatus());
            stm.setString(2, celular.getEstadoBem());
            stm.setString(3, celular.getObservacao());
            stm.setInt(4, celular.getIdCelular());
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
    public boolean alterarAcessoriosEmprestadoNoCadastro(Celular celular) {

        String sql = "UPDATE celular set caixa=?, carregador=?, manual=?, adaptador=?, foneOuvido=?, capinha = ? where idCelular = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
             stm.setBoolean(1, celular.isCaixa());
            stm.setBoolean(2, celular.isCarregador());
            stm.setBoolean(3, celular.isManual());
            stm.setBoolean(4, celular.isAdaptador());
            stm.setBoolean(5, celular.isFoneOuvido());
            stm.setBoolean(6, celular.isCapinha());
            stm.setInt(7, celular.getIdCelular());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Celular não pode ser excluído,  tem funcionário cadastrado. ");
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
                + "JOIN empresa ON  celular.empresa_id = empresa.idEmpresa "
                + "WHERE idcelular = ?";
        Celular celular = null;
        Marca marca;
        Categoria categoria;
        Empresa empresa;
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
                    empresa = new Empresa();

                    celular.setIdCelular(rs.getInt("idCelular"));
                    celular.setImei1(rs.getString("imei1"));
                    celular.setImei2(rs.getString("imei2"));
                    celular.setSerie(rs.getString("serie"));
                    celular.setStatus(rs.getString("status"));
                    celular.setObservacao(rs.getString("observacao"));
                    celular.setEstadoBem(rs.getString("estadoBem"));
                    celular.setCaixa(rs.getBoolean("caixa"));
                    celular.setCarregador(rs.getBoolean("carregador"));
                    celular.setAdaptador(rs.getBoolean("adaptador"));
                    celular.setManual(rs.getBoolean("manual"));
                    celular.setFoneOuvido(rs.getBoolean("foneOuvido"));
                    celular.setCapinha(rs.getBoolean("capinha"));
                    celular.setPatrimonio(rs.getString("patrimonio"));

                    //empresa
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                    celular.setEmpresa(empresa);
                    // marca
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    // categoria
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
    public ArrayList<Celular> getListagemLike(String tipo, String busca) {

        ArrayList<Celular> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM celular "
                + "JOIN marca ON  celular.marca_id = marca.idMarca "
                + "JOIN categoria ON  marca.categoria_id = categoria.idCategoria "
                + "JOIN empresa ON  celular.empresa_id = empresa.idEmpresa "
                + "WHERE " + tipo + " LIKE '%" + busca + "%'";
        Celular celular = null;
        Marca marca = null;
        Categoria categoria = null;
        Empresa empresa;

        SqlGlobal.setSqlGlogalCelulares(sql);

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            //stm.setString(1, "%" + busca + "%");

            rs = stm.executeQuery();
            while (rs.next()) {

                celular = new Celular();
                categoria = new Categoria();
                marca = new Marca();
                empresa = new Empresa();
                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                celular.setImei2(rs.getString("imei2"));
                celular.setSerie(rs.getString("serie"));
                celular.setStatus(rs.getString("status"));
                celular.setObservacao(rs.getString("observacao"));
                celular.setEstadoBem(rs.getString("estadoBem"));
                celular.setCaixa(rs.getBoolean("caixa"));
                celular.setCarregador(rs.getBoolean("carregador"));
                celular.setAdaptador(rs.getBoolean("adaptador"));
                celular.setManual(rs.getBoolean("manual"));
                celular.setFoneOuvido(rs.getBoolean("foneOuvido"));
                celular.setCapinha(rs.getBoolean("capinha"));
                celular.setPatrimonio(rs.getString("patrimonio"));

                //empresa
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                celular.setEmpresa(empresa);
                // marca
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                // categoria
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
    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------

    public ArrayList<Celular> getListagemLikeAtivos(String busca, String tipoPesquisa) {

        ArrayList<Celular> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM celular "
                + "JOIN marca ON  celular.marca_id = marca.idMarca "
                + "JOIN categoria ON  marca.categoria_id = categoria.idCategoria "
                + "JOIN empresa ON  celular.empresa_id = empresa.idEmpresa "
                + "WHERE ("+ tipoPesquisa +" LIKE ?) AND celular.status = 'Disponível'";
        Celular celular = null;
        Marca marca = null;
        Categoria categoria = null;
        Empresa empresa;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
 
            rs = stm.executeQuery();
            while (rs.next()) {

                celular = new Celular();
                categoria = new Categoria();
                marca = new Marca();
                empresa = new Empresa();

                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                celular.setImei2(rs.getString("imei2"));
                celular.setSerie(rs.getString("serie"));
                celular.setStatus(rs.getString("status"));
                celular.setObservacao(rs.getString("observacao"));
                celular.setEstadoBem(rs.getString("estadoBem"));
                celular.setCaixa(rs.getBoolean("caixa"));
                celular.setCarregador(rs.getBoolean("carregador"));
                celular.setAdaptador(rs.getBoolean("adaptador"));
                celular.setManual(rs.getBoolean("manual"));
                celular.setFoneOuvido(rs.getBoolean("foneOuvido"));
                celular.setCapinha(rs.getBoolean("capinha"));
                celular.setPatrimonio(rs.getString("patrimonio"));
                //empresa
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                celular.setEmpresa(empresa);
                // marca
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                // categoria
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
    public Celular verificarSeCadastrado(String serie, String imei, String patrimonio) {

        String sql = "SELECT * FROM celular "
                + "JOIN marca ON  celular.marca_id = marca.idMarca "
                + "JOIN categoria ON  marca.categoria_id = categoria.idCategoria "
                + "JOIN empresa ON  celular.empresa_id = empresa.idEmpresa "
                + "WHERE imei1 = ? OR serie = ? OR patrimonio = ?";
        Celular celular = null;
        Marca marca = null;
        Categoria categoria = null;
        Empresa empresa;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, imei);
            stm.setString(2, serie);
            stm.setString(3, patrimonio);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    celular = new Celular();
                    categoria = new Categoria();
                    marca = new Marca();
                    empresa = new Empresa();

                    celular.setIdCelular(rs.getInt("idCelular"));
                    celular.setImei1(rs.getString("imei1"));
                    celular.setImei2(rs.getString("imei2"));
                    celular.setSerie(rs.getString("serie"));
                    celular.setStatus(rs.getString("status"));
                    celular.setObservacao(rs.getString("observacao"));
                    celular.setEstadoBem(rs.getString("estadoBem"));
                    celular.setCaixa(rs.getBoolean("caixa"));
                    celular.setCarregador(rs.getBoolean("carregador"));
                    celular.setAdaptador(rs.getBoolean("adaptador"));
                    celular.setManual(rs.getBoolean("manual"));
                    celular.setFoneOuvido(rs.getBoolean("foneOuvido"));
                    celular.setCapinha(rs.getBoolean("capinha"));
                    celular.setPatrimonio(rs.getString("patrimonio"));
                    //empresa
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                    celular.setEmpresa(empresa);
                    // marca
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    // categoria
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
