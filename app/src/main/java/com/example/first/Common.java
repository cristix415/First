package com.example.first;

import android.view.View;
import android.widget.LinearLayout;

public class Common {


    public static void setVisibilityGone(View v, LinearLayout container) {
        v.setVisibility(View.GONE);
        container.setVisibility(View.VISIBLE);


    }

    public static void setVisibilityVisible(View v, LinearLayout container) {
        v.setVisibility(View.VISIBLE);
        container.setVisibility(View.GONE);

    }

    static void OpenSearchFragment() {


    }
}

