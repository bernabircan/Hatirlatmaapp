package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HatirlatmaListFragment  extends Fragment {

    private RecyclerView mHatirlatmaRecyclerView;
    private HatirlatmaAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hatirlatma_list, container, false);
        mHatirlatmaRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycler_view);
        mHatirlatmaRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); //sunun amacını cok bilmiyom

        updateUI(); //önemli

        return view; //view döndürülür.
    }

    @Override
    public void onResume() { //kullanıcı bir crime da değişklik yaptı geri geldi o zaman çağırılır.
        super.onResume();   //crimefragmentte işlem yaparken crimelistfragment stop oluyo crimefragmentte kapatınca crimefragmentliste donuluyo o da resume oluyo
        updateUI();
    }
    private void updateUI() {
        HatirlatmaLab hatirlatmaLab = HatirlatmaLab.get(getActivity());//getactivity ne acep neyse oluşturuluyor crimelab
        List<Hatirlatma> hatirlatmas = hatirlatmaLab.getHatirlatmas(); //crime listesi buraya veriliyor.

        if (mAdapter == null) { //güncellendi
            mAdapter = new HatirlatmaAdapter(hatirlatmas);
            mHatirlatmaRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged(); //güncelleme varsa
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) { //menuyu olustur
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_hatirlatma_list, menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_crime: //+ ya basıldıgında
                Hatirlatma hatirlatma = new Hatirlatma();
                HatirlatmaLab.get(getActivity()).addHatirlatma(hatirlatma );
                Intent intent = HatirlatmaPagerActivity
                        .newIntent(getActivity(), hatirlatma .getId());
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class HatirlatmaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDetailTextView;
        private TextView mTamalandimiTextView;
        private Hatirlatma mHatirlatma;


        public HatirlatmaHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_hatirlatma, parent, false));
            itemView.setOnClickListener(this); //bu ne ??

            mTitleTextView = (TextView) itemView.findViewById(R.id.hatirlatma_title);
            mDetailTextView = (TextView) itemView.findViewById(R.id.hatirlatma_detail);
            mTamalandimiTextView = (TextView) itemView.findViewById(R.id.tamamlandimi);

        }
        public void bind(Hatirlatma hatirlatma) {
            mHatirlatma = hatirlatma;
            mTitleTextView.setText( "id :"+ mHatirlatma.getId()+" " + mHatirlatma.getTitle() );
            mDetailTextView.setText(mHatirlatma.getDetail());
            if (hatirlatma.getTamamlandi()) {
                mTamalandimiTextView.setText("Tamamlandi");
            }else{
                mTamalandimiTextView.setText("Tamamlanmadi");
            }

        }
         @Override
       public void onClick(View view) {

            Intent intent = HatirlatmaPagerActivity.newIntent(getActivity(), mHatirlatma.getId()); //eklendi
            startActivity(intent);

        }



    }


    private class  HatirlatmaAdapter extends RecyclerView.Adapter<HatirlatmaHolder> {
        private List<Hatirlatma> mHatirlatmas;
        public HatirlatmaAdapter(List<Hatirlatma> hatirlatmas) {
            mHatirlatmas =  hatirlatmas;
        }
        @Override
        public HatirlatmaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new HatirlatmaHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(HatirlatmaHolder holder, int position) {
            Hatirlatma  hatirlatma = mHatirlatmas.get(position);
            holder.bind( hatirlatma);
        }
        @Override
        public int getItemCount() {
            return mHatirlatmas.size();
        }
    }






























}
