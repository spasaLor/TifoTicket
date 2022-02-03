package com.Tifoticket;

import com.Tifoticket.Stadio;
import com.Tifoticket.Settore;
import com.Tifoticket.Partita;
import com.Tifoticket.Curva;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TifoTicket {
    private static TifoTicket tifoticket;
    private Partita partitaCorrente;
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

    public boolean inserimentoNuovaPartita(String codice,Date data, String avversario,String tipologia){ 
        Partita p=listaPartite.get(codice);
        if (p==null){
            this.partitaCorrente= new Partita(codice,data,avversario,tipologia);
            System.out.println("Partita inserita");
        }
        else{
            System.err.println("Partita già presente");
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
	    stadio.listaPartite.put(partitaCorrente.getCodice(),partitaCorrente);
            partitaCorrente=null;
            System.out.println("Inserimento partita concluso");
        }
    }
    
    public void loadSettori(){
        Settore c1= new Curva("Curva Sud",5);
        Settore c2= new Curva("Curva Nord",5);
        Settore t1=new Tribuna("Tribuna Est",10);
        Settore t2=new Tribuna("Tribuna Ovest",10);
   
        HashMap<String,Settore> settori=new HashMap();
        settori.put("CurvaSud",c1);
        settori.put("CurvaNord",c2);
        settori.put("TribunaEst",t1);
        settori.put("TribunaOvest",t2);
        stadio.setListaSettori(settori);
        System.out.println("Settori caricati");
    }

    public Partita getPartitaCorrente() {
        return partitaCorrente;
    }

    public Map<String, Partita> getListaPartite() {
        return listaPartite;
    }
    
    public Partita getPartita(String codicePartita){
        Partita pa=this.listaPartite.get(codicePartita);
        return pa;
    }
}
