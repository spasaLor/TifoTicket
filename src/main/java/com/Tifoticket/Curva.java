package com.Tifoticket;

public class Curva extends Settore{
    
    public Curva(String nome, int capienza){
        super(nome,capienza);
    }
    
    @Override
    public float calcolaPrezzoAbb(Abbonamento abb){
         float prezzo=0;
         if(abb.getEta()<18)
              prezzo=150;
         else if(abb.getEta()>18 && abb.getEta()<65)
              prezzo= 200;
         else if(abb.getEta()>65)
              prezzo=250;
         return prezzo;
    }
}
