package com.example.first;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
            //   capitoleFragment = (CapitoleFragment)getSupportFragmentManager().findFragmentById(R.id.fragmentcapitole);
            //    if (capitoleFragment != null)

            capitoleFragment.updateArticleView(position);
//            else {
//                CapitoleFragment newFragment = new CapitoleFragment();
//                Bundle args = new Bundle();
//                args.putInt("position", position);
//                newFragment.setArguments(args);
//
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                //   articleFrag.updateArticleView(position);
//
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack so the user can navigate back
//                transaction.replace(R.id.linear, newFragment);
//                transaction.addToBackStack(null);
////newFragment.updateArticleView(position);
//                // Commit the transaction
//                transaction.commit();
//                String ff = "hh";
//            }
        } else if (nivel == 2) {
//            verseteFragment = (VerseteFragment)
//                    getSupportFragmentManager().findFragmentById(R.id.fragmentversete);
//            if (verseteFragment != null)
            verseteFragment.updateArticleView(position);
//            else {
//                VerseteFragment newFragment = new VerseteFragment();
//                Bundle args = new Bundle();
//                args.putInt("position", position);
//                newFragment.setArguments(args);
//
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                //   articleFrag.updateArticleView(position);
//
//                // Replace whatever is in the fragment_container view with this fragment,
//                // and add the transaction to the back stack so the user can navigate back
//                transaction.replace(R.id.linearV, newFragment);
//                transaction.addToBackStack(null);
////newFragment.updateArticleView(position);
//                // Commit the transaction
//                transaction.commit();
//                String ff = "hh";
//            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carti);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //      getSupportActionBar().setCustomView(R.layout.custom_actionbar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        //EditText et = (LinearLayout)  .getRootView().findViewById(R.id.linearLayoutVechiull);
        //   LinearLayout linearLayoutVechiull = findViewById(R.id.linearLayoutVechiul);
        //  LinearLayout linearLayoutNpull = findViewById(R.id.linearLayouttt);
        //Log.d("Name", et.getText().toString());

        // linearLayoutVechiull = findViewById(R.id.linearLayoutVechiul);
        //linearLayoutNpull = findViewById(R.id.linearLayoutNoul);

        //      bibliaInstance = BibleHelper.getInstance(Carti.this);

//        for (int i = 1; i <= 66; i++) {
//
//            Button b = new Button(this);
//            b.setText(bibliaInstance.carti[i]);
//            b.setId(bibliaInstance.booknumber[i]);
//
//            b.setOnClickListener(this);
//            b.setWidth(80);
//            b.setTextSize(20);
//            ViewCompat.setElevation(b, 10);
//            b.setBackgroundResource(R.drawable.button_carte);
//
//            //  LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//            //  layoutParams.setMargins(50, 80, 50, 50);
//            //             if (i<=39)
//            //           linearLayoutVechiull.addView(b);
////else
//            //                  linearLayoutNpull.addView(b);
//
//        }

    }


    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CartiFragment(), "CARTI");
        adapter.addFragment(new CapitoleFragment(), "CAPITOLE");
        adapter.addFragment(new VerseteFragment(), "VERSETE");
        viewPager.setAdapter(adapter);
    }

    @Override
//    public void onClick(View v) {
//        v.setBackgroundColor(Color.RED);
//        Referinta.BookNumber = v.getId();
//        Referinta.Short_name = ((Button) v).getText().toString();
//        Referinta.NrCapitole = bibliaInstance.SetNrCapitole(v.getId());
//        Intent intent = new Intent(this, CapitoleActivity.class);
//
//        startActivity(intent);
//        finish();
//
//    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(Carti.this, "Action View Expender", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(Carti.this, "Action View Collapsed", Toast.LENGTH_SHORT).show();
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
