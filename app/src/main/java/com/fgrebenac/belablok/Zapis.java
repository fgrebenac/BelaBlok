package com.fgrebenac.belablok;

import java.util.UUID;

import io.realm.RealmObject;

public class Zapis extends RealmObject {

    String uniqueID;

    int miZvanja;
    int viZvanja;
    int miIgra;
    int viIgra;

    int tkoJeZvao;

    public Zapis(){

    }

    public Zapis(int miZvanja, int viZvanja, int miIgra, int viIgra, int tkoJeZvao) {
        this.uniqueID = UUID.randomUUID().toString();
        this.miZvanja = miZvanja;
        this.viZvanja = viZvanja;
        this.miIgra = miIgra;
        this.viIgra = viIgra;
        this.tkoJeZvao = tkoJeZvao;
    }

    public int getMiZvanja() {
        return miZvanja;
    }

    public void setMiZvanja(int miZvanja) {
        this.miZvanja = miZvanja;
    }

    public int getViZvanja() {
        return viZvanja;
    }

    public void setViZvanja(int viZvanja) {
        this.viZvanja = viZvanja;
    }

    public int getMiIgra() {
        return miIgra;
    }

    public void setMiIgra(int miIgra) {
        this.miIgra = miIgra;
    }

    public int getViIgra() {
        return viIgra;
    }

    public void setViIgra(int viIgra) {
        this.viIgra = viIgra;
    }

    public int getMiBodovi() {
        if(tkoJeZvao == 1 && (miIgra + miZvanja) <= (viIgra + viZvanja)){
            return 0;
        } else if(tkoJeZvao == 2 && (miIgra + miZvanja) >= (viIgra + viZvanja)) {
            return miIgra + miZvanja + viIgra + viZvanja;
        } else {
            return miIgra + miZvanja;
        }
    }

    public int getViBodovi() {
        if(tkoJeZvao == 1 && (miIgra + miZvanja) <= (viIgra + viZvanja)){
            return miIgra + miZvanja + viIgra + viZvanja;
        } else if(tkoJeZvao == 2 && (miIgra + miZvanja) >= (viIgra + viZvanja)) {
            return 0;
        } else {
            return viIgra + viZvanja;
        }
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public int getTkoJeZvao() {
        return tkoJeZvao;
    }

    public void setTkoJeZvao(int tkoJeZvao) {
        this.tkoJeZvao = tkoJeZvao;
    }
}
