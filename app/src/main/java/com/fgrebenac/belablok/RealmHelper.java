package com.fgrebenac.belablok;

import android.widget.ArrayAdapter;

//import com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.PartijaUTroje;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm){
        this.realm = realm;
    }

    public void novaIgra(final Igra igra){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(igra);
            }
        });
    }

//    public void novaIgraUTroje(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje igraUTroje){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealm(igraUTroje);
//            }
//        });
//    }

    public RealmList<Igra> sveIgre(){
        RealmList<Igra> sveIgre = new RealmList<>();
        RealmResults<Igra> results = realm.where(Igra.class).findAll();

        for(Igra z : results){
            sveIgre.add(z);
        }
        return sveIgre;
    }

//    public RealmList<com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje> sveIgreUTroje(){
//        RealmList<com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje> sveIgreUTroje = new RealmList<>();
//        RealmResults<com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje> results = realm.where(com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje.class).findAll();
//
//        for(com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje igra : results){
//            sveIgreUTroje.add(igra);
//        }
//        return sveIgreUTroje;
//    }

    public void novaPartija(final Igra igra, final Partija partija){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                igra.getPartije().add(partija);
            }
        });
    }

//    public void novaPartijaUTroje(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje igraUTroje, final PartijaUTroje partijaUTroje){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                igraUTroje.getPartijeUTroje().add(partijaUTroje);
//            }
//        });
//    }

    public RealmList<Partija> svePartije(Igra igra){
        RealmList<Partija> svePartije = igra.getPartije();

        return svePartije;
    }

//    public RealmList<PartijaUTroje> svePartijeUTroje(com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje igraUTroje){
//        RealmList<PartijaUTroje> svePartije = igraUTroje.getPartijeUTroje();
//
//        return svePartije;
//    }

    public void dodajZapis(final Zapis zapis, final Partija partija){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                partija.getZapisi().add(zapis);

                if(partija.miBodovi >= partija.igraDo || partija.viBodovi >= partija.igraDo){
                    partija.gotovaPartija = true;
                }
            }
        });
    }

//    public void dodajZapisUTroje(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.ZapisUTroje zapisUTroje, final PartijaUTroje partijaUTroje){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                partijaUTroje.getZapisiUTroje().add(zapisUTroje);
//
//                if(partijaUTroje.getPlayer1bodovi() > partijaUTroje.getPlayer2bodovi() &&
//                        partijaUTroje.getPlayer1bodovi() > partijaUTroje.getPlayer3bodovi() ||
//                        partijaUTroje.getPlayer2bodovi() > partijaUTroje.getPlayer1bodovi() &&
//                                partijaUTroje.getPlayer2bodovi() > partijaUTroje.getPlayer3bodovi() ||
//                        partijaUTroje.getPlayer3bodovi() > partijaUTroje.getPlayer1bodovi() &&
//                                partijaUTroje.getPlayer3bodovi() > partijaUTroje.getPlayer2bodovi()){
//
//                    partijaUTroje.gotovaPartija = true;
//
//                }
//            }
//        });
//    }

    public void updateZapis(final Zapis zapis, final int miZvanja, final int viZvanja, final int miIgra, final int viIgra,
                            final int tkoJeZvao){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                zapis.miZvanja = miZvanja;
                zapis.viZvanja = viZvanja;
                zapis.miIgra = miIgra;
                zapis.viIgra = viIgra;
                zapis.tkoJeZvao = tkoJeZvao;
            }
        });
    }

//    public void updateZapisUTroje(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.ZapisUTroje zapisUTroje, final int player1zvanja, final int player2zvanja, final int player3zvanja,
//                                  final int player1igra, final int player2igra, final int player3igra, final int tkoJeZvao){
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                zapisUTroje.player1zvanja = player1zvanja;
//                zapisUTroje.player2zvanja = player2zvanja;
//                zapisUTroje.player3zvanja = player3zvanja;
//                zapisUTroje.player1igra = player1igra;
//                zapisUTroje.player2igra = player2igra;
//                zapisUTroje.player3igra = player3igra;
//                zapisUTroje.tkoJeZvao = tkoJeZvao;
//            }
//        });
//
//    }

    public RealmList<Zapis> sviZapisi(Partija partija){
        RealmList<Zapis> sviZapisi = partija.getZapisi();

        return sviZapisi;
    }

