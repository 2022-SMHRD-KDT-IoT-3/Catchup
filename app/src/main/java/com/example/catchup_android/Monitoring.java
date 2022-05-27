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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Monitoring extends AppCompatActivity {



    private TextView tv_id_monit;
    private Button btn_insertMonint, btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        tv_id_monit= findViewById(R.id.tv_id_monit);

        btn_insertMonint= findViewById(R.id.btn_insertMonit);
        btn_logout= findViewById(R.id.btn_logout);

        //로그아웃 기능
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginCheck.logout();

                Intent intent = new Intent(getApplicationContext(),Login2.class );
                startActivity(intent);
                finish();

                Toast.makeText(getApplicationContext() , "로그아웃 완료",Toast.LENGTH_SHORT).show();
            }
        });
        
        // 모니터링 데이터 입력
        btn_insertMonint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log.v("resultValue","모니터 입력대기");


            }
        });


    }


}



