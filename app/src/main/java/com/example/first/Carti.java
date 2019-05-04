package com.example.first;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Carti extends AppCompatActivity implements ILegatura {

    CartiFragment cartiFragment;
    CapitoleFragment capitoleFragment;
    VerseteFragment verseteFragment;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private ViewPager viewPager;
    private Toolbar toolbar;
    android.support.v7.widget.SearchView searchView;
    ;

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof CartiFragment) {
            cartiFragment = (CartiFragment) fragment;
            cartiFragment.setLegatura(this);
            //  capitoleFragment = null;
            //  verseteFragment = null;
        }
        if (fragment instanceof CapitoleFragment) {
            capitoleFragment = (CapitoleFragment) fragment;
            capitoleFragment.setLegatura(this);
            cartiFragment = null;
            //  verseteFragment = null;
        }
        if (fragment instanceof VerseteFragment) {
            verseteFragment = (VerseteFragment) fragment;
            verseteFragment.setLegatura(this);
            cartiFragment = null;
            // capitoleFragment = null;
        }
    }

    public void onArticleSelected(int position, int nivel) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
        Log.e("fragmentCarti", "Activity" + position);
        if (nivel == 1) {


            capitoleFragment.updateArticleView(position);

        } else if (nivel == 2) {

            verseteFragment.updateArticleView(position);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carti);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CartiFragment(), "CARTI");
        adapter.addFragment(new CapitoleFragment(), "CAPITOLE");
        adapter.addFragment(new VerseteFragment(), "VERSETE");


        viewPager.setAdapter(adapter);
    }


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

    public void setCurrentItem(int item, boolean smoothScroll) {
        CapitoleFragment ff = new CapitoleFragment();
        viewPager.setCurrentItem(item, smoothScroll);
    }

    void OpenSearchFragment() {


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.searchLayout, new SearchFragment());
        transaction.commit();
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
    public void Rasfoieste(View v) {



    }
}

class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private Observable mObservers = new FragmentObserver();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        mObservers.deleteObservers(); // Clear existing observers.
        if (mFragmentList.get(position) instanceof Observer)
            mObservers.addObserver((Observer) mFragmentList.get(position));
        return mFragmentList.get(position);
    }

    public void updateFragments() {
        mObservers.notifyObservers();
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
