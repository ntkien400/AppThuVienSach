package com.kien.appdocsach;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kien.lib.RetrofitClient;
import com.kien.lib.insertModel.UserInsertModel;
import com.kien.lib.insertModel.UserInsertModelResult;
import com.kien.lib.interfaceRepository.Methods;

public class SignUpActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtRepeatPassword;
    TextView txtRepeatpassword;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtRepeatPassword = findViewById(R.id.edtRepeatPassword);
        txtRepeatpassword = findViewById(R.id.txtRepeatPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtUsername.getText() != null && edtEmail.getText() != null && edtPassword.getText() != null
                && edtRepeatPassword.getText() != null){
                    CreateUser(v);

                }
                else {
                    txtRepeatpassword.setText("Chưa điền đủ thông tin hoặc mật khẩu không trùng khớp");
                }

            }
        });

    }
    private void CreateUser(View view){
        UserInsertModel userInsertModel = new UserInsertModel();
        userInsertModel.setUsername(edtUsername.getText().toString());
        userInsertModel.setEmail(edtEmail.getText().toString());
        userInsertModel.setPassword(edtPassword.getText().toString());
        Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
        Call<UserInsertModelResult> call = methods.CreateUser(userInsertModel);
        call.enqueue(new Callback<UserInsertModelResult>() {
            @Override
            public void onResponse(Call<UserInsertModelResult> call, Response<UserInsertModelResult> response) {
                Toast.makeText(SignUpActivity.this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
                GoToLoginPage();
            }

            @Override
            public void onFailure(Call<UserInsertModelResult> call, Throwable t) {

            }
        });
    }

    private void GoToLoginPage() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}