package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Infectivity extends AppCompatActivity {

    private ImageView img_infectivity_back, img_s_1, img_s_2, img_s_3, img_s_4, img_s_5, img_s_6, img_s_7, img_s_8, img_s_9;
    private Button btn_chagedate;
    private TextView tv_all_infectivity, tv_part_infectivity, tv_infectdate;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    String dateMessage = "";
    String month_string = "";
    String day_string = "";
    String year_string = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infectivity);


        img_infectivity_back = findViewById(R.id.img_infectivity_back);
        img_s_1 = findViewById(R.id.img_s_1);
        img_s_2 = findViewById(R.id.img_s_2);
        img_s_3 = findViewById(R.id.img_s_3);
        img_s_4 = findViewById(R.id.img_s_4);
        img_s_5 = findViewById(R.id.img_s_5);
        img_s_6 = findViewById(R.id.img_s_6);
        img_s_7 = findViewById(R.id.img_s_7);
        img_s_8 = findViewById(R.id.img_s_8);
        img_s_9 = findViewById(R.id.img_s_9);

        btn_chagedate = findViewById(R.id.btn_changedate);
        tv_infectdate = findViewById(R.id.tv_infectdate);
        tv_all_infectivity = findViewById(R.id.tv_all_infectivity);
        tv_part_infectivity = findViewById(R.id.tv_part_infectivity);


        img_infectivity_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyFarm.class);
                startActivity(intent);
                finish();
            }
        });
        sendRequest();
    }
    public void showDatePicker2 (View view) {
        DialogFragment newFragment = new DatePickerFragment2();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        month_string = Integer.toString(month+1);
        day_string = Integer.toString(day);
        year_string = Integer.toString(year);
//        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        dateMessage = (year_string + "-" + month_string + "-" + day_string);
        tv_infectdate.setText(dateMessage);
        sendRequest();

        Toast.makeText(getApplicationContext(),"Date: "+dateMessage,Toast.LENGTH_SHORT).show();
    }

    public void sendRequest(){
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = "http://192.168.131.151:8081/app/Infectivity.do";
        // 요청시 필요한 문자열 객체(get or post, 어디로 통신, 응답시 받아올) 스피링은 post
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("rsvtime", "실행");
                Log.v("result", response);
                ArrayList<MonitVo> monitorList = new ArrayList<>();
                MonitVo mvo = new MonitVo();
                ImageView imgarr [] = {img_s_1, img_s_2, img_s_3, img_s_4, img_s_5, img_s_6, img_s_7, img_s_8, img_s_9};
                try {
                    JSONObject jsonArray = new JSONObject(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(String.valueOf(i));
                        String area = jsonObject.getString("monit_area");
                        String infected = jsonObject.getString("monit_infected");
                        mvo.setMonit_area(area);
                        mvo.setMonit_infected(infected);
                        monitorList.add(mvo);
                        if(infected.equals("0")){
                            imgarr[i].setImageDrawable(getDrawable(R.drawable.normal));
                        }else if(infected.equals("1")){
                            imgarr[i].setImageDrawable(getDrawable(R.drawable.danger));
                        }else{
                            imgarr[i].setImageDrawable(getDrawable(R.drawable.dead));
                        }

                    }


                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {
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
            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // id, password 같은 값을 보낼때
                Map<String, String> params = new HashMap<String, String>();

                UserVo vo = LoginCheck.info;
                String id = vo.getId();
                String infecttime = dateMessage;
                params.put("user_id", id);
                params.put("monit_time", infecttime);

                Log.v("idvalues", dateMessage);

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);
    }
}