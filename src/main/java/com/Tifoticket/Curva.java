package com.Tifoticket;

public class Curva extends Settore{
    
    public Curva(String nome, int capienza){
        super(nome,capienza);
    }
    
    @Override
    public void calcolaPrezzoAbb(Settore se){
         Tessera t=se.getAbbonamentoCorrente();
         
         float prezzo=0;
         if(t.getEta()<18)
              prezzo=150;
         else if(t.getEta()>18 && t.getEta()<65)
              prezzo= 200;
         else if(t.getEta()>65)
              prezzo=250;
         
         t.setPrezzo(prezzo);
    }
}
