package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Homepage extends AppCompatActivity{ //implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, Login.class));
        }
        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //initializing views
        //textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        //buttonLogout = (Button) findViewById(R.id.buttonLogout);

        //displaying logged in user name
        textViewUserEmail.setText("Welcome " + user.getEmail());

        //adding listener to button
        //buttonLogout.setOnClickListener(this);
                setContentView(R.layout.activity_main);
                ImageView imageView =findViewById(R.id.menu);
                TextView textView= findViewById(R.id.text);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Homepage.this, "My Menu", Toast.LENGTH_LONG).show();
                    }
                });




    }
}


   // @Override
    //public void onClick(View v) {
        //if(v == buttonLogout){
            //logging out the user
            //firebaseAuth.signOut();
            //closing activity
           // finish();
            //starting login activity
           // startActivity(new Intent(this, Login.class));
        //}
    //}
   // }