package com.example.afinal;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class HatirlatmaActivity  extends  SingleFragmentActivity{
    private static final String EXTRA_HATIRLATMA_ID =
            "com.bignerdranch.android.criminalintent.crime_id";


    public static Intent newIntent(Context packageContext, int crimeId) {
        Intent intent = new Intent(packageContext, HatirlatmaActivity.class);
        intent.putExtra(EXTRA_HATIRLATMA_ID , crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() { //fragment argumanıyla ilgili


        int hatirlatmaId = (int) getIntent()
                .getSerializableExtra(EXTRA_HATIRLATMA_ID );
        return HatirlatmaFragment.newInstance(hatirlatmaId);



    }
}

