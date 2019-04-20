package com.example.first;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class VersetActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    FlexboxLayout flexLayout;
    TextView referintaText;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verset);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        flexLayout = findViewById(R.id.linearLayout);
        referintaText = findViewById(R.id.referintaText);
        referintaText.setText(Referinta.Short_name + " " + Referinta.Capitol);


        //db = openOrCreateDatabase("/data/data/com.example.first/databases/robible.db", MODE_PRIVATE, null);

        //  Log.e("gg", "select * from rotext where bible=" + (Referinta.BookNumber) + " and chapter=" + Referinta.Capitol + "");
        //  Cursor cc  = db.rawQuery("select * from rotext where bible='"+Referinta.CarteId +"'" + "  and chapter='"+ Referinta.Capitol+"'" , null);
        //  Cursor cc = db.rawQuery("select * from rotext where bible=" + (Referinta.BookNumber) + " and chapter=" + Referinta.Capitol + "", null);

        // cc.moveToFirst();


        for (int i = 1; i <= Referinta.Verset; i++) {


            Button b = new Button(this);
            b.setText(String.valueOf(i));
            b.setId(i);

            b.setWidth(200);
            b.setTextSize(20);

            b.setOnClickListener(this);

            b.setBackgroundResource(R.drawable.button_carte);


            flexLayout.addView(b);

        }

    }

    @Override
    public void onClick(View v) {
        v.setBackgroundColor(Color.RED);
        Referinta.Verset = v.getId();

        Intent intent = new Intent(this, TextActivity.class);
        // 2. put key/value data

        //   intent.putExtra("referinta", referinta );
        //  intent.putExtra("message",  capitole[1]);

        // 3. or you can add data to a bundle


        // 5. start the activity
        startActivity(intent);
        finish();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(VersetActivity.this, "Action View Expender", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(VersetActivity.this, "Action View Collapsed", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setOnActionExpandListener(onActionExpandListener);
        return true;
    }
}
