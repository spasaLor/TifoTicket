/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

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
public class TestTribuna {
     Tribuna se;
     @BeforeEach
     public void init(){
          se=new Tribuna("Tribuna Est",10);
     }
     
     @Test
     public void testSceltaPosto(){
          //SCELTA CORRETTA
          try {
               assertNotNull(se.sceltaPosto(1, 10));
          } catch (Exception ex) {
               System.err.println("Unexpected error: "+ex.getMessage());
          }
          //FALLISCE PERCHE' HO UNA FILA DA 10 POSTI
          try{
               se.sceltaPosto(1,11);
               fail();
          }
          catch(Exception e){
               System.err.println("Expected error: "+e.getMessage());
          }
     }
     @Test
     public void testElencoPostiDisponibili(){
          assertNotNull(se.elencoPostiDisponibili());
          assertEquals(10,se.elencoPostiDisponibili().size());
     }
}
