/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author otoniel.aalves
 */
public class Acessorio {

    private int idAcessorio;
    private String nomeAcessorio;
    private Emprestimo emprestimo;

    public Acessorio() {
    }

    public Acessorio(int idAcessorio) {
        this.idAcessorio = idAcessorio;

    }

    /**
     * @return the idAcessorio
     */
    public int getIdAcessorio() {
        return idAcessorio;
    }

    /**
     * @param idAcessorio the idAcessorio to set
     */
    public void setIdAcessorio(int idAcessorio) {
        this.idAcessorio = idAcessorio;
    }

    /**
     * @return the nomeAcessorio
     */
    public String getNomeAcessorio() {
        return nomeAcessorio;
    }

    /**
     * @param nomeAcessorio the nomeAcessorio to set
     */
    public void setNomeAcessorio(String nomeAcessorio) {
        this.nomeAcessorio = nomeAcessorio;
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

}
