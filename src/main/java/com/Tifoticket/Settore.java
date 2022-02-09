package com.Tifoticket;

import java.util.ArrayList;
import java.util.List;

public abstract class Settore {
    public String nome;
    private int capienza;
    private float prezzoBiglietto;
    private List<Biglietto> listaBiglietti;
    
    public Settore(String nome, int capienza) {
        this.nome = nome;
        this.capienza = capienza;
        this.listaBiglietti=new ArrayList<>();
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

    public float getPrezzoBiglietto() {
        return prezzoBiglietto;
    }

    public void setPrezzoBiglietto(float prezzoBiglietto) {
        this.prezzoBiglietto = prezzoBiglietto;
    }

     public List<Biglietto> getListaBiglietti() {
          return listaBiglietti;
     }

     public void setListaBiglietti(List<Biglietto> listaBiglietti) {
          this.listaBiglietti = listaBiglietti;
     }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settore)) return false;
        Settore settore = (Settore) o;
        return capienza == settore.capienza && Float.compare(settore.prezzoBiglietto, prezzoBiglietto) == 0 && nome.equals(settore.nome);
    }

}
