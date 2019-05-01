package com.example.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Map;


public class CartiFragment extends Fragment {
    ILegatura callback;
    LinearLayout linearLayoutVechiul, linearLayoutNoul;
    BibleHelper bibliaInstance;


    public CartiFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_carti, container, false);
        linearLayoutVechiul = (LinearLayout) rootView.findViewById(R.id.linearLayoutVechiul);
        linearLayoutNoul = (LinearLayout) rootView.findViewById(R.id.linearLayoutNoul);
        bibliaInstance = BibleHelper.getInstance(getContext());
        Referinta.NrCapitole = 0;


        for (Map.Entry<Integer, String> entry : BibleHelper.dictShort.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            //         int buttonStyle = R.style.butoane;
            //      Button b = new Button(new ContextThemeWrapper(getActivity(), buttonStyle), null, buttonStyle);

            Button b = new Button(getActivity());
            b.setText(value);
            b.setId(key);
            b.setBackgroundResource(R.drawable.butoane);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Referinta.BookNumber = v.getId();
                    Referinta.Short_name = ((Button) v).getText().toString();
                    Referinta.NrCapitole = bibliaInstance.SetNrCapitole(v.getId());
                    callback.onArticleSelected(Referinta.NrCapitole, 1);
                    ((Carti) getActivity()).setCurrentItem(1, true);

//
                }
            });

            if (key <= 460)
                linearLayoutVechiul.addView(b);
            else
                linearLayoutNoul.addView(b);

        }
        return rootView;
    }


    // This interface can be implemented by the Activity, parent Fragment,
    // or a separate test implementation.
    //   public interface OnHeadlineSelectedListener {
    //     public void onArticleSelected(int position);
    //}
}
