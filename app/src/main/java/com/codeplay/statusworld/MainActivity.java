package com.codeplay.statusworld;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    PagerAdapter pagerAdapter;
    ModelMovies modelMovies;
    List<ModelMovies> model;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    CircleIndicator3 indicator;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        indicator = findViewById(R.id.indicator);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Movies");
        recyclerView = findViewById(R.id.recView);
        textView = findViewById(R.id.textView5);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        model = new ArrayList<>();
        viewPager2 = findViewById(R.id.ViewPager);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String title;
                String thumbnail;
                for (DataSnapshot ds: snapshot.getChildren())
                {
                    title = ds.child("title").getValue(String.class);
                    thumbnail = ds.child("thumbnail").getValue(String.class);
                    modelMovies = new ModelMovies(title,thumbnail);
                    model.add(modelMovies);
                }
                pagerAdapter = new PagerAdapter(MainActivity.this,model);
                viewPager2.setAdapter(pagerAdapter);
                textView.setText("Newly Uploaded");
                indicator.setViewPager(viewPager2);
                recyclerAdapter = new RecyclerAdapter(MainActivity.this,model);
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}