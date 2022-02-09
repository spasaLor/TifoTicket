package com.Tifoticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stadio {
    private String nome;
    private int capienza;
    private Map<String,Settore> listaSettori;
    private ArrayList<Partita> listaPartite;
    private Settore settoreScelto;

    public Stadio(String nome, int capienza) {
        listaSettori=new HashMap<>();
        listaPartite=new ArrayList<>(); 
        this.nome = nome;
        this.capienza = capienza;
    }

    public String impostaPrezzo(String nomeSettore, float prezzoBiglietto) throws Exception {
        Settore se= listaSettori.get(nomeSettore);
        if(se != null) {
            se.setPrezzoBiglietto(prezzoBiglietto);
            return "Prezzo biglietti inserito";
        }
        else{
            throw new Exception("Settore inesistente,riprova");
        }
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
         List <Posto> listaDisponibili=tr.getListaPosti();
         for(Biglietto b : tr.getListaBiglietti()){
              Posto tmp=b.getPosto();
              if(tmp.getFila()==b.getPosto().getFila() && tmp.getNumero() == b.getPosto().getNumero())
                   listaDisponibili.remove(tmp);
         }
         return listaDisponibili;
    }
    
    public Posto sceltaPosto(int fila,int numero) throws Exception{
         Tribuna tr=(Tribuna)settoreScelto;
         return tr.sceltaPosto(fila,numero);
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
}
