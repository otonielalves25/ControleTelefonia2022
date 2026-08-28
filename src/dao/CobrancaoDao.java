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
import modelo.Cobranca;
import modelo.Emprestimo;
import modelo.Funcionario;
import modelo.Localidade;
import modelo.Usuario;

/**
 *
 * @author Tony
 */
public class CobrancaoDao {

    private Connection con;
    private PreparedStatement stm = null;
    private ResultSet rs;

    //INSERINDO NOVO CADASTRO **************************************************    
    public boolean insert(Cobranca cobranca) {

        String sql = "INSERT INTO cobranca (data, funcionario_id, protocolo, motivo, emprestimo_id, status, usuario_id, quantidade) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, cobranca.getData());
            stm.setInt(2, cobranca.getFuncionario().getIdFuncionario());
            stm.setString(3, cobranca.getProtocolo());
            stm.setString(4, cobranca.getMotivo());
            stm.setInt(5, cobranca.getEmprestimo().getIdEmprestimo());
            stm.setString(6, cobranca.getStatus());
            stm.setInt(7, cobranca.getUsuario().getIdUsuario());
            stm.setInt(8, 0);

            stm.execute();

            con.close();
            stm.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar Cobrança DAO. " + ex);
            return false;
        }
    }

// ------------ALTERAR CADASTRO --------------------------------------
    public boolean update(Cobranca cobranca) {

        String sql = "UPDATE cobranca SET data = ?, funcionario_id = ?, protocolo = ?, motivo = ?, emprestimo_id = ?, status = ?, dataEnvio = ?, usuario_id = ?, quantidade = ?, usuario_envio_id = ? "
                + "WHERE idCobranca = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, cobranca.getData());
            stm.setInt(2, cobranca.getFuncionario().getIdFuncionario());
            stm.setString(3, cobranca.getProtocolo());
            stm.setString(4, cobranca.getMotivo());
            stm.setInt(5, cobranca.getEmprestimo().getIdEmprestimo());
            stm.setString(6, cobranca.getStatus());
            stm.setString(7, cobranca.getDataEnvio());
            stm.setInt(8, cobranca.getUsuario().getIdUsuario());
            stm.setInt(9, cobranca.getQuantidade());
            stm.setInt(10, cobranca.getUsuarioEnvio().getIdUsuario());
            stm.setInt(11, cobranca.getIdCobranca());

            stm.execute();

            // fechando as conexões
            con.close();
            stm.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Cobrança DAO. " + ex);
            return false;
        }
    }

    //-----------DELETAR USUARIO -----------------------------------------------
    public boolean delete(int codigo) {

        String sql = "DELETE FROM cobranca WHERE idCobranca= ?";

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
            JOptionPane.showMessageDialog(null, "Cobrança não pode ser excluído,  tem funcionário cadastrado. ");
            return false;
        }

    }

    // Busca pelo id da pessoa.
    public Cobranca buscaByID(int idCobranca) {
        Cobranca cobranca = null;

        String sql = "SELECT * FROM cobranca c "
                + "JOIN funcionario f  ON c.funcionario_id = f.idFuncionario "
                + "JOIN emprestimo e ON c.emprestimo_id = e.idEmprestimo "
                + "JOIN usuario u ON c.usuario_id = u.idUsuario "
                + "LEFT JOIN usuario ue ON c.usuario_envio_id = ue.idUsuario "
                + "JOIN localidade l ON f.localidade_id = l.idLocalidade "
                + "WHERE c.idCobranca = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, idCobranca);

            rs = stm.executeQuery();

            if (rs.next()) {

                cobranca = new Cobranca();

                cobranca.setIdCobranca(rs.getInt("idCobranca"));
                cobranca.setData(rs.getString("data"));
                cobranca.setProtocolo(rs.getString("protocolo"));
                cobranca.setMotivo(rs.getString("motivo"));
                cobranca.setStatus(rs.getString("status"));
                cobranca.setDataEnvio(rs.getString("dataEnvio"));
                cobranca.setQuantidade(rs.getInt("quantidade"));

                Localidade localidade = new Localidade();
                localidade.setIdLocalidade(rs.getInt("l.idLocalidade"));
                localidade.setNomeLocalidade(rs.getString("l.nomeLocalidade"));

                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("f.idFuncionario"));
                funcionario.setNome(rs.getString("f.nome"));
                funcionario.setEmail(rs.getString("f.email"));
                funcionario.setLocalidade(localidade);
                cobranca.setFuncionario(funcionario);

                Emprestimo emprestimo = new EmprestimoDao().retornaPorID(rs.getInt("emprestimo_id"));
                cobranca.setEmprestimo(emprestimo);

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("u.idUsuario"));
                usuario.setNome(rs.getString("u.nome"));

                Usuario usuarioEnvio = new Usuario();
                usuarioEnvio.setIdUsuario(rs.getInt("ue.idUsuario"));
                usuarioEnvio.setNome(rs.getString("ue.nome"));

                cobranca.setUsuario(usuario);
                cobranca.setUsuarioEnvio(usuarioEnvio);

            }

            // fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todas as cobranças DAO. " + ex);
        }

        return cobranca;

    }

//----------- RETORNA TODOS ------------------------------------------------------------
    public ArrayList<Cobranca> getListagemCobrancasAlls(int ckBusca, String filtro, int usuario_id) {

        Cobranca cobranca;

        ArrayList<Cobranca> listagem = new ArrayList<>();
        String sql = "SELECT * FROM cobranca c "
                + "JOIN funcionario f  ON c.funcionario_id = f.idFuncionario "
                + "JOIN emprestimo e ON c.emprestimo_id = e.idEmprestimo "
                + "JOIN usuario u ON c.usuario_id = u.idUsuario "
                + "LEFT JOIN usuario ue ON c.usuario_envio_id = ue.idUsuario "
                + "JOIN localidade l ON f.localidade_id = l.idLocalidade ";

        switch (ckBusca) {
            case 1:
                sql = sql + " WHERE c.status = 'PENDENTE'";
                break;
            case 2:
                sql = sql + " WHERE c.status = 'FINALIZADO'";
                break;
            case 3:
                sql = sql + " WHERE c.status = 'FINALIZADO' OR c.status = 'PENDENTE'";
                break;
            default:

        }

        if (usuario_id > 0) {
            sql = sql + " AND c.usuario_id = " + usuario_id;
        }

        if (!filtro.equalsIgnoreCase("")) {
            sql = sql + " AND c.protocolo LIKE '%" + filtro + "%' OR f.nome LIKE '%" + filtro + "%'";
        }

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            while (rs.next()) {

                cobranca = new Cobranca();

                cobranca.setIdCobranca(rs.getInt("idCobranca"));
                cobranca.setData(rs.getString("data"));
                cobranca.setProtocolo(rs.getString("protocolo"));
                cobranca.setMotivo(rs.getString("motivo"));
                cobranca.setStatus(rs.getString("status"));
                cobranca.setDataEnvio(rs.getString("dataEnvio"));
                cobranca.setQuantidade(rs.getInt("quantidade"));

                Localidade localidade = new Localidade();
                localidade.setIdLocalidade(rs.getInt("l.idLocalidade"));
                localidade.setNomeLocalidade(rs.getString("l.nomeLocalidade"));

                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("f.idFuncionario"));
                funcionario.setNome(rs.getString("f.nome"));
                funcionario.setEmail(rs.getString("f.email"));
                funcionario.setLocalidade(localidade);
                cobranca.setFuncionario(funcionario);

                Emprestimo emprestimo = new EmprestimoDao().retornaPorID(rs.getInt("emprestimo_id"));
                cobranca.setEmprestimo(emprestimo);

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("u.idUsuario"));
                usuario.setNome(rs.getString("u.nome"));

                Usuario usuarioEnvio = new Usuario();
                usuarioEnvio.setIdUsuario(rs.getInt("ue.idUsuario"));
                usuarioEnvio.setNome(rs.getString("ue.nome"));

                cobranca.setUsuario(usuario);
                cobranca.setUsuarioEnvio(usuarioEnvio);

                listagem.add(cobranca);
            }

            // fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todas as cobranças DAO. " + ex);
        }

        return listagem;
    }

    // Verifica se já tem cadastros
    public boolean verificaJaCadastradoPorProtocolo(String protocolo) {

        String sql = "SELECT 1 FROM cobranca WHERE protocolo = ?";

        try (
                Connection con = conexao.ConexaoMySql.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, protocolo);

            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar cargo: " + e.getMessage());
            return false;
        }
    }

    // Verifica se já tem cadastros
    public boolean verificaJaCadastradoPorProtocoloPendentes(String protocolo) {

        String sql = "SELECT 1 FROM cobranca WHERE protocolo = ? AND status = 'PENDENTE'";

        try (
                Connection con = conexao.ConexaoMySql.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {

            stm.setString(1, protocolo);

            try (ResultSet rs = stm.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao consultar cargo: " + e.getMessage());
            return false;
        }
    }

    // ------------ALTERAR PARA FINALIZADO --------------------------------------
    public boolean marcarFinalizado(int idCobranca) {

        String sql = "UPDATE cobranca SET status = 'FINALIZADO' WHERE idCobranca = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);

            stm.setInt(1, idCobranca);

            stm.execute();

            // fechando as conexões
            con.close();
            stm.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Cobrança DAO. " + ex);
            return false;
        }
    }

    // ------------ALTERAR PARA FINALIZADO --------------------------------------
    public boolean marcarReaberto(int idCobranca) {

        String sql = "UPDATE cobranca SET status = 'PENDENTE' WHERE idCobranca = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);

            stm.setInt(1, idCobranca);

            stm.execute();

            // fechando as conexões
            con.close();
            stm.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Cobrança DAO. " + ex);
            return false;
        }
    }

    // ------------ALTERAR PARA FINALIZADO --------------------------------------
    public boolean marcarEnvioEmail(Cobranca cobranca) {

        String sql = "UPDATE cobranca SET dataEnvio = ?, usuario_envio_id = ?, quantidade = ? WHERE idCobranca = ?";

        try {
            con = conexao.ConexaoMySql.getConnection();
            stm = con.prepareStatement(sql);

            stm.setString(1, cobranca.getDataEnvio());
            stm.setInt(2, cobranca.getUsuarioEnvio().getIdUsuario());
            stm.setInt(3, cobranca.getQuantidade());
            stm.setInt(4, cobranca.getIdCobranca());

            stm.execute();

            // fechando as conexões
            con.close();
            stm.close();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar Cobrança DAO. " + ex);
            return false;
        }
    }

}
