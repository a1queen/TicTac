package com.example.tictac;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AddGameActivity extends AppCompatActivity {


    private static final String TAG = AddGameActivity.class.getSimpleName();
    DatabaseReference reff;
    ArrayList<String> names = new ArrayList<>();
    Game game;
    ValueEventListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        reff = FirebaseDatabase.getInstance().getReference().child("Game");
        listener = reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                names.clear();
                if (dataSnapshot.exists())
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Game post = postSnapshot.getValue(Game.class);
                        names.add(post.getName());

                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button btnAddItem = findViewById(R.id.btn_save);
        game = new Game();
        btnAddItem.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String name = ((EditText) findViewById(R.id.editTxt_name)).getText().toString();
                Log.e("moj nazwy", names.toString() + " " + name);
                if (names.contains(name) || MainActivity.user == null)
                    Toast.makeText(AddGameActivity.this, "Nie zapisano ", Toast.LENGTH_LONG).show();
                else {
                    game.setName(name);
                    game.setPlayer1(MainActivity.user);
                    game.setActive(true);
                    reff.child(name).setValue(game);
                    Toast.makeText(AddGameActivity.this, "Zapisano ", Toast.LENGTH_LONG).show();
                    Intent newActivity = new Intent(AddGameActivity.this, Waiting.class);
                    newActivity.putExtra("id", name);
                    newActivity.putExtra("multiplayer", false);
                    AddGameActivity.this.startActivity(newActivity);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (reff != null && listener != null) {
            reff.removeEventListener(listener);
        }
    }


}
