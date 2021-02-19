package com.example.atomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class GuestRegActivity extends AppCompatActivity {

    EditText personName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_reg);
        findViewById(R.id.guest_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personName = findViewById(R.id.personName);
                if(personName.getText().toString().isEmpty()){
                    Toast.makeText(GuestRegActivity.this, "Enter a Valid Name", Toast.LENGTH_SHORT).show();
                }
                else {
                    signin();
                }
            }
        });

    }

    private void signin() {
        MainActivity.mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(GuestRegActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}