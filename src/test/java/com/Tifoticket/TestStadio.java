/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
      void testListaPartite(){
          LocalDate ld= LocalDate.parse("2022-05-06");
          LocalTime lt= LocalTime.parse("20:45");
          LocalDateTime data=LocalDateTime.of(ld,lt);
          Partita pa=new Partita("AAA111",data,"MILAN","CAMPIONATO",st);
          
          ld= LocalDate.parse("2022-08-06");
          LocalDateTime data2= LocalDateTime.of(ld,lt);
          Partita pa2=new Partita("AAA112",data2,"INTER","CAMPIONATO",st);
          
          ArrayList lp= new ArrayList<Partita>();
          lp.add(pa);
          lp.add(pa2);
          //VERIFICO CHE LE PARTITE SIANO STATE INSERITE CORRETTAMENTE
          st.setListaPartite(lp);
          assertEquals(2,st.getListaPartite().size());
          assertNotNull(st.getListaPartite().get(0));
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
          } catch (Exception ex) {
               System.err.println("Expected error:  "+ex.getMessage());
          }
     }
     @Test
      void testDatiClienteAbb(){
          try {
               st.sceltaSettore("Curva Sud");
          } catch (Exception ex) {
               System.out.println("UNExpected error: "+ex.getMessage());
          }
          try {//INSERIMENTO CORRETTO
               st.datiClienteAbb("Lorenzo spadaro","abababababababab", 20);
               assertNotNull(st.getSettoreScelto().getAbbonamentoCorrente());
               st.confermaAbbonamento();
          } catch (Exception ex) {
               System.out.println("UNExpected error: "+ex.getMessage());
          }
          try {// FALLISCE PERCHE' STO INSERENDO UN CF UGUALE AD UNO GIA' PRESENTE
               st.sceltaSettore("Curva Sud");
               st.datiClienteAbb("Lorenzo spadaro","abababababababab", 30);
               fail();
          } catch (Exception ex) {
               System.out.println("Expected error: "+ex.getMessage());
          }
     }
     
     @Test
      void testPostoAbbonamento(){
          try {//FLUSSO CORRETTO
               st.sceltaSettore("Tribuna Ovest");
               st.datiClienteAbb("Lorenzo spadaro","abababababababab", 30);
               assertNotNull(st.postoAbbonamento(1, 8));
          } catch (Exception ex) {
               System.out.println("Unexpected error:PA "+ex.getMessage());
          }
          try {//FALLSICE PERCHE' SCELGO UN POSTO CHE NON ESISTE
               st.sceltaSettore("Tribuna Est");
               st.postoAbbonamento(2, 10);
               fail();
          } catch (Exception ex) {
               System.out.println("Expected error: "+ex.getMessage());
          }
          
     }
     
}
