package com.loic.loicfirstapplication;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface URL {
    @GET("loic.json")
    Call<List<Utilisateur>> getUtilisateur();
}
