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
public class Logo {

    private int idImagem;
    private byte[] imagem1;
    private byte[] imagem2;
    private byte[] imagem3;
    private byte[] imagem4;

    public Logo() {
    }

    public Logo(int idImagem, byte[] imagem1, byte[] imagem2, byte[] imagem3, byte[] imagem4) {
        this.idImagem = idImagem;
        this.imagem1 = imagem1;
        this.imagem2 = imagem2;
        this.imagem3 = imagem3;
        this.imagem4 = imagem4;
    }

    

    /**
     * @return the imagem1
     */
    public byte[] getImagem1() {
        return imagem1;
    }

    /**
     * @param imagem1 the imagem1 to set
     */
    public void setImagem1(byte[] imagem1) {
        this.imagem1 = imagem1;
    }

    /**
     * @return the imagem2
     */
    public byte[] getImagem2() {
        return imagem2;
    }

    /**
     * @param imagem2 the imagem2 to set
     */
    public void setImagem2(byte[] imagem2) {
        this.imagem2 = imagem2;
    }

    /**
     * @return the imagem3
     */
    public byte[] getImagem3() {
        return imagem3;
    }

    /**
     * @param imagem3 the imagem3 to set
     */
    public void setImagem3(byte[] imagem3) {
        this.imagem3 = imagem3;
    }

    /**
     * @return the imagem4
     */
    public byte[] getImagem4() {
        return imagem4;
    }

    /**
     * @param imagem4 the imagem4 to set
     */
    public void setImagem4(byte[] imagem4) {
        this.imagem4 = imagem4;
    }

    /**
     * @return the idImagem
     */
    public int getIdImagem() {
        return idImagem;
    }

    /**
     * @param idImagem the idImagem to set
     */
    public void setIdImagem(int idImagem) {
        this.idImagem = idImagem;
    }

}