//    public RealmList<com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.ZapisUTroje> sviZapisiUTroje(PartijaUTroje partijaUTroje){
//        RealmList<com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.ZapisUTroje> sviZapisiUTroje = partijaUTroje.getZapisiUTroje();
//
//        return sviZapisiUTroje;
//    }

    public void deleteIgra(final Igra igra){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                igra.deleteFromRealm();
            }
        });
    }

    public void deleteIgraUTroje(final Igra igraUTroje){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                igraUTroje.deleteFromRealm();
            }
        });
    }

    public void deletePartija(final Partija partija){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                partija.deleteFromRealm();
            }
        });
    }

//    public void deletePartijaUTroje(final PartijaUTroje partijaUTroje){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                partijaUTroje.deleteFromRealm();
//            }
//        });
//    }

    public void setGotovaPartija(final Partija partija, final boolean gotova){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                partija.setGotovaPartija(gotova);
            }
        });
    }

//    public void setGotovaPartijaUTroje(final PartijaUTroje partijaUTroje, final boolean gotova){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                partijaUTroje.setGotovaPartija(gotova);
//            }
//        });
//    }

    public void deleteZapis(final Zapis zapis){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                zapis.deleteFromRealm();
            }
        });
    }

//    public void deleteZapisUTroje(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.ZapisUTroje zapisUTroje){
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                zapisUTroje.deleteFromRealm();
//            }
//        });
//    }

    public int getBrojPobjedaMi(final Igra igra){
        int suma = 0;
        try {
            for (Partija p : igra.getPartije()) {
                if (p.gotovaPartija && p.getMiBodovi() > p.getViBodovi()) {
                    suma += 1;
                }
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

    public int getBrojPobjedaVi(final Igra igra){
        int suma = 0;
        try {
            for (Partija p : igra.getPartije()) {
                if (p.gotovaPartija && p.getMiBodovi() < p.getViBodovi()) {
                    suma += 1;
                }
            }
        }catch (NullPointerException e){
            return 0;
        }
        return suma;
    }

//    public int getPlayer1brojPobjeda(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje igra) {
//        int suma = 0;
//        try{
//            for(PartijaUTroje partija : igra.getPartijeUTroje()){
//                if(partija.gotovaPartija && partija.getPlayer1bodovi() > partija.getPlayer2bodovi() &&
//                        partija.getPlayer1bodovi() > partija.getPlayer3bodovi()){
//                    suma += 1;
//                }
//            }
//        }catch (NullPointerException e){
//            return 0;
//        }
//
//        return suma;
//    }

//    public int getPlayer2brojPobjeda(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje igra) {
//        int suma = 0;
//        try{
//            for(PartijaUTroje partija : igra.getPartijeUTroje()){
//                if(partija.gotovaPartija && partija.getPlayer2bodovi() > partija.getPlayer1bodovi() &&
//                        partija.getPlayer2bodovi() > partija.getPlayer3bodovi()){
//                    suma += 1;
//                }
//            }
//        }catch (NullPointerException e){
//            return 0;
//        }
//
//        return suma;
//    }

//    public int getPlayer3brojPobjeda(final com.fgrebenac.belablok.com.fgrebenac.belablok.igrautroje.IgraUTroje igra) {
//        int suma = 0;
//        try{
//            for(PartijaUTroje partija : igra.getPartijeUTroje()){
//                if(partija.gotovaPartija && partija.getPlayer3bodovi() > partija.getPlayer1bodovi() &&
//                        partija.getPlayer3bodovi() > partija.getPlayer2bodovi()){
//                    suma += 1;
//                }
//            }
//        }catch (NullPointerException e){
//            return 0;
//        }
//
//        return suma;
//    }

}
