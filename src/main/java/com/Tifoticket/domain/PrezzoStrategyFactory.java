/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket.domain;

/**
 *
 * @author arcap
 */
public class PrezzoStrategyFactory {
     private static PrezzoStrategyFactory factory;
     
     private PrezzoStrategyFactory(){};
     
     public static PrezzoStrategyFactory getInstance(){
        if(factory == null)
            factory = new PrezzoStrategyFactory();       
        return factory;
    }
     
     public PrezzoStrategyInterface getPrezzoStrategy(){
        return new PrezzoStrategy();
    }
}
