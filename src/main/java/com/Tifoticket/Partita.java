package com.Tifoticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Partita {
    private String codice;
    private LocalDateTime data;
    private String avversario;
    private String tipologia;
    private Biglietto bigliettoCorrente;
    private Stadio stadio;
    private List<Biglietto> listaBiglietti;

    public Partita(String codice, LocalDateTime data, String avversario, String tipologia,Stadio stadio) {
        this.codice = codice;
        this.data = data;
        this.avversario = avversario;
        this.tipologia = tipologia;
        this.stadio=stadio;
        this.listaBiglietti=new ArrayList<>();
    }

    public String impostaPrezzoBiglietti(String nomeSettore,float prezzo) throws Exception{
        String res;
        res=stadio.impostaPrezzo(nomeSettore,prezzo);
        return res;
    }
    
    public int sceltaSettore(String nomeSettore) throws Exception{
          int posti_liberi=0;
          Settore se=stadio.sceltaSettore(nomeSettore);
          posti_liberi=se.getCapienza()-se.getListaBiglietti().size();  //CAPIENZA DEL SETTORE - BIGLIETTI VENDUTI= POSTI LIBERI
          if(posti_liberi>0){
               String codiceBiglietto=UUID.randomUUID().toString().substring(0,5);
               bigliettoCorrente=new Biglietto(codiceBiglietto,this,se);
          }
          return posti_liberi;     
    }

    public String sceltaPosto(int fila,int numero){
         Posto po=null;
         String res=null;
         try {
              po=stadio.sceltaPosto(fila,numero);
              if(po!=null){
                    bigliettoCorrente.setPosto(po);
                    res="Il posto scelto è stato selezionato correttamente.";
              }
  
         } catch (Exception ex) {
              System.out.println(ex.getMessage());
         }
         return res;
    }
    
    public void datiCliente(String nominativo, int eta){ 
         bigliettoCorrente.setNominativo(nominativo);
         bigliettoCorrente.setEta(eta);
         System.out.println("Dati cliente inseriti con successo");
    }
    
    public Biglietto confermaAcquisto(){
         float prezzo=calcolaPrezzo();
         Settore se=bigliettoCorrente.getSettore();
         
         bigliettoCorrente.setPrezzo(prezzo);
         listaBiglietti.add(bigliettoCorrente);
         se.getListaBiglietti().add(bigliettoCorrente);
         
         Biglietto tmp=bigliettoCorrente;
         
         bigliettoCorrente=null;
         return tmp;
    }
    
    public float calcolaPrezzo(){
         Settore se=bigliettoCorrente.getSettore();
         int eta=bigliettoCorrente.getEta();
         float base=se.getPrezzoBiglietto();
         float prezzo=0;
         
         if(eta<16 && this.tipologia.equals("Campionato"))
              prezzo= base - ((base *15)/100);
         else if(eta<16 && this.tipologia.equals("Coppa Nazionale"))
              prezzo= base - ((base * 25)/100);
         else if(eta<16 && this.tipologia.equals("Coppa Continentale"))
              prezzo= base + ((base *15)/100);
         else if(eta>65 && this.tipologia.equals("Campionato"))
              prezzo= base - ((base *15)/100);
         else if(eta>65 && this.tipologia.equals("Coppa Nazionale"))
              prezzo= base - ((base * 25)/100);
         else if(eta>65 && this.tipologia.equals("Coppa Continentale"))
              prezzo= base + ((base *15)/100);
         else
              prezzo=base;
         return prezzo;
    }
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getAvversario() {
        return avversario;
    }

    public void setAvversario(String avversario) {
        this.avversario = avversario;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

     public Biglietto getBigliettoCorrente() {
          return bigliettoCorrente;
     }

     public void setBigliettoCorrente(Biglietto bigliettocorrente) {
          this.bigliettoCorrente = bigliettocorrente;
     }

     public Stadio getStadio() {
          return stadio;
     }

     public void setStadio(Stadio stadio) {
          this.stadio = stadio;
     }

     public List<Biglietto> getListaBiglietti() {
          return listaBiglietti;
     }

     public void setListaBiglietti(List<Biglietto> listaBiglietti) {
          this.listaBiglietti = listaBiglietti;
     }
    
    @Override
    public String toString() {
        return "Furci vs " + avversario +", partita di: " + tipologia 
                +" del "+ data.getDayOfMonth()+"/"+data.getMonth()+"/"+data.getYear()+" Orario: "+data.getHour()+":"+data.getMinute();
    }
    
    public boolean equalsAvversario(String avv){
        return (this.avversario.equals(avv));
    }
}