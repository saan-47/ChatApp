package com.hassan.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    TextView acc;
    Button signUp;
    private boolean isSigningUp = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        acc  =findViewById(R.id.login);
        signUp = findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    if(isSigningUp && username.getText().toString().isEmpty()){
                        Toast.makeText(MainActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if(isSigningUp){
                    handleSignup();
                }
                else {
                    handleLogIn();
                }
            }
        });
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSigningUp){
                    isSigningUp  =false;
                    username.setVisibility(View.GONE);
                    signUp.setText("Login");
                    acc.setText("Don't have an account? Sign up");

                }
                else {
                    isSigningUp  =true;
                    username.setVisibility(View.VISIBLE);
                    signUp.setText("Sign Up");
                    acc.setText("Already have an account? Login");

                }
            }
        });
    }
    private void handleSignup(){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "You successfully Signed Up.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Sign Up not successful try again."+ task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void handleLogIn(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, FriendsActivity.class));
                    Toast.makeText(MainActivity.this, "Congrats login successfully", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}