package com.example.first;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Referinta implements Serializable {

    static int BookNumber;

    static String Short_name;
    static String Long_name;
    static int NrCapitole = 0;
    static int Capitol = 0;
    static int Verset = 0;
     static String VersetText;
    static List<String> ListVerses = new ArrayList<String>();


    public static void ClearReferinta() {
        Capitol = 0;
        Verset = 0;
        ListVerses.clear();
        BookNumber = 0;
        VersetText = "";

    }
}
