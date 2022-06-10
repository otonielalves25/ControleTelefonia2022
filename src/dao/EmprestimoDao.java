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
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Cargo;
import modelo.Categoria;
import modelo.Celular;
import modelo.Chip;
import modelo.Empresa;
import modelo.Emprestimo;
import modelo.Funcionario;
import modelo.Localidade;
import modelo.Marca;
import modelo.Usuario;
import utilidade.SqlGlobal;

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

        String sql = "INSERT INTO emprestimo (situacao, dataEmprestimo, dataDevolucao, funcionario_id, usuario_id, "
                + "observacao, celular_id, chip_id, protocolo) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, emprestimo.getSituacao());
            stm.setString(2, emprestimo.getDataEmprestimo());
            stm.setString(3, emprestimo.getDataDevolucao());
            stm.setInt(4, emprestimo.getFuncionario().getIdFuncionario());
            stm.setInt(5, emprestimo.getUsuario().getIdUsuario());
            stm.setString(6, emprestimo.getObservacao());
            if (emprestimo.getCelular().getIdCelular() > 0) {
                stm.setInt(7, emprestimo.getCelular().getIdCelular());
            } else {
                stm.setNull(7, Types.INTEGER);

            }

            if (emprestimo.getChip().getIdChip() > 0) {
                stm.setInt(8, emprestimo.getChip().getIdChip());
            } else {
                stm.setNull(8, Types.INTEGER);

            }
            stm.setString(9, emprestimo.getProtocolo());
            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar Emprestimo Dao. " + ex);
            return false;
        }

    }

    // ------------ALTERAR CADASTRA  --------------------------------------    
    public boolean update(Emprestimo emprestimo) {

        String sql = "UPDATE emprestimo SET situacao=?, dataEmprestimo=?, dataDevolucao=?, funcionario_id=?, usuario_id=?, "
                + "observacao=?, celular_id=?, chip_id=?, protocolo = ? where idEmprestimo = ?";
        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, emprestimo.getSituacao());
            stm.setString(2, emprestimo.getDataEmprestimo());
            stm.setString(3, emprestimo.getDataDevolucao());
            stm.setInt(4, emprestimo.getFuncionario().getIdFuncionario());
            stm.setInt(5, emprestimo.getUsuario().getIdUsuario());
            stm.setString(6, emprestimo.getObservacao());
            if (emprestimo.getCelular().getIdCelular() > 0) {
                stm.setInt(7, emprestimo.getCelular().getIdCelular());
            } else {
                stm.setNull(7, Types.INTEGER);

            }

            if (emprestimo.getChip().getIdChip() > 0) {
                stm.setInt(8, emprestimo.getChip().getIdChip());
            } else {
                stm.setNull(8, Types.INTEGER);

            }

            stm.setString(9, emprestimo.getProtocolo());
            stm.setInt(10, emprestimo.getIdEmprestimo());

            stm.execute();
            //fechando as conexões
            con.close();
            stm.close();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Alterar emprestimo Dao. " + ex);
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
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
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
        Empresa empresa;
        Cargo cargo;

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
                    empresa = new Empresa();
                    cargo = new Cargo();
                    //----------------------------------------------------------
                    emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                    emprestimo.setSituacao(rs.getString("situacao"));
                    emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                    emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                    emprestimo.setProtocolo(rs.getString("protocolo"));
                    emprestimo.setObservacao(rs.getString("observacao"));
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
                    //CARGO
                    cargo = new Cargo();
                    cargo.setIdCargo(rs.getInt("idCargo"));
                    cargo.setNomeCargo(rs.getString("nomeCargo"));

                    funcionario.setCargo(cargo);
                    emprestimo.setFuncionario(funcionario);
                    // USUARIO QUE CADASTROU 
                    usuario.setIdUsuario(rs.getInt("usuario.idUsuario"));
                    usuario.setNome(rs.getString("usuario.nome")); // AQUI PEGA O NUMERO DA COLUNA  
                    emprestimo.setUsuario(usuario);
                    // CATEGORIA
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setCategoria(rs.getString("categoria"));
                    // marca
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    marca.setCategoria(categoria);
                    // empresa
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                    // celualar
                    celular.setMarca(marca);
                    celular.setEmpresa(empresa);
                    celular.setIdCelular(rs.getInt("idCelular"));
                    celular.setSerie(rs.getString("serie"));
                    celular.setImei1(rs.getString("imei1"));
                    celular.setPatrimonio(rs.getString("patrimonio"));
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
        } else if (tipoPesquisa.equals("nomeLocalidade")) {
            modoPesquisa = "localidade.nomeLocalidade";
        } else if (tipoPesquisa.equals("patrimonio")) {
            modoPesquisa = "patrimonio";
        } else if (tipoPesquisa.equals("modelo")) {
            modoPesquisa = "marca.marca";
        } else {
            modoPesquisa = "chip.numeroLinha";
        }

        //System.out.println(soEmprestados);
        if (soEmprestados.equals("EMPRESTADO")) {
            sql = "SELECT * FROM emprestimo "
                    + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                    + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                    + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                    + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                    + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                    + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                    + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                    + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                    + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria WHERE " + modoPesquisa + " "
                    + "LIKE '%" + procura + "%' AND emprestimo.situacao = 'EMPRESTADO' ORDER BY " + modoPesquisa + ", funcionario.nome";
        } else {
            sql = "SELECT * FROM emprestimo "
                    + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                    + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                    + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                    + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                    + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                    + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                    + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                    + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                    + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria WHERE " + modoPesquisa + " "
                    + "LIKE '%" + procura + "%' ORDER BY " + modoPesquisa + ", funcionario.nome";
        }

        // PASSANDO OS VALORES NAS VARIÁVEL GLOBAL ////////////////////////////
        SqlGlobal.setSqlGlogalEmprestimos(sql);

        // PEQUISA POR NOME TUDO ///////////////////////////////////////////////////////////////// 
        Emprestimo emprestimo = null;
        Funcionario funcionario;
        Localidade localidade;
        Categoria categoria;
        Usuario usuario;
        Celular celular;
        Chip chip;
        Marca marca;
        Empresa empresa;
        Cargo cargo;

        try {
            con = conexao.ConexaoSqLite.getConnection();

            stm = con.prepareStatement(sql);
            //stm.setString(1, "%" + procura + "%");
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
                empresa = new Empresa();
                cargo = new Cargo();
                //----------------------------------------------------------
                emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                emprestimo.setSituacao(rs.getString("situacao"));
                emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                emprestimo.setProtocolo(rs.getString("protocolo"));
                emprestimo.setObservacao(rs.getString("observacao"));

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
                //CARGO
                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNomeCargo(rs.getString("nomeCargo"));

                funcionario.setCargo(cargo);

                emprestimo.setFuncionario(funcionario);
                // USUARIO QUE CADASTROU 
                usuario.setIdUsuario(rs.getInt("usuario.idUsuario"));
                usuario.setNome(rs.getString("usuario.nome"));  // PEGA NUMERO DA COLUNA                  
                emprestimo.setUsuario(usuario);
                // CATEGORIA
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setCategoria(rs.getString("categoria"));
                // marca
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                marca.setCategoria(categoria);
                // empresa
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                // celualar
                celular.setMarca(marca);
                celular.setEmpresa(empresa);
                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                celular.setSerie(rs.getString("serie"));
                celular.setPatrimonio(rs.getString("patrimonio"));
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

    //    //----------- BUSCA CELULA EMPRESTADO ------------------------------------------------------------
    public Emprestimo buscaPorCelularEmprestado(String imeiCelular) {

        String sql;

        sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria WHERE celular.imei1 "
                + "= ? AND emprestimo.situacao = 'EMPRESTADO'";

        // PEQUISA POR NOME TUDO ///////////////////////////////////////////////////////////////// 
        Emprestimo emprestimo = null;
        Funcionario funcionario;
        Localidade localidade;
        Categoria categoria;
        Usuario usuario;
        Celular celular;
        Chip chip;
        Marca marca;
        Empresa empresa;
        Cargo cargo;

        try {
            con = conexao.ConexaoSqLite.getConnection();

            stm = con.prepareStatement(sql);
            stm.setString(1, imeiCelular);
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
                empresa = new Empresa();
                //----------------------------------------------------------
                emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                emprestimo.setSituacao(rs.getString("situacao"));
                emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                emprestimo.setProtocolo(rs.getString("protocolo"));
                emprestimo.setObservacao(rs.getString("observacao"));

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
                //CARGO
                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNomeCargo(rs.getString("nomeCargo"));

                funcionario.setCargo(cargo);
                emprestimo.setFuncionario(funcionario);
                // USUARIO QUE CADASTROU 
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("usuario.nome"));  // PEGA NUMERO DA COLUNA                  
                emprestimo.setUsuario(usuario);
                // CATEGORIA
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setCategoria(rs.getString("categoria"));
                // marca
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                marca.setCategoria(categoria);
                // empresa
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                // celualar
                celular.setMarca(marca);
                celular.setEmpresa(empresa);
                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                celular.setSerie(rs.getString("serie"));
                celular.setPatrimonio(rs.getString("patrimonio"));
                emprestimo.setCelular(celular);

                // chip
                chip.setIdChip(rs.getInt("idChip"));
                chip.setStatus(rs.getString("codigoChip"));
                chip.setNumeroLinha(rs.getString("numeroLinha"));
                chip.setIsDado(rs.getBoolean("dados"));
                chip.setIsTelefonia(rs.getBoolean("telefonia"));
                emprestimo.setChip(chip);

            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos DAO. " + ex);
        }
        return emprestimo;
    }

    //    //----------- BUSCA CELULA EMPRESTADO ------------------------------------------------------------
    public Emprestimo buscaPorChipEmprestado(int chip_id) {

        String sql;

        sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria WHERE chip.idChip "
                + "= ? AND emprestimo.situacao = 'EMPRESTADO'";

        // PEQUISA POR NOME TUDO ///////////////////////////////////////////////////////////////// 
        Emprestimo emprestimo = null;
        Funcionario funcionario;
        Localidade localidade;
        Categoria categoria;
        Usuario usuario;
        Celular celular;
        Chip chip;
        Marca marca;
        Empresa empresa;
        Cargo cargo;

        try {
            con = conexao.ConexaoSqLite.getConnection();

            stm = con.prepareStatement(sql);
            stm.setInt(1, chip_id);
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
                empresa = new Empresa();
                //----------------------------------------------------------
                emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                emprestimo.setSituacao(rs.getString("situacao"));
                emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                emprestimo.setProtocolo(rs.getString("protocolo"));
                emprestimo.setObservacao(rs.getString("observacao"));

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
                //CARGO
                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNomeCargo(rs.getString("nomeCargo"));

                funcionario.setCargo(cargo);
                emprestimo.setFuncionario(funcionario);
                // USUARIO QUE CADASTROU 
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("usuario.nome"));  // PEGA NUMERO DA COLUNA                  
                emprestimo.setUsuario(usuario);
                // CATEGORIA
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setCategoria(rs.getString("categoria"));
                // marca
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                marca.setCategoria(categoria);
                // empresa
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                // celualar
                celular.setMarca(marca);
                celular.setEmpresa(empresa);
                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                celular.setSerie(rs.getString("serie"));
                celular.setPatrimonio(rs.getString("patrimonio"));
                emprestimo.setCelular(celular);

                // chip
                chip.setIdChip(rs.getInt("idChip"));
                chip.setStatus(rs.getString("codigoChip"));
                chip.setNumeroLinha(rs.getString("numeroLinha"));
                chip.setIsDado(rs.getBoolean("dados"));
                chip.setIsTelefonia(rs.getBoolean("telefonia"));
                emprestimo.setChip(chip);

            }
            //fechando as conexões
            con.close();
            stm.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar todos DAO. " + ex);
        }
        return emprestimo;
    }

    //    //----------- RETORNA TODOS USUARIOS ------------------------------------------------------------
    public ArrayList<Emprestimo> getListagemEmprestimoPorNome(String nomeFuncionario) {

        ArrayList<Emprestimo> Listagem = new ArrayList<>();
        String sql;

        sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria WHERE funcionario.nome "
                + "= ? AND emprestimo.situacao = 'EMPRESTADO'";

        // PEQUISA POR NOME TUDO ///////////////////////////////////////////////////////////////// 
        Emprestimo emprestimo = null;
        Funcionario funcionario;
        Cargo cargo;
        Localidade localidade;
        Categoria categoria;
        Usuario usuario;
        Celular celular;
        Chip chip;
        Marca marca;
        Empresa empresa;

        try {
            con = conexao.ConexaoSqLite.getConnection();

            stm = con.prepareStatement(sql);
            stm.setString(1, nomeFuncionario);
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
                empresa = new Empresa();

                //----------------------------------------------------------
                emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                emprestimo.setSituacao(rs.getString("situacao"));
                emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                emprestimo.setProtocolo(rs.getString("protocolo"));
                emprestimo.setObservacao(rs.getString("observacao"));

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
                //CARGO
                cargo = new Cargo();
                cargo.setIdCargo(rs.getInt("idCargo"));
                cargo.setNomeCargo(rs.getString("nomeCargo"));

                funcionario.setCargo(cargo);
                emprestimo.setFuncionario(funcionario);
                // USUARIO QUE CADASTROU 
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNome(rs.getString("usuario.nome"));  // PEGA NUMERO DA COLUNA                  
                emprestimo.setUsuario(usuario);
                // CATEGORIA
                categoria.setIdCategoria(rs.getInt("idCategoria"));
                categoria.setCategoria(rs.getString("categoria"));
                // marca
                marca.setIdMarca(rs.getInt("idMarca"));
                marca.setMarca(rs.getString("marca"));
                marca.setCategoria(categoria);
                // empresa
                empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                // celualar
                celular.setMarca(marca);
                celular.setEmpresa(empresa);
                celular.setIdCelular(rs.getInt("idCelular"));
                celular.setImei1(rs.getString("imei1"));
                celular.setSerie(rs.getString("serie"));
                celular.setPatrimonio(rs.getString("patrimonio"));
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

    // RETORNA OBSERVAÇÃO DE DEVOLUÇÃO/////////////////////////////////////////
    public Emprestimo retornaObservacaoDevolucao(String imei) {

        String sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria where celular.imei1 = ?";

        Emprestimo emprestimo = null;
        Funcionario funcionario;
        Localidade localidade;
        Categoria categoria;
        Usuario usuario;
        Celular celular;
        Chip chip;
        Marca marca;
        Empresa empresa;
        Cargo cargo;

        try {
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            stm.setString(1, imei);
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
                    empresa = new Empresa();
                    cargo = new Cargo();
                    //----------------------------------------------------------
                    emprestimo.setIdEmprestimo(rs.getInt("idEmprestimo"));
                    emprestimo.setSituacao(rs.getString("situacao"));
                    emprestimo.setDataEmprestimo(rs.getString("dataEmprestimo"));
                    emprestimo.setDataDevolucao(rs.getString("dataDevolucao"));
                    emprestimo.setProtocolo(rs.getString("protocolo"));
                    emprestimo.setObservacao(rs.getString("observacao"));
                    emprestimo.setObservacaoDevolucao(rs.getString("observacaoDevolucao"));

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
                    //CARGO
                    cargo = new Cargo();
                    cargo.setIdCargo(rs.getInt("idCargo"));
                    cargo.setNomeCargo(rs.getString("nomeCargo"));

                    funcionario.setCargo(cargo);
                    emprestimo.setFuncionario(funcionario);
                    // USUARIO QUE CADASTROU 
                    usuario.setIdUsuario(rs.getInt("usuario.idUsuario"));
                    usuario.setNome(rs.getString("usuario.nome")); // AQUI PEGA O NUMERO DA COLUNA  
                    emprestimo.setUsuario(usuario);
                    // CATEGORIA
                    categoria.setIdCategoria(rs.getInt("idCategoria"));
                    categoria.setCategoria(rs.getString("categoria"));
                    // marca
                    marca.setIdMarca(rs.getInt("idMarca"));
                    marca.setMarca(rs.getString("marca"));
                    marca.setCategoria(categoria);
                    // empresa
                    empresa.setIdEmpresa(rs.getInt("idEmpresa"));
                    empresa.setNomeEmpresa(rs.getString("nomeEmpresa"));
                    // celualar
                    celular.setMarca(marca);
                    celular.setEmpresa(empresa);
                    celular.setIdCelular(rs.getInt("idCelular"));
                    celular.setSerie(rs.getString("serie"));
                    celular.setImei1(rs.getString("imei1"));
                    celular.setPatrimonio(rs.getString("patrimonio"));
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

}
