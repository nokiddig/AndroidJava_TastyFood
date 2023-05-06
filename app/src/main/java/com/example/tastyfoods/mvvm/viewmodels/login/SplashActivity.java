package com.example.tastyfoods.mvvm.viewmodels.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.tastyfoods.MainActivity;
import com.example.tastyfoods.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        handler.postDelayed(() -> nextActivity(), 2000);

        handler.postDelayed(() -> {
            intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }, 4000);
    }

    private void nextActivity() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            intent=new Intent(this,LoginActivity.class);
        }
        else {
            intent=new Intent(this, MainActivity.class);
            intent.putExtra("phoneNumber",user.getPhoneNumber());
        }
        startActivity(intent);
        finish();
    }
}