package com.agungrian.rempahkitaapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_login extends AppCompatActivity {
    private Button masuk;
    private EditText email, password;

    ////////////////////////////
    private FirebaseAuth firebaseAuth;

    ///////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        ///////////////////////
        firebaseAuth = FirebaseAuth.getInstance();
        ///////////////////////

        masuk = findViewById(R.id.masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailNya = email.getText().toString();
                String passwordNya = password.getText().toString();

                Log.d("USERNAME", emailNya);
                Log.d("PASSWORD", passwordNya);

                ///////////////////////
                login(emailNya, passwordNya);
                ///////////////////////
            }
        });
    }
    public void Daftar (View view){
        Intent intent = new Intent(activity_login.this, activity_daftar.class);
        startActivity(intent);

    }

    public void login(String email, String password) {
        final ProgressDialog progressDialog = new ProgressDialog(activity_login.this);
        progressDialog.setMessage("Login...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        task.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        });
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(activity_login.this, activity_utama.class);
                            startActivity(intent);
                            finish();
                        } else {
//                            task.getResult().toString();
                            Toast.makeText(activity_login.this, "Gagal login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
