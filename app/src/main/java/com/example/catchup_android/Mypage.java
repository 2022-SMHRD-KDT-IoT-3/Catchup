package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Mypage extends AppCompatActivity {

    private TextView tv_mypage, tv_toMyInfo, tv_toFarmInfo, tv_toInquiry, tv_toMyWriting;
    private Button btn_logout;

    UserVo info = LoginCheck.info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        tv_mypage = findViewById(R.id.tv_mypage);
        tv_toMyInfo = findViewById(R.id.tv_toMyInfo);
        tv_toFarmInfo = findViewById(R.id.tv_toFarmInfo);
        tv_toInquiry = findViewById(R.id.tv_toInquiry);
        tv_toMyWriting = findViewById(R.id.tv_toMyWriting);
        btn_logout = findViewById(R.id.btn_logout);

        tv_mypage.setText(info.getId() + " 님 환영합니다  ");

        // 내 정보
        tv_toMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyInfo.class);
                startActivity(intent);
                finish();

            }
        });
        // 농장정보
        tv_toFarmInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FarmInfo.class);
                startActivity(intent);
                finish();

            }
        });
        //1:1문의
        tv_toInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // 내 글 보기
        tv_toMyWriting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Postlist.class);
                startActivity(intent);
                finish();

            }
        });

        // 로그아웃
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 전역에서 로그아웃 가능 
                LoginCheck.logout();
                // context 문제때문에 각각 페이지에서 이하를 구현
                Intent intent = new Intent(getApplicationContext(), Login2.class);
                startActivity(intent);
                finish();

                Toast.makeText(getApplicationContext(), "로그아웃 완료", Toast.LENGTH_SHORT).show();

            }
        });


    }


}



