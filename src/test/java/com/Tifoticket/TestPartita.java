/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author arcap
 */
 class TestPartita {
     Stadio st;
     Partita pa;
     
     @BeforeEach
      void init (){
          st=new Stadio("nomeStadio",30);
          LocalDate ld= LocalDate.parse("2022-05-06");
          LocalTime lt= LocalTime.parse("20:45");
          LocalDateTime data=LocalDateTime.of(ld,lt);
          pa=new Partita("AAA",data,"MILAN","CAMPIONATO",st);
     }
     
     @Test
      void testSceltaSettore(){   //CONTROLLA CHE IN UNA PARTITA APPENA INSERITA I POSTI DISPONIBILI
                                        //SIANO UGUALI ALLA CAPIENZA DEI SETTORI
          try {
               assertEquals(5,pa.sceltaSettore("Curva Sud"));
               assertEquals(5,pa.sceltaSettore("Curva Nord"));
               assertEquals(10,pa.sceltaSettore("Tribuna Est"));
               assertEquals(10,pa.sceltaSettore("Tribuna Ovest"));
          } catch (Exception ex) {
               System.err.println("Unexpectd error: "+ex.getMessage());
          }
     }
     
     @Test
      void testSceltaPosto(){
          //SCELTA VALIDA
          try {
               pa.sceltaSettore("Tribuna Est");
          } catch (Exception ex) {
               System.err.println("Unexpectd error: "+ex.getMessage());
          }
          String res;
          try {
               res = pa.sceltaPosto(1, 1);
               assertEquals(res,"Il posto scelto è stato selezionato correttamente.");
          } catch (Exception ex) {
               System.err.println("Unexpectd error: "+ex.getMessage());
          }
          try {
               //FALLISCE PERCHE' SCELGO UN POSTO CHE NON ESISTE
               pa.sceltaPosto(2, 1);
               fail();
          } catch (Exception ex) {
               System.err.println("Expectd error: "+ex.getMessage());
          }
         
     }
     
     @Test
      void testConfermaAcquisto(){  
          //FALLISCE PERCHE' NON E' STATO CREATO IL BIGLIETTO
          try{
               pa.confermaAcquisto();   
               fail();
          }
          catch(NullPointerException ex){
               System.err.println("Expected error: nullpointer "+ex.getMessage());
          }
          //ACQUISTO VALIDO 
          try {
               pa.sceltaSettore("Curva Nord");
               assertNotNull(pa.confermaAcquisto()); 
          } catch (Exception ex) {
               System.err.println("Unexpected error: "+ex.getMessage());
          }          
     }
}
