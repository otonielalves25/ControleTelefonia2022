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
import modelo.Chip;
import modelo.Emprestimo;
import modelo.Funcionario;
import modelo.Localidade;
import modelo.Marca;
import modelo.Usuario;

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
            stm.setString(2, emprestimo.getDataEmprestimo());
            stm.setString(3, emprestimo.getDataDevolucao());
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
            stm.setString(2, emprestimo.getDataEmprestimo());
            stm.setString(3, emprestimo.getDataDevolucao());
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
            stm.setString(2, emprestimo.getDataDevolucao());
            stm.setString(3, emprestimo.getObservacaoDevolucao());
            stm.setInt(4, emprestimo.getIdEmprestimo());

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

    // RETORNAR O ULTIMO ID ////////////////////////////////////////////////////
    public int retornaUltimoIDCadastrado() {
        int retorno = 0;

        String sql = "SELECT MAX(idEmprestimo) FROM emprestimo";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);

            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    retorno = rs.getInt(1);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Consultar acessorio DAO. " + ex);

        }
        return retorno;

    }

//    //----------- RETORNA APENAS UM USUARIO ---------------------------------------------------------
    public Emprestimo retornaPorID(int codigo) {

        String sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria where emprestimo.idEmprestimo = ?";

        Emprestimo emprestimo = null;
        Funcionario funcionario;
        Localidade localidade;
        Categoria categoria;
        Usuario usuario;
        Celular celular;
        Chip chip;
        Marca marca;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, codigo);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    emprestimo = new Emprestimo();
                    localidade = new Localidade();
                    funcionario = new Funcionario();
                    usuario = new Usuario();
                    categoria = new Categoria();
                    marca = new Marca();
                    celular = new Celular();
                    chip = new Chip();
                    //----------------------------------------------------------
                    emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                    emprestimo.setSituacao(rs.getString("situacao"));
                    emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                    emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                    // localidade 
                    localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                    localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                    localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));
                    // BUSCANDO FUNCIONARIO
                    funcionario.setLocalidade(localidade);
                    funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setRg(rs.getString("rg"));
                    funcionario.setCargo(rs.getString("cargo"));
                    emprestimo.setFuncionario(funcionario);
                    // USUARIO QUE CADASTROU 
                    usuario.setIdUsuario(rs.getInt(21));
                    usuario.setNome(rs.getString(22)); // AQUI PEGA O NUMERO DA COLUNA  
                    emprestimo.setUsuario(usuario);
                    // CATEGORIA
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setCategoria(rs.getString("categoria"));
                    // marca
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    marca.setCategoria(categoria);
                    // celualar
                    celular.setMarca(marca);
                    celular.setIdCelular(rs.getInt("idCelular"));
                    celular.setImei1(rs.getString("imei1"));
                    emprestimo.setCelular(celular);
                    // chip
                    chip.setIdChip(rs.getInt("idChip"));
                    chip.setCodigoChip(rs.getString("codigoChip"));
                    chip.setNumeroLinha(rs.getString("numeroLinha"));
                    chip.setIsDado(rs.getBoolean("dados"));
                    chip.setIsTelefonia(rs.getBoolean("telefonia"));
                    emprestimo.setChip(chip);

                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Consultar um DAO. " + ex);
        }

        return emprestimo;
    }

//    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Emprestimo> getListagemLike(String procura, String soEmprestados, String tipoPesquisa) {

        ArrayList<Emprestimo> Listagem = new ArrayList<>();
        String sql;
        
        // MODO DE PESQUISA
        String modoPesquisa;
        if (tipoPesquisa.equals("nome")) {
            modoPesquisa = "funcionario.nome";
        } else if (tipoPesquisa.equals("imei")) {
            modoPesquisa = "celular.imei1";
        } else {
            modoPesquisa = "chip.numeroLinha";
        }
        
        System.out.println(soEmprestados);
        if(soEmprestados.equals("EMPRESTADO")){
                 sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria WHERE " + modoPesquisa + " "
                + "LIKE ? AND emprestimo.situacao = 'EMPRESTADO'";
        } else{
                sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria WHERE " + modoPesquisa + " "
                + "LIKE ?";
        }
        // PEQUISA POR NOME TUDO ///////////////////////////////////////////////////////////////// 
   

        Emprestimo emprestimo = null;
        Funcionario funcionario;
        Localidade localidade;
        Categoria categoria;
        Usuario usuario;
        Celular celular;
        Chip chip;
        Marca marca;

        try {
            con = conexao.ConexaoSqLite.getConnection();
      
            stm = con.prepareStatement(sql);
            stm.setString(1, "%" + procura + "%");          
            rs = stm.executeQuery();
            while (rs.next()) {

                emprestimo = new Emprestimo();
                localidade = new Localidade();
                funcionario = new Funcionario();
                usuario = new Usuario();
                categoria = new Categoria();
                marca = new Marca();
                celular = new Celular();
                chip = new Chip();
                //----------------------------------------------------------
                emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                emprestimo.setSituacao(rs.getString("situacao"));
                emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                // localidade 
                localidade.setIdLocalidade(rs.getInt("idLocalidade"));
                localidade.setNomeLocalidade(rs.getString("nomeLocalidade"));
                localidade.setTipoLocalidade(rs.getString("tipoLocalidade"));
                // BUSCANDO FUNCIONARIO
                funcionario.setLocalidade(localidade);
                funcionario.setIdFuncionario(rs.getInt("idFuncionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setRg(rs.getString("rg"));
                funcionario.setCargo(rs.getString("cargo"));
                emprestimo.setFuncionario(funcionario);
                // USUARIO QUE CADASTROU 
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString(22));  // PEGA NUMERO DA COLUNA                  
                emprestimo.setUsuario(usuario);
                // CATEGORIA
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setCategoria(rs.getString("categoria"));
                // marca
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                marca.setCategoria(categoria);
                // celualar
                celular.setMarca(marca);
                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                emprestimo.setCelular(celular);
                // chip
                chip.setIdChip(rs.getInt("idChip"));
                chip.setStatus(rs.getString("codigoChip"));
                chip.setNumeroLinha(rs.getString("numeroLinha"));
                chip.setIsDado(rs.getBoolean("dados"));
                chip.setIsTelefonia(rs.getBoolean("telefonia"));
                emprestimo.setChip(chip);

                Listagem.add(emprestimo);
            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos DAO. " + ex);
        }
        return Listagem;
    }
    
    
    // RETORNAR O ULTIMO ID ////////////////////////////////////////////////////
    public int verificaEmprestimoFuncionario(int funcionario_id) {
        int retorno = 0;

        String sql = "SELECT * FROM emprestimo WHERE funcionario_id = ? AND situacao = 'EMPRESTADO'";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setInt(1, funcionario_id);
            rs = stm.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    retorno = rs.getInt(1);
                }
            }
            //fechando as conexões
            con.close();
            stm.close();
        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Consultar emprestimo do funcionario DAO. " + ex);

        }
        return retorno;

    }
    
}
