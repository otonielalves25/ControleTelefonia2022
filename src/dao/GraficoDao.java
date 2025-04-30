/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.GraficoBean;

/**
 *
 * @author otoniel.aalves
 */
public class GraficoDao {
    
    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    // retorna os dados do grafico
    public List<GraficoBean> quantidadeCelularesPorMarca() {
        List<GraficoBean> listagem = new ArrayList();
        String sql = "SELECT marca.idMarca as id,  marca.marca as marca, COUNT(*) AS quantidade "
                + "FROM marca "
                + "INNER JOIN celular "
                + "ON celular.marca_id = marca.idMarca "
                + "GROUP BY marca.idMarca";
        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                GraficoBean gra = new GraficoBean();
                gra.setId(rs.getInt("id"));
                gra.setMarca(rs.getString("marca"));
                gra.setQuantidade(rs.getInt("quantidade"));
                listagem.add(gra);
                
            }
        } catch (SQLException e) {
        }
        
        return listagem;
    }
    
        // retorna os dados do grafico
    public List<GraficoBean> quantidadeCelularesPorMarcaEmprestado() {
        List<GraficoBean> listagem = new ArrayList();
        String sql = "SELECT marca.idMarca as id,  marca.marca as marca, COUNT(*) AS quantidade "
                + "FROM marca "
                + "INNER JOIN celular "
                + "ON celular.marca_id = marca.idMarca "
                + "WHERE celular.status = 'EMPRESTADO' "
                + "GROUP BY marca.idMarca";
        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                GraficoBean gra = new GraficoBean();
                gra.setId(rs.getInt("id"));
                gra.setMarca(rs.getString("marca"));
                gra.setQuantidade(rs.getInt("quantidade"));
                listagem.add(gra);
                
            }
        } catch (SQLException e) {
        }
        
        return listagem;
    }
    


    
}
