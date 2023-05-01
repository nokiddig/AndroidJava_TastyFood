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
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    public static final String TAG = RegisterActivity.class.getName();

    EditText editTextUsername;

    EditText editTextPassword;

    EditText editTextPhoneNumber;

    ImageView imageViewBack;

    Button btn_register;

    User user;
    FirebaseFirestore db;

    FirebaseAuth mAuth;

    Boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        initWidgets();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String userPassword = editTextPassword.getText().toString().trim();
                String userPhoneNumber = editTextPhoneNumber.getText().toString().trim();
                user = new User(username, userPhoneNumber, userPassword);
                if(checkUser(userPhoneNumber)){
                    onClickVerifyPhoneNumber(userPhoneNumber);
                }
            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLoginActivity();
            }
        });
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void initWidgets() {

        btn_register = findViewById(R.id.btn_register);
        editTextPassword = findViewById(R.id.edt_password);
        editTextUsername = findViewById(R.id.edt_user_name);
        editTextPhoneNumber = findViewById(R.id.edt_phonenumber);
        imageViewBack = findViewById(R.id.img_back);
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

    private Boolean checkUser(String phoneNumber) {
        check=true;
        db.collection("user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.getId().equals(phoneNumber)) {
                            check=false;
                        }
                    }
                    Log.d(TAG, "Error getting documents: ", task.getException());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        return check;
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