package com.example.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class CartiFragment extends Fragment {
    OnHeadlineSelectedListener callback;
    LinearLayout linearLayoutVechiul, linearLayoutNoul;
    BibleHelper bibliaInstance;


    public CartiFragment() {
        // Required empty public constructor
    }

    public void setOnHeadlineSelectedListener(OnHeadlineSelectedListener callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // this.container = container;
        // ListView lv = (ListView)YourView.findViewById(R.id.baustellenverwaltung_listview);
        View rootView = inflater.inflate(R.layout.fragment_carti, container, false);
        // View rootVieww = inflater.inflate(R.layout.fragment_capitole, container, false);
        //     RelativeLayout rl = (RelativeLayout) rootView.findViewById(R.id.daa);
        //     RelativeLayout rl1 = (RelativeLayout) rootView.findViewById(R.id.daa);
        linearLayoutVechiul = (LinearLayout) rootView.findViewById(R.id.linearLayoutVechiul);
        linearLayoutNoul = (LinearLayout) rootView.findViewById(R.id.linearLayoutNoul);
        //   LinearLayout lll = (LinearLayout) rootVieww.findViewById(R.id.linearCapitole);
        //  linearLayoutNpull.addView(new Button(getActivity()));
        bibliaInstance = BibleHelper.getInstance(getContext());
        //    Button bbbb = new Button(getActivity());
        //   lll.addView(bbbb);
        for (int i = 1; i <= 66; i++) {

            Button b = new Button(getActivity());
            b.setText(bibliaInstance.carti[i]);
            b.setId(bibliaInstance.booknumber[i]);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Referinta.BookNumber = v.getId();
                    Referinta.Short_name = ((Button) v).getText().toString();
                    Referinta.NrCapitole = bibliaInstance.SetNrCapitole(v.getId());
                    callback.onArticleSelected(Referinta.NrCapitole);
                    ((Carti) getActivity()).setCurrentItem(1, true);

//
                }
            });

            if (i <= 39)
                linearLayoutVechiul.addView(b);
            else
                linearLayoutNoul.addView(b);

        }
//nnn();

        return rootView;
    }

    public void onClick(View v) {
//        v.setBackgroundColor(Color.RED);
//        Referinta.BookNumber = v.getId();
//        Referinta.Short_name = ((Button) v).getText().toString();
//        Referinta.NrCapitole = bibliaInstance.SetNrCapitole(v.getId());
//        Intent intent = new Intent(getContext(), CapitoleActivity.class);
//
//        startActivity(intent);
        // finish();

    }

    public void nnn() {
        Button b = new Button(getActivity());
        View rootView = getView();
        LinearLayout linearCapitole = (LinearLayout) rootView.findViewById(R.id.linearLayoutNoul);
        linearCapitole.addView(b);
    }

    // This interface can be implemented by the Activity, parent Fragment,
    // or a separate test implementation.
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
}
