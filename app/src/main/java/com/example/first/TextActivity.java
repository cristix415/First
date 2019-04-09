package com.example.first;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TextActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        db = openOrCreateDatabase("/data/data/com.example.first/databases/robible.db", MODE_PRIVATE, null);

        Cursor cc = db.rawQuery("select * from rotext where bible=" + Referinta.CarteId + " and chapter=" + Referinta.Capitol + "", null);
        //     Toast.makeText(this, "Car: " + nrcarte + " " + "Cap." + nrcapitol + "Vers." + cc.getCount(), Toast.LENGTH_LONG).show();
        cc.moveToFirst();

        // versetetext[i]= c.getString(c.getColumnIndex("poemtext"));

        for (int i = 1; i <= cc.getCount(); i++) {

            String verset = cc.getString(cc.getColumnIndex("poemtext"));
            t = new TextView(this);
            //t.setText(i + ". " + versetetext[i]);
            t.setId(i);
            //  t.setTag(i + " " + message);
            t.setTextSize(18);


            t.setText(Html.fromHtml("<html><body style='text-align:justify;'><font color=red size=1>" + "   " + i +
                    "</font> <font color=black size=10>" + verset + "</font></body></html>"));

            cc.moveToNext();
//            linLayout.addView(t, lpView);
            linearLayout.addView(t);
        }
    }
}