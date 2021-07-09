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
public class Celular {

    private int idCelular;
    private String imei1;
    private String imei2;
    private String status;
    private String observacao;
    private String serie;
    private Marca marca;
    private Empresa empresa;

    public Celular() {
    }

    public Celular(int idCelular) {
        this.idCelular = idCelular;

    }

    /**
     * @return the idCelular
     */
    public int getIdCelular() {
        return idCelular;
    }

    /**
     * @param idCelular the idCelular to set
     */
    public void setIdCelular(int idCelular) {
        this.idCelular = idCelular;
    }

    /**
     * @return the imei1
     */
    public String getImei1() {
        return imei1;
    }

    /**
     * @param imei1 the imei1 to set
     */
    public void setImei1(String imei1) {
        this.imei1 = imei1;
    }

    /**
     * @return the imei2
     */
    public String getImei2() {
        return imei2;
    }

    /**
     * @param imei2 the imei2 to set
     */
    public void setImei2(String imei2) {
        this.imei2 = imei2;
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
     * @return the marca
     */
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    /**
     * @return the serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * @param serie the serie to set
     */
    public void setSerie(String serie) {
        this.serie = serie;
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

  
}
