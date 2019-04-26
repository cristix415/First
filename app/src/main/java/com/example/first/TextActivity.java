package com.example.first;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

public class TextActivity extends AppCompatActivity {

    private TextView t;
    TextView carteActionbar, capitolActionbar;
    Button cautaActionbar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(TextActivity.this, "Action View Expender", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(TextActivity.this, "Action View Collapsed", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnActionExpandListener(onActionExpandListener);
        return true;
    }

}