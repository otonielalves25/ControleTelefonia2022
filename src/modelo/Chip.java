
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
public class Chip {

    private int idChip;
    private String numeroLinha;
    private String status;
    private String observacao;
    private String codigoChip;
    private boolean isTelefonia;
    private boolean isDado;
    private Empresa empresa;
    private String estadoBem;

    public Chip() {
    }

    public Chip(int idChip) {
        this.idChip = idChip;

    }

    /**
     * @return the idChip
     */
    public int getIdChip() {
        return idChip;
    }

    /**
     * @param idChip the idChip to set
     */
    public void setIdChip(int idChip) {
        this.idChip = idChip;
    }

    /**
     * @return the numeroLinha
     */
    public String getNumeroLinha() {
        return numeroLinha;
    }

    /**
     * @param numeroLinha the numeroLinha to set
     */
    public void setNumeroLinha(String numeroLinha) {
        this.numeroLinha = numeroLinha;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return the isTelefonia
     */
    public boolean isIsTelefonia() {
        return isTelefonia;
    }

    /**
     * @param isTelefonia the isTelefonia to set
     */
    public void setIsTelefonia(boolean isTelefonia) {
        this.isTelefonia = isTelefonia;
    }

    /**
     * @return the isDado
     */
    public boolean isIsDado() {
        return isDado;
    }

    /**
     * @param isDado the isDado to set
     */
    public void setIsDado(boolean isDado) {
        this.isDado = isDado;
    }

    /**
     * @return the codigoChip
     */
    public String getCodigoChip() {
        return codigoChip;
    }

    /**
     * @param codigoChip the codigoChip to set
     */
    public void setCodigoChip(String codigoChip) {
        this.codigoChip = codigoChip;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getEstadoBem() {
        return estadoBem;
    }

    public void setEstadoBem(String estadoBem) {
        this.estadoBem = estadoBem;
    }

}
