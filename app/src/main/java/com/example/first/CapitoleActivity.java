package com.example.first;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class CapitoleActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView scrollId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitole);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //  scrollId =  new ScrollView(this  );
        scrollId =  findViewById(R.id.scroll_id);


        Intent intent = getIntent();
      //  String message = intent.getStringExtra("message");    Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
//        Integer capitole = Integer.parseInt( bundle.getString("Capitole"));
//Referinta  referinta = (Referinta) intent.getSerializableExtra("referinta");
//Log.d("CArte", ((Referinta) referinta).CarteText);
        Button b;
        for (int i=1;i<=Referinta.NrCapitole;i++)
        {
            b= new Button(this);
            b.setText("Cap. " +i);
            b.setId(i);
            b.setOnClickListener(this);
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(7);
            gd.setStroke(1, 0xFF000000);
            b.setBackgroundDrawable(gd);
            linearLayout.addView(b);
        }
        scrollId.addView(linearLayout);

    }

    @Override
    public void onClick(View v) {
        v.setBackgroundColor(Color.RED);
//        Referinta.CarteId = Integer.parseInt( v.getTag().toString());
       Referinta.Capitol = String.valueOf(v.getId());
       Log.e("hhhh", Referinta.Capitol+"");
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
