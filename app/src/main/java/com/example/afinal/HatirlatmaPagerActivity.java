package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class HatirlatmaPagerActivity extends   AppCompatActivity{

        private static final String EXTRA_HATIRLATMA_ID =
                "com.bignerdranch.android.criminalintent.crime_id";
        private ViewPager mViewPager;
        private List<Hatirlatma> mHatirlatmas;

        public static Intent newIntent(Context packageContext, int hatirlatmaId) {
            Intent intent = new Intent(packageContext, HatirlatmaPagerActivity.class);
            intent.putExtra(EXTRA_HATIRLATMA_ID, hatirlatmaId);
            return intent;
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hatirlatma_pager);
            int hatirlatmaId = (int) getIntent()
                    .getSerializableExtra(EXTRA_HATIRLATMA_ID);

            mViewPager = (ViewPager) findViewById(R.id.hatirlatma_view_pager);
            mHatirlatmas = HatirlatmaLab.get(this).getHatirlatmas();
            FragmentManager fragmentManager = getSupportFragmentManager();
            mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
                @Override
                public Fragment getItem(int position) {
                    Hatirlatma hatirlatma = mHatirlatmas.get(position);
                    return HatirlatmaFragment.newInstance(hatirlatma.getId());
                }
                @Override
                public int getCount() {
                    return mHatirlatmas.size();
                }
            });

            for (int i = 0; i < mHatirlatmas.size(); i++) {
                if (mHatirlatmas.get(i).getId()==(hatirlatmaId)) {
                    mViewPager.setCurrentItem(i);
                    break;
                }
            }


        }


}
