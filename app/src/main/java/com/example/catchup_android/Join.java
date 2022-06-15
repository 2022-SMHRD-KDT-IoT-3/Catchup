package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Join extends AppCompatActivity {

    private EditText edt_join_id, edt_join_pw, edt_name, edt_nick, edt_email, edt_serial;
    private TextView tv_idChk;
    private Button btn_idChk,  btn_joinInsert;

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    private Boolean isJoined=false;
    private Boolean isChk=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_join_id=findViewById(R.id.edt_join_id);
        edt_join_pw=findViewById(R.id.edt_join_pw);
        edt_name=findViewById(R.id.edt_id);
        edt_nick=findViewById(R.id.edt_nick);
        edt_email=findViewById(R.id.edt_email);
        edt_serial=findViewById(R.id.edt_serial);

        tv_idChk=findViewById(R.id.tv_idChk);

        btn_idChk=findViewById(R.id.btn_idChk);
        btn_joinInsert=findViewById(R.id.btn_login);
        

        // 아이디 중복확인
        btn_idChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest2();

            }
        });

        //회원가입
        btn_joinInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();

                Intent intent = new Intent(getApplicationContext(),Login2.class );
                startActivity(intent);
                finish();

                if(isJoined==false){
                    Toast.makeText(getApplicationContext() , "회원가입 실패",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    //중복확인
    public void sendRequest2(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext()); ///// 안쪽 내용을 this로 대체할수있다
        // 서버에 요청할 주소
        String url="http://192.168.131.151:8081/app/idCheck.do";

        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[중복확인 통신성공]");
                String chkId= edt_join_id.getText().toString();
                //Log.v("resultValue",response);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String id=jsonObject.getString("user_id");
                    String nick=jsonObject.getString("user_nick");


                    Log.v("resultValue","user_id : "+id+" user_nick : "+nick+" chkId :  "+chkId);


                    if (nick!=null || chkId==null ) { // 닉네임이 들어오거나 입력아이디가 빈칸일때

                        tv_idChk.setText("아이디를 확인해주세요");
                        tv_idChk.setTextColor(Color.parseColor("red"));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v("resultValue","중복확인 catch감지");

                    isChk=true;


                }
                if(isChk==true){
                    tv_idChk.setText("이용이 가능한 아이디 입니다.");
                    tv_idChk.setTextColor(Color.parseColor("blue") );
                    isChk=false;
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

                String id=edt_join_id.getText().toString();

                Log.v("resultValue","[idChk id : ]"+id );


                params.put("user_id",id );

                return params;
            }
        };
        stringRequest.setTag("join");  ///// 이곳은 구분자다.
        requestQueue.add(stringRequest);  ///// 이곳이 더 중요하다
    }

    public void sendRequest(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext()); ///// 안쪽 내용을 this로 대체할수있다
        // 서버에 요청할 주소
        String url="http://192.168.131.151:8081/app/joinInsert.do";

        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[회원가입 통신성공]");
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
                String mail=edt_email.getText().toString();
                String serial=edt_serial.getText().toString();

                Log.v("resultValue","[join_안드로>스프링]"+id+" "+pw+" "+name+" "+nick+" "+mail+" "+
                        serial);



                params.put("user_id",id );
                params.put("user_pw",pw );
                params.put("user_name",name );
                params.put("user_nick",nick );
                params.put("user_mail",mail );
                params.put("user_serial",serial );

                isJoined=true;




//                edt_id.setText("");   ///// 입력했던 내용을 초기화시켜서 다음 입력을 편리하게 만든다 동시에 처리하다보니까 안되는 것이었다 임시로 주석처리
//                edt_pw.setText("");


                return params;
            }
        };
        stringRequest.setTag("join");  ///// 이곳은 구분자다.
        requestQueue.add(stringRequest);  ///// 이곳이 더 중요하다
    }




}



