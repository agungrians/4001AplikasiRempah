package com.agungrian.rempahkitaapps;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
///////////////////////
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
///////////////////////

public class activity_daftar extends AppCompatActivity {
    private EditText namalengkap,email,password;
    private Button daftarakhun;

    //////////////////////////////////////////////////////////
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        namalengkap = findViewById(R.id.namalengkap);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        daftarakhun = findViewById(R.id.daftarakun);

        daftarakhun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addnamalengkap = namalengkap.getText().toString();
                String addemail = email.getText().toString();
                String addpassword = password.getText().toString();
                /////////////
                registerUser(addemail, addpassword);
                /////////////
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void registerUser(String email, String password){

        final ProgressDialog progressDialog = new ProgressDialog(activity_daftar.this);
        progressDialog.setMessage("Mendaftar...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            Toast.makeText(activity_daftar.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(activity_daftar.this, "Registrasi gagal", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }

    public void Login (View view){
        Intent intent = new Intent(activity_daftar.this, activity_login.class);
        startActivity(intent);
    }


