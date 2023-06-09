package com.example.letsconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SineupActivity extends AppCompatActivity {

     FirebaseAuth auth;

    EditText namebox, Email ,Password;
    Button Loginbutton,Createbutton;

    FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sineup);

        database=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        Email=findViewById(R.id.Email);
        namebox= findViewById(R.id.namebox);
        Password=findViewById(R.id.Password);

        Loginbutton=findViewById(R.id.Loginbutton);
        Createbutton=findViewById(R.id.Createbutton);
        Createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass,name;
                email=Email.getText().toString();
                pass=Password.getText().toString();
                name=namebox.getText().toString();

                User user=new User();

                user.setEmail(email);
                user.setPass(pass);
                user.setName(name);

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            database.collection("User")
                                    .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    startActivity(new Intent(SineupActivity.this, LoginActivity.class));
                                }
                            });
                            Toast.makeText(SineupActivity.this, "Account is created", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SineupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}