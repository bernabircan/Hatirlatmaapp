package com.example.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Date;
import java.util.UUID;

public class HatirlatmaFragment extends Fragment {

    private static final String ARG_HATIRLATMA_ID = "hatirlatma_id";
    private Hatirlatma mHatirlatma;
    private HatirlatmaLab mHatirlatmaLab;
    private EditText mTitleField;
    private EditText mDetailField;
    private CheckBox mTamamlandi;
    private Button mSil;



    public static HatirlatmaFragment newInstance(int hatirlatmaId) {
        Bundle args = new Bundle(); //fragment argumanıyla ilgili
        args.putSerializable(ARG_HATIRLATMA_ID, hatirlatmaId);
        HatirlatmaFragment fragment = new HatirlatmaFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        int hatirlatmaId = (int) getArguments().getSerializable(ARG_HATIRLATMA_ID);
        mHatirlatma = HatirlatmaLab.get(getActivity()).getHatirlatma(hatirlatmaId ); //o crimeId'deki crime cekildi.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hatirlatma, container, false);//false koyunca kod kullanarak fragment yapıcam demiş oluyormuşuz.

        mTitleField = (EditText) v.findViewById(R.id.hatirlatma_title_tek);
        mTitleField.setText(mHatirlatma.getTitle()); //eklendi
        mTitleField.addTextChangedListener(new TextWatcher() { //metin değiştirildiğinde çağırılacak kod
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mHatirlatma.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDetailField = (EditText) v.findViewById(R.id.hatirlatma_detail_tek);
        mDetailField.setText(mHatirlatma.getDetail()); //eklendi
        mDetailField.addTextChangedListener(new TextWatcher() { //metin değiştirildiğinde çağırılacak kod
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mHatirlatma.setDetail(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mTamamlandi = (CheckBox) v.findViewById(R.id.Tamamlandi_tek);
        mTamamlandi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                mHatirlatma.setTamamlandi(isChecked);
            }
        });





        mSil= (Button) v.findViewById(R.id.sil);

        mSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mHatirlatmaLab= HatirlatmaLab.get(getActivity());
                mHatirlatmaLab.silHatirlatma(mHatirlatma);
                getActivity().onBackPressed();
            }

        });






        return v;
    }




































}
