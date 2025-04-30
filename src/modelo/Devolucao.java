/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author otoniel.aalves
 */
public class Devolucao {
    
    private int id;
    private String situacao;  // DEVOLVIDO - AGUARDANDO
    private Emprestimo emprestimo;
    private Chip chip;   
    private Celular celular;
    private String formaEnvio;
    private String dataEnvio;
    private String dataDevolucao;
    private Usuario usuario;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the emprestimo
     */
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    /**
     * @param emprestimo the emprestimo to set
     */
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
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
     * @return the formaEnvio
     */
    public String getFormaEnvio() {
        return formaEnvio;
    }

    /**
     * @param formaEnvio the formaEnvio to set
     */
    public void setFormaEnvio(String formaEnvio) {
        this.formaEnvio = formaEnvio;
    }

    /**
     * @return the dataEnvio
     */
    public String getDataEnvio() {
        return dataEnvio;
    }

    /**
     * @param dataEnvio the dataEnvio to set
     */
    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
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

 
    
}
