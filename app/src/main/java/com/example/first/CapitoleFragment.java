package com.example.first;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.flexbox.FlexboxLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CapitoleFragment extends Fragment {
    View rootView;
    ILegatura callback;

    public CapitoleFragment() {

        // Required empty public constructor
    }

    public void setLegatura(ILegatura callback) {
        this.callback = callback;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_capitole, container, false);

        update();


        // Inflate the layout for this fragment
        return rootView;
    }

    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if (true) {
            // refresh fragment


        }
    }


    public void updateArticleView(int position) {
        Log.e("daaaaaaaaa", "reusiiit");
        update();
    }

    public void update() {
        if (rootView == null)
            rootView = getView();
        FlexboxLayout linearCapitole = (FlexboxLayout) rootView.findViewById(R.id.linear);
        linearCapitole.removeAllViews();
        //  linearLayoutNpull.addView(new Button(getActivity()));
        //  bibliaInstance = BibleHelper.getInstance(getContext());
        Referinta.Verset = 0;
        Referinta.ListVerses.clear();

        for (int i = 1; i <= Referinta.NrCapitole; i++) {
            Button b = new Button(getActivity());
            b.setText("Cap. " + i);
            b.setId(i);
            b.setBackgroundResource(R.drawable.butoane);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Referinta.Capitol = v.getId();
                    Referinta.ListVerses = BibleHelper.getInstance(getContext()).GetVersete(Referinta.BookNumber, v.getId());
                    callback.onArticleSelected(Referinta.NrCapitole, 2);

                    // Referinta.NrCapitole =  bibliaInstance.SetNrCapitole(v.getId());
                    ((Carti) getActivity()).setCurrentItem(2, true);
//
                }
            });

            linearCapitole.addView(b);
        }

    }

}
