/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ctu.labiras.aco.grafo;

/**
 *
 * @author thiago
 */
public class AcoLink {
    private double peso;
    private int id;

    public AcoLink(int id, double peso) {
        this.peso = peso;
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "E" + id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
