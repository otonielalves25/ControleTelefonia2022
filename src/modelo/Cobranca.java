/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author otoniel.aalves
 */
public class Cobranca {
    
    private int idCobranca;
    private String data;
    private Funcionario funcionario;
    private String protocolo;
    private String motivo;
    private Emprestimo emprestimo;
    private String status;  // PENDENTE OU FINALIZADO    
    private String dataEnvio;
    private Usuario usuario;    
    private int quantidade;
    private Usuario usuarioEnvio;   

    public Cobranca() {
    }

    public Cobranca(int idCobranca, String data, Funcionario funcionario, String protocolo, String motivo, Emprestimo emprestimo, String status, String dataEnvio, Usuario usuario, int quantidade,  Usuario usuarioEnvio) {
        this.idCobranca = idCobranca;
        this.data = data;
        this.funcionario = funcionario;
        this.protocolo = protocolo;
        this.motivo = motivo;
        this.emprestimo = emprestimo;
        this.status = status;
        this.dataEnvio = dataEnvio;
        this.usuario = usuario;
        this.quantidade = quantidade;
        this.usuarioEnvio = usuarioEnvio;
    }




    public int getIdCobranca() {
        return idCobranca;
    }

    public void setIdCobranca(int idCobranca) {
        this.idCobranca = idCobranca;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(String dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getUsuarioEnvio() {
        return usuarioEnvio;
    }

    public void setUsuarioEnvio(Usuario usuarioEnvio) {
        this.usuarioEnvio = usuarioEnvio;
    }
    
    
    
    
    
}
