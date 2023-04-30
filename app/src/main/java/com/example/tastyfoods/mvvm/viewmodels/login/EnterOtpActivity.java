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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EnterOtpActivity extends AppCompatActivity {

    static  final  String  TAG=EnterOtpActivity.class.getName();

    PhoneAuthProvider.ForceResendingToken mForceResendingToken;


    String action;

    String mUsername;

    String mUserpassword;
    FirebaseAuth mAuth;

    FirebaseFirestore db;
    EditText edtOtp;
    Button btnSendOtpCode;
    TextView tvSendOtpAgain;

    String mPhoneNumber;
    String mVerificationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        getDataIntent();
        INitWidgest();
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        btnSendOtpCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOtp=edtOtp.getText().toString().trim();
                onClickSendOtpCode(strOtp);
            }
        });
        tvSendOtpAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSendOtpAgain();
            }
        });
    }

    private void onClickSendOtpAgain() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(mPhoneNumber)       // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this).setForceResendingToken(mForceResendingToken)// (optional) Activity for callback binding
                // If no activity is passed, reCAPTCHA verification can not be used.
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(EnterOtpActivity.this,"onVerificationFailed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verificationId, forceResendingToken);
                        mVerificationId=verificationId;
                        mForceResendingToken=forceResendingToken;
                    }
                })
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private  void getDataIntent(){
        mPhoneNumber=getIntent().getStringExtra("phone_number");
        mVerificationId=getIntent().getStringExtra("verification_id");
        mUsername=getIntent().getStringExtra("username");
        mUserpassword=getIntent().getStringExtra("password");
        action=getIntent().getStringExtra("action");
    }

    private void onClickSendOtpCode(String strOtp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, strOtp);
        signInWithPhoneAuthCredential(credential);
    }

    private void INitWidgest() {
        edtOtp=findViewById(R.id.edt_otp);
        tvSendOtpAgain=findViewById(R.id.tv_send_otp_again);
        btnSendOtpCode=findViewById(R.id.btn_send_otp_code);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            if(action.equals("register")) {
                                addUser(mUsername,mPhoneNumber,mUserpassword);
                            }
                            // Update UI
                            gotoMainActivity();
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(EnterOtpActivity.this,"The verification code entered was invalid",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void  addUser(String name,String phoneNumber,String password){
        Map<String, Object> luser = new HashMap<>();
        luser.put("userId", "3");
        luser.put("phoneNumber",phoneNumber);
        luser.put("password", password);
        luser.put("name", name);
        luser.put("birthday", null);
        luser.put("address", "");
        luser.put("image", "");
        db.collection("user").document(phoneNumber)
                .set(luser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
    }
    private void gotoMainActivity() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}