package com.example.first;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class BibleHelper extends AppCompatActivity {

    private static BibleHelper INSTANCE = null;
    public final String path = "/data/data/com.example.first/databases/";
    public final String NameDB = "VDC2016SQLite3";
    static TreeMap<Integer, String> dictShort = new TreeMap<Integer, String>();
    public String[] capitole = new String[160];

    SQLiteDatabase db;
    // other instance variables can be here

    private BibleHelper(Context context) {
        db = context.openOrCreateDatabase("/data/data/com.example.first/databases/VDC2016SQLite3", MODE_PRIVATE, null);
        Log.e("open??", String.valueOf(db.isOpen()));
        try {

            OutputStream myOutput = new FileOutputStream(path + NameDB);
            byte[] buffer = new byte[1024];
            int length;
            //  Toast.makeText(this, "inter", Toast.LENGTH_LONG).show();

            InputStream myInput = context.getAssets().open("VDC2016SQLite3");
            //   Toast.makeText(this, "11111111 ", Toast.LENGTH_LONG).show();
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            //   Toast.makeText(this, "copiat", Toast.LENGTH_LONG).show();
            myInput.close();
            myOutput.flush();
            myOutput.close();
        } catch (
                Exception e) {
            //Toast.makeText(this, "eroareeeeeeeeeee", Toast.LENGTH_LONG).show();

            e.printStackTrace();
        }
        SetCarti();
    }

    ;

    public static BibleHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new BibleHelper(context);
        }
        return (INSTANCE);
    }

    public static String getShortName(int bookNumber) {
        return dictShort.get(bookNumber);
    }

    public void SetCarti() {

        Cursor c = db.rawQuery("select * from books", null);
        c.moveToFirst();
        do {
            dictShort.put(c.getInt(c.getColumnIndex("book_number")), c.getString(c.getColumnIndex("short_name")));

        } while (c.moveToNext());

    }
    public int SetNrCapitole(int booknumber) {

        Cursor c = db.rawQuery("SELECT COUNT ( DISTINCT chapter)  FROM 'verses' where book_number=" + booknumber, null);
        c.moveToFirst();
        int count = c.getInt(0);
        c.close();
        return count;

    }

    public List<String> GetVersete(int booknumber, int capitolnumber) {

        List<String> lista = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT text  FROM 'verses' where book_number=" + booknumber + " and chapter= " + capitolnumber, null);
        c.moveToFirst();
        do {
            lista.add(c.getString(c.getColumnIndex("text")));
        } while (c.moveToNext());
        Referinta.Verset = lista.size();
        c.close();
        return lista;
    }

    public List<Referinta> Search(String cuvinte) {
        List<Referinta> lista = new ArrayList<Referinta>();

        Cursor c = db.rawQuery("SELECT *  FROM 'verses' where text LIKE \"" + "%" + cuvinte + "%" + "\"", null);
        c.moveToFirst();
        do {
            Referinta ref = new Referinta();
            ref.BookNumbe = c.getInt(c.getColumnIndex("book_number"));
            ref.Short_nam = c.getString(c.getColumnIndex("book_number"));
            ref.Capito = c.getInt(c.getColumnIndex("chapter"));
            ref.Verse = c.getInt(c.getColumnIndex("verse"));
            ref.VersetTex = c.getString(c.getColumnIndex("text"));
            lista.add(ref);
        } while (c.moveToNext());
        // Referinta.Verset = lista.size();
        c.close();


        return lista;
    }


}
