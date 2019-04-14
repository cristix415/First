package com.example.first;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class CapitoleActivity extends AppCompatActivity implements View.OnClickListener {

    FlexboxLayout flexLayout;
    TextView referintaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitole);

        flexLayout = findViewById(R.id.linearLayout);
        referintaText = findViewById(R.id.referintaText);
        referintaText.setText(Referinta.CarteText);

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
        Referinta.Capitol = String.valueOf(v.getId());
        Log.e("hhhh", Referinta.Capitol + "");
        Intent intent = new Intent(this, VersetActivity.class);
        // 2. put key/value data

        //   intent.putExtra("referinta", referinta );
        //  intent.putExtra("message",  capitole[1]);

        // 3. or you can add data to a bundle


        // 5. start the activity
        startActivity(intent);
        finish();

    }
}
