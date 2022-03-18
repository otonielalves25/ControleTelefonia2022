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
public class Cargo {
    
     private int idCargo;
    private String nomeCargo;

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    @Override
    public String toString() {
        return nomeCargo; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
   
    
    
}
