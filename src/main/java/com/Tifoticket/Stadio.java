package com.Tifoticket;

import com.Tifoticket.Settore;
import com.Tifoticket.Partita;
import java.util.ArrayList;
import java.util.HashMap;

public class Stadio {
    private String nome;
    private int capienza;
    private HashMap<String,Settore> listaSettori;
    private ArrayList<Partita> listapartite;

    public Stadio(String nome, int capienza) {
        this.nome = nome;
        this.capienza = capienza;
    }

    public String impostaPrezzo(String nomeSettore, float prezzoBiglietto) throws Exception {
        Settore se= listaSettori.get("nomeSettore");
        if(se != null) {
            se.setPrezzoBiglietto(prezzoBiglietto);
            return "Prezzo biglietti inserito";
        }
        else{
            throw new Exception("Settore inesistente,riprova");
        }
    }

    public HashMap<String, Settore> getListaSettori() {
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

    public ArrayList<Partita> getListapartite() {
        return listapartite;
    }  
    
    public void setListapartite(ArrayList<Partita> listapartite) {
        this.listapartite = listapartite;
    }
}
