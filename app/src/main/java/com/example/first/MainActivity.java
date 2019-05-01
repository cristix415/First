package com.example.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;


public class MainActivity extends AppCompatActivity {
    android.support.v7.widget.SearchView searchView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (android.support.v7.widget.SearchView) myActionMenuItem.getActionView();
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Common.setVisibilityVisible(findViewById(R.id.cautareExtensie), (LinearLayout) findViewById(R.id.container));
                //OpenSearchFragment();
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

    public void intraCarti(View v) {

        Intent startIntent = new Intent(this, Carti.class);
        // startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);

    }

    public void Cauta(View v) {

        String cuvinte = searchView.getQuery().toString();
        RadioGroup rg = findViewById(R.id.radioGroup);

        int index = rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));

        SearchFragment sFrag = SearchFragment.newInstance(cuvinte, Integer.toString(index));


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.searchLayout, sFrag);
        transaction.commit();

    }

}
