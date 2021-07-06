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
public class Session {

    // ESTE MODELO PEGA O USUARIO LOGAGO DO SISTEMA
    private static int idUsuario;
    private static String nome;
    private static String login;    
    private static String previlegio;

    /**
     * @return the idUsuario
     */
    public static int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param aIdUsuario the idUsuario to set
     */
    public static void setIdUsuario(int aIdUsuario) {
        idUsuario = aIdUsuario;
    }

    /**
     * @return the nome
     */
    public static String getNome() {
        return nome;
    }

    /**
     * @param aNome the nome to set
     */
    public static void setNome(String aNome) {
        nome = aNome;
    }

    /**
     * @return the previlegio
     */
    public static String getPrevilegio() {
        return previlegio;
    }

    /**
     * @param aPrevilegio the previlegio to set
     */
    public static void setPrevilegio(String aPrevilegio) {
        previlegio = aPrevilegio;
    }

    /**
     * @return the login
     */
    public static String getLogin() {
        return login;
    }

    /**
     * @param aLogin the login to set
     */
    public static void setLogin(String aLogin) {
        login = aLogin;
    }

}
