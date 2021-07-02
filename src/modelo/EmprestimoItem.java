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
public class EmprestimoItem {
   private int idemprestimo_item;            
   private Equipamento equipamento;
   private Emprestimo emprestimo;

    public EmprestimoItem() {
    }

    public EmprestimoItem(int idemprestimo_item) {
        this.idemprestimo_item = idemprestimo_item;
      
    }

    /**
     * @return the idemprestimo_item
     */
    public int getIdemprestimo_item() {
        return idemprestimo_item;
    }

    /**
     * @param idemprestimo_item the idemprestimo_item to set
     */
    public void setIdemprestimo_item(int idemprestimo_item) {
        this.idemprestimo_item = idemprestimo_item;
    }

    /**
     * @return the aparelho
     */


    /**
     * @return the emprestimo
     */
    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    /**
     * @param emprestimo the emprestimo to set
     */
    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    /**
     * @return the equipamento
     */
    public Equipamento getEquipamento() {
        return equipamento;
    }

    /**
     * @param equipamento the equipamento to set
     */
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
   
    
   
    
}
