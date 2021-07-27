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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRResultSetDataSource;

/**
 *
 * @author Tony
 */
public class ImpressaoDao {

    Connection con;
    PreparedStatement stm = null;
    PreparedStatement stmImagem = null;
    ResultSet rs;
    ResultSet rsImagem;

    // IMPRIMIR TERMO DE ENVIO //////////////////////////////////////////////////
    public void imprimirEmprestimoChip(int codigoTermo) {
        Map parametroImagem = new HashMap();
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
            stmImagem = con.prepareStatement("SELECT * FROM logo");
            rsImagem = stmImagem.executeQuery();

            parametroImagem.put("LOGO", rsImagem.getBytes("imagem1"));
            // FECHA O BANCO DE DADOS //////////////////////////////////////////
            con.close();

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoEmprestimoChip.jasper");
        //String caminho = "/termo/TermoEmprestimoChip.jasper";
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {
            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, parametroImagem, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setVisible(true);
            //JasperPrintManager.printPage(impressao, 0, true);

        } catch (JRException e) {
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
            stmImagem = con.prepareStatement("SELECT * FROM logo");
            rsImagem = stmImagem.executeQuery();

            paramatros.put("LOGO", rsImagem.getBytes("imagem1"));
// BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            con.close();

        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoEmprestimoCelular.jasper");
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {
            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, paramatros, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setVisible(true);
            //JasperPrintManager.printPage(impressao, 0, true);

        } catch (JRException e) {
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
            stmImagem = con.prepareStatement("SELECT * FROM logo");
            rsImagem = stmImagem.executeQuery();

            paramatros.put("LOGO", rsImagem.getBytes("imagem1"));

            // BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            
            con.close();
            
        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoEmprestimoCelularEChip.jasper");
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {
            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, paramatros, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setVisible(true);
            //JasperPrintManager.printPage(impressao, 0, true);

        } catch (JRException e) {
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
            stmImagem = con.prepareStatement("SELECT * FROM logo");
            rsImagem = stmImagem.executeQuery();

            paramatros.put("LOGO", rsImagem.getBytes("imagem1"));

            // FIM BUSCADO IMAGEM NO BANCO /////////////////////////////////////////
            
            con.close();
            
        } catch (SQLException ex) {

        }

        InputStream caminhoRelJasper = this.getClass().getResourceAsStream("/termo/TermoDevolucao.jasper");
        //String caminhoTeste = "../termo/TermoEmprestimoChip.jasper";
        JRResultSetDataSource result = new JRResultSetDataSource(rs);

        try {
            JasperPrint impressao = JasperFillManager.fillReport(caminhoRelJasper, paramatros, result);
            JasperViewer view = new JasperViewer(impressao, false);
            view.setVisible(true);
            //JasperPrintManager.printPage(impressao, 0, true);

        } catch (JRException e) {
        }
    }

}
