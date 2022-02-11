/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author arcap
 */
public class Abbonamento {
     private String codice;
     private int eta;
     private String nominativo;
     private float prezzo;
     private Posto posto;
     private String CF;
     private Settore settore;
     private List<Partita> listaPartiteValide;
             
     public Abbonamento(String nominativo, int eta,Settore settore) {
          this.eta = eta;
          this.nominativo = nominativo;
          this.settore=settore;
          listaPartiteValide=new ArrayList<>();
     }
     
     public String getCodice() {
          return codice;
     }

     public void setCodice(String codice) {
          this.codice = codice;
     }

     public int getEta() {
          return eta;
     }

     public void setEta(int eta) {
          this.eta = eta;
     }

     public String getNominativo() {
          return nominativo;
     }

     public void setNominativo(String nominativo) {
          this.nominativo = nominativo;
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

     public List<Partita> getListaPartiteValide() {
          return listaPartiteValide;
     }

     public void setListaPartiteValide(List<Partita> listaPartiteValide) {
          this.listaPartiteValide = listaPartiteValide;
     }

     public String getCF() {
          return CF;
     }

     public void setCF(String CF) {
          this.CF = CF;
     }
     
     

     @Override
     public String toString() {
          if(posto == null)
                return "Stampa abbonamento in corso...\n" + "codice:" + codice + "\nNominativo: " + nominativo + ", età: " + eta +
                  "\nPrezzo: € " + prezzo +", Settore: " + settore.getNome() + 
                  "\nValido per: tutte le partite di Campionato";
          else
               return "Stampa abbonamento in corso...\n" + "codice:" + codice + "\nNominativo: " + nominativo + ", età: " + eta +
                  "\nPrezzo: € " + prezzo +", Settore: " + settore.getNome() + posto + 
                  "\nValido per: tutte le partite di Campionato";
     }
}
