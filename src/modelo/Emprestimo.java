/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Tony
 */
public class Emprestimo {

    private int idEmprestimo;
    private String situacao;
    private String observacao;
    private String dataEmprestimo;
    private String dataDevolucao;
    private Funcionario funcionario;
    private Usuario usuario;
    private Celular celular;
    private Chip chip;
    private String observacaoDevolucao;
    private String protocolo;

    public Emprestimo() {
    }

    public Emprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;

    }

    /**
     * @return the dataEmprestimo
     */
    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * @param dataEmprestimo the dataEmprestimo to set
     */
    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * @return the dataDevolucao
     */
    public String getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * @param dataDevolucao the dataDevolucao to set
     */
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    /**
     * @return the celular
     */
    public Celular getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(Celular celular) {
        this.celular = celular;
    }

    /**
     * @return the chip
     */
    public Chip getChip() {
        return chip;
    }

    /**
     * @param chip the chip to set
     */
    public void setChip(Chip chip) {
        this.chip = chip;
    }

    /**
     * @return the idEmprestimo
     */
    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    /**
     * @param idEmprestimo the idEmprestimo to set
     */
    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the observacaoDevolucao
     */
    public String getObservacaoDevolucao() {
        return observacaoDevolucao;
    }

    /**
     * @param observacaoDevolucao the observacaoDevolucao to set
     */
    public void setObservacaoDevolucao(String observacaoDevolucao) {
        this.observacaoDevolucao = observacaoDevolucao;
    }

    /**
     * @return the protocolo
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * @param protocolo the protocolo to set
     */
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

}
