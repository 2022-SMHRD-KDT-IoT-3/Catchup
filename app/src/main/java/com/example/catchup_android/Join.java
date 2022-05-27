package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class Join extends AppCompatActivity {

    private EditText edt_join_id, edt_join_pw, edt_name, edt_nick, edt_mail, edt_serial;
    private Button btn_idChk,  btn_join;

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_join_id=findViewById(R.id.edt_join_id);
        edt_join_pw=findViewById(R.id.edt_join_pw);
        edt_name=findViewById(R.id.edt_name);
        edt_nick=findViewById(R.id.edt_nick);
        edt_mail=findViewById(R.id.edt_mail);
        edt_serial=findViewById(R.id.edt_serial);

        btn_idChk=findViewById(R.id.btn_idChk);
        btn_join=findViewById(R.id.btn_join);
        
        
        // 아이디 중복체크 미구현
        btn_idChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //회원가입
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest();
            }
        });


    }

    public void sendRequest(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext()); ///// 안쪽 내용을 this로 대체할수있다
        // 서버에 요청할 주소
        String url="http://211.48.228.42:8081/app/joinInsert.do";

        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

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

                String id=edt_join_id.getText().toString();
                String pw=edt_join_pw.getText().toString();
                String name=edt_name.getText().toString();
                String nick=edt_nick.getText().toString();
                String mail=edt_mail.getText().toString();
                String serial=edt_serial.getText().toString();

                Log.v("resultValue",id+" "+pw+" "+name+" "+nick+" "+mail+" "+
                        serial);



                params.put("user_id",id );
                params.put("user_pw",pw );
                params.put("user_name",name );
                params.put("user_nick",nick );
                params.put("user_mail",mail );
                params.put("user_serial",serial );


//                edt_id.setText("");   ///// 입력했던 내용을 초기화시켜서 다음 입력을 편리하게 만든다 동시에 처리하다보니까 안되는 것이었다 임시로 주석처리
//                edt_pw.setText("");


                return params;
            }
        };
        stringRequest.setTag("join");  ///// 이곳은 구분자다.
        requestQueue.add(stringRequest);  ///// 이곳이 더 중요하다
    }



}



