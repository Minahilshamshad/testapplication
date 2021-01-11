package com.example.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button callLogin;
    private TextInputLayout regName, regUsername, regNumber;
    TextInputEditText regPassword, regEmail;
    private Button regbtn, regtologinbtn;
    private ProgressDialog progressDialog;
    private FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initializing firebase auth object
        mfirebaseAuth = FirebaseAuth.getInstance();


        //if getCurrentUser does not returns null
        if (mfirebaseAuth.getCurrentUser() != null) {
            //that means user is already logged in so close this activity
            finish();
            startActivity(new Intent(getApplicationContext(), Homepage.class));
        }

        //initializing views
        regName = findViewById(R.id.name);
        regUsername = findViewById(R.id.username);
        regPassword = findViewById(R.id.password);
        regEmail = findViewById(R.id.email);
        regNumber = findViewById(R.id.phone);
        regtologinbtn = findViewById(R.id.login_screen);
        regbtn = findViewById(R.id.login_btn);
        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        regbtn.setOnClickListener(this);
        regtologinbtn.setOnClickListener(this);
    }


    private void registerUser() {
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        final String email = regEmail.getText().toString();
        final String password = regPassword.getText().toString();
        String phone = regNumber.getEditText().getText().toString();
        if (name.isEmpty()) {
            regName.setError("Please Enter Username");
            regName.requestFocus();
        } else if (username.isEmpty()) {
            regUsername.setError("Please Username");
            regUsername.requestFocus();
        } else if (email.isEmpty()) {
            regEmail.setError("Please Enter Your Email");
            regEmail.requestFocus();
        } else if (password.isEmpty()) {
            regPassword.setError("Please Enter Password");
            regPassword.requestFocus();
        } else if (phone.isEmpty()) {
            regNumber.setError("Please Enter your Number");
            regNumber.requestFocus();
        }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        mfirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), Homepage.class));
                        } else {
                            //display some message here
                            Toast.makeText(SignUp.this, "Registration Error", Toast.LENGTH_LONG).show();
                            Log.d("HAXX", task.getException().getMessage());
                            Log.d("HAXX", "Email : " + email);
                            Log.d("HAXX", "Passw : " + password);
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == regbtn) {
            registerUser();
        }

        if (v == regtologinbtn) {
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, Login.class));
        }

    }
}