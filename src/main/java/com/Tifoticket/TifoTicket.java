package com.Tifoticket;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
            this.partitaCorrente= new Partita(codice,data,avversario,tipologia,stadio);
            System.out.println("Partita inserita");
        }
        else{
            System.err.println("ERRORE: Partita già presente");
            return false;
        }
        return true;
    }
    
    public String impostaPrezzoBiglietto(String nomeSettore,float prezzoBiglietto) {
        String res;
        try {
            res = partitaCorrente.impostaPrezzoBiglietti(nomeSettore, prezzoBiglietto);
        } catch (Exception e) {
            return e.getMessage();
        }
        return res;
    }

     public void confermaInserimento(){
        if(partitaCorrente != null){
            this.listaPartite.put(partitaCorrente.getCodice(),partitaCorrente);
            partitaCorrente=null;
            System.out.println("Inserimento partita concluso");
        }
    }
    
     public Partita cercaPartita(String avversario){
          for(Map.Entry<String,Partita> entry : listaPartite.entrySet()){
               if(entry.getValue().equalsAvversario(avversario))
                     partitaScelta = entry.getValue();
               else
                    System.err.println("Errore: Partita non trovata");
          }
          return partitaScelta;
     }
     
     public void sceltaSettore(String nomeSettore){
         int posti_liberi=0;
         try {
              posti_liberi = partitaScelta.sceltaSettore(nomeSettore);
         } catch (Exception ex) {
              System.err.println(ex.getMessage());
         }
               if (posti_liberi==0)
                    System.err.println("Settore pieno. Scegli un altro settore.");
               else{
                    System.out.println("Ci sono ancora "+posti_liberi+" posti disponibili in "+nomeSettore+".");
                    if(nomeSettore.contains("Tribuna"))
                         System.out.println("Elenco posti liberi in tribuna:\n"+stadio.elencoPostiDisponibili(nomeSettore).toString());
               }
     }
     
     public String sceltaPosto(int fila,int numero){
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
