/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import com.Tifoticket.domain.TifoTicket;
import com.Tifoticket.exceptions.PartitaException;
import com.Tifoticket.exceptions.PostoException;
import com.Tifoticket.exceptions.SettoreException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
          try {
               tifoticket.inserimentoNuovaPartita(codice, ldt, avversario, tipo);
          } catch (PartitaException ex) {
               System.out.println("unexpected error: "+ex.getMessage());
          }
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
                // IL FORMATO DELL'ORA E' ERRATO
               lt=LocalTime.parse("20.30");
               ldt=LocalDateTime.of(ld,lt);
          }
          catch(DateTimeParseException dtpe){
               System.err.println("Expected error: "+dtpe.getMessage());
          }
          try{
               //INSERIMENTO DI DUE PARTITE IN SOVRAPPOSIZIONE
               tifoticket.inserimentoNuovaPartita("ppp", ldt, avversario, tipo);
               tifoticket.confermaInserimento();
               tifoticket.inserimentoNuovaPartita("www", ldt, avversario, tipo);
               fail();
               //L'ultimo inserimento non avviene
          }
          catch(PartitaException e){
               System.err.println("Expected error: "+e.getMessage());
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

               assertEquals(3,tifoticket.getListaPartite().size());
         }
         catch(PartitaException e){
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
          catch(PartitaException e){
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
          catch(SettoreException e){
               System.err.println("Expected error: "+e.getMessage());
          } catch (PartitaException ex) {
              System.err.println("Unexpected error: "+ex.getMessage());
          }
          try{//RICERCA CORRETTA
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
          } catch (PostoException ex) {
               System.err.println("Expected error: "+ex.getMessage());
          } catch (SettoreException ex) {
               System.err.println("Unexpected error: "+ex.getMessage());
          }
          
          try {//RICERCA CORRETTA
               tifoticket.sceltaSettore("Tribuna Est");
               tifoticket.sceltaPosto(1, 10);
          } catch (Exception ex) {
               System.err.println("Unexpected error: ");
          }
     }
      @Test 
      void testGetVenditePartita(){
           //CERCO I DATI PER UNA PARTITA CHE NON ESISTE
           try{
                tifoticket.getVenditePartita("codice");
           }
           catch(NullPointerException npe){
                System.err.println("Expected error: nullpointer "+npe.getLocalizedMessage());
           }
           //CERCO I DATI PER UNA PARTITA INSERITA
           try{
               String codice="jkl";
               LocalDate ld=LocalDate.parse("2022-07-07");
               LocalTime lt=LocalTime.parse("20:45");
               LocalDateTime ldt= LocalDateTime.of(ld,lt);
               String avversario= "Parma";
               String tipo="Campionato";

               tifoticket.inserimentoNuovaPartita(codice, ldt, avversario, tipo);
               tifoticket.confermaInserimento();
               tifoticket.getVenditePartita("jkl");
               assertNotNull(tifoticket.getListaPartite().get("jkl"));
           }
           catch(Exception e){
                System.err.println("Unexpected error: "+e.getMessage());
           }
      }
}
