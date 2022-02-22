/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import com.Tifoticket.domain.Stadio;
import com.Tifoticket.domain.Partita;
import com.Tifoticket.exceptions.PostoException;
import com.Tifoticket.exceptions.SettoreException;
import com.Tifoticket.exceptions.datiClienteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author arcap
 */
 class TestStadio {
     private Stadio st;
     
     @BeforeEach
     public void init(){
          st=new Stadio("nomeStadio",30);
     }
     
     @Test
      void testImpostaPrezzo(){
          String res;
          res=st.impostaPrezzo("Curva Sud",100);
          assertEquals("Prezzo biglietti inserito",res);    
     }
     
     @Test
      void testListaSettori(){
          assertEquals(4,st.getListaSettori().size());
          assertNotNull(st.getListaSettori().get("Curva Sud"));
          assertNotNull(st.getListaSettori().get("Curva Nord"));
     }
     
      @Test
      void testSceltaSettore(){
          try {     //CURVA EST NON ESISTE
               st.sceltaSettore("Curva Est");
               fail();
          } catch (SettoreException ex) {
               System.out.println("Expected error: "+ex.getMessage());
          }
          try {
               assertNotNull(st.sceltaSettore("Tribuna Est"));
          } catch (SettoreException ex) {
               System.out.println("Unexpected error: "+ex.getMessage());
          }
      }
      
     @Test
      void testElencoDisponibili(){
          try {
               st.sceltaSettore("Tribuna Est");
               assertNotNull(st.elencoPostiDisponibili("Tribuna Est"));    //LISTA COLLEGATA AD UNA TRIBUNA: ESISTE

          } catch (Exception ex) {
               System.err.println("Unexpected error: "+ex.getMessage());
          }
          try{
               st.sceltaSettore("Tribuna Sud");
               st.elencoPostiDisponibili("Tribuna Sud");  //LISTA COLLEGATA AD UNA TRIBUNA CHE NON ESISTE: DARA' UNA NULLPOINTER EXCEPTION
               fail();
          }    
          catch(NullPointerException ex){
               System.out.println("Expected error nullPointer: "+ex.getMessage());
          } catch (SettoreException ex) {
               System.err.println("Expected error:  "+ex.getMessage());
          }
     }
     @Test
      void testDatiClienteAbb(){
          try {
               st.sceltaSettore("Curva Sud");
          } catch (Exception ex) {
               System.out.println("Unexpected error: "+ex.getMessage());
          }
          try {//INSERIMENTO CORRETTO
               st.datiClienteAbb("Lorenzo spadaro","abababababababab", 20);
               assertNotNull(st.getSettoreScelto().getAbbonamentoCorrente());
               st.confermaAbbonamento();
          } catch (Exception ex) {
               System.out.println("Unexpected error: "+ex.getMessage());
          }
          try {// FALLISCE PERCHE' STO INSERENDO UN CF UGUALE AD UNO GIA' PRESENTE
               st.sceltaSettore("Curva Sud");
               st.datiClienteAbb("Lorenzo spadaro","abababababababab", 30);
               fail();
          } catch (datiClienteException ex) {
               System.out.println("Expected error: "+ex.getMessage());
          } catch (SettoreException ex) {
               System.out.println("Unexpected error: "+ex.getMessage());
          }
     }
     
     @Test
      void testPostoAbbonamento(){
          try {//FLUSSO CORRETTO
               st.sceltaSettore("Tribuna Ovest");
               st.datiClienteAbb("Lorenzo spadaro","abababababababab", 30);
               assertNotNull(st.postoAbbonamento(1, 8));
          } catch (Exception ex) {
               System.out.println("Unexpected error: "+ex.getMessage());
          }
          try {//FALLSICE PERCHE' SCELGO UN POSTO CHE NON ESISTE
               st.sceltaSettore("Tribuna Est");
               st.postoAbbonamento(2, 10);
               fail();
          } catch (PostoException ex) {
               System.out.println("Expected error: "+ex.getMessage());
          } catch (SettoreException ex) {
               System.out.println("Unexpected error: "+ex.getMessage());
          }
          
     }
     
}
