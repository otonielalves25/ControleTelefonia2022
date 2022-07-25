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
public class MotivoEmprestimo {

    private int idMotivoEmprestimo;
    private String motivoEmprestimo;

    public MotivoEmprestimo() {
    }

    public int getIdMotivoEmprestimo() {
        return idMotivoEmprestimo;
    }

    public void setIdMotivoEmprestimo(int idMotivoEmprestimo) {
        this.idMotivoEmprestimo = idMotivoEmprestimo;
    }

    public String getMotivoEmprestimo() {
        return motivoEmprestimo;
    }

    public void setMotivoEmprestimo(String motivoEmprestimo) {
        this.motivoEmprestimo = motivoEmprestimo;
    }

    @Override
    public String toString() {
        return motivoEmprestimo; //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
}
