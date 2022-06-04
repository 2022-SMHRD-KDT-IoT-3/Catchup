package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MyInfo extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    private TextView tv_id_info,tv_joindate;
    private EditText edt_name_info, edt_mail_info,edt_nick_info;
    private Button btn_updateInfo;

    UserVo info=LoginCheck.info;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        tv_id_info=findViewById(R.id.tv_id_info);
        tv_joindate=findViewById(R.id.tv_joindate);

        edt_name_info=findViewById(R.id.edt_name_info);
        edt_mail_info=findViewById(R.id.edt_mail_info);
        edt_nick_info=findViewById(R.id.edt_nick_info);

        btn_updateInfo=findViewById(R.id.btn_updateInfo);

        String newJoindate=info.getJoindate().substring(0,10); // 날짜 포맷 변경이 잘 안되서 잘라내기로 처리함


        tv_id_info.setText("아이디 : "+info.getId());
        tv_joindate.setText("가입일자 : "+newJoindate );
        edt_name_info.setText(info.getName());
        edt_mail_info.setText(info.getMail());
        edt_nick_info.setText(info.getNick());

        btn_updateInfo.setOnClickListener(new View.OnClickListener() {
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
        String url="http://211.48.228.42:8081/app/updateInfo.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[MyInfo 통신성공]");

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

                String name=edt_name_info.getText().toString();
                String mail=edt_mail_info.getText().toString();
                String nick=edt_nick_info.getText().toString();

                Log.v("resultValue",name+" / "+mail+" / "+nick);  // 찍히는 것 확인

                params.put("user_name",name );
                params.put("user_mail",mail );
                params.put("user_nick",nick );

                params.put("user_id",info.getId());

                return params;
            }
        };
        stringRequest.setTag("myFarm");  // 구분자
        requestQueue.add(stringRequest);
    }
}