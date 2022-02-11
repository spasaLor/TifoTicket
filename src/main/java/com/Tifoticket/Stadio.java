package com.Tifoticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Stadio {
    private String nome;
    private int capienza;
    private Map<String,Settore> listaSettori;
    private ArrayList<Partita> listaPartite;
    private Settore settoreScelto;
    private Abbonamento abbonamentoCorrente;
    private List<Abbonamento> listaAbbonamenti;

    public Stadio(String nome, int capienza) {
        listaSettori=new HashMap<>();
        listaPartite=new ArrayList<>(); 
        this.nome = nome;
        this.capienza = capienza;
        this.listaSettori=new HashMap<>();
        this.listaPartite= new ArrayList<>();
        this.listaAbbonamenti=new ArrayList<>();
    }

    public String impostaPrezzo(String nomeSettore, float prezzoBiglietto){
        Settore se= listaSettori.get(nomeSettore);
        se.setPrezzoBiglietto(prezzoBiglietto);
        return "Prezzo biglietti inserito";
    }
    
    public Settore sceltaSettore(String nomeSettore) throws Exception{
         if(listaSettori.containsKey(nomeSettore)){
              settoreScelto=listaSettori.get(nomeSettore);
              return settoreScelto;
         }
         else
              throw new Exception("ERRORE. Settore non trovato");
    }

    public List<Posto> elencoPostiDisponibili(String nomeSettore){
         Tribuna tr= (Tribuna)listaSettori.get(nomeSettore);
         List <Posto> listaDisponibili=tr.elencoPostiDisponibili();
         for(Abbonamento a : this.getListaAbbonamenti()){
              Posto tmp=a.getPosto();
              if(tmp.getFila()==a.getPosto().getFila() && tmp.getNumero() == a.getPosto().getNumero())
                   listaDisponibili.remove(tmp);
         }
         return listaDisponibili;
    }
    
    public Posto sceltaPosto(int fila,int numero) throws Exception{
         Tribuna tr=(Tribuna)settoreScelto;
         return tr.sceltaPosto(fila,numero);
    }
    
    public void datiClienteAbb(String nominativo,String CF,int eta) throws Exception{
          for(Abbonamento a: listaAbbonamenti){
              if(a.getCF().equals(CF))
                   throw new Exception("A questo codice fiscale è già associato un abbonamento valido.");
         }
         abbonamentoCorrente=new Abbonamento(nominativo,eta,settoreScelto);
         abbonamentoCorrente.setCF(CF);
         
         float prezzo=settoreScelto.calcolaPrezzoAbb(abbonamentoCorrente);
         abbonamentoCorrente.setPrezzo(prezzo);
         
         String codice=UUID.randomUUID().toString().substring(0,5);
         abbonamentoCorrente.setCodice(codice);
         
         for(Partita p: listaPartite){
              if(p.getTipologia().equals("Campionato"))
                   abbonamentoCorrente.getListaPartiteValide().add(p);
         }
    }
    
    public Posto postoAbbonamento(int fila,int numero) throws Exception{
          Tribuna tr=(Tribuna)settoreScelto;
          Posto po=tr.postoAbbonamento(fila,numero);
          abbonamentoCorrente.setPosto(po);
          return po;
     }
    
    public Abbonamento confermaAbbonamento(){
         this.listaAbbonamenti.add(abbonamentoCorrente);
         Abbonamento tmp=abbonamentoCorrente;
         abbonamentoCorrente=null;
         return tmp;
    }
    
    public Map<String, Settore> getListaSettori() {
        return listaSettori;
    }

    public void setListaSettori(HashMap<String, Settore> listaSettori) {
        this.listaSettori = listaSettori;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public ArrayList<Partita> getListaPartite() {
        return listaPartite;

    }  
    
    public void setListaPartite(ArrayList<Partita> listaPartite) {
        this.listaPartite = listaPartite;
    }

     public List<Abbonamento> getListaAbbonamenti() {
          return listaAbbonamenti;
     }

     public void setListaAbbonamenti(List<Abbonamento> listaAbbonamenti) {
          this.listaAbbonamenti = listaAbbonamenti;
     }

     public Abbonamento getAbbonamentoCorrente() {
          return abbonamentoCorrente;
     }

     public void setAbbonamentoCorrente(Abbonamento abbonamentoCorrente) {
          this.abbonamentoCorrente = abbonamentoCorrente;
     }

     public Settore getSettoreScelto() {
          return settoreScelto;
     }

     public void setSettoreScelto(Settore settoreScelto) {
          this.settoreScelto = settoreScelto;
     }
    
}
