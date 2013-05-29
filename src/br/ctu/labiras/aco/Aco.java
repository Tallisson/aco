/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ctu.labiras.aco;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author thiago
 */
public class Aco {
    public static double INVALIDO = -1;
    
    private Estrategia estrategia;
    private Formiga[] formigas;
    private double[] taxaFeromonio;
    private double[] probTransicaoNo;
    private double media;
    private double melhorAvaliacao;
    private String melhorSolucao;
    private double piorAvaliacao;
    private String piorSolucao;
            
    public Aco() {
        this.inicializa();
    }   
    
    private int obterNumeroAleatorio(int fim) {
        Random random = new Random();
        return random.nextInt(fim);
    }
    
    private void iniciarFormiga(Formiga formiga) {        
        int numCelulas = estrategia.getNumCelulas();
        int celulaAleatoria = obterNumeroAleatorio(numCelulas-1);
        Integer celula = new Integer(celulaAleatoria);
        formiga.adicionarNoVisitado(celula);
    }
    
    private void iniciarTaxaFeromonio() {
        int numCelulas = estrategia.getNumCelulas();
        this.taxaFeromonio = new double[numCelulas];
        this.probTransicaoNo = new double[numCelulas];
        
        double taxaInicial = estrategia.getC();
        for(int i = 0; i < numCelulas; i++) {
            this.taxaFeromonio[i] = taxaInicial;
        }
    }
    
    private void inicializa() {
        this.estrategia = new Estrategia();
        int numFormigas = estrategia.getNumAnts();
        this.formigas = new Formiga[numFormigas];

        int numCelulas = estrategia.getNumCelulas();
        for(int i = 0; i < numFormigas; i++) {
            formigas[i] = new Formiga();
            formigas[i].configurarVizinhos(numCelulas);
        }
        iniciarTaxaFeromonio();
    }
    
    private void construirSolucao() {
        int numCelulas = estrategia.getNumCelulas();
        int numTransicoes = estrategia.getNumTransicoes();        
        double alfa = estrategia.getAlpha();
        
        for(Formiga formiga: formigas) {
            iniciarFormiga(formiga);
            List<Integer> nosVizinhos = formiga.getNosVizinhos();
            int numNos = nosVizinhos.size();
            int indice = 0;
            int j = 0;
            
            for(int z = 1; z < numTransicoes; z++) {                                
                double probTransTotal = .0;
                
                for(j = 0; j < numNos; j++) {
                    indice = nosVizinhos.get(j).intValue();
                    if(formiga.comtemNoVisitado(indice) == false) {
                        if(taxaFeromonio[indice] >= 0) {
                            probTransTotal += Math.pow(taxaFeromonio[indice], alfa);
                        }
                    }
                }
             
                for(j = 0; j < numNos; j++) {
                    indice = nosVizinhos.get(j).intValue();
                    if(formiga.comtemNoVisitado(indice) == false) {
                        if(taxaFeromonio[indice] >= 0) {
                            probTransicaoNo[indice] = Math.pow(taxaFeromonio[indice], alfa)/probTransTotal;
                        } else {
                            probTransicaoNo[indice] = .0;
                        }
                    } else {
                        probTransicaoNo[indice] = .0;
                    }
                }
     
                // Roleta
                double roleta = Math.random();
                double maior = .0;
                double menor = .0;                
                
                for(j = 0; j < numNos; j++) {
                    indice = nosVizinhos.get(j).intValue();
                    
                    if(formiga.comtemNoVisitado(indice) == false) {
                        maior += probTransicaoNo[indice];

                        if(roleta >= menor && roleta <= maior) {   
                            formiga.adicionarNoVisitado(indice);
                            probTransicaoNo[indice] = .0;
                            break;
                        } else {
                            menor = maior;
                        }
                    } 
                }
            }
        }
    }
    
    // Integrar avaliação com estrategia do testador
    private void avaliarSolucoes() {
        double total = .0;
        
        for(Formiga formiga: formigas) {
            Iterator<Integer> it = formiga.getNosVisitados().iterator();
            int no;
            int soma = 0;
            
            while(it.hasNext()) {
                no = it.next().intValue();
                soma += no;
            }
            formiga.setAvaliacao(soma);
            total += soma;            
        }
        this.media = total/formigas.length;
        for(Formiga formiga: formigas) {
            formiga.setAvaliacao(Math.abs(formiga.getAvaliacao()-media));
        }
    }
    
    private void evaporarFeromonio() {
        int tam = taxaFeromonio.length;        
        double persistenciaTrilha = 1 - estrategia.getRho();
        
        for(int i = 0; i < tam; i++) {
            taxaFeromonio[i] *= persistenciaTrilha;
        }
    }
    
    private void atualizarFeromonio() {
        int tam = taxaFeromonio.length;  
        double q = estrategia.getQ();
        double qtdFeromDepositar, avaliacao;
        
        for(int i = 0; i < tam; i++) {
            qtdFeromDepositar = .0;
            
            for(Formiga formiga: formigas) {
                if(formiga.comtemNoVisitado(i) == true) {
                    avaliacao = formiga.getAvaliacao();
                    qtdFeromDepositar += q/( avaliacao==0 ? 1 : avaliacao);
                }
            }
            taxaFeromonio[i] += qtdFeromDepositar;
        }
    }
    
    private void checarSolucoes() {
        double avaliacao;
        
        piorAvaliacao = melhorAvaliacao = formigas[0].getAvaliacao();
        for(Formiga formiga: formigas) {
            avaliacao = formiga.getAvaliacao();
            if(melhorAvaliacao > avaliacao) {
                melhorAvaliacao = avaliacao;
                melhorSolucao = formiga.exibirCaminho();
            } 
            if(piorAvaliacao < avaliacao) {
                piorAvaliacao = avaliacao;
                piorSolucao = formiga.exibirCaminho();
            }
        }
    }
    
    private void resetarFormigas() {
        for(Formiga formiga: formigas) {
            formiga.setAvaliacao(.0);
            formiga.resetarSolucao();
        }
    }
    
    public void executar() {
        int numIteracoes = estrategia.getNumIter();
        for(int i = 0 ; i < numIteracoes; i++) {
            construirSolucao();
            avaliarSolucoes();
            checarSolucoes();
            evaporarFeromonio();
            atualizarFeromonio();
            resetarFormigas();
            System.out.println("Melhor Avaliação: " + melhorAvaliacao + " Pior Avaliação: " + piorAvaliacao);
            System.out.println("Melhor Solução: " + melhorSolucao + " Pior Solução: " + piorSolucao);
        }
    }
    
    public static void main(String [] args) {
        Aco aco = new Aco();        
        aco.executar();                
    }
}
