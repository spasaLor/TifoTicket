package com.Tifoticket;

public class Posto {
    private int fila;
    private int numero;
    //private boolean occupato;

    public Posto(int fila, int numero) {
        this.fila = fila;
        this.numero = numero;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

   /* public boolean isOccupato() {
        return occupato;
    }

    public void setOccupato(boolean occupato) {
        this.occupato = occupato;
    }
*/
    @Override
    public String toString() {
        return "fila: " + fila + " numero: " + numero;
                
    }
}
