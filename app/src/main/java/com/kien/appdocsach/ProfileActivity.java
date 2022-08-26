package com.kien.appdocsach;

import static com.kien.lib.RetrofitClient.getRetrofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.kien.lib.RetrofitClient;
import com.kien.lib.insertModel.UserInsertModel;
import com.kien.lib.insertModel.UserInsertModelResult;
import com.kien.lib.interfaceRepository.Methods;
import com.kien.lib.model.UserModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ImageView imgvAvarta;
    TextView txtNameUser,txtTitle,txtEmail, txtLogOut;
    EditText edtOldPass,edtNewPass,edtRepeatPass,edtName;
    String userId, username, userEmail, userTitle, userImage, name;
    Button btnSave;
    LinearLayout layoutProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        SetLayout();
    }
    private void SetLayout(){
        imgvAvarta = findViewById(R.id.imgvAvarta);
        txtNameUser = findViewById(R.id.txtNameUser);
        txtTitle = findViewById(R.id.txtTitle);
        edtName = findViewById(R.id.edtName);
        txtEmail = findViewById(R.id.txtEmail);
        edtOldPass = findViewById(R.id.edtOldPass);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtRepeatPass = findViewById(R.id.edtRepeatPass);
        txtLogOut = findViewById(R.id.txtLogOut);
        btnSave = findViewById(R.id.btnSave);
        layoutProfile = findViewById(R.id.layoutProfile);
        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginInfo", MODE_PRIVATE);
        userId = sharedPreferences.getString("UserId",null);
        if(userId == null){
            txtNameUser.setText("Đăng nhập");
            txtNameUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoToLoginPage();
                }
            });
            txtTitle.setVisibility(View.INVISIBLE);
            txtLogOut.setVisibility(View.INVISIBLE);
            layoutProfile.setVisibility(View.INVISIBLE);
        }
        else {
            GetUserInfo();
            txtLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogOut();
                    GoToLoginPage();
                }
            });
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ChangeInfo();
                }
            });
        }

    }

    private void GoToLoginPage() {
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void GetUserInfo(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginInfo", MODE_PRIVATE);
        username = sharedPreferences.getString("Username",null);
        userEmail = sharedPreferences.getString("UserEmail",null);
        userTitle = sharedPreferences.getString("UserTitle",null);
        userImage = sharedPreferences.getString("UserImage",null);
        name = sharedPreferences.getString("Name", null);
        txtNameUser.setText(username);
        edtName.setText(name);
        txtTitle.setText(userTitle);
        txtEmail.setText(userEmail);
        Picasso.get().load(RetrofitClient.getBase_Url() + userImage).into(imgvAvarta);

    }
    private void LogOut(){
        SharedPreferences sharedPreferences = getSharedPreferences("LoginInfo", MODE_PRIVATE);
        SharedPreferences.Editor info = sharedPreferences.edit();
        info.putString("UserId", null);
        info.putString("Username", null);
        info.putString("UserEmail", null);
        info.putString("UserTitle", null);
        info.putString("UserImage", null);
        info.commit();
    }
    private void ChangeInfo(){
        UserInsertModel userInsertModel = new UserInsertModel();
        userInsertModel.setName(edtName.getText().toString());
        userInsertModel.setPassword(edtNewPass.getText().toString());
        SharedPreferences sharedPreferences = this.getSharedPreferences("LoginInfo", MODE_PRIVATE);
        if(edtNewPass.getText().toString().equals(edtRepeatPass.getText().toString())){
           Methods methods = RetrofitClient.getRetrofit().create(Methods.class);
           Call<UserInsertModelResult> call = methods.UpdateUser(sharedPreferences.getString("UserId",null),userInsertModel);
           call.enqueue(new Callback<UserInsertModelResult>() {
               @Override
               public void onResponse(Call<UserInsertModelResult> call, Response<UserInsertModelResult> response) {
                   Toast.makeText(ProfileActivity.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                   LogOut();
                   GoToLoginPage();
               }

               @Override
               public void onFailure(Call<UserInsertModelResult> call, Throwable t) {
                   Log.e("onFailure",t.getMessage());

               }
           });
        }
    }
}
