package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonSubmit;
    EditText editTextBird,editTextZip,editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        editTextBird = findViewById(R.id.editTextBird);
        editTextZip = findViewById(R.id.editTextZip);
        editTextName = findViewById(R.id.editTextName);

        buttonSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mybirds");

        if (view == buttonSubmit) {
//I used on purpose "inputtype = number" not integer constraints to remove the risk of misinput by user.
            String createBird = editTextBird.getText().toString();
            String createZip = editTextZip.getText().toString();
            String createName = editTextName.getText().toString();

            Bird createBirds = new Bird(createBird,createZip,createName);

            myRef.push().setValue(createBirds);
        }


        if (editTextBird.getText().toString().trim().equalsIgnoreCase("")) {
            editTextBird.setError("This field can not be blank");
        }

        if (editTextZip.getText().toString().trim().equalsIgnoreCase("")) {
            editTextZip.setError("This field can not be blank");
        }

        if (editTextName.getText().toString().trim().equalsIgnoreCase("")) {
            editTextName.setError("This field can not be blank");
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
            Toast.makeText(this, "You are already in Report page, you fool!", Toast.LENGTH_SHORT).show();


        } else if(item.getItemId() == R.id.itemSearch) {
            Intent searchIntent = new Intent(this,SearchActivity.class);
            startActivity(searchIntent);
        }

        return super.onOptionsItemSelected(item);
    }

}
