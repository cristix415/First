package com.example.first;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.flexbox.FlexboxLayout;

public class CapitoleActivity extends AppCompatActivity implements View.OnClickListener {
    BibleHelper bibliaInstance;
    FlexboxLayout flexLayout;
    TextView referintaText;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitole);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        flexLayout = findViewById(R.id.linearLayout);
        referintaText = findViewById(R.id.referintaText);
        referintaText.setText(Referinta.Short_name);

        bibliaInstance = BibleHelper.getInstance(CapitoleActivity.this);
        // frameLayout.setNumColumns(GridView.AUTO_FIT);
        //   frameLayout.setColumnWidth(70);

        Button b;
        for (int i = 1; i <= Referinta.NrCapitole; i++) {
            b = new Button(this);
            b.setText("Cap. " + i);
            b.setId(i);
            b.setOnClickListener(this);
            b.setBackgroundResource(R.drawable.button_carte);

            //RelativeLayout.Relati layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //relativeLayout.setMargins(100,30,100,30);
            flexLayout.addView(b);
        }

    }

    @Override
    public void onClick(View v) {
        v.setBackgroundColor(Color.RED);
//        Referinta.CarteId = Integer.parseInt( v.getTag().toString());
        Referinta.Capitol = v.getId();
        Referinta.ListVerses = bibliaInstance.GetVersete(Referinta.BookNumber, v.getId());


        Intent intent = new Intent(this, VersetActivity.class);


        startActivity(intent);
        finish();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(CapitoleActivity.this, "Action View Expender", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(CapitoleActivity.this, "Action View Collapsed", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnActionExpandListener(onActionExpandListener);
        return true;
    }
}
