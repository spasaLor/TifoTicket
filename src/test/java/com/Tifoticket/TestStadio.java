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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 *
 * @author arcap
 */
public class TestStadio {
     static Stadio st;
     
     @BeforeAll
     public static void init(){
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
     }
     
     @Test
     public void testImpostaPrezzo(){
          String res;
          try {
               res=st.impostaPrezzo("Curva Sud",100);
               assertEquals("Prezzo biglietti inserito",res);
          } catch (Exception ex) {
               Logger.getLogger(TestStadio.class.getName()).log(Level.SEVERE, null, ex);
          }    
     }
     
     @Test
     public void testListaSettori(){
          assertEquals(4,st.getListaSettori().size());
          assertNotNull(st.getListaSettori().get("Curva Sud"));
     }
     
     @Test
     public void testListaPartite(){
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
          
          st.setListaPartite(lp);
          assertEquals(2,st.getListaPartite().size());
          assertNotNull(st.getListaPartite().get(0));
     }
     
     @Test
     public void testElencoDisponibili(){
     assertNotNull(st.elencoPostiDisponibili("Tribuna Est"));    //LISTA COLLEGATA AD UNA TRIBUNA: ESISTE
     try{
          st.elencoPostiDisponibili("Tribuna Sud");  //LISTA COLLEGATA AD UNA CURVA: NON ESISTE QUINDI DARA' UNA NULLPOINTER EXCEPTION
          fail();
     }    
     catch(NullPointerException ex){
          System.out.println(ex.getMessage());
     }
}
          
}
