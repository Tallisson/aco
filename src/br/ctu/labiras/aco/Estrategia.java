/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ctu.labiras.aco;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 *
 * @author thiago
 */
public class Estrategia {
    private float alpha;
    private float beta;
    private double rho;
    private double q;
    private boolean usarHeuristica;
    private int numAnts;       
    private int numCiclos;
    private int numIter;
    private double c;
    private int numCelulas;
    private int numTransicoes;
    
    public Estrategia() {
        configuraParametros();
    }
        
    public void configuraParametros() {
        try{
            DocumentBuilderFactory xml = DocumentBuilderFactory.newInstance();
            Document doc = xml.newDocumentBuilder().parse(new File("src/br/ctu/labiras/aco/utils/config.xml"));                        
            
            alpha = Float.parseFloat(doc.getElementsByTagName("alpha").item(0).getTextContent());
            beta = Float.parseFloat(doc.getElementsByTagName("beta").item(0).getTextContent());
            rho = Double.parseDouble(doc.getElementsByTagName("rho").item(0).getTextContent());
            q = Double.parseDouble(doc.getElementsByTagName("q").item(0).getTextContent());
            usarHeuristica = Boolean.parseBoolean(doc.getElementsByTagName("heuristica").item(0).getTextContent());
            numAnts = Integer.parseInt(doc.getElementsByTagName("ants").item(0).getTextContent());            
            numIter = Integer.parseInt(doc.getElementsByTagName("iteracoes").item(0).getTextContent());            
            c = Double.parseDouble(doc.getElementsByTagName("c").item(0).getTextContent());
            
            numCelulas = Integer.parseInt(doc.getElementsByTagName("num_celulas").item(0).getTextContent());            
            numTransicoes = Integer.parseInt(doc.getElementsByTagName("num_transicoes").item(0).getTextContent());            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the numAnts
     */
    public int getNumAnts() {
        return numAnts;
    }

    /**
     * @return the numCelulas
     */
    public int getNumCelulas() {
        return numCelulas;
    }

    /**
     * @return the numTransicoes
     */
    public int getNumTransicoes() {
        return numTransicoes;
    }

    /**
     * @return the c
     */
    public double getC() {
        return c;
    }

    /**
     * @return the numIter
     */
    public int getNumIter() {
        return numIter;
    }

    /**
     * @return the alpha
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * @return the beta
     */
    public float getBeta() {
        return beta;
    }

    /**
     * @return the rho
     */
    public double getRho() {
        return rho;
    }

    /**
     * @return the q
     */
    public double getQ() {
        return q;
    }
}
