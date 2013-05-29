/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ctu.labiras.aco;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author thiago
 */
public class Formiga {  
    // Fitness
    private double avaliacao;    
    // Celulas disponíveis
    private List<Integer> nosVizinhos;
    // Lista tabu
    private List<Integer> nosVisitados;
    
    public Formiga() {
        this.avaliacao = .0;
        this.nosVizinhos = new ArrayList<>();
        this.nosVisitados = new ArrayList<>();
    }

    /**
     * @return a avaliação
     */
    public double getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao a avaliação a configurar
     */
    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void configurarVizinhos(int numCelulas) {
        for(int i = 0; i < numCelulas; i++) {
            getNosVizinhos().add(i);
        }
    }
    
    /**
     * @return os nosVisitados
     */
    public List<Integer> getNosVisitados() {
        return nosVisitados;
    }
    
    public void removerNoVizinho(Integer no) {
        nosVizinhos.remove(no);
    }
    
    /**
     * @param indice 
     */
    public void adicionarNoVisitado(Integer valor) {
        nosVisitados.add(valor);
    }

    public void adicionarNoVisitado(int indice, Integer valor) {
        nosVisitados.add(indice, valor);
    }
    /**
     * @return os nosVizinhos
     */
    public List<Integer> getNosVizinhos() {
        return nosVizinhos;
    }
    
    public boolean comtemNoVisitado(Integer no) {
        return (nosVisitados.contains(no));
    }
    
    public String exibirCaminho() {
        String caminho = "{ ";
        
        Iterator<Integer> it = nosVisitados.iterator();
        while(it.hasNext()) {
            caminho += it.next().toString()+ " ";
        }
        caminho += "}";
        
        return caminho;
    }
    
    public void resetarSolucao() {        
        nosVisitados.clear();
    }
}
