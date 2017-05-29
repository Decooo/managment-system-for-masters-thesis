package com.jakub.model;

import javax.persistence.*;

/**
 * Created by Jakub on 29.05.2017.
 */
@Entity
@Table(name = "wiadomosci")
public class Wiadomosci {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idwiadomosci;
    private int idnadawcy;
    private int idadresata;
    private String temat;
    private String tresc;

    @Override
    public String toString() {
        return "Wiadomosci{" +
                "idwiadomosci=" + idwiadomosci +
                ", idnadawcy=" + idnadawcy +
                ", idadresata=" + idadresata +
                ", temat='" + temat + '\'' +
                ", tresc='" + tresc + '\'' +
                '}';
    }

    public int getIdwiadomosci() {
        return idwiadomosci;
    }

    public void setIdwiadomosci(int idwiadomosci) {
        this.idwiadomosci = idwiadomosci;
    }

    public int getIdnadawcy() {
        return idnadawcy;
    }

    public void setIdnadawcy(int idnadawcy) {
        this.idnadawcy = idnadawcy;
    }

    public int getIdadresata() {
        return idadresata;
    }

    public void setIdadresata(int idadresata) {
        this.idadresata = idadresata;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
