package com.fgrebenac.belablok;


import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;



public class Igra extends RealmObject {

    String uniqueID;

    RealmList<Partija> partije;

    String igrac1;
    String igrac2;
    String igrac3;
    String igrac4;

    int suma = 0;


    int miBrojPobjeda = getBrojPobjedaMi();
    int viBrojPobjeda = getBrojPobjedaVi();

    public Igra(){

    }

    public Igra(RealmList<Partija> partije, String igrac1, String igrac2, String igrac3, String igrac4){
        this.uniqueID = UUID.randomUUID().toString();
        this.partije = partije;
        this.igrac1 = igrac1;
        this.igrac2 = igrac2;
        this.igrac3 = igrac3;
        this.igrac4 = igrac4;
    }

    public int getBrojPobjedaMi(){

        try {
            for (Partija p : partije) {
                if (p.gotovaPartija && p.getMiBodovi() > p.getViBodovi()) {
                    suma += 1;
                }
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

    public int getBrojPobjedaVi(){

        try {
            for (Partija p : partije) {
                if (p.gotovaPartija && p.getViBodovi() > p.getMiBodovi()) {
                    suma += 1;
                }
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

    public int getMiBrojPobjeda() {
        return miBrojPobjeda;
    }

    public void setMiBrojPobjeda(int miBrojPobjeda) {
        this.miBrojPobjeda = miBrojPobjeda;
    }

    public int getViBrojPobjeda() {
        return viBrojPobjeda;
    }

    public void setViBrojPobjeda(int viBrojPobjeda) {
        this.viBrojPobjeda = viBrojPobjeda;
    }

    public RealmList<Partija> getPartije() {

        return partije;
    }

    public void setPartije(RealmList<Partija> partije) {
        this.partije = partije;
    }

    public String getIgrac1() {
        return igrac1;
    }

    public void setIgrac1(String igrac1) {
        this.igrac1 = igrac1;
    }

    public String getIgrac2() {
        return igrac2;
    }

    public void setIgrac2(String igrac2) {
        this.igrac2 = igrac2;
    }

    public String getIgrac3() {
        return igrac3;
    }

    public void setIgrac3(String igrac3) {
        this.igrac3 = igrac3;
    }

    public String getIgrac4() {
        return igrac4;
    }

    public void setIgrac4(String igrac4) {
        this.igrac4 = igrac4;
    }

    public String getUniqueID() {
        return uniqueID;
    }
}
