/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidade;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author otoniel.aalves
 */
public class EnviarEmail {

    private final String myEmail = "cgidot@gmail.com";  // Quem está enviando
    private final String senha = "govvnyypurhroizi"; // Senha do DOT
    private final String smtpHost = "smtp.gmail.com";
    private final int port = 465;

    // Método privado que CRIA e CONFIGURA um novo e-mail
    private Email criarEmailConfigurado() {

        HtmlEmail email = new HtmlEmail();
        email.setHostName(smtpHost);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(myEmail, senha));
        email.setCharset("UTF-8");
        email.setStartTLSEnabled(false);
        email.setSSLOnConnect(true);
        return email;
    }

    public void enviar(String emailDestino, String cabecalho, String mensagem) {

        try {

            // Pega um e-mail novo e limpo totalmente configurado
            Email email = criarEmailConfigurado();

            email.setFrom(myEmail); // QUEM ESTÁ ENVIANDO

            email.addTo(emailDestino);
            email.setSubject(cabecalho);
            email.setMsg(mensagem);

            // com copia ************************************
            //for(String cc : pessoa.getEmail()){
            //email.addCc(cc); }	
            email.send();

            System.out.println("Enviado com Sucesso para:" + emailDestino);

        } catch (EmailException ex) {

            System.out.println("Erro de conexão com Gmail.");

        }

    }

}
