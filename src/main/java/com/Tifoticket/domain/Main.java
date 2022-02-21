/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket.domain;

import exceptions.NominativoException;
import exceptions.PartitaException;
import exceptions.PostoException;
import exceptions.SettoreException;
import exceptions.datiClienteException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ConcurrentModificationException;
import java.util.Map;
/**
 *
 * @author arcap
 */
public class Main {
     
     public static void stampaMenu(){
          System.out.println("\n*** MENU ***");
          System.out.println("1. Inserimento nuova Partita");
          System.out.println("2. Vendita Biglietto");
          System.out.println("3. Sostituzione nominativo su Biglietto");
          System.out.println("4. Vendita Abbonamento");
          System.out.println("5. Visualizza informazioni vendite per una specifica partita");
          System.out.println("6. Visualizza informazioni vendite biglietti complessive");
          System.out.println("7. USCITA");
          System.out.println("Scegli l'operazione: ");
     }
     
     public static void main(String args[]){
          TifoTicket tifoticket=TifoTicket.getInstance();
          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          int scelta=0;
          
          do{
               try {
                    System.out.println("\n\tTIFOTICKET");
                    stampaMenu();
                    scelta = Integer.parseInt(br.readLine());
                    switch(scelta){
                         case 1:
                              System.out.println("\nInserisci il codice della partita: ");
                              String codice=br.readLine();
                              System.out.println("\nInserisci la data della partita: ");
                              String data=br.readLine();
                              LocalDate ld=LocalDate.parse(data);
                              System.out.println("Inserisci l'ora della partita: ");
                              String ora=br.readLine();
                              LocalTime lt=LocalTime.parse(ora);
                              LocalDateTime ldt=LocalDateTime.of(ld,lt);
                              System.out.println("\nInserisci il nome della squadra avversaria: ");
                              String avv=br.readLine();
                              System.out.println("Inserisci la tipologia della partita: ");
                              String tipologia=br.readLine();
                              
                              if(!tifoticket.inserimentoNuovaPartita(codice,ldt, avv,tipologia)){
                                   System.out.println("Inserimento partita annullato.");
                                   break;
                              }                                  
                              else{
                                   Map<String, Settore>listaSettori=tifoticket.getStadio().getListaSettori();
                                   for(Map.Entry<String,Settore> entry : listaSettori.entrySet()){
                                        System.out.println("Inserisci il prezzo base del biglietto per la "+entry.getKey()+" : ");
                                        float prezzo=Float.parseFloat(br.readLine());
                                        String res = tifoticket.impostaPrezzoBiglietto(entry.getKey(),prezzo);                                        
                                        System.out.println(res);
                                   }
                                   System.out.println("\nInvio per confermare l'inserimento dei dati.");
                                   if(br.readLine().length()==0)
                                        tifoticket.confermaInserimento();
                                   else
                                        System.out.println("Inserimento partita annullato.");
                              }
                              break;
                         case 2:
                              System.out.println("\nInserisci il nome della squadra avversaria: ");
                              String avversario=br.readLine();
                              Map<String,Partita> pa= tifoticket.cercaPartita(avversario);
                              if(pa.isEmpty()){
                                   System.err.println("Nessuna partita trovata. Riprova.");
                                   break;
                              }
                              else{
                              System.out.println("\nElenco partite trovate: ");
                              for(Map.Entry<String,Partita>entry : pa.entrySet())
                                   System.out.println("Codice partita: "+entry.getKey()+", Partita: "+entry.getValue()+"\n");
                              System.out.println("Scegli la partita inserendo il codice: ");
                              codice=br.readLine();
                              tifoticket.setPartitaScelta(pa.get(codice));
                              if(pa.get(codice)==null){
                                   System.out.println("Il codice inserito non appartiene a nessuna partita. Riprova");
                                   break;
                              }
                              System.out.println("\nPartita selezionata: "+pa.get(codice));
                              System.out.println("\nInserisci il settore per cui vuoi vendere un biglietto: ");
                              String settore=br.readLine();
                              try {
                                   tifoticket.sceltaSettore(settore);
                              }
                              catch(ConcurrentModificationException cme){
                                   System.out.println(tifoticket.getPartitaScelta().getStadio().elencoPostiDisponibili(settore).toString());
                              } catch (SettoreException ex) {   
                                   System.err.println("ERRORE: "+ex.getMessage());
                                   break;
                              }
                              if(settore.contains("Tribuna")){
                                   System.out.println("\nInserisci la fila: ");
                                   int fila=Integer.parseInt(br.readLine());
                                   System.out.println("Inserisci il numero del posto scelto: ");
                                   int numero=Integer.parseInt(br.readLine());
                                   String res;
                                   try {
                                        res = tifoticket.sceltaPosto(fila, numero);
                                        if(res!=null)
                                             System.out.println(res);
                                   } catch (PostoException ex) {
                                        System.err.println("ERRORE: "+ex.getMessage());
                                        break;
                                   }
                              }           

                              System.out.println("\nInserisci nome e cognome del cliente: ");
                              String nominativo=br.readLine();
                              System.out.println("Inserisci l'età del cliente: ");
                              int eta=Integer.parseInt(br.readLine());
                              tifoticket.datiCliente(nominativo, eta);
                              System.out.println("\nInvio per confermare l'inserimento dei dati.");
                              if(br.readLine().length()==0)
                                   tifoticket.confermaAcquisto();
                              else
                                   System.out.println("Vendita Biglietto annullata.");
                              break;
                              }
                         case 3: 
                              System.out.println("\nInserisci il codice del biglietto: ");
                              String codiceB=br.readLine();
                              System.out.println("\nInserisci il codice della partita: ");
                              String codicePar=br.readLine();
                              if(tifoticket.getListaPartite().containsKey(codicePar))
                                   System.out.println("\nPartita trovata");
                              else{
                                   System.out.println("\nIl codice inserito non corrisponde a nessuna partita.");
                                   break;
                              }
                              System.out.println("\nInserisci il nuovo nominativo da inserire sul biglietto: ");
                              String nuovoNom=br.readLine();
                              System.out.println("\nInserisci l'età da sostituire sul biglietto: ");
                              int nuovaEta= Integer.parseInt(br.readLine());
                              System.out.println("\nInvio per confermare l'inserimento dei dati.");
                              if(br.readLine().length()==0){
                                   Biglietto tmp=tifoticket.sostituzioneNominativo(codiceB, codicePar, nuovoNom, nuovaEta);
                                   if(tmp == null)
                                        break;
                                   else
                                        System.out.println(tmp);
                              }
                              else{
                                   System.out.println("Sostituzione nominativo Biglietto annullata.");
                                   break;
                              }
                              break;
                         case 4:
                              System.out.println("\nInserisci il settore per cui vuoi vendere un abbonamento annuale: ");
                              String settore=br.readLine();
                         {
                              try {
                                   int posti_liberi=tifoticket.settoreAbbonamento(settore);
                                   if(posti_liberi==0){
                                        System.out.println("Non ci sono posti liberi in questo settore. Scegli un altro settore.");
                                        break;
                                   }
                                   else
                                        System.out.println("Ci sono ancora "+posti_liberi+" posti liberi in "+settore);
                              } catch (SettoreException ex) {
                                   System.err.println(ex.getMessage());
                                   break;
                              }
                         }
                              System.out.println("\nInserisci nome e cognome del cliente: ");
                              String nominativo=br.readLine();
                              System.out.println("\nInserisci il codice fiscale del cliente: ");
                              String CF=br.readLine();
                              System.out.println("\nInserisci l'età del cliente: ");
                              int eta=Integer.parseInt(br.readLine());
                         {
                              try {
                                   tifoticket.datiClienteAbb(nominativo,CF,eta);
                              } catch (datiClienteException ex) {
                                   System.err.println("ERRORE: "+ex.getMessage());
                                   break;
                              }
                         }
                              if(settore.contains("Tribuna")){
                                   System.out.println("Inserisci la fila: ");
                                   int fila=Integer.parseInt(br.readLine());
                                   System.out.println("Inserisci il numero del posto scelto: ");
                                   int numero=Integer.parseInt(br.readLine());
                                   try {
                                        tifoticket.postoAbbonamento(fila, numero);
                                   } catch (PostoException ex) {
                                       System.err.println("ERRORE: "+ex.getMessage());
                                       break;
                                   }
                              }
                              System.out.println("\nInvio per confermare l'inserimento dei dati.");
                              if(br.readLine().length()==0)
                                   System.out.println(tifoticket.confermaAbbonamento());
                              else{
                                   System.out.println("Vendita Abbonamento annullata.");
                                   break;
                              }
                              break;
                    
                         case 5:
                              System.out.println("\nPartite attualmente disponibili: ");
                              if(tifoticket.getListaPartite().isEmpty()){
                                   System.out.println("ERRORE: Nessuna partita presente in memoria");
                                   break;
                              }
                              else{
                                   for(Map.Entry<String,Partita> entry : tifoticket.getListaPartite().entrySet())
                                        System.out.println("Codice Partita: "+entry.getKey()+", Partita: "+entry.getValue()+"\n");
                              }
                              
                              System.out.println("Scegli la partita inserendo il codice: ");
                              codice=br.readLine();
                              if(tifoticket.getListaPartite().containsKey(codice)){
                                   System.out.println("\nRecupero informazioni sulle vendite in corso...");
                                   tifoticket.getVenditePartita(codice);
                              }
                              else{
                                   System.out.println("ERRORE: Il codice inserito è errato.");
                                   break;
                              }
                              break;
                         case 6:
                              System.out.println("\nRecupero informazioni sulle vendite in corso...");
                              tifoticket.getDatiVenditeTotali();
                              break;
                         case 7:
                              System.out.println("\nCiao ciao");
                              break;
                         default:
                              System.out.println("\nScelta non valida");
                              break;
                    }
               } catch (IOException ex) {
                    System.err.println(ex.getMessage());
               }
               catch(DateTimeParseException dtpe){
                    System.err.println("Formato data/ora incorretto. Formati corretti:(AAAA-MM-GG) / (HH:mm)");
              }
               catch(NumberFormatException nfe){
                    System.err.println("ERRORE: E' richiesto l'inserimento di un numero");
              } catch (PartitaException ex) {
                    System.err.println("ERRORE: "+ex.getMessage());
               } catch (NominativoException ex) {
                    System.err.println("ERRORE: "+ex.getMessage());
               }
         }while(scelta!=7);    
     }
}