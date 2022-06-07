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

public class FarmInfo extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    TextView tv_fInfo,tv_serial;
    EditText edt_houseNum,edt_lineNum,edt_areaNum;
    Button btn_refresh_fInfo,btn_updateFarmInfo;

    UserVo info=LoginCheck.info;

    String id=info.getId() , linenum,areanum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_info);

        tv_fInfo=findViewById(R.id.tv_fInfo);
        tv_serial=findViewById(R.id.tv_serial);

        edt_houseNum=findViewById(R.id.edt_houseNum);
        edt_lineNum=findViewById(R.id.edt_lineNum);
        edt_areaNum=findViewById(R.id.edt_areaNum);

        btn_refresh_fInfo=findViewById(R.id.btn_refresh_fInfo);
        btn_updateFarmInfo=findViewById(R.id.btn_updateFarmInfo);


        tv_fInfo.setText(info.getId()+" 의 농장");
        tv_serial.setText(info.getSerial());

        sendRequest1();

        btn_refresh_fInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest1();
            }
        });

        btn_updateFarmInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest2();
            }
        });


    }

    // farm테이블 select
    public void sendRequest1(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url="http://211.48.228.42:8081/app/selectFarmInfo.do";



        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[fInfo send1 성공] "+response);

                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        String plant=jsonObject.getString("farm_plant");
                        String linenum=jsonObject.getString("farm_linenum");
                        String areanum=jsonObject.getString("farm_areanum");


                        Log.v("resultValue","주수 / 라인 / 구역 "+plant+"/"+linenum+"/"+areanum);

                        edt_lineNum.setText(linenum);
                        edt_areaNum.setText(areanum);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.v("resultValue","[fInfo send1 실패]");

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
                
                Log.v("resultValue","[fInfo send1 출발]");
                params.put("user_id",id );

                return params;
            }
        };
        stringRequest.setTag("fInfo");  ///// 이곳은 구분자다.
        requestQueue.add(stringRequest);  ///// 이곳이 더 중요하다
    }

    // farm테이블 updates
    public void sendRequest2(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url="http://211.48.228.42:8081/app/updateFarmInfo.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {

                //통신 성공
                Log.v("resultValue","[FarmInfo 통신성공]");

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

                String linenum=edt_lineNum.getText().toString();
                String areanum=edt_areaNum.getText().toString();

                Log.v("resultValue",linenum+" / "+areanum);  // 찍히는 것 확인

                params.put("farm_linenum",linenum );
                params.put("farm_areanum",areanum );

                params.put("user_id",info.getId());

                return params;
            }
        };
        stringRequest.setTag("FarmInfo");  // 구분자
        requestQueue.add(stringRequest);
    }
}
