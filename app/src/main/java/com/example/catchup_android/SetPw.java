package com.example.catchup_android;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class SetPw extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    UserVo info= LoginCheck.info;

    private EditText edt_pw1, edt_pw2;
    private TextView tv_pwCheck;
    private Button btn_updatePw;

   // private boolean isSetting=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pw);

        edt_pw1=findViewById(R.id.edt_pw1);
        edt_pw2=findViewById(R.id.edt_pw2);

        tv_pwCheck=findViewById(R.id.tv_pwCheck);

        btn_updatePw=findViewById(R.id.btn_updatePw);

        tv_pwCheck.setText("");
        tv_pwCheck.setTextColor(Color.parseColor("black") );

        btn_updatePw.setOnClickListener(new View.OnClickListener() {
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
        String url="http://211.48.228.42:8081/app/setPw.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[setPw 통신성공]");



               /* if(isSetting==true){


                    isSetting=false;
                }
*/


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

                if(edt_pw1.getText().toString().equals(  edt_pw2.getText().toString() )){
                    String pw= edt_pw1.getText().toString();
                    tv_pwCheck.setText("비밀번호가 일치합니다");
                    tv_pwCheck.setTextColor(Color.parseColor("blue") );

                    Log.v("resultValue","setPw 출발 : "+pw);  // 찍히는 것 확인

                    params.put("user_pw",pw );
                    params.put("user_id",info.getId());

                    Intent intent = new Intent(getApplicationContext(),Login2.class );
                    startActivity(intent);
                    finish();

                }else{
                    tv_pwCheck.setText("비밀번호가 서로 다릅니다");
                    tv_pwCheck.setTextColor(Color.parseColor("red") );

                }



                return params;
            }
        };
        stringRequest.setTag("setPw");  // 구분자
        requestQueue.add(stringRequest);
    }
}