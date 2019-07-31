/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pc
 */
public class Internet {

    private int id;
    private String ime;
    private String prezime;
    private int brzina;
    private int protok;
    private int ugovor;
    private int identifikacioni_broj;

    public Internet(String ime) {
        this.ime=ime;
    }

    public Internet(int id, String ime, String prezime, int brzina, int protok, int ugovor, int identifikacioni_broj) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.brzina = brzina;
        this.protok = protok;
        this.ugovor = ugovor;
        this.identifikacioni_broj = identifikacioni_broj;
    }
    

    public Internet(String ime, String prezime, int brzina, int protok, int ugovor) {
        this.ime = ime;
        this.prezime = prezime;
        this.brzina = brzina;
        this.protok = protok;
        this.ugovor = ugovor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getBrzina() {
        return brzina;
    }

    public void setBrzina(int brzina) {
        this.brzina = brzina;
    }

    public int getProtok() {
        return protok;
    }

    public void setProtok(int protok) {
        this.protok = protok;
    }

    public int getUgovor() {
        return ugovor;
    }

    public void setUgovor(int ugovor) {
        this.ugovor = ugovor;
    }

    public int getIdentifikacioni_broj() {
        return identifikacioni_broj;
    }

    public void setIdentifikacioni_broj(int identifikacioni_broj) {
        this.identifikacioni_broj = identifikacioni_broj;
    }

    @Override
    public String toString() {
        return "Internet{" + "id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", brzina=" + brzina + ", protok=" + protok + ", ugovor=" + ugovor + ", identifikacioni_broj=" + identifikacioni_broj + '}';
    }
    
    
    
}
