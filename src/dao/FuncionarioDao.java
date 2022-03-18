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
import modelo.Funcionario;
import modelo.Localidade;

/**
 *
 * @author Tony
 */
public class FuncionarioDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Funcionario funcionario) {

        String sql = "INSERT INTO funcionario (nome,cpf,rg,status,localidade_id, cargo_id) VALUES (?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, funcionario.getNome());
            stm.setString(2, funcionario.getCpf());
            stm.setString(3, funcionario.getRg());
            stm.setString(4, funcionario.getStatus());
            stm.setInt(5, funcionario.getLocalidade().getIdLocalidade());
            stm.setInt(6, funcionario.getCargo().getIdCargo());
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
    public boolean update(Funcionario funcionario) {

        String sql = "UPDATE funcionario set nome=?,cpf=?,rg=?,status=?,localidade_id=?, cargo_id=? where idFuncionario = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, funcionario.getNome());
            stm.setString(2, funcionario.getCpf());
            stm.setString(3, funcionario.getRg());
            stm.setString(4, funcionario.getStatus());
            stm.setInt(5, funcionario.getLocalidade().getIdLocalidade());
            stm.setInt(6, funcionario.getCargo().getIdCargo());
            stm.setInt(7, funcionario.getIdFuncionario());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar Dao. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int codigo) {
        String sql = "UPDATE funcionario SET status = 'EXCLUIDO' where idFuncionario= ?";

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

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean deleteDefinitivo(int codigo) {
        String sql = "DELETE  FROM funcionario WHERE idFuncionario = ?";

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

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean marcarExonerado(int codigo) {
        String sql = "UPDATE funcionario SET status = 'EXONERADO' where idFuncionario= ?";

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
    public Funcionario getPorID(int codigo) {

        String sql = "SELECT * FROM funcionario "
                + "JOIN localidade ON funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "WHERE idfuncionario = ?";
        Funcionario funcionario = null;
        Localidade localidade = null;
        Cargo cargo = null;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setRg(rs.getString("rg"));
                    funcionario.setStatus(rs.getString("status"));
                    //LOCALIDADE ////////////////////////////
                    localidade = new Localidade();
                    localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                    localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                    localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));

                    //CARGO
                    cargo = new Cargo();
                    cargo.setIdCargo(rs.getInt("idCargo"));
                    cargo.setNomeCargo(rs.getString("nomeCargo"));

                    funcionario.setLocalidade(localidade);
                    funcionario.setCargo(cargo);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar um DAO. " + ex);
        }

        return funcionario;
    }

    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Funcionario retornaPorNome(String procura) {

        String sql = "SELECT * FROM funcionario JOIN localidade ON funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "WHERE nome = ?";
        Funcionario funcionario = null;
        Localidade localidade = null;
        Cargo cargo = null;
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, procura);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setRg(rs.getString("rg"));
                    funcionario.setStatus(rs.getString("status"));
                    //LOCALIDADE ////////////////////////////
                    localidade = new Localidade();
                    localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                    localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                    localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));

                    //CARGO
                    cargo = new Cargo();
                    cargo.setIdCargo(rs.getInt("idCargo"));
                    cargo.setNomeCargo(rs.getString("nomeCargo"));

                    funcionario.setLocalidade(localidade);
                    funcionario.setCargo(cargo);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar Categoria DAO. " + ex);
        }

        return funcionario;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Funcionario> getListagemLike(String tipoBusca, String busca) {

        ArrayList<Funcionario> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM funcionario JOIN localidade ON funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "WHERE " + tipoBusca + " LIKE ? ORDER BY nome";
        Funcionario funcionario = null;
        Localidade localidade = null;
        Cargo cargo = null;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
            rs = stm.executeQuery();
            while (rs.next()) {

                funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setStatus(rs.getString("status"));
                //LOCALIDADE ////////////////////////////
                localidade = new Localidade();
                localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));

                //CARGO
                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNomeCargo(rs.getString("nomeCargo"));

                funcionario.setLocalidade(localidade);
                funcionario.setCargo(cargo);

                Listagem.add(funcionario);
            }
            //fechando as conexõesfuncionariolocalidade
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos DAO. " + ex);
        }
        return Listagem;
    }

    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Funcionario> getListagemLikeAtivos(String busca) {

        ArrayList<Funcionario> Listagem = new ArrayList<>();
        String sql = "SELECT * FROM funcionario JOIN localidade ON funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "WHERE nome LIKE ? AND status = 'Ativo' ORDER BY nome";
        Funcionario funcionario = null;
        Localidade localidade = null;
        Cargo cargo = null;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + busca + "%");
            rs = stm.executeQuery();
            while (rs.next()) {

                funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setStatus(rs.getString("status"));

                //LOCALIDADE ////////////////////////////
                localidade = new Localidade();
                localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));

                //CARGO
                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNomeCargo(rs.getString("nomeCargo"));

                funcionario.setLocalidade(localidade);
                funcionario.setCargo(cargo);

                Listagem.add(funcionario);
            }
            //fechando as conexõesfuncionariolocalidade
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos DAO. " + ex);
        }
        return Listagem;
    }

}
