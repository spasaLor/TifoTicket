package com.Tifoticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TifoTicket {
    private static TifoTicket tifoticket;
    private Partita partitaCorrente;
    private Partita partitaScelta;
    private Map<String,Partita> listaPartite;
    private Stadio stadio;
	
    private TifoTicket(){
         stadio=new Stadio("Furci",30);
         this.listaPartite= new HashMap<>();
         loadSettori();
    }
    public static TifoTicket getInstance(){
        if (tifoticket ==null)
            tifoticket= new TifoTicket();
        else
            System.out.println("Istanza giÃ  creata");
        return tifoticket;
    }

    public boolean inserimentoNuovaPartita(String codice,LocalDateTime data, String avversario,String tipologia){
        Partita p=listaPartite.get(codice);
        if (p==null){
             if(!controllaSovrapposizione(data)){//se non si sovrappone con altre partite
                  if(tipologia.equals("Campionato")||tipologia.equals("Coppa")||tipologia.equals("Coppa Europea")){
                    this.partitaCorrente= new Partita(codice,data,avversario,tipologia,stadio);
                    System.out.println("Partita inserita");
                  }
                  else{
                       System.err.println("ERRORE: Errore nell'immissione della tipologia.");
                       return false;
                  }
             }
             else{
                  System.err.println("ERRORE: Partita in sovrapposizione con un'altra.");
                  return false;
             }
        }
        else{
            System.err.println("ERRORE: Codice partita già presente");
            return false;
        }
        return true;
    }
    
    public boolean controllaSovrapposizione(LocalDateTime data){
         for(Map.Entry<String,Partita>entry: listaPartite.entrySet() ){
              if(entry.getValue().getData().isEqual(data))
                   return true; //si sovrappone                        
          }
          return false; 
    }
    
    public String impostaPrezzoBiglietto(String nomeSettore,float prezzoBiglietto){
        String res;
        res = partitaCorrente.impostaPrezzoBiglietti(nomeSettore, prezzoBiglietto);
        return res;
    }

     public void confermaInserimento(){
        if(partitaCorrente != null){
            this.listaPartite.put(partitaCorrente.getCodice(),partitaCorrente);
            stadio.getListaPartite().add(partitaCorrente);
            partitaCorrente=null;
            System.out.println("Inserimento partita concluso con successo.");
        }
    }
    
     public Map<String,Partita> cercaPartita(String avversario){
          Map<String,Partita> partiteTrovate=new HashMap<>();
          for(Map.Entry<String,Partita> entry : listaPartite.entrySet()){
               if(entry.getValue().equalsAvversario(avversario))
                     //partitaScelta = entry.getValue();
                    partiteTrovate.put(entry.getKey(), entry.getValue());
               else
                    System.err.println("ERRORE: Partita non trovata.");
          }
          //return partitaScelta;
          return partiteTrovate;
     }
     
     public void sceltaSettore(String nomeSettore) throws Exception{
          int posti_liberi=0;
          posti_liberi = partitaScelta.sceltaSettore(nomeSettore);
          if (posti_liberi==0)
               System.err.println("ERRORE: Settore pieno. Scegli un altro settore.");
          else{
               System.out.println("Ci sono ancora "+posti_liberi+" posti disponibili in "+nomeSettore+".");
               if(nomeSettore.contains("Tribuna"))
                    System.out.println("Elenco posti liberi in tribuna:\n"+stadio.elencoPostiDisponibili(nomeSettore).toString());
          }
     }
     
     public String sceltaPosto(int fila,int numero) throws Exception{
          return partitaScelta.sceltaPosto(fila,numero);
     }
     
     public void datiCliente(String nominativo, int eta){
          partitaScelta.datiCliente(nominativo,eta);
     }
     
     public void confermaAcquisto(){
          Biglietto bi=partitaScelta.confermaAcquisto();
          partitaScelta=null;
          System.out.println(bi);
     }
     
     public int settoreAbbonamento(String nomeSettore) throws Exception{
          Settore se=stadio.sceltaSettore(nomeSettore);
          //NEL MAIN CONTROLLO SE I POSTI SONO >0
          int posti_liberi= se.getCapienza()-stadio.getListaAbbonamenti().size()-se.getListaBiglietti().size();
          if(nomeSettore.contains("Tribuna"))
                System.out.println("Elenco posti liberi in tribuna:\n"+stadio.elencoPostiDisponibili(nomeSettore).toString());
          return posti_liberi;
     }
     
     public void datiClienteAbb(String nominativo,String CF,int eta) throws Exception{
          stadio.datiClienteAbb(nominativo,CF,eta);
     }
     
     public void postoAbbonamento(int fila,int numero) throws Exception{
          stadio.postoAbbonamento(fila,numero);
     }
     
     public Abbonamento confermaAbbonamento(){
          return stadio.confermaAbbonamento();
     }
     
    public void loadSettori(){
        Settore c1=new Curva("Curva Sud",5);
        Settore c2=new Curva("Curva Nord",5);
        Settore t1=new Tribuna("Tribuna Est",10);
        Settore t2=new Tribuna("Tribuna Ovest",10);
   
        HashMap<String,Settore> settori=new HashMap();
        settori.put("Curva Sud",c1);
        settori.put("Curva Nord",c2);
        settori.put("Tribuna Est",t1);
        settori.put("Tribuna Ovest",t2);
        stadio.setListaSettori(settori);
        System.out.println("Settori caricati");
    }

    public Partita getPartitaCorrente() {
        return partitaCorrente;
    }

     public void setPartitaCorrente(Partita partitaCorrente) {
          this.partitaCorrente = partitaCorrente;
     }

     public void setPartitaScelta(Partita partitaScelta) {
          this.partitaScelta = partitaScelta;
     }
    
    
    public Partita getPartitaScelta() {
        return partitaScelta;
    }

    public Map<String, Partita> getListaPartite() {
        return listaPartite;
    }
    
    public Partita getPartita(String codicePartita){
        Partita pa=this.listaPartite.get(codicePartita);
        return pa;
    }

     public Stadio getStadio() {
          return stadio;
     }

     public void setStadio(Stadio stadio) {
          this.stadio = stadio;
     }
    

}
