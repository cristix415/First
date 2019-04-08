package com.example.first;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;

public class DataDB {
	Connection eventoConexion;
	String name;

	public String getNameDB(Context context){
		eventoConexion = new Connection(context);

		try {
			eventoConexion.createDataBase();
		} catch (IOException e) {
		}

		if(eventoConexion.checkDataBase()){
			eventoConexion.openDataBase();
			SQLiteDatabase db = eventoConexion.getWritableDatabase();
			Cursor cursor = db.rawQuery("SELECT poemtext FROM rotext where _ID = 15 or _ID = 16", null);
			while(cursor.moveToNext()){
				name = cursor.getString(0);
			}

			eventoConexion.close();

			return name;
		}else{
			return "ERROR";
		}
	}
}
