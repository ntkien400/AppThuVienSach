package com.kien.appdocsach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kien.lib.RetrofitClient;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.LoginModelResult;

public class LoginActivity extends AppCompatActivity {
    EditText edtLoginUsername;
    EditText edtLoginPassword;
    TextView txtSignUp;
    Button btnLogin;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SetLayout();
    }
    private void SetLayout(){
        edtLoginUsername = findViewById(R.id.edtLoginUsername);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        txtSignUp = findViewById(R.id.txtSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToSignUpPage();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edtLoginUsername.getText().toString();
                password = edtLoginPassword.getText().toString();
                Login(username, password);
            }
        });
    }
    private void Login(String username, String password){
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<LoginModelResult> call = methods.Login(username, password);
        call.enqueue(new Callback<LoginModelResult>() {
            @Override
            public void onResponse(Call<LoginModelResult> call, Response<LoginModelResult> response) {
                Log.e("onResponse", response.message());
                LoginModelResult loginModelResult = response.body();
                if(loginModelResult.getMessage().equals("fail")){
                    Log.v("F","That bai");
                    Toast.makeText(LoginActivity.this,"Login fail",Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.v("S","Thanh cong");
                    SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo", MODE_PRIVATE);
                    SharedPreferences.Editor info = sharedPreferences.edit();
                    info.putString("UserId",loginModelResult.getUser().getId());
                    info.putString("UserPassword",loginModelResult.getUser().getPassword());
                    info.putString("Username", loginModelResult.getUser().getUsername());
                    info.putString("UserEmail", loginModelResult.getUser().getEmail());
                    info.putString("UserTitle", loginModelResult.getUser().getUserTitle());
                    info.putString("UserImage", loginModelResult.getUser().getImage());
                    info.putString("Name", loginModelResult.getUser().getName());
                    info.commit();
                    GoToHomePage();
                }
            }

            @Override
            public void onFailure(Call<LoginModelResult> call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    private void GoToSignUpPage() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
    private void GoToHomePage(){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}