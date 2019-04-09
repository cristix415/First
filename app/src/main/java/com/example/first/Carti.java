package com.example.first;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class Carti extends AppCompatActivity implements View.OnClickListener {
    public final String path = "/data/data/com.example.first/databases/";
    public final String Name = "robible.db";
    public String[] carti = new String[67];
    public String[] capitole = new String[160];

    SQLiteDatabase db;
    DatabaseHelper myDb;
    DataDB data = new DataDB();
    ScrollView scrollId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carti);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
      //  scrollId =  new ScrollView(this  );
        scrollId =  findViewById(R.id.scroll_id);
        Log.e("ddddddd", (scrollId.getId()) + " ");
        db = openOrCreateDatabase("/data/data/com.example.first/databases/robible.db", MODE_PRIVATE, null);
        try{

            _copydatabase();



            carti = display();
            for (int i =1 ; i <= 66; i++) {

                Button b = new Button(this);
                b.setText(carti[i]);
                b.setId(i);
                b.setTag(i);
                b.setOnClickListener(this);
                //      linLayout.addView(b, lpView);
                b.setWidth(200);
                b.setTextSize(20);

                GradientDrawable gd = new GradientDrawable();
                gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
                gd.setCornerRadius(7);
                gd.setStroke(1, 0xFF000000);
                b.setBackgroundDrawable(gd);

                linearLayout.addView(b);
            }
            //  Toast.makeText(this, carti[0], Toast.LENGTH_LONG).show();
           // ScrollView gg;
//            ((ScrollView) findViewById(R.id.scroll_id)).addView(linearLayout);
            //setContentView(findViewById(R.id.scroll_id));
scrollId.addView(linearLayout);
//            setContentView(scrollId);
         //    this.setContentView(linearLayout, new LinearLayout.LayoutParams(
           //        LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }catch(IOException e){
            Toast.makeText(this, "eroareeeeeeeeeee", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    public void _copydatabase() throws IOException {

        OutputStream myOutput = new FileOutputStream(path + Name);
        byte[] buffer = new byte[1024];
        int length;
        InputStream myInput = getAssets().open("robible.db");
        Toast.makeText(this, "11111111 ", Toast.LENGTH_LONG).show();
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myInput.close();
        myOutput.flush();
        myOutput.close();

    }
    public String[] display()
    {
        //use cursor to keep all data
        //cursor can keep data of any data type
        Cursor c=db.rawQuery("select * from robible", null);
        // tv.setText("");
        //move cursor to first position
        c.moveToFirst();
        //fetch all data one by one
        int i=0;

        do
        {i++;


            carti[i]= c.getString(c.getColumnIndex("biblename"));
            capitole[i]= c.getString(c.getColumnIndex("chapters"));


        }while(c.moveToNext());
        return carti;
    }

    @Override
    public void onClick(View v) {
        v.setBackgroundColor(Color.RED);
Referinta.CarteId =  v.getTag().toString();
Referinta.NrCapitole = Integer.parseInt( capitole[Integer.parseInt(v.getTag().toString())]);
        Intent intent = new Intent(this, CapitoleActivity.class);
        // 2. put key/value data

     //   intent.putExtra("referinta", referinta );
        //  intent.putExtra("message",  capitole[1]);

        // 3. or you can add data to a bundle


        // 5. start the activity
        startActivity(intent);
        finish();

    }
}
