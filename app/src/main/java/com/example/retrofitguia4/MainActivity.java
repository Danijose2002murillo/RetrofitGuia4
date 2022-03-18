package com.example.retrofitguia4;

import Interface.JsonHolder;
import Modelo.Post;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView JsonTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JsonTexto = findViewById(R.id.JsonText);
        Getpost();
    }
    private void Getpost(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonHolder jsonHolder = retrofit.create(JsonHolder.class);
        Call<List<Post>> call = jsonHolder.getpost();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    JsonTexto.setText("Codigo: " + response.code());
                    return;
                }
                List<Post> postList = response.body();
                for (Post post:postList){
                    String mensaje = "";
                    mensaje += "id: " + post.getId() + "\n";
                    mensaje += "nombre: " + post.getNombre() + "\n";
                    mensaje += "user name: " + post.getUsername() + "\n";
                    mensaje += "email: " + post.getEmail() + "\n";
                    JsonTexto.append(mensaje);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                JsonTexto.setText(t.getMessage());
            }
        });
    }
}