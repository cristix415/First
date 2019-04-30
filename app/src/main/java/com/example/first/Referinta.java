package com.example.first;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Referinta implements Serializable {

    static int BookNumber;
    public String Short_nam;
    static String Short_name;

    static String Long_name;
    static int NrCapitole = 0;
    static int Capitol = 0;
    static int Verset = 0;
     static String VersetText;
    static List<String> ListVerses = new ArrayList<String>();
    public String Long_nam;
    public int NrCapitol = 0;
    public int Capito = 0;
    public int Verse = 0;
    public String VersetTex;
    int BookNumbe;


    public static void ClearReferinta() {
        Capitol = 0;
        Verset = 0;
        ListVerses.clear();
        BookNumber = 0;
        VersetText = "";

    }
}
