package com.example.catchup_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Reservation_list extends AppCompatActivity {

    private ImageView img_rsv;
    private ListView listView;
    private TextView tv_date;
    private ImageView img_res_list_back;
    String dateMessage = "";
    String month_string = "";
    String day_string = "";
    String year_string = "";
    int removeitem = 0;
    private ReservationAdapter adapter = new ReservationAdapter();
    ReservationVO resvo = new ReservationVO();
    //    private ArrayAdapter<String> adapter; // 리스트뷰에 적용되는 어뎁터
//    // 어뎁터에 들어갈 데이터
//    private ArrayList<String> items = new ArrayList<String>();
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        tv_date = findViewById(R.id.tv_date);
        img_rsv = findViewById(R.id.img_rsv);
        listView = findViewById(R.id.rsv_list);
        img_res_list_back = findViewById(R.id.img_res_list_back);

        img_res_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyFarm.class );
                startActivity(intent);
                finish();
            }
        });

        sendRequest();

        img_rsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),Reservation_add.class );
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.v("items",adapter.getItem(i).toString());
                resvo = (ReservationVO)adapter.getItem(i);
                removeitem = resvo.getRsv_seq();
                sendRequest2();
                adapter.removeItem();
                sendRequest();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        month_string = Integer.toString(month+1);
        day_string = Integer.toString(day);
        year_string = Integer.toString(year);
//        String dateMessage = (month_string + "/" + day_string + "/" + year_string);
        dateMessage = (year_string + "-" + month_string + "-" + day_string);
        tv_date.setText(dateMessage);
        adapter.removeItem();
        sendRequest();

        Toast.makeText(getApplicationContext(),"Date: "+dateMessage,Toast.LENGTH_SHORT).show();
    }

    public void sendRequest2(){
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = "http://211.223.37.232:8081/app/Reservation_remove.do";
        // 요청시 필요한 문자열 객체(get or post, 어디로 통신, 응답시 받아올) 스피링은 post
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터 받아오는 곳
            @Override
            public void onResponse(String response) {

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
                String rsvtime = dateMessage;
                int rsv_seq = removeitem;

                params.put("user_id", id);
                params.put("res_seq", rsv_seq+"");
                params.put("res_rsvtime", rsvtime);

                Log.v("idvalues", params.toString());

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);
    }


    public void sendRequest(){
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = "http://211.223.37.232:8081/app/Reservation_list.do";
        // 요청시 필요한 문자열 객체(get or post, 어디로 통신, 응답시 받아올) 스피링은 post
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("rsvtime", "실행");
                Log.v("result", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
//                    Array array = new Array(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String rsvtime = jsonObject.getString("res_rsvtime");
                        String result = rsvtime.substring(rsvtime.length()-10, rsvtime.length()-5);
                        int rsv_seq = jsonObject.getInt("res_seq");
                        System.out.println(result);
                        String pesticide = jsonObject.getString("res_pesticide");
                        adapter.addItem(result, pesticide, rsv_seq);

                    }
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
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
                String rsvtime = dateMessage;
                params.put("user_id", id);
                params.put("res_rsvtime", rsvtime);

                Log.v("idvalues", params.toString());

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);
    }
}