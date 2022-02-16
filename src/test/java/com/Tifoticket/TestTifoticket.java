/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author arcap
 */
 class TestTifoticket {
     static TifoTicket tifoticket;
     
     @BeforeAll
      static void initTest(){
          tifoticket=TifoTicket.getInstance();
     }
     
     
     @Test
      void testInserimentoNuovaPartita(){
          String codice="aaa";
          LocalDate ld=LocalDate.parse("2022-10-10");
          LocalTime lt=LocalTime.parse("20:45");
          LocalDateTime ldt= LocalDateTime.of(ld,lt);
          String avversario= "inter";
          String tipo="Campionato";
          
          tifoticket.inserimentoNuovaPartita(codice, ldt, avversario, tipo);
          assertNotNull(tifoticket.getPartitaCorrente());
          
          try{
                // IL FORMATO DELLA DATA E' ERRATO
               ld=LocalDate.parse("10-10-2022");
               ldt=LocalDateTime.of(ld,lt);
          }
          catch(DateTimeParseException dtpe){
               System.err.println("Expected error: "+dtpe.getMessage());
          }
          try{
               //INSERIMENTO DI DUE PARTITE IN SOVRAPPOSIZIONE
               tifoticket.inserimentoNuovaPartita("ppp", ldt, avversario, tipo);
               tifoticket.confermaInserimento();
               assertFalse(tifoticket.inserimentoNuovaPartita("www", ldt, avversario, tipo));
               //L'ultimo inserimento non avviene
          }
          catch(Exception e){
               System.err.println("unexpected error: "+e.getMessage());
          }
     }
     
     @Test
      void testConfermaInserimento(){
         try{ 
               String codice="bbb";
               LocalDate ld=LocalDate.parse("2022-11-11");
               LocalTime lt=LocalTime.parse("20:45");
               LocalDateTime ldt= LocalDateTime.of(ld,lt);
               String avversario= "inter";
               String tipo="Campionato"; 

               tifoticket.inserimentoNuovaPartita(codice, ldt, avversario, tipo);
               tifoticket.confermaInserimento();

               assertNotNull(tifoticket.getListaPartite());
               assertNotNull(tifoticket.getStadio().getListaPartite());
         }
         catch(Exception e){
              System.err.println("unexpected error: "+e.getMessage());
         }
          
     }
     @Test
      void testCercaPartita(){
         try{
              //CERCO UNA PARTITA NON ANCORA INSERITA,QUINDI LA LISTA SARA' VUOTA
          assertEquals(0,tifoticket.cercaPartita("Roma").size());
         }
         catch(Exception e){
               System.err.println("Unexpected error");
          }
          //INSERISCO UNA PARTITA E CERCO
          try{
               String codice="abc";
               LocalDate ld=LocalDate.parse("2022-08-04");
               LocalTime lt=LocalTime.parse("20:45");
               LocalDateTime ldt= LocalDateTime.of(ld,lt);
               String avversario= "Milan";
               String tipo="Campionato";

               tifoticket.inserimentoNuovaPartita(codice, ldt, avversario, tipo);
               tifoticket.confermaInserimento();
               assertNotNull(tifoticket.cercaPartita("Milan"));
               assertEquals(1,tifoticket.getListaPartite().size());
          }
          catch(Exception e){
               System.err.println("Unexpected error"+e.getMessage());
          }
     }
     
     @Test
      void testSceltaSettore(){
          try{ //CERCO UN SETTORE CHE NON ESISTE
               String codice="ccc";
               LocalDate ld=LocalDate.parse("2022-02-07");
               LocalTime lt=LocalTime.parse("20:45");
               LocalDateTime ldt= LocalDateTime.of(ld,lt);
               String avversario= "Parma";
               String tipo="Campionato";

               tifoticket.inserimentoNuovaPartita(codice, ldt, avversario, tipo);
               tifoticket.confermaInserimento();
               tifoticket.setPartitaScelta(tifoticket.getPartita("ccc"));
               tifoticket.sceltaSettore("Curva Est");
               fail();
          }
          catch(Exception e){
               System.err.println("Expected error: "+e.getMessage());
          }
          try{
               String codice="cba";
               LocalDate ld=LocalDate.parse("2022-02-04");
               LocalTime lt=LocalTime.parse("20:45");
               LocalDateTime ldt= LocalDateTime.of(ld,lt);
               String avversario= "Verona";
               String tipo="Campionato";

               tifoticket.inserimentoNuovaPartita(codice, ldt, avversario, tipo);
               tifoticket.confermaInserimento();
               tifoticket.setPartitaScelta(tifoticket.getPartita("cba"));
               tifoticket.sceltaSettore("Curva Sud");
                    
          }
          catch(Exception e){
               System.err.println("Unexpected error: "+e.getMessage());
          }
     }
     
     @Test 
      void testSceltaPosto(){
          try {//CERCO UN POSTO CHE NON ESISTE
               tifoticket.sceltaSettore("Tribuna Est");
               tifoticket.sceltaPosto(2, 10);
               fail();
          } catch (Exception ex) {
               System.err.println("Expected error: "+ex.getMessage());
          }
          try {//RICERCA CORRETTA
               tifoticket.sceltaSettore("Tribuna Est");
               tifoticket.sceltaPosto(1, 10);
          } catch (Exception ex) {
               System.err.println("Unexpected error: ");
          }
          
     }
}
