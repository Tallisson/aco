/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ctu.labiras.aco;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thiago
 */
public class Caminho {
    List<Object> trajeto;
    private double qtdFermonio;
    
    public Caminho() {
        trajeto = new ArrayList<>();
    }
    
    public boolean adicionarNo(Object no) {
        return (trajeto.add(no));
    }
    
    public Object obterNo(int indice) {
        return (trajeto.get(indice));
    }
    
    public boolean contemNo(Object no) {
        return (trajeto.contains(no));
    }

    /**
     * @return the qtdFermonio
     */
    public double getQtdFermonio() {
        return qtdFermonio;
    }

    /**
     * @param qtdFermonio the qtdFermonio to set
     */
    public void setQtdFermonio(double qtdFermonio) {
        this.qtdFermonio = qtdFermonio;
    }
}
