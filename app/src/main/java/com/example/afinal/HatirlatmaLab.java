package com.example.afinal;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class HatirlatmaLab {

    private List<Hatirlatma> mHatirlatmas;
    private static HatirlatmaLab sHatirlatmaLab;

    public static HatirlatmaLab get(Context context) { //context ne aga
        if (sHatirlatmaLab == null) {
            sHatirlatmaLab = new HatirlatmaLab(context);
        }
        return sHatirlatmaLab;
    }

    public void addHatirlatma(Hatirlatma c) {
        mHatirlatmas.add(c);
    }

    private HatirlatmaLab(Context context) {
        mHatirlatmas = new ArrayList<>();



    }
    public List<Hatirlatma> getHatirlatmas() {
        return  mHatirlatmas;
    }
    public Hatirlatma getHatirlatma(int id) {
        for (Hatirlatma hatirlatma :  mHatirlatmas) {
            if (hatirlatma.getId()==(id)) {
                return hatirlatma;
            }
        }
        return null;
    }
    public void silHatirlatma(Hatirlatma hatirlatma){
        mHatirlatmas.remove(hatirlatma);

    }
}
