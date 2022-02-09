/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author arcap
 */
public class TestPartita {
     Stadio st;
     Partita pa;
     
     @BeforeEach
     public void init (){
          st=new Stadio("nomeStadio",30);
          Settore c1= new Curva("Curva Sud",5);
          Settore c2= new Curva("Curva Nord",5);
          Settore t1=new Tribuna("Tribuna Est",10);
          Settore t2=new Tribuna("Tribuna Ovest",10);

          HashMap<String,Settore> settori=new HashMap();
          settori.put("Curva Sud",c1);
          settori.put("Curva Nord",c2);
          settori.put("Tribuna Est",t1);
          settori.put("Tribuna Ovest",t2);
          st.setListaSettori(settori);
          
          LocalDate ld= LocalDate.parse("2022-05-06");
          LocalTime lt= LocalTime.parse("20:45");
          LocalDateTime data=LocalDateTime.of(ld,lt);
          pa=new Partita("AAA",data,"MILAN","CAMPIONATO",st);
     }
     
     @Test
     public void testSceltaSettore(){   //CONTROLLA CHE IN UNA PARTITA APPENA INSERITA I POSTI DISPONIBILI SIANO UGUALI ALLA CAPIENZA DEI SETTORI
          
          try {
               assertEquals(5,pa.sceltaSettore("Curva Sud"));
               assertEquals(5,pa.sceltaSettore("Curva Nord"));
               assertEquals(10,pa.sceltaSettore("Tribuna Est"));
               assertEquals(10,pa.sceltaSettore("Tribuna Ovest"));
          } catch (Exception ex) {
               Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
          }
     }
     
     @Test
     public void testSceltaPosto(){
          //SCELTA VALIDA
          try {
               pa.sceltaSettore("Tribuna Est");
          } catch (Exception ex) {
               Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
          }
          String res=pa.sceltaPosto(1, 1);
          assertEquals(res,"Il posto scelto è stato selezionato correttamente.");
          
          //FALLISCE PERCHE' SCELGO UN POSTO CHE NON ESISTE
          assertNull(pa.sceltaPosto(2, 1));
         
     }
     
     @Test
     public void testConfermaAcquisto(){  
          //FALLISCE PERCHE' NON E' STATO CREATO IL BIGLIETTO
          try{
               pa.confermaAcquisto();   
               fail();
          }
          catch(NullPointerException ex){
               System.err.println(ex.getMessage());
          }
          //ACQUISTO VALIDO 
          try {
               pa.sceltaSettore("Tribuna Est");
          } catch (Exception ex) {
               Logger.getLogger(TestPartita.class.getName()).log(Level.SEVERE, null, ex);
          }
          assertNotNull(pa.confermaAcquisto()); 
          
     }
     
}
