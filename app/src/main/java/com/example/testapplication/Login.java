package com.example.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
     private Button callSignUp,regtologinbtn;
    private TextInputLayout regName, regPassword;
    private FirebaseAuth mfirebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mfirebaseAuth= FirebaseAuth.getInstance();
        if(mfirebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(getApplicationContext(), Homepage.class));
        }

        regName=findViewById(R.id.username);
        regPassword=findViewById(R.id.password);
        regtologinbtn=findViewById(R.id.login_btn);
        callSignUp=findViewById(R.id.signup_screen);

        progressDialog = new ProgressDialog(this);

        //attaching click listener
        regtologinbtn.setOnClickListener(this);
        callSignUp.setOnClickListener(this);
    }
    private void userLogin(){
                String username =regName.getEditText().getText().toString();
                String password =regPassword.getEditText().getText().toString();
                if (username.isEmpty()){
                    regName.setError("Please Enter Username");
                    regName.requestFocus();
                }
                else if (password.isEmpty()){
                    regPassword.setError("Please Enter Password");
                    regPassword.requestFocus();
                }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

//logging in the user
        mfirebaseAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), Homepage.class));
                        }
                    }
                });

    }


    @Override
    public void onClick(View v) {
        if(v == regtologinbtn){
            userLogin();
        }

        if(v == callSignUp){
            finish();
            startActivity(new Intent(this, SignUp.class));
        }
    }
}