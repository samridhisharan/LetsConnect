package com.example.letsconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText Email ,Password ;
    Button Loginbutton,Createbutton;

    FirebaseAuth auth;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait...");

        auth=FirebaseAuth.getInstance() ;

        Email=findViewById(R.id.Email);
                Password=findViewById(R.id.Password);

                        Loginbutton=findViewById(R.id.Loginbutton);
                                Createbutton=findViewById(R.id.Createbutton);

                                Loginbutton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        dialog.show();

                                        String email,password;
                                        email=Email.getText().toString();
                                        password=Password.getText().toString();

                                        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                dialog.dismiss();
                                                if(task.isSuccessful())
                                                {
//                                                    Toast.makeText( LoginActivity.this,"Logged in",Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));

                                            }else{
                                                    Toast.makeText(LoginActivity.this,task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                                }

                                                }
                                        });

                                    }
                                });

                                Createbutton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(LoginActivity.this,SineupActivity.class));
                                    }
                                });
    }
}