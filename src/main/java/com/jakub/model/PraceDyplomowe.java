package com.jakub.model;

import javax.persistence.*;

/**
 * Created by lenovo on 08.06.2017.
 */
@Entity
@Table(name = "pracedyplomowe")
public class PraceDyplomowe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idpracy;
    private int iduzytkownika;
    private int idtematy;
    private byte praca;
    private String status;

    @Override
    public String toString() {
        return "PraceDyplomowe{" +
                "idpracy=" + idpracy +
                ", iduzytkownika=" + iduzytkownika +
                ", idtematy=" + idtematy +
                ", praca=" + praca +
                ", status='" + status + '\'' +
                '}';
    }

    public int getIdpracy() {
        return idpracy;
    }

    public void setIdpracy(int idpracy) {
        this.idpracy = idpracy;
    }

    public int getIduzytkownika() {
        return iduzytkownika;
    }

    public void setIduzytkownika(int iduzytkownika) {
        this.iduzytkownika = iduzytkownika;
    }

    public int getIdtematy() {
        return idtematy;
    }

    public void setIdtematy(int idtematy) {
        this.idtematy = idtematy;
    }

    public byte getPraca() {
        return praca;
    }

    public void setPraca(byte praca) {
        this.praca = praca;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
