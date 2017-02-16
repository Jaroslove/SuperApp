package ru.diplom.ru.superapp.model;

import java.sql.Timestamp;

/**
 * Created by 1 on 16.02.2017.
 */

public class Event {
    private int id;
    private String name;
    private Timestamp date;
    private int idUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return name+" "+date;
    }
}
