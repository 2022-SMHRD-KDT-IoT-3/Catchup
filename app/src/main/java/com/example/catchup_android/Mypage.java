package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    UserVo vo= new UserVo();
    UserVo info  =LoginCheck.info;

    private Button btn_toUserEdit, btn_logout_myP, btn_toUserDelete, btn_toMonit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        btn_toUserEdit=findViewById(R.id.btn_toUserEdit);
        btn_logout_myP=findViewById(R.id.btn_logout_myP);
        btn_toUserDelete=findViewById(R.id.btn_toUserDelete);
        btn_toMonit=findViewById(R.id.btn_toMonit);


        //모니터링 임시
        btn_toMonit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Monitoring.class );
                startActivity(intent);
                finish();

            }
        });

        // 회원정보 수정 클릭
        btn_toUserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 정보수정 페이지로 이동
                /*Intent intent = new Intent(getApplicationContext(),UserEdit.class );
                startActivity(intent);
                finish();*/

            }
        });

        // 회원 탈퇴
        btn_toUserDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 탈퇴 페이지로 이동
                /*Intent intent = new Intent(getApplicationContext(),UserDelete.class );
                startActivity(intent);
                finish();*/

            }
        });


        // 로그아웃
        btn_logout_myP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 전역에서 로그아웃 가능 
                LoginCheck.logout();
                // context 문제때문에 각각 페이지에서 이하를 구현
                Intent intent = new Intent(getApplicationContext(),Login2.class );
                startActivity(intent);
                finish();

                Toast.makeText(getApplicationContext() , "로그아웃 완료",Toast.LENGTH_SHORT).show();

            }
        });


    }


}



