package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {
    MenuItem item;
    private TextView t;
    TextView carteActionbar, capitolActionbar;
    Button cautaActionbar;
    Toolbar toolbar;
    android.support.v7.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //menu.findItem(R.id.action_search);

        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);

        // txtView.startAnimation(fadeOut);
        fadeIn.setDuration(4000);
        fadeIn.setFillAfter(true);
        final ScrollView scroll = findViewById(R.id.scroll_id);
        for (int i = 1; i <= Referinta.ListVerses.size(); i++) {

            // String verset = cc.getString(cc.getColumnIndex("poemtext"));
            t = new TextView(this);
            //t.setText(i + ". " + versetetext[i]);
            t.setId(i);
            //  t.setTag(i + " " + message);
            t.setTextSize(18);

            t.setText(Html.fromHtml("<html><body style='text-align:justify;'><font color=red size=1>" + "   " + i +
                    "</font> <font color=black size=10>" + Referinta.ListVerses.get(i - 1) + "</font></body></html>"));


            //      linLayout.addView(t, lpView);

            linearLayout.addView(t);
            if (i == Referinta.Verset) {
                t.startAnimation(fadeIn);

                t.getParent().requestChildFocus(t, t);
                //    scroll.scrollTo(100, (int) t.getY());
            }
        }
    }

    public void DisplayCarti(View v) {
        Intent startIntent = new Intent(this, Carti.class);
        // startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (android.support.v7.widget.SearchView) myActionMenuItem.getActionView();


        //  getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Common.setVisibilityVisible(findViewById(R.id.cautareExtensie), (LinearLayout) findViewById(R.id.container));
                OpenSearchFragment();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Common.setVisibilityGone(findViewById(R.id.cautareExtensie), (LinearLayout) findViewById(R.id.container));
                return true;
            }
        };
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnActionExpandListener(onActionExpandListener);
        return true;
    }

    void OpenSearchFragment() {

    }

    public void Cauta(View v) {

        String cuvinte = searchView.getQuery().toString();
        SearchFragment sFrag = SearchFragment.newInstance(cuvinte, "daaa");


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.searchLayout, sFrag);
        transaction.commit();

    }

}