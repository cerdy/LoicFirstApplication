package com.loic.loicfirstapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private Adaptateur adaptateur;
    private ArrayList<Utilisateur> utilisateurs;
    public static String TAG = "erreur";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        retrofitCall();
        chargement();
    }
    private void chargement(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        String json = sharedPreferences.getString("utilisateur", null);
        Type type = new TypeToken<ArrayList<Utilisateur>>() {}.getType();
        utilisateurs = new Gson().fromJson(json, type);
        if(utilisateurs == null) {
            Log.i(TAG, "data null here");
            utilisateurs = new ArrayList<Utilisateur>();
            retrofitCall();
        }
        else{
            setAdaptateur();
        }
    }
    private void retrofitCall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://rly-chrono.fr/api/loic/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        URL url = retrofit.create(URL.class);
        Call<List<Utilisateur>> call = url.getUtilisateur();
        call.enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response){
                if(!response.isSuccessful()){
                    Log.i(TAG, "réponse mais pas successful");
                    return;
                }
                Log.i(TAG, "successful");
                for(Utilisateur x : response.body()){
                    utilisateurs.add(x);
                }
                sauvegarder();
                setAdaptateur();
            }
            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t){
                Log.i(TAG, "onFailure carrément:"+t.getMessage());
            }
        });
    }
    private void sauvegarder(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = new Gson().toJson(this.utilisateurs);
        editor.putString("utilisateur", json);
        editor.apply();
    }
    private void setAdaptateur(){
        ArrayList<String> speudo = speudo();
        ArrayList<String> link = link();
        this.adaptateur = new Adaptateur(speudo,link);
        this.recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.recycler.setAdapter(this.adaptateur);
        this.adaptateur.setOnItemClickListener(new Adaptateur.OnItemClickListener() {
            @Override
            public void clickAction(int position) {
                moreActivity(position);
            }
        });
    }
    private ArrayList<String> speudo(){
        ArrayList<String> x = new ArrayList<String>();
        for(Utilisateur y : this.utilisateurs){
            x.add(y.login);
        }
        return x;
    }
    private ArrayList<String> link(){
        ArrayList<String> x = new ArrayList<String>();
        for(Utilisateur y : this.utilisateurs){
            x.add(y.avatar_url);
        }
        return x;
    }
    private void moreActivity(int position){
        Intent intent = new Intent(this,SecondeActivity.class);
        intent.putExtra("user",this.utilisateurs.get(position));
        startActivity(intent);
    }
}
