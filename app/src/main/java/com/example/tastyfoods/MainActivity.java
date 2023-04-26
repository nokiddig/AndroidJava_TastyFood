package com.example.tastyfoods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tastyfoods.mvvm.viewmodels.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button btn_sign_out,btn_create;
    String phoneNumber = "+1234567890";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initwidgest();
        btn_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.createUserWithEmailAndPassword("","")
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // User created successfully
                            } else {
                                // User creation failed
                            }
                        });
            }
        });
    }

    private void Initwidgest() {
        btn_sign_out=findViewById(R.id.btn_sign_out);
        btn_create=findViewById(R.id.btn_create);
    }
}