/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidade;

import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author Tony
 */
public class MaximoLength {

    // FUNÇÃO QUE VEFICA CAIXA DE TESTT
    public static void tamanhoMaximoCaracter(JTextField caixaTexto, int tamanho) {
        if (caixaTexto.getText().length() >= tamanho) {
            caixaTexto.setText(caixaTexto.getText().substring(0, tamanho - 1));

        }
    }
// FUNÇÃO QUE VEFICA CAIXA DE PAINEL DE TESTE

    public static void tamanhoMaximoCaracter(JTextPane painelTexto, int tamanho) {
        if (painelTexto.getText().length() >= tamanho) {
            painelTexto.setText(painelTexto.getText().substring(0, tamanho - 1));

        }
    }
}
