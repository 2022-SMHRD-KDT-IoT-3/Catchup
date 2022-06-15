package com.example.catchup_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class MyFarm extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열

    private TextView tv_temprt,tv_humid,tv_infected,tv_realTime1,tv_realTime2;
    private Button btn_refresh;
    private ImageView img_reservation, img_monitoring, img_calendar, img_diary, img_disease;

    private TimeZone tz=TimeZone.getTimeZone("Asia/Seoul"); // 객체생성 + TimeZone에 표준시 설정
    private DateFormat dateFormat1= new SimpleDateFormat("yyyy'년' MM'월' dd'일'", Locale.KOREAN);
    private DateFormat dateFormat2= new SimpleDateFormat("a hh:mm:ss", Locale.KOREAN);
    private DateFormat dateFormat_res= new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
    private Date date = new Date();

    UserVo info= LoginCheck.info;

    int cnt = 0;
    String res_rsvtime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_farm);

        tv_temprt=findViewById(R.id.tv_toMyInfo);
        tv_humid=findViewById(R.id.tv_toFarmInfo);
        tv_infected=findViewById(R.id.tv_toInquiry);
        tv_realTime1=findViewById(R.id.tv_mypage);
        tv_realTime2=findViewById(R.id.tv_realTime2);

        btn_refresh=findViewById(R.id.btn_refresh);

        img_reservation = findViewById(R.id.img_reservation);
        img_monitoring = findViewById(R.id.img_monitoring);
        img_calendar = findViewById(R.id.img_calendar);
        img_diary = findViewById(R.id.img_diary);
        img_disease = findViewById(R.id.img_disease);

        tv_realTime1.setText(dateFormat1.format(date) );
        tv_realTime2.setText(dateFormat2.format(date) );




        // 농장정보 출력
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();

                tv_realTime1.setText(dateFormat1.format(date) );
                tv_realTime2.setText(dateFormat2.format(date) );

                sendRequest1();
                sendRequest2();
                //Log.v("resultValue","[리프레시]" +);



            }
        });

        sendRequest1();
        sendRequest2();


        img_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Reservation_list.class);
                startActivity(intent);
                //finish();
            }
        });

        // 전염도 확인 버튼
        img_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Infectivity.class);
                startActivity(intent);
                //finish();
            }
        });


        /*tv_temprt.setText();
        tv_humid.setText();*/
        img_monitoring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Monitoring.class);
                startActivity(intent);
                //finish();
            }
        });

        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url="http://192.168.131.151:8081/app/reservationCount.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                //Log.v("resultValue","[myFarm send2 통신성공]   "+response);


                try {
                    Log.v("return","예약 횟수 통신 완료");

                    //String response1 = response; // response가 json이 아니라 단순한 문자열이기 때문에 직접 받는 것이 가능하다

                    cnt = Integer.parseInt((String) response);
                    //Log.v("cnt1",response1);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.v("return","예약 횟수 통신 실패");
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

                String id = info.getId();
                res_rsvtime = dateFormat_res.format(date);

                Log.v("resultValue",id);
                Log.v("resultValue", res_rsvtime);

                params.put("user_id",id );
                params.put("res_rsvtime", res_rsvtime);
                return params;
            }
        };
        stringRequest.setTag("myFarm");  // 구분자
        requestQueue.add(stringRequest);

        img_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendRequest3();

                Intent intent = new Intent(getApplicationContext(), DiaryMain.class);

                String temperature = (String) tv_temprt.getText();
                String temp = temperature.replaceAll("[^0-9]","");
                Log.v("humid",(String) tv_humid.getText());
                String humidity = (String) tv_humid.getText();
                String humid = humidity.replaceAll("[^0-9]","");
                String percent = (String) tv_infected.getText();
                String disease = percent.replaceAll("[^0-9]","");

                Log.v("cnt2", String.valueOf(cnt));

                intent.putExtra("temprt",temp);
                intent.putExtra("humid",humid);
                intent.putExtra("disease",disease);
                intent.putExtra("cnt","2");
                intent.putExtra("pesti",disease);
                intent.putExtra("date",res_rsvtime);
                startActivity(intent);
                //finish();
            }
        });

        img_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DiaryList.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    // 환경정보 가저오기
    public void sendRequest1(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url="http://192.168.131.151:8081/app/selectEnv.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                Log.v("resultValue","[myFarm send1 통신성공]");
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String seq=jsonObject.getString("env_seq");
                    String temprt=jsonObject.getString("env_temprt");
                    String humid=jsonObject.getString("env_humid");
                    String env_time=jsonObject.getString("env_time");
                    String id=jsonObject.getString("user_id");


                    Log.v("resultValue",seq+" / "+temprt+" / "+humid+" / "+env_time+" / "+id);



                    tv_temprt.setText(temprt+" 도");
                    tv_humid.setText(humid+" %");

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.v("resultValue","[myFarm send1 통신오류]");
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

                String id=info.getId();
                Log.v("resultValue",id);  // 찍히는 것 확인
                params.put("user_id",id );

                return params;
            }
        };
        stringRequest.setTag("myFarm");  // 구분자
        requestQueue.add(stringRequest);
    }

    // 감염률 가져오기
    public void sendRequest2(){
        // Request 객체 생성
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url="http://192.168.131.151:8081/app/getInfected.do";


        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
            @Override
            public void onResponse(String response) {
                //Log.v("resultValue","[myFarm send2 통신성공]   "+response);


                try {
                    Log.v("resultValue","[myFarm send2 통신성공] "+response);

                    String value= response; // response가 json이 아니라 단순한 문자열이기 때문에 직접 받는 것이 가능하다

                    Log.v("resultValue","value : "+value );

                    tv_infected.setText(value + " %");


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.v("resultValue","[myFarm send2 통신오류]  "+response );
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

                String id=info.getId();
                Log.v("resultValue",id);  // 찍히는 것 확인
                params.put("user_id",id );

                return params;
            }
        };
        stringRequest.setTag("myFarm");  // 구분자
        requestQueue.add(stringRequest);
    }

//    // 예약 횟수 통신
//    public void sendRequest3(){
//
//    }

}