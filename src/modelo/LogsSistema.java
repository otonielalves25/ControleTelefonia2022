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
public class LogsSistema {

    private int idLog;
    private String data;
    private String atividade;
    private Usuario usuario;

    public LogsSistema() {
    }

    public LogsSistema(String data, String atividade, Usuario usuario) {
    
        this.data = data;
        this.atividade = atividade;
        this.usuario = usuario;
    }


    
    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



}
