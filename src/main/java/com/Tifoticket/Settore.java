package com.Tifoticket;

import java.util.Objects;

public abstract class Settore {
    public String nome;
    private int capienza;
    private float prezzoBiglietto;

    public Settore(String nome, int capienza) {
        this.nome = nome;
        this.capienza = capienza;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settore)) return false;
        Settore settore = (Settore) o;
        return capienza == settore.capienza && Float.compare(settore.prezzoBiglietto, prezzoBiglietto) == 0 && nome.equals(settore.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, capienza, prezzoBiglietto);
    }
}
