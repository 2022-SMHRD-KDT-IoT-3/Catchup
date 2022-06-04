package com.example.catchup_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MyFarm extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    private TextView tv_temprt,tv_humid,tv_infected;
    private ImageView img_reservation, img_monitoring, img_calendar, img_diary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_farm);

        tv_temprt=findViewById(R.id.tv_temprt);
        tv_humid=findViewById(R.id.tv_humid);
        tv_infected=findViewById(R.id.tv_infected);
        img_reservation = findViewById(R.id.img_reservation);
        img_monitoring = findViewById(R.id.img_monitoring);
        img_calendar = findViewById(R.id.img_calendar);
        img_diary = findViewById(R.id.img_diary);


        img_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Reservation_list.class);
                startActivity(intent);
                finish();
            }
        });
        sendRequest();

        /*tv_temprt.setText();
        tv_humid.setText();*/
        img_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Monitoring.class);
                startActivity(intent);
                finish();
            }
        });

        img_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiaryMain.class);
                startActivity(intent);
                finish();
            }
        });

        img_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiaryList.class);
                startActivity(intent);
                finish();
            }
        });





    }

    public void sendRequest(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url="http://222.102.104.159:8081/app/getEnv.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[myFarm 통신성공]");

                /*JSONObject jsonObject=new JSONObject(response);
                String id=jsonObject.getString("user_id");
                String pw=jsonObject.getString("user_pw");
                String name=jsonObject.getString("user_name");
                String nick=jsonObject.getString("user_nick");
                String mail=jsonObject.getString("user_mail");
                String serial=jsonObject.getString("user_serial");

                Log.v("resultValue",id+"/"+pw+"/"+name+"/"+nick+"/"+mail+"/"+serial);
                //Log.v("resultValue",response);*/






            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }


            // 보낼 데이터를 저장하는 곳 ///// 이곳이 중요하다
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return null;

            }
        };
        stringRequest.setTag("myFarm");  // 구분자
        requestQueue.add(stringRequest);
    }

}