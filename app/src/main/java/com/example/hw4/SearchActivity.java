package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonSearch;
    EditText editTextZipSearch;
    TextView textViewBird,textViewName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);

        editTextZipSearch = findViewById(R.id.editTextZipSearch);
        textViewBird = findViewById(R.id.textViewBird);
        textViewName = findViewById(R.id.textViewName);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mybirds");

        if (view == buttonSearch) {

            String findZip = editTextZipSearch.getText().toString();

            myRef.orderByChild("zip").equalTo(findZip).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    // String findKey = dataSnapshot.getKey();
                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findBird = foundBird.bird;
                    String findName = foundBird.name;

                    textViewBird.setText(findBird);
                    textViewName.setText(findName);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        if (editTextZipSearch.getText().toString().trim().equalsIgnoreCase("")) {
            editTextZipSearch.setError("This field can not be blank");
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemRegister){
            Intent registerIntent = new Intent(this,MainActivity.class);
            startActivity(registerIntent);

        } else if(item.getItemId() == R.id.itemSearch) {
            Toast.makeText(this, "You are already in Search page, you fool!", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
