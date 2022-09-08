package com.nextapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nextapp.dto.User;

import java.util.Objects;

public class activity_login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    Button btnLogin;
    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    TextView txtForgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnLogin = findViewById(R.id.btnlogin);
        txtEmail = findViewById(R.id.txtLoginEmail);
        txtPassword = findViewById(R.id.txtLoginPassword);
        txtForgetPwd = findViewById(R.id.txtForgetPwd);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            validateUser(currentUser.getUid());

        } else {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = txtEmail.getText().toString();
                    String password = txtPassword.getText().toString();

                    if (email.isEmpty()) {
                        Toast.makeText(activity_login.this, "Email is Required",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                        Toast.makeText(activity_login.this, "Invalid Email",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password.isEmpty()) {
                        Toast.makeText(activity_login.this, "Password is Required",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password.length()<=6) {
                        Toast.makeText(activity_login.this, "Password must be more than 6 Character",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }



                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(activity_login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        validateUser(user.getUid());
                                    } else {
                                        Toast.makeText(activity_login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });

        }

        txtForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();

                if (email.isEmpty()) {
                    Toast.makeText(activity_login.this, "Email is Required",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(activity_login.this, "Password Reset Link Send to the Email.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(activity_login.this, "Forgot Password Failed - "+ task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void validateUser(String userId) {
        db.collection("users").document(userId)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            User myUser = new User();
                            myUser.setUserId(userId);
                            myUser.setName(task.getResult().getData().get("name").toString());
                            myUser.setContactNo(task.getResult().getData().get("contactNo").toString());
                            myUser.setUserType(task.getResult().getData().get("userType").toString());

                            Intent intent;
                            if (Objects.equals(myUser.getUserType(), "ADMIN")) {
                                intent = new Intent(getApplicationContext(), activity_home.class);
                            } else {
                                intent = new Intent(getApplicationContext(), activity_image_capture.class);
                            }
                            intent.putExtra("user", myUser);
                            startActivity(intent);
                        }
                    }
                });
    }
}