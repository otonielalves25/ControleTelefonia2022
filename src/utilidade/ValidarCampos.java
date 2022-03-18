/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidade;

import componentes.UJComboBox;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author otoniel.aalves
 */
public class ValidarCampos {

    /**
     * FUNÇÃO STATICA VALIDAR CAMPOS
     *
     * @param txtCampo
     * @param nomeCampo
     * @return
     */
    // VALIDA CAMPO TXT ///////////////////////////////////////////////////////
    public static boolean validarCampo(JTextField txtCampo, String nomeCampo) {
        if (txtCampo.getText().trim().length() <= 0 || txtCampo.getText().equalsIgnoreCase("")) {
            txtCampo.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(null, "O campo " + nomeCampo + " é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            txtCampo.grabFocus();
            return true;
        } else {
            txtCampo.setBackground(Color.white);
            return false;
        }
    }

    // VALIDA COMBOBOX ///////////////////////////////////////////////////////
    public static boolean validarCampo(UJComboBox cboCombo, String nomeCampo) {
        if (cboCombo.getModel().getSelectedItem().equals("") || cboCombo.getModel().getSelectedItem().equals("Selecione...")) {
            cboCombo.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(null, "O campo " + nomeCampo + " é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
            cboCombo.grabFocus();
            return true;
        } else {
            cboCombo.setBackground(Color.white);
            return false;
        }
    }

    // COMO USAR A FUNÇÃO
    // if (ValidarCampos.validarTexto(txtSetor, "Setor")) {
    // return }
}
