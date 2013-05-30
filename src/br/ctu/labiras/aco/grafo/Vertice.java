/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ctu.labiras.aco.grafo;

/**
 *
 * @author thiago
 */
public class Vertice {
    private int id;    
    public int numAgentesAqui=0;    

    public Vertice(int id){
            this.id=id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumAgentesAqui() {
        return numAgentesAqui;
    }

    public void setNumAgentesAqui(int numAgentesAqui) {
        this.numAgentesAqui = numAgentesAqui;
    }
}