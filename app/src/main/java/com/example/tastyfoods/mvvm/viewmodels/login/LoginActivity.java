package com.example.tastyfoods.mvvm.viewmodels.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tastyfoods.MainActivity;
import com.example.tastyfoods.R;
import com.google.android.gms.tasks.OnCompleteListener;
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

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    private  static  final  String TAG= MainActivity.class.getName();
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    Button btnVerifyPhoneNumber;
    EditText edtPhoneNumber;

    TextView extError;

    TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitWidgest();
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        btnVerifyPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPhoneNumber=edtPhoneNumber.getText().toString().trim();
                checkUser(strPhoneNumber);
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegisterActivity();
            }
        });
    }

    private void gotoRegisterActivity() {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    private void InitWidgest() {
        btnVerifyPhoneNumber=findViewById(R.id.btn_verify_phone_number);
        edtPhoneNumber=findViewById(R.id.edt_phone_number);
        tvRegister=findViewById(R.id.tv_register);
        extError=findViewById(R.id.ext_error);
    }

    private void onClickVerifyPhoneNumber(String strPhoneNumber){
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(strPhoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(LoginActivity.this,"onVerificationFailed",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                gotoEnterOtpActivity(strPhoneNumber,verificationId);
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            gotoMainActivity(user.getPhoneNumber());
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(LoginActivity.this,"The verification code entered was invalid",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void checkUser(String phonenumber){
        db.collection("user").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if(document.getId().equals(phonenumber)){
                            onClickVerifyPhoneNumber(phonenumber);
                        }
                    }
                    Log.d(TAG, "Check user ", task.getException());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void gotoMainActivity(String phoneNumber) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("phone_number",phoneNumber);
        startActivity(intent);
    }

    private void gotoEnterOtpActivity(String strPhoneNumber, String verificationId) {
    Intent intent=new Intent(this,EnterOtpActivity.class);
    intent.putExtra("phone_number",strPhoneNumber);
    intent.putExtra("action","login");
    intent.putExtra("verification_id",verificationId);
    startActivity(intent);
    }
}