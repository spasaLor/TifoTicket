package com.Tifoticket;

import java.util.ArrayList;
import java.util.List;

public class Tribuna extends Settore{
    private List<Posto> listaPosti;
    
    public Tribuna (String nome, int capienza){
        super(nome,capienza);
        listaPosti=new ArrayList();
        loadPosti();
    }
    
    public Posto sceltaPosto(int fila,int numero) throws Exception{
         Posto po=null;
          for(Posto p: listaPosti){
               if(p.getFila()==fila && p.getNumero()==numero){
                     po=p;
               }
          }
          if(po!= null)
               return po;
          else 
               throw new Exception("ERRORE: Impossibile scegliere il posto selezionato. Riprova con un altro posto.");
          
     }
    
    public List<Posto> elencoPostiDisponibili(){
         List<Posto> postiDisponibili= this.listaPosti;
         
         for(Biglietto b : this.getListaBiglietti()){
              Posto tmp=b.getPosto();
              if(tmp.getFila()==b.getPosto().getFila() && tmp.getNumero() == b.getPosto().getNumero())
                   postiDisponibili.remove(tmp);
         }
         return postiDisponibili;
    }
    
    @Override
    public float calcolaPrezzoAbb(Abbonamento a){
         float prezzo=0;
         if(a.getEta()<18)
              prezzo=250;
         else if(a.getEta()>18 && a.getEta()<65)
              prezzo= 450;
         else if(a.getEta()>65)
              prezzo=300;
         return prezzo;
    }
    
    public Posto postoAbbonamento(int fila,int numero) throws Exception{
         Posto po=null;
          for(Posto p: listaPosti){
               if(p.getFila()==fila && p.getNumero()==numero){
                     po=p;
               }
          }
          if(po!= null)
               return po;
          
          else 
               throw new Exception("ERRORE: Impossibile scegliere il posto selezionato. Riprova con un altro posto.");
    }
    
    public List<Posto> getListaPosti() {
        return listaPosti;
    }

    public void setListaPosti(ArrayList<Posto> listaPosti) {
        this.listaPosti = listaPosti;
    }
    
    @Override
    public String toString() {
        return "Tribuna{" +
                "nome='" + nome + '\'' +
                ", listaPosti=" + listaPosti +
                '}';
    }
    public void loadPosti(){
        Posto p1 = new Posto(1,1);
        Posto p2 = new Posto(1,2);
        Posto p3 = new Posto(1,3);
        Posto p4 = new Posto(1,4);
        Posto p5 = new Posto(1,5);
        Posto p6 = new Posto(1,6);
        Posto p7 = new Posto(1,7);
        Posto p8 = new Posto(1,8);
        Posto p9 = new Posto(1,9);
        Posto p10 = new Posto(1,10);
        this.listaPosti.add(p1);
        this.listaPosti.add(p2);
        this.listaPosti.add(p3);
        this.listaPosti.add(p4);
        this.listaPosti.add(p5);
        this.listaPosti.add(p6);
        this.listaPosti.add(p7);
        this.listaPosti.add(p8);
        this.listaPosti.add(p9);
        this.listaPosti.add(p10);
        System.out.println("caricamento posti tribuna "+nome+" completato");
    }
}
