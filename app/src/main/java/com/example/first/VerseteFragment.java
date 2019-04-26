package com.example.first;


import android.content.Intent;
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
public class VerseteFragment extends Fragment {
    View rootView;
    ILegatura callback;

    public VerseteFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_versete, container, false);

        update();


        // Inflate the layout for this fragment
        return rootView;
    }

    public void updateArticleView(int position) {
        Log.e("daaaaaaaaa", "reusiiit");
        update();
    }

    public void update() {
        if (rootView == null)
            rootView = getView();
        FlexboxLayout linearVersete = (FlexboxLayout) rootView.findViewById(R.id.linearV);
        //  linearLayoutNpull.addView(new Button(getActivity()));
        //  bibliaInstance = BibleHelper.getInstance(getContext());

        for (int i = 1; i <= Referinta.ListVerses.size(); i++) {
            Button b = new Button(getActivity());
            b.setText(" " + i);
            b.setId(i);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //     Referinta.BookNumber = v.getId();
                    //      Referinta.Short_name = ((Button) v).getText().toString();
                    // Referinta.NrCapitole =  bibliaInstance.SetNrCapitole(v.getId());
                    Referinta.Verset = v.getId();

                    Intent intent = new Intent(getContext(), TextActivity.class);
                    // 2. put key/value data

                    //   intent.putExtra("referinta", referinta );
                    //  intent.putExtra("message",  capitole[1]);

                    // 3. or you can add data to a bundle


                    // 5. start the activity
                    startActivity(intent);
                    // finish();

                }
            });

            linearVersete.addView(b);
        }

    }


}
