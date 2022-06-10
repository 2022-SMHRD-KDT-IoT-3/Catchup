package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Login2 extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    private EditText edt_join_id, edt_pw;
    private Button btn_login, btn_join;
    private TextView tv_toFindId,tv_toFindPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        Log.v("activity","loginActivity");

        edt_join_id=findViewById(R.id.edt_join_id);
        edt_pw=findViewById(R.id.edt_pw);

        btn_login=findViewById(R.id.btn_login);
        btn_join=findViewById(R.id.btn_join);

        tv_toFindId=findViewById(R.id.tv_toFindId);
        tv_toFindPw=findViewById(R.id.tv_toFindPw);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_join_id.getText().length()>0 ||edt_pw.getText().length()>0   ){

                    sendRequest();
                }else{
                    Toast.makeText(getApplicationContext() , "입력을 확인하세요",Toast.LENGTH_SHORT).show();
                }



            }
        });

       btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Join.class );
                startActivity(intent);
                finish();
                Log.v("resultValue","[회원가입 진입]");
            }
        });

       tv_toFindId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FindId.class );
                startActivity(intent);
                finish();
            }
        });

        tv_toFindPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FindPw.class );
                startActivity(intent);
                finish();
            }
        });


    }

    public void sendRequest(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
//        String url="http://211.48.228.42:8081/app/login.do"; // jm
        //String url="http://211.223.37.232:8081/app/login.do";  // 팀장님
        String url="http://222.102.104.159:8081/app/login.do";  // 재경
        



        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                //Log.v("resultValue",response);

                Log.v("resultValue",response.length() +"");


                if (response.length() > 5 && edt_join_id.getText()!=null ) {

                    //로그인 성공
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String id=jsonObject.getString("user_id");
                        String pw=jsonObject.getString("user_pw");
                        String name=jsonObject.getString("user_name");
                        String nick=jsonObject.getString("user_nick");
                        String mail=jsonObject.getString("user_mail");
                        String serial=jsonObject.getString("user_serial");
                        String joindate=jsonObject.getString("user_joindate");


                        Log.v("resultValue",id+"/"+pw+"/"+name+"/"+nick+"/"+mail+"/"+serial);
                        //Log.v("resultValue",response);

                        Toast.makeText(getApplicationContext() , id + " 님 환영합니다",Toast.LENGTH_SHORT).show();

                         LoginCheck.info = new UserVo(id,pw,name,nick,mail,serial,joindate);

                        Intent intent = new Intent(getApplicationContext(),MyFarm.class );
                        startActivity(intent);
                        finish();



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    //로그인 실패
                    Toast.makeText(getApplicationContext() , "로그인 실패",Toast.LENGTH_SHORT).show();
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
                String pw=edt_pw.getText().toString();

                Log.v("resultValue",id+" "+pw);  // 찍히는 것 확인
                params.put("user_id",id );
                params.put("user_pw",pw );

                return params;
            }
        };
        stringRequest.setTag("login2");  ///// 이곳은 구분자다.
        requestQueue.add(stringRequest);  ///// 이곳이 더 중요하다
    }

}



