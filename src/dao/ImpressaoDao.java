/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utilidade.SqlGlobal;

/**
 *
 * @author Tony
 */
public class ImpressaoDao {

    Connection con;
    PreparedStatement stm = null;
    ResultSet rs;

    // IMAGENS DO RELATORIO ****************************************************
    InputStream logoDetran = this.getClass().getResourceAsStream("/imagem/logoDetran.png");
    InputStream logoDot = this.getClass().getResourceAsStream("/imagem/logoDot.png");
    InputStream logoCoogi = this.getClass().getResourceAsStream("/imagem/logoCoogi.png");
    // FIM IMAGENS DO RELETORIO ************************************************

    // IMPRIMIR TERMO DE ENVIO //////////////////////////////////////////////////
    public void imprimirEmprestimoChip(int codigoTermo) {
        Map parametro = new HashMap();

        String sql2 = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN empresa on chip.empresa_id = empresa.idEmpresa "
                + "where emprestimo.idEmprestimo = " + codigoTermo;

        try {

            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql2);
            rs = stm.executeQuery();
            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            //* coloca fotos nos relatorios *//

            parametro.put("logoDetran", logoDetran);
            parametro.put("logoDot", logoDot);
            parametro.put("logoCoogi", logoCoogi);
            // FECHA O BANCO DE DADOS //////////////////////////////////////////

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoEmprestimoChip.jasper");

        //String caminho = "/termo/TermoEmprestimoChip.jasper";
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, parametro, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);
            view.setLocationRelativeTo(null);

            view.setVisible(true);
            //parametro.clear();
            //JasperPrintManager.printPage(impressao, 0, true);    

            //JasperExportManager.exportReportToPdfFile(impressao,"C:\\temp\\teste.pdf");
        } catch (JRException e) {
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // IMPRIMIR TERMO DE CELULAR //////////////////////////////////////////////////
    public void imprimirEmprestimoCelular(int codigoTermo, HashMap paramatros) {

        String sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria where emprestimo.idEmprestimo = " + codigoTermo;
        try {

            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            paramatros.put("logoDetran", logoDetran);
            paramatros.put("logoDot", logoDot);
            paramatros.put("logoCoogi", logoCoogi);
            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoEmprestimoCelular.jasper");
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, paramatros, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);  // TAMANHO DA JANELA TELA 
            view.setLocationRelativeTo(null);

            view.setVisible(true);
            //paramatros.clear();
            //JasperPrintManager.printPage(impressao, 0, true);

        } catch (JRException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // IMPRIMIR TERMO DE CELULAR //////////////////////////////////////////////////
    public void imprimirEmprestimoCelularEChip(int codigoTermo, HashMap paramatros) {

        String sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria where emprestimo.idEmprestimo = " + codigoTermo;
        try {

            // System.out.println(paramatros);
            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            paramatros.put("logoDetran", logoDetran);
            paramatros.put("logoDot", logoDot);
            paramatros.put("logoCoogi", logoCoogi);

            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoEmprestimoCelularEChip.jasper");
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, paramatros, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);
            view.setLocationRelativeTo(null);

            view.setVisible(true);

        } catch (JRException e) {
        }
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // IMPRIMIR TERMO DE CELULAR //////////////////////////////////////////////////
    public void imprimirTermoDevolucao(int codigoTermo, HashMap paramatros) {

        String sql = "SELECT * FROM emprestimo "
                + "JOIN funcionario on emprestimo.funcionario_id = funcionario.idFuncionario "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "JOIN usuario on emprestimo.usuario_id = usuario.idUsuario "
                + "LEFT JOIN celular on emprestimo.celular_id = celular.idCelular "
                + "LEFT JOIN empresa on celular.empresa_id = empresa.idEmpresa "
                + "LEFT JOIN chip on emprestimo.chip_id = chip.idChip "
                + "LEFT JOIN marca on celular.marca_id = marca.idMarca "
                + "LEFT JOIN categoria on marca.categoria_id = categoria.idCategoria where emprestimo.idEmprestimo = " + codigoTermo;
        try {

            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            paramatros.put("logoDetran", logoDetran);
            paramatros.put("logoDot", logoDot);
            paramatros.put("logoCoogi", logoCoogi);

            // FIM BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            //con.close();
        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoDevolucao.jasper");

        //String caminhoTeste = "../termo/TermoEmprestimoChip.jasper";
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, paramatros, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);
            view.setLocationRelativeTo(null);

            view.setVisible(true);
            //JasperPrintManager.printPage(impressao, 0, true);
            // paramatros.clear();

            //con.close();
        } catch (JRException e) {
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // IMPRIMIR RELATORIO DE CELULARES //////////////////////////////////////////////////
    public void imprimirCelulares() {

        // VARIAVEL GLOBAL BUSCA O FILTRO
        String sql = SqlGlobal.getSqlGlogalCelelares();

        try {

            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/RelatorioCelulares.jasper");

        //String caminhoTeste = "../termo/TermoEmprestimoChip.jasper";
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, null, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);
            view.setLocationRelativeTo(null);
            view.setVisible(true);

        } catch (JRException e) {
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // IMPRIMIR RELATORIO DE CELULARES //////////////////////////////////////////////////
    public void imprimirChipes() {

        // VARIAVEL GLOBAL BUSCA O FILTRO
        String sql = SqlGlobal.getSqlGlogalChipes();

        try {

            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/RelatorioChipes.jasper");

        //String caminhoTeste = "../termo/TermoEmprestimoChip.jasper";
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, null, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);
            view.setLocationRelativeTo(null);

            view.setVisible(true);
            //JasperPrintManager.printPage(impressao, 0, true);
            // paramatros.clear();

            //con.close();
        } catch (JRException e) {
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // IMPRIMIR TODOS OS EMPRESTIMOS ///////////////////////////////////////////
    public void imprimirEmprestados() {

        String sql = SqlGlobal.getSqlGlogalEmprestimos();

        try {

            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/RelatorioEmprestimos.jasper");

        //String caminhoTeste = "../termo/TermoEmprestimoChip.jasper";
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, new HashMap(), result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);
            view.setLocationRelativeTo(null);

            view.setVisible(true);

        } catch (JRException e) {
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
        // IMPRIMIR TERMO DE ENVIO //////////////////////////////////////////////////
    public void imprimirEmprestimoGenerico(String nomeFuncionario) {
        Map parametro = new HashMap();

        String sql = "SELECT * FROM funcionario "           
                + "JOIN cargoFuncionario ON funcionario.cargo_id = cargoFuncionario.idCargo "
                + "JOIN localidade on funcionario.localidade_id = localidade.idLocalidade "
                + "where funcionario.nome = '" + nomeFuncionario + "'";

        try {

            con = conexao.ConexaoSqLite.getConnection();
            stm = con.prepareStatement(sql);
            rs = stm.executeQuery();
            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            //* coloca fotos nos relatorios *//

            parametro.put("logoDetran", logoDetran);
            parametro.put("logoDot", logoDot);
            parametro.put("logoCoogi", logoCoogi);
            // FECHA O BANCO DE DADOS //////////////////////////////////////////

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoEmprestimoGenerico.jasper");

        JRResultSetDataSource resultado = new JRResultSetDataSource(rs);

        try {

            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, parametro, resultado);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setSize(1200, 1000);
            view.setLocationRelativeTo(null);
       
            view.toFront();     
            view.setVisible(true);

        } catch (JRException e) {
           e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImpressaoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
