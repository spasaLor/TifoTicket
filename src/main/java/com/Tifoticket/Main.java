/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author arcap
 */
public class Main {
     
     public static void stampaMenu(){
          System.out.println("\n*** MENU ***");
          System.out.println("1. Inserimento nuova Partita");
          System.out.println("2. Vendita Biglietto");
          System.out.println("3. Vendita Abbonamento");
          System.out.println("4. USCITA");
          System.out.println("Scelta-> ");
     }
     
     public static void main(String args[]){      //TODO: CONTROLLI SU TUTTE LE FUNZIONI PER GLI ERRORI, LE SOVRAPPOSIZIONI
          TifoTicket tifoticket=TifoTicket.getInstance();
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          boolean uscita=false;
          int scelta=0;
          
          do{
               try {
                    stampaMenu();
                    scelta = Integer.parseInt(br.readLine());
                    switch(scelta){
                         case 1:
                              System.out.println("Inserisci il codice della partita: ");
                              String codice=br.readLine();
                              System.out.println("Inserisci la data della partita: ");
                              String data=br.readLine();
                              LocalDate ld=LocalDate.parse(data);
                              System.out.println("Inserisci l'ora della partita: ");
                              String ora=br.readLine();
                              LocalTime lt=LocalTime.parse(ora);
                              LocalDateTime ldt=LocalDateTime.of(ld,lt);
                              System.out.println("Inserisci il nome della squadra avversaria: ");
                              String avv=br.readLine();
                              System.out.println("Inserisci la tipologia della partita: ");
                              String tipologia=br.readLine();
                              
                              if(!tifoticket.inserimentoNuovaPartita(codice, ldt, avv, tipologia))
                                   break;
                              else{
                                   Map<String, Settore>listaSettori=tifoticket.getStadio().getListaSettori();
                                   for(Map.Entry<String,Settore> entry : listaSettori.entrySet()){
                                        System.out.println("Inserisci il prezzo base del biglietto per la "+entry.getKey()+" : ");
                                        float prezzo=Float.parseFloat(br.readLine());
                                        String res=tifoticket.impostaPrezzoBiglietto(entry.getKey(),prezzo);
                                        System.out.println(res);
                                   }
                                   System.out.println("Invio per confermare l'inserimento dei dati.");
                                   if(br.readLine().length()==0)
                                        tifoticket.confermaInserimento();
                                   else
                                        System.out.println("Inserimento partita annullato.");
                              }
                              break;
                         case 2:
                              System.out.println("Inserisci il nome della squadra avversaria ");
                              String avversario=br.readLine();
                              Partita pa= tifoticket.cercaPartita(avversario);
                              System.out.println("Inserisci il settore per cui vuoi comprare un biglietto: ");
                              String settore=br.readLine();
                              tifoticket.sceltaSettore(settore);
                              if(settore.contains("Tribuna")){
                                   System.out.println("Inserisci la fila: ");
                                   int fila=Integer.parseInt(br.readLine());
                                   System.out.println("Inserisci il numero del posto scelto: ");
                                   int numero=Integer.parseInt(br.readLine());
                                   String res=tifoticket.sceltaPosto(fila, numero);
                                   System.out.println(res);
                              }
                              System.out.println("Inserisci le generalità del cliente: ");
                              String nominativo=br.readLine();
                              System.out.println("Inserisci l'età del cliente: ");
                              int eta=Integer.parseInt(br.readLine());
                              tifoticket.datiCliente(nominativo, eta);
                              System.out.println("Invio per confermare l'inserimento dei dati.");
                              if(br.readLine().length()==0)
                                   tifoticket.confermaAcquisto();
                              else
                                   System.out.println("Acquisto Biglietto annullato.");
                              break;
                         case 3:
                              break;
                         case 4:
                              System.out.println("Ciao ciao");
                              break;
                    }
               } catch (IOException ex) {
                    System.err.println(ex.getMessage());
               }
         }while(scelta!=4);    
     }
}
