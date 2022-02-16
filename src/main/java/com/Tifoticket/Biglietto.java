/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

/**
 *
 * @author arcap
 */
public class Biglietto extends Tessera{


     public Biglietto(String codice,Settore settore,Partita partita){
          super(codice,null,settore,partita);
     }

     @Override
     public String toString() {
          if(super.getPosto() == null)
               return "Stampa biglietto in corso...\n" + "Codice biglietto:" + super.getCodice() + "\nNominativo: " + super.getNominativo() +
                      ", età: " + super.getEta() +"\nValido per la partita: "+super.getPartita()+ "\n"+ "Prezzo: € " + super.getPrezzo() + "\n"
                  + "Settore: " + super.getSettore().getNome() + ", Posto:  ";
          else
             return "Stampa biglietto in corso...\n" + "Codice biglietto:" + super.getCodice() + "\nNominativo: " + super.getNominativo() +
                      ", età: " + super.getEta() +"\nValido per la partita: "+super.getPartita()+ "\n"+ "Prezzo: € " + super.getPrezzo() + "\n"
                  + "Settore: " + super.getSettore().getNome() + ", Posto: "+super.getPosto();
     }
     
     
     
}
