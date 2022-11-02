package com.example.memessharingapp;

import static com.example.memessharingapp.R.layout.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.UrlRewriter;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public ImageView imageView;
    public Button next;
    public String currentimage=null;
    public ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        Log.d("Hello","Gautam1 Successfully");
        getmeme();
        next=findViewById(R.id.button);
        progressBar=findViewById(R.id.progress);
        next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

          progressBar.setVisibility(View.VISIBLE);
                getmeme();
            }
        });
    }
    private void getmeme(){
        Log.d("Hello","Gautam2 Successfully");
//        final TextView textView = (TextView) findViewById(R.id.text);
// ...
// Instantiate the RequestQueue.

                    Log.d("Hello","Gautam3.1 Successfully");
                    Log.d("Hello","Gautam3.2 Successfully");
        imageView=findViewById(R.id.imageView);
                    Log.d("Hello","Gautam3.3 Successfully");
//        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://meme-api.herokuapp.com/gimme";
// Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    Log.d("Hello","Gautam4 Successfully");
//                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                        String url1=null;
                    Log.d("Hello","Gautam5 Successfully");
                        try {
                            Log.d("MYAPP","Try run successfully");
                            url1 = response.getString("url");
                            currentimage=url1;
                        } catch (JSONException e) {
                            Log.d("MYAPP","Exception executed");
                            e.printStackTrace();
                        }
                    Log.d("Hello","Gautam5 Successfully");
                        Glide.with(getApplicationContext()).load(url1).listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                Log.d("Hello","Gautam6 Successfully");
                                progressBar.setVisibility(progressBar.INVISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                Log.d("Hello","Gautam7 Successfully");
                                progressBar.setVisibility(progressBar.INVISIBLE);
                                return false;
                            }
                        }).into(imageView);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                                Log.d("Hello","Gautam8 Successfully");
//                        Toast.makeText(MainActivity.this, "fail", Toast.LENGTH_SHORT).show();
                    }
                }
        );
// Add the request to the RequestQueue.
//                queue.add(jsonObjectRequest);
                MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        Log.d("Hello","Gautam9 Successfully");
    }

    public void sharememe(View view) {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,currentimage);
        Intent chooser= Intent.createChooser(intent,"Share this meme url...");
        startActivity(chooser);
    }

}