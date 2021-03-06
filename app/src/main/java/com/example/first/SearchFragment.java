package com.example.first;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public List<Referinta> listaCautate;
    // TODO: Rename and change types of parameters
    private String cuvinte;
    private String tipCautare;
    private OnFragmentInteractionListener mListener;
    private View rootView;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param cuvinte Parameter 1.
     * @param tipCautare Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String cuvinte, String tipCautare) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, cuvinte);
        args.putString(ARG_PARAM2, tipCautare);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cuvinte = getArguments().getString(ARG_PARAM1);
            tipCautare = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        LinearLayout lin = (LinearLayout) rootView.findViewById(R.id.linearLayout);


        listaCautate = BibleHelper.getInstance(getContext()).Search(cuvinte, tipCautare);
        if (listaCautate.size() == 0) {
            TextView t = new TextView(getActivity());

            t.setText("Nici un rezultat.");

            t.setTextSize(18);
            lin.addView(t);
        } else {
            TextView rez = new TextView(getActivity());
            rez.setText("S-ai gasit: " + listaCautate.size() + " rezultate.");
            rez.setTextSize(18);
            lin.addView(rez);

            for (Referinta ref : listaCautate) {
                TextView t = new TextView(getActivity());

                t.setText(Html.fromHtml("<html><body style='text-align:justify;'><font color=red size=1>" + "   " + BibleHelper.getShortName(ref.BookNumbe) + " " + ref.Capito + ":" + ref.Verse +
                        "</font> <font color=black size=10>" + ref.VersetTex + "</font></body></html>"));

                t.setTextSize(18);
                lin.addView(t);
            }
        }

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
