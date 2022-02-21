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
public class PrezzoStrategy implements PrezzoStrategyInterface{

     @Override
     public float applicaTariffa(Tessera t) {
          Partita pa=t.getPartita();
          String tipologia= pa.getTipologia();
          int eta=t.getEta();
          float base=t.getSettore().getPrezzoBiglietto();
          float prezzo=0;
          
          switch(tipologia){
               case "Campionato":
                    if(eta<14)
                         prezzo= base - ((base *20)/100);
                    else if(eta>65)
                         prezzo= base - ((base *30)/100);
                    else
                         prezzo=base;
                    break;
               case "Coppa":
                    if(eta<14)
                         prezzo= base - ((base *10)/100);
                    else if(eta>65)
                         prezzo= base - ((base *15)/100);
                    else
                         prezzo=base;
                    break;
               case "Coppa Europea": 
                    if(eta<14)
                         prezzo= base - ((base *10)/100);
                    else if(eta>65)
                         prezzo= base - ((base *15)/100);
                    else
                         prezzo=base;
                    break;
          }
          return prezzo;
     }
     
}
