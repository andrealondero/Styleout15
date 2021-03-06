package com.example.styleout15.HomeAccess;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.styleout15.DataBase.DBAdapterLogin;
import com.example.styleout15.DataBase.Vestito;
import com.example.styleout15.FirstAppAccess.Down.Down;
import com.example.styleout15.FirstAppAccess.Top.Top;
import com.example.styleout15.FirstAppAccess.Up.Up;
import com.example.styleout15.R;
import com.example.styleout15.Utility.Preferenze;

import java.util.ArrayList;

public class FragmentHomeOne extends Fragment {

    ImageView imageView;
    ImageView imageView2;
    ImageButton btnConferma;
    ImageButton btnRifiuta;
    ImageButton btnAggiungi;
    ImageButton btnModifica;
    ImageButton btnScegliOutfitFatto;
    DBAdapterLogin db;
    public static ArrayList<Vestito> selectedOutfit;
    public static boolean CREATO;
    public static ArrayList<Integer> postFatto;

    public FragmentHomeOne() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.fragment_home_one, container, false);
        db = new DBAdapterLogin(view.getContext());
        final Preferenze pref = new Preferenze();
        final Up up = new Up();
        final Down down = new Down();
        final Top top = new Top();
        postFatto = new ArrayList<>();
        selectedOutfit = new ArrayList<>();
        CREATO = false;

        imageView = view.findViewById(R.id.upperView);
        imageView2 = view.findViewById(R.id.downView);

//        int asd = view.getContext().getResources().getIdentifier("tshirt_red", "drawable", getActivity().getPackageName());

//        db.addVestito("red", "#C40233", 1, "Vestito", "cotone", 3, R.drawable.hoodie);
//        db.addVestito("yellow", "#FFD400", 1, "Vestito", "cotone", 201, R.drawable.trousers);

        btnConferma = view.findViewById(R.id.btnConferma);
        btnRifiuta = view.findViewById(R.id.btnRifiuta);
//        btnAggiungi = view.findViewById(R.id.btnAggiungi);
        btnModifica = view.findViewById(R.id.btnModifica);
        btnScegliOutfitFatto = view.findViewById(R.id.btnOutList);

        ArrayList<Vestito> id = db.getVestitiFatti("InvernaleFeriale", pref, postFatto);
        //postFatto.add( id.get( 0 ).getPosFatto() );
        StringBuilder sb = new StringBuilder();
        //selectedOutfit = id;

        if(id!=null) {
            int i = 0;
            for (Vestito v1 : id) {

                int res = 0;
                if(Integer.parseInt(v1.getTipoVestito())>100 && Integer.parseInt(v1.getTipoVestito()) < 200)
                    res = up.getLstUp().get(up.getTypeUp().indexOf(Integer.parseInt(v1.getTipoVestito())));

                if(Integer.parseInt(v1.getTipoVestito())<100) {
                    res = top.getLstTop().get(top.getTypeTop().indexOf(Integer.parseInt(v1.getTipoVestito())));
                    imageView.setImageResource(res);
                    imageView.setBackgroundColor( Color.parseColor(v1.getColorCode()));
                }

                else {
                    res = down.getLstDown().get(down.getTypeDown().indexOf(Integer.parseInt(v1.getTipoVestito())));
                    imageView2.setImageResource(res);
                    imageView2.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                }
            }
        }

        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CREATO){
                    db.addOutfitFatto(selectedOutfit.get(0).getSelected(), selectedOutfit);
                    Toast.makeText(getContext(), "OUTFIT ADDED, PRESS AGAIN", Toast.LENGTH_SHORT).show();
                    CREATO = false;
                }
                else {
                    Fragment fragmentHomeTwo = new FragmentHomeTwo();
                    getFragmentManager().beginTransaction().replace( R.id.container, fragmentHomeTwo ).commit();
                }
            }
        });

        btnRifiuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CREATO = true;

                if(postFatto.size() >= db.getoutfitFattiCount()-1){
                    postFatto.clear();
                }
                ArrayList<Vestito> id = db.getVestiti("InvernaleFeriale", pref);
                postFatto.add(id.get(0).getPosFatto());
                StringBuilder sb = new StringBuilder();

                for(Vestito v1: id){
//                    Toast.makeText(getContext(), sb.append(v1.getId())+ " ", Toast.LENGTH_SHORT).show();
                }
                if(id!=null) {
                    for (Vestito v1 : id) {

                        int res = 0;
                        if(Integer.parseInt(v1.getTipoVestito())<100) {
                            res = top.getLstTop().get(top.getTypeTop().indexOf(Integer.parseInt(v1.getTipoVestito())));
                            imageView.setImageResource(res);
                            imageView.setBackgroundColor(Color.parseColor(v1.getColorCode()));

                            if (Integer.parseInt(v1.getTipoVestito())>100 && Integer.parseInt(v1.getTipoVestito()) < 200)
                            res = up.getLstUp().get(up.getTypeUp().indexOf(Integer.parseInt(v1.getTipoVestito())));
                            imageView.setImageResource(res);
                            imageView.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                        }

                        else {
                            res = down.getLstDown().get(down.getTypeDown().indexOf(Integer.parseInt(v1.getTipoVestito())));
                            imageView2.setImageResource(res);
                            imageView2.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                        }
                    }
                }
                selectedOutfit = id;

            }
        });

        btnModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentModificaOutfit = new FragmentModificaOutfit();
                getFragmentManager().beginTransaction().replace(R.id.container, fragmentModificaOutfit).commit();
            }
        });

        btnScegliOutfitFatto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragmentArmadio = new FragmentArmadio();
//                getFragmentManager().beginTransaction().replace(R.id.container, fragmentArmadio).commit();

 //               btnAggiungi.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
                        CREATO = false;
                        ArrayList<Vestito> id = db.getVestitiFatti("InvernaleFeriale", pref, postFatto);
                        //postFatto.add( id.get( 0 ).getPosFatto() );
                        StringBuilder sb = new StringBuilder();
                        for(Vestito i : id)
                            sb.append(i.getId()+" ");
                        Toast.makeText(view.getContext(), sb, Toast.LENGTH_SHORT).show();
                        if(id!=null) {
                            int i = 0;
                            for (Vestito v1 : id) {

                                int res = 0;
                                if(Integer.parseInt(v1.getTipoVestito())>100 && Integer.parseInt(v1.getTipoVestito()) < 200) {
                                    res = up.getLstUp().get(up.getTypeUp().indexOf(Integer.parseInt(v1.getTipoVestito())));

                                }

                                else if(Integer.parseInt(v1.getTipoVestito())<100) {
                                    res = top.getLstTop().get(top.getTypeTop().indexOf(Integer.parseInt(v1.getTipoVestito())));
                                    imageView.setImageResource(res);
                                    imageView.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                                }

                                else {
                                    res = down.getLstDown().get(down.getTypeDown().indexOf(Integer.parseInt(v1.getTipoVestito())));
                                    imageView2.setImageResource(res);
                                    imageView2.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                                }
                            }
                        }
                        selectedOutfit = id;
                    }
                });
 //           }
 //       });

        return view;
    }
}
