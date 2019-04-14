package com.example.first;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class VersetActivity extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;
    FlexboxLayout flexLayout;
    TextView referintaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verset);

        flexLayout = findViewById(R.id.linearLayout);
        referintaText = findViewById(R.id.referintaText);
        referintaText.setText(Referinta.CarteText + " " + Referinta.Capitol);

        db = openOrCreateDatabase("/data/data/com.example.first/databases/robible.db", MODE_PRIVATE, null);

        Log.e("gg", "select * from rotext where bible=" + (Referinta.CarteId) + " and chapter=" + Referinta.Capitol + "");
        //  Cursor cc  = db.rawQuery("select * from rotext where bible='"+Referinta.CarteId +"'" + "  and chapter='"+ Referinta.Capitol+"'" , null);
        Cursor cc = db.rawQuery("select * from rotext where bible=" + (Referinta.CarteId) + " and chapter=" + Referinta.Capitol + "", null);

        cc.moveToFirst();


        for (int i = 1; i <= cc.getCount(); i++) {


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
        Referinta.Verset = String.valueOf(v.getId());

        Intent intent = new Intent(this, TextActivity.class);
        // 2. put key/value data

        //   intent.putExtra("referinta", referinta );
        //  intent.putExtra("message",  capitole[1]);

        // 3. or you can add data to a bundle


        // 5. start the activity
        startActivity(intent);
        finish();
    }
}
