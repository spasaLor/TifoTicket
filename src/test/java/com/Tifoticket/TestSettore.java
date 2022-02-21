/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import com.Tifoticket.domain.Settore;
import com.Tifoticket.domain.Tribuna;
import com.Tifoticket.exceptions.PostoException;
import com.Tifoticket.exceptions.datiClienteException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author arcap
 */
 class TestSettore {
      Settore se;
      
      @BeforeEach
      void init(){
           se= new Tribuna("Tribuna Est",10);
      }
      
      @Test
      void testDatiClienteAbb(){
           try {
                se.datiClienteAbb("Lorenzo Spadaro","abababababababab", 30);
                se.postoAbbonamento(1, 10);
                se.confermaAbbonamento();
                assertEquals(1,se.getListaAbbonamenti().size());
           } catch (Exception ex) {
                System.err.println("Unexpected error: "+ex.getMessage());
           }
           
           try {// DUE PERSONE CON LO STESSO NOME E COGNOME MA DIVERSO CF, L'INSERIMENTO AVVIENE
                se.datiClienteAbb("Lorenzo Spadaro","babababababababa", 30);
                se.postoAbbonamento(1, 8);
                se.confermaAbbonamento();
                assertEquals(2,se.getListaAbbonamenti().size());
           } catch (Exception ex) {
                System.err.println("Unexpected error: "+ex.getMessage());
           }
           
           try {//STO INSERENDO UN CODICE FISCALE GIA' PRESENTE
                se.datiClienteAbb("Lorenzo Spadaro","abababababababab", 30);
                fail();
           } catch (datiClienteException ex) {
                System.err.println("Expected error: "+ex.getMessage());
           }
           try {//STO INSERENDO UN CODICE FISCALE ERRATO
                se.datiClienteAbb("Lorenzo Spadaro","bbdajf", 30);
                fail();
           } catch (datiClienteException ex) {
                System.err.println("Expected error: "+ex.getMessage());
           }
      }
      
      @Test
      void testPostoAbbonamento(){
           try { //SCELGO UN POSTO CHE NON ESISTE
                se.datiClienteAbb("Lorenzo Spadaro","babababababababa", 30);
                assertNull(se.postoAbbonamento(2, 8));
                fail();
           } catch (PostoException ex) {
                System.err.println("Expected error: "+ex.getMessage());
           } catch (Exception ex) {
                System.err.println("Unexpected error: "+ex.getMessage());
           }
           try { //FLUSSO CORRETTO
                se.datiClienteAbb("Lorenzo Spadaro","babababababababa", 30);
                assertNotNull(se.postoAbbonamento(1, 8));
                se.confermaAbbonamento();
                assertEquals(1,se.getListaAbbonamenti().size());
           } catch (Exception ex) {
                System.err.println("Unexpected error: "+ex.getMessage());
           }
           
      }
}
