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
public class Localidade {

    private int idLocalidade;
    private String nomeLocalidade;
    private String tipoLocalidade;

    public Localidade() {
    }

    public Localidade(int idLocalidade) {
        this.idLocalidade = idLocalidade;
     
    }

    /**
     * @return the idLocalidade
     */
    public int getIdLocalidade() {
        return idLocalidade;
    }

    /**
     * @param idLocalidade the idLocalidade to set
     */
    public void setIdLocalidade(int idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    /**
     * @return the nomeLocalidade
     */
    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    /**
     * @param nomeLocalidade the nomeLocalidade to set
     */
    public void setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    /**
     * @return the tipoLocalidade
     */
    public String getTipoLocalidade() {
        return tipoLocalidade;
    }

    /**
     * @param tipoLocalidade the tipoLocalidade to set
     */
    public void setTipoLocalidade(String tipoLocalidade) {
        this.tipoLocalidade = tipoLocalidade;
    }

    @Override
    public String toString() {
        return nomeLocalidade; //To change body of generated methods, choose Tools | Templates.
    }

}
