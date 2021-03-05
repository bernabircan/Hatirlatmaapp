package com.example.afinal;

import java.util.Date;
import java.util.UUID;

public class Hatirlatma {
   public static int idSayac=1;
    private int mId;
    private String mTitle;
    private String mDetail;
    private boolean mTamamlandi;



    public Hatirlatma() {
        mId = idSayac;
        idSayac++;
    }

    public int getId() {
        return mId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        mTitle = title;
    }
    public String getDetail() {
        return mDetail;
    }
    public void setDetail(String detail) {
        mDetail = detail;
    }
    public boolean getTamamlandi() {
        return mTamamlandi;
    }
    public void setTamamlandi(boolean solved) {
        mTamamlandi = solved;
    }
}
