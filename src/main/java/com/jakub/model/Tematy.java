package com.jakub.model;

import javax.persistence.*;

/**
 * Created by Jakub on 01.06.2017.
 */
@Entity
@Table(name = "tematy")
public class Tematy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idtematy;
    private  String temat;
    private int idpromotora;
    private String status;
    private int idUser;

    @Override
    public String toString() {
        return "Tematy{" +
                "idtematy=" + idtematy +
                ", temat='" + temat + '\'' +
                ", idpromotora=" + idpromotora +
                ", status='" + status + '\'' +
                ", idUser=" + idUser +
                '}';
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdtematy() {
        return idtematy;
    }

    public void setIdtematy(int idtematy) {
        this.idtematy = idtematy;
    }

    public String getTemat() {
        return temat;
    }

    public void setTemat(String temat) {
        this.temat = temat;
    }

    public int getIdpromotora() {
        return idpromotora;
    }

    public void setIdpromotora(int idpromotora) {
        this.idpromotora = idpromotora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
