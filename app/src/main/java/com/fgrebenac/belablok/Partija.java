package com.fgrebenac.belablok;

import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Nullable;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Partija extends RealmObject {

    String uniqueID;

    RealmList<Zapis> zapisi;
    int igraDo;
    boolean gotovaPartija;

    int miBodovi = 0;
    int viBodovi = 0;

    public Partija(){

    }

    public Partija(RealmList<Zapis> zapisi, int igraDo) {
        this.uniqueID = UUID.randomUUID().toString();
        this.zapisi = zapisi;
        this.igraDo = igraDo;
    }

    public int sumMiBodovi(RealmList<Zapis> zapisi){
        int suma = 0;
        try {
            for (Zapis z : zapisi) {
                suma += z.getMiBodovi();
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

    public int sumViBodovi(RealmList<Zapis> zapisi){
        int suma = 0;
        try {
            for (Zapis z : zapisi) {
                suma += z.getViBodovi();
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

    public RealmList<Zapis> getZapisi() {

        return zapisi;

    }

    public void setZapisi(RealmList<Zapis> zapisi) {
        this.zapisi = zapisi;
    }

    public int getIgraDo() {
        return igraDo;
    }

    public void setIgraDo(int igraDo) {
        this.igraDo = igraDo;
    }

    public boolean isGotovaPartija() {
        return gotovaPartija;
    }

    public void setGotovaPartija(boolean gotovaPartija) {
        this.gotovaPartija = gotovaPartija;
    }

    public int getMiBodovi() {
        return sumMiBodovi(zapisi);
    }

    public int getViBodovi() {
        return sumViBodovi(zapisi);
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
