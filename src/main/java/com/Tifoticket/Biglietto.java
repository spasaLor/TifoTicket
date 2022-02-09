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
public class Biglietto {
     private String codice;
     private String nominativo;
     private int eta;
     private float prezzo;
     private Posto posto;
     private Settore settore;
     private Partita partita;

     public Biglietto(String codice,Partita partita,Settore settore) {
          this.codice = codice;
          this.partita=partita;
          this.settore=settore;
     }

     public String getCodice() {
          return codice;
     }

     public void setCodice(String codice) {
          this.codice = codice;
     }

     public String getNominativo() {
          return nominativo;
     }

     public void setNominativo(String nominativo) {
          this.nominativo = nominativo;
     }

     public int getEta() {
          return eta;
     }

     public void setEta(int eta) {
          this.eta = eta;
     }

     public float getPrezzo() {
          return prezzo;
     }

     public void setPrezzo(float prezzo) {
          this.prezzo = prezzo;
     }

     public Posto getPosto() {
          return posto;
     }

     public void setPosto(Posto posto) {
          this.posto = posto;
     }

     public Settore getSettore() {
          return settore;
     }

     public void setSettore(Settore settore) {
          this.settore = settore;
     }

     public Partita getPartita() {
          return partita;
     }

     public void setPartita(Partita partita) {
          this.partita = partita;
     }

     @Override
     public String toString() {
          if(posto == null)
               return "Stampa biglietto in corso...\n" + "Codice biglietto: " + codice + "\n"
                  +"Nominativo: " + nominativo + ", eta: " + eta+"\n"
                  +"Valido per la partita: "+partita+ "\n"
                  + "Prezzo: " + prezzo + "\n"
                  + "Settore: " + settore.getNome() + ", Posto:  ";
          else
              return "Stampa biglietto in corso...\n" + "Codice biglietto: " + codice + "\n"
                  +"Nominativo: " + nominativo + ", eta: " + eta+"\n"
                  +"Valido per la partita: "+partita+ "\n"
                  + "Prezzo: " + prezzo + "\n"
                  + "Settore: " + settore.getNome() +", "+posto; 
     }
     
     
     
}
