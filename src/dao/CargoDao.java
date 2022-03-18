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


/**
 *
 * @author Tony
 */
public class CargoDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Cargo cargo) {

        String sql = "INSERT INTO cargoFuncionario (nomeCargo) VALUES (?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, cargo.getNomeCargo());
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
    public boolean update(Cargo cargo) {

        String sql = "UPDATE cargoFuncionario set nomeCargo = ? where idCargo = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, cargo.getNomeCargo());
            stm.setInt(2, cargo.getIdCargo());
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
        String sql = "DELETE from cargoFuncionario where idCargo= ?";

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
    public ArrayList<Cargo> getListagemCargos() {

        ArrayList<Cargo> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM cargoFuncionario ORDER BY nomeCargo";
        Cargo cargo;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);           
            rs = stm.executeQuery();
            while (rs.next()) {

                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNomeCargo(rs.getString("nomeCargo"));
                Listagem.add(cargo);
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
