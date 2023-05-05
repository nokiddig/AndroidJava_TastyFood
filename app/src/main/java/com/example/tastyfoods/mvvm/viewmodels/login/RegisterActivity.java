package com.example.tastyfoods.mvvm.viewmodels.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tastyfoods.R;
import com.example.tastyfoods.mvvm.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = RegisterActivity.class.getName();

    private EditText edt_username;

    private EditText edt_password;

    private EditText edt_phonenumber;

    private ImageView img_back;

    private Button btn_register;

    User user;
    private FirebaseFirestore db;

    private FirebaseAuth mAuth;

    private Boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Initwigest();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edt_username.getText().toString().trim();
                String userpassword = edt_password.getText().toString().trim();
                String userphonenumber = edt_phonenumber.getText().toString().trim();
                user = new User(username, userphonenumber, userpassword);
                checkUser(userphonenumber);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLoginActivity();
            }
        });
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void Initwigest() {

        btn_register = findViewById(R.id.btn_register);
        edt_password = findViewById(R.id.edt_password);
        edt_username = findViewById(R.id.edt_user_name);
        edt_phonenumber = findViewById(R.id.edt_phonenumber);
        img_back = findViewById(R.id.img_back);
    }

    private void onClickVerifyPhoneNumber(String strPhoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(strPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(RegisterActivity.this, "onVerificationFailed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                gotoEnterOtpActivity(user, verificationId);
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void checkUser(String phonenumber) {
        check=true;
        db.collection("user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.getId().equals(phonenumber)) {
                            onClickVerifyPhoneNumber(phonenumber);
                        }
                    }
                    return;
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }



    private void gotoEnterOtpActivity(User user,String verificationId) {
        Intent intent=new Intent(this,EnterOtpActivity.class);
        intent.putExtra("action","register");
        intent.putExtra("verification_id",verificationId);
        intent.putExtra("phone_number",user.getPhoneNumber());
        intent.putExtra("password",user.getPassword());
        intent.putExtra("username",user.getName());
        startActivity(intent);
    }
}