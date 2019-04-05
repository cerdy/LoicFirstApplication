package com.loic.loicfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);
        Utilisateur user = getIntent().getParcelableExtra("user");
        Picasso.with(this).load(user.avatar_url).into((ImageView)findViewById(R.id.IMG));
        ((TextView) findViewById(R.id.Nom)).setText(user.login);
        ((TextView) findViewById(R.id.URL_GITHUB)).setText(user.html_url);
    }
}
