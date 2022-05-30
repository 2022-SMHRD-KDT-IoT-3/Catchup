package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private TextView tv_result_home;
<<<<<<< HEAD
    private Button btn_toLogin;
=======
    private Button btn_toLogin, btn_toMyP;

    UserVo vo= new UserVo();
    UserVo info  =LoginCheck.info;
>>>>>>> android

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_result_home=findViewById(R.id.tv_result_home);
        btn_toLogin=findViewById(R.id.btn_toLogin);
<<<<<<< HEAD
=======
        btn_toMyP=findViewById(R.id.btn_toMyP);



        if (vo !=null){
            tv_result_home.setText(info.toString());
        }

>>>>>>> android

        btn_toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Login2.class );
                startActivity(intent);
                finish();
            }
        });

<<<<<<< HEAD
=======
        btn_toMyP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Mypage.class );
                startActivity(intent);
                finish();
            }
        });

>>>>>>> android

    }
}