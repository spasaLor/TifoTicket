package com.Tifoticket;

import java.util.Date;

public class Partita {
    private String codice;
    private Date data;
    private String avversario;
    private String tipologia;
    private Stadio st;

    public Partita(String codice, Date data, String avversario, String tipologia) {
        this.codice = codice;
        this.data = data;
        this.avversario = avversario;
        this.tipologia = tipologia;
    }

    public String impostaPrezzoBiglietti(String nomeSettore,float prezzo) throws Exception{
        String res;
        res=st.impostaPrezzo(nomeSettore,prezzo);
        return res;
    }
    
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

    @Override
    public String toString() {
        return "Partita{" +
                "codice='" + codice + '\'' +
                ", data=" + data +
                ", avversario='" + avversario + '\'' +
                ", tipologia='" + tipologia + '\'' +
                '}';
    }
}
