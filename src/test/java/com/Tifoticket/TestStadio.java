/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Tifoticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
/**
 *
 * @author arcap
 */
public class TestStadio {
     Stadio st=new Stadio("nomeStadio",30);
     
     @BeforeClass
     public void init(){
          
          Settore c1= new Curva("Curva Sud",5);
          Settore c2= new Curva("Curva Nord",5);
          Settore t1=new Tribuna("Tribuna Est",10);
          Settore t2=new Tribuna("Tribuna Ovest",10);

          HashMap<String,Settore> settori=new HashMap();
          settori.put("CurvaSud",c1);
          settori.put("CurvaNord",c2);
          settori.put("TribunaEst",t1);
          settori.put("TribunaOvest",t2);
          st.setListaSettori(settori);
     }
     
     @Test
     public void testImpostaPrezzo(){
          String res;
          try {
               res=st.impostaPrezzo("CurvaSud",100);
               assertEquals("Prezzo biglietti inserito",res);
          } catch (Exception ex) {
               Logger.getLogger(TestStadio.class.getName()).log(Level.SEVERE, null, ex);
          }    
     }
     
     @Test
     public void testListaSettori(){
          assertEquals(4,st.getListaSettori().size());
          assertNotNull(st.getListaSettori().get("CurvaSud"));
     }
     
     
     @Test
     public void testListaPartite(){
          Date data=new Date(20/05/2022);
          Partita pa=new Partita("AAA111",data,"MILAN","CAMPIONATO");
          Date data2= new Date(10/06/2022);
          Partita pa2=new Partita("AAA112",data,"INTER","CAMPIONATO");
          
          ArrayList lp= new ArrayList<Partita>();
          lp.add(pa);
          lp.add(pa2);
          
          st.setListapartite(lp);
          assertEquals(2,st.getListapartite().size());
          assertNotNull(st.getListapartite().get(0));
     }
     
}
