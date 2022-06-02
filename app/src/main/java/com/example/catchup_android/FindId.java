package com.example.catchup_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FindId extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    private EditText edt_name,edt_email;
    private TextView tv_fRes1,tv_fRes2,tv_fRes3;
    private Button btn_findId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        edt_name=findViewById(R.id.edt_name);
        edt_email=findViewById(R.id.edt_email);

        tv_fRes1=findViewById(R.id.tv_fRes1);
        tv_fRes2=findViewById(R.id.tv_fRes2);
        tv_fRes3=findViewById(R.id.tv_fRes3);

        btn_findId=findViewById(R.id.btn_findId);

        btn_findId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });
    }

    public void sendRequest(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url="http://211.48.228.42:8081/app/findId.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[아이디 찾기성공]");

                    //로그인 성공
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String id=jsonObject.getString("user_id");
                        String pw=jsonObject.getString("user_pw");
                        String name=jsonObject.getString("user_name");
                        String nick=jsonObject.getString("user_nick");
                        String mail=jsonObject.getString("user_mail");
                        String serial=jsonObject.getString("user_serial");

                        Log.v("resultValue",id+"/"+pw+"/"+name+"/"+nick+"/"+mail+"/"+serial);

                        tv_fRes1.setText("아이디 : "+id);
                        //setText(id);
                       // tv_fRes3.setText(" 입니다");
                        //Toast.makeText(getApplicationContext() , "회원님의 아이디는 위와 같습니다",Toast.LENGTH_SHORT).show();




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



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

                String name=edt_name.getText().toString();
                String email=edt_email.getText().toString();

                Log.v("resultValue",name+" "+email);  // 찍히는 것 확인
                params.put("user_name",name );
                params.put("user_mail",email );

                return params;
            }
        };
        stringRequest.setTag("findId");  // 구분자
        requestQueue.add(stringRequest);
    }
}