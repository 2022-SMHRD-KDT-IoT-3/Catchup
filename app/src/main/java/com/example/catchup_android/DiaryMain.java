package com.example.catchup_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.example.catchup_android.decorators.EventDecorator;
import com.example.catchup_android.decorators.OneDayDecorator;
import com.example.catchup_android.decorators.SaturdayDecorator;
import com.example.catchup_android.decorators.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class DiaryMain extends AppCompatActivity {

    private RequestQueue requestQueue; // 서버에 요청을 하는 객체
    private StringRequest stringRequest; // 요청 시 필요한 문자열
    private StringRequest stringRequest1;

    private MaterialCalendarView calendarView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    Cursor cursor;
    private LinearLayout content_layout;
    private ListView calendar_list_view;
    private DiaryAdapter adapter;
    private ImageView img_cal_back;

    UserVo info= LoginCheck.info;
    private DiaryVO diary_vo = new DiaryVO();
    ArrayList<String> pesti_list = new ArrayList<>();
    JSONArray jsonArray = null;
    String shot_Day = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_main);

        calendarView = findViewById(R.id.calendarView);
        content_layout = findViewById(R.id.content_layout);
        calendar_list_view = findViewById(R.id.calendar_list);
        img_cal_back = findViewById(R.id.img_cal_back);

        Intent intent = getIntent();


        // 뒤로가기
        img_cal_back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyFarm.class );
                startActivity(intent);
                finish();
            }
        }));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT /* layout_width */, LinearLayout.LayoutParams.WRAP_CONTENT /* layout_height */, 1f /* layout_weight */);

        // 달력 최소, 최대 날짜 지정
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2020,0,1)) // calendar start
                .setMaximumDate(CalendarDay.from(2030,11,31))   // calendar end
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        // 공휴일, 일요일, 월요일 달력 커스텀
        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        String[] result = {"2020,03,18","2020,04,18","2020,05,18","2020,06,18"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        /*setOnDateChangedListener -> 해당 날짜 콜백*/
        calendarView.setOnDateChangedListener(new OnDateSelectedListener(){
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                shot_Day = Year + "-" + Month + "-" + Day;

                Log.i("shot_Day test", shot_Day + "");


                Toast.makeText(getApplicationContext(), shot_Day , Toast.LENGTH_SHORT).show();

                //sendRequest2();
                //calendarView.clearSelection();
                content_layout.removeAllViews();



                // 방제 약품 데이터 요청하기
                // Request 객체 생성
                requestQueue= Volley.newRequestQueue(getApplicationContext());
                // 서버에 요청할 주소
                String url="http://192.168.131.151:8081/app/resPesticide.do";


                // 요청시 필요한 문자열 객체
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    // 응답데이터를 받아오는 곳 ///// 서버와 통신하면 마지막에 넘어오는 곳이다
                    @Override
                    public void onResponse(String response) {
                        //Log.v("resultValue","[myFarm send2 통신성공]   "+response);


                        try {
                            Log.v("return","방제약품 통신 완료");

                            //String response1 = response; // response가 json이 아니라 단순한 문자열이기 때문에 직접 받는 것이 가능하다
                            Log.v("result","들어오니");

                            jsonArray = new JSONArray(response);
                            Log.v("jsonArray",jsonArray.toString());

                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String diary_pesti = jsonObject.getString("res_pesticide");
                                Log.v("pesti", diary_pesti);
                                pesti_list.add(diary_pesti);
                            }









                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.v("return","방제약품 통신 실패");
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
                        String res_rsvtime = intent.getStringExtra("date");

                        Log.v("resultValue_diarymain",id);
                        Log.v("resultValue_diarymain", res_rsvtime);

                        params.put("user_id",id );
                        params.put("res_rsvtime", shot_Day);
                        return params;
                    }
                };
                stringRequest.setTag("diaryMain");  // 구분자
                requestQueue.add(stringRequest);


                // 선택된 날 다이어리 가져오기
                // RequestQueue 객체 생성
                requestQueue = Volley.newRequestQueue(getApplicationContext());
                // 서버에 요청할 주소
                String url1 = "http://192.168.131.151:8081/app/selectDiaryList.do";
                // 요청시 필요한 문자열 객체(get or post, 어디로 통신, 응답시 받아올) 스피링은 post

                Log.v("stringRequest",stringRequest.toString());
                stringRequest1 = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                    // 응답데이터 받아오는 곳
                    @Override
                    public void onResponse(String response) {
                        Log.v("result", "sendRequest2 실행");
                        Log.v("result", response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            Log.v("jsonArray",jsonArray.toString());

                            if (jsonArray.length() == 0 || jsonArray == null){
                                // add plus image
                                content_layout.removeAllViews();    // remove layout
                                ImageView imgadd = new ImageView(DiaryMain.this);
                                imgadd.setImageResource(R.drawable.add_resize);
                                content_layout.addView(imgadd);  // add layout

                                // 다이어리 글 쓰기
                                imgadd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // 캘린더에서 넘겨받은 인텐트
                                        Intent cal_intent = getIntent();

                                        int diary_temp = Integer.parseInt(cal_intent.getStringExtra("temprt"));
                                        int diary_humid = Integer.parseInt(cal_intent.getStringExtra("humid"));
                                        int diary_percent = Integer.parseInt(cal_intent.getStringExtra("disease"));
                                        int diary_cnt = Integer.parseInt(cal_intent.getStringExtra("cnt"));
                                        //String diary_pesti = cal_intent.getStringExtra("pesti");

                                        diary_vo.setDiary_temp(diary_temp);
                                        diary_vo.setDiary_humid(diary_humid);
                                        diary_vo.setDiary_percent(diary_percent);
                                        diary_vo.setDiary_cnt(diary_cnt);
                                        //                        .setDiary_pesti(pesti_list);
                                        Log.v("diary_main_vo",diary_vo.toString());
                                        Intent intent = new Intent(getApplicationContext(), DiaryWrite.class);
                                        intent.putExtra("diary_time",shot_Day);
                                        intent.putExtra("pesti_list",pesti_list);
                                        intent.putExtra("diary_vo",diary_vo);

                                        startActivityForResult(intent,1004);
                                        //                        finish();
                                    }
                                });
                            }
//                            else {
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                    String diary_title = jsonObject.getString("diary_title");
//                                    String diary_content = jsonObject.getString("diary_content");
//                                    String diary_dt = jsonObject.getString("diary_dt");
//                                    int diary_temp = Integer.parseInt(jsonObject.getString("diary_temp"));
//                                    int diary_humid = Integer.parseInt(jsonObject.getString("diary_humid"));
//                                    int diary_percent = Integer.parseInt(jsonObject.getString("diary_percent"));
//                                    // 리스트 타입을 json으로 어덯게 가져오지??
//                                    String diary_pesti = jsonObject.getString("diary_pesti");
//                                    int diary_cnt = Integer.parseInt(jsonObject.getString("diary_cnt"));
//
//                                    Log.v("diary_t", diary_title);
//                                    Log.v("diary_c", diary_content);
//                                    Log.v("diary_d", diary_dt);
//                                    Log.v("diary_c", String.valueOf(diary_temp));
//                                    Log.v("diary_c", String.valueOf(diary_humid));
//                                    Log.v("diary_c", String.valueOf(diary_percent));
//                                    Log.v("diary_c", String.valueOf(diary_cnt));
//                                    Log.v("diary_p", diary_pesti);
//                                    //String result = rsvtime.substring(rsvtime.length()-10, rsvtime.length()-5);
//                                    //int rsv_seq = jsonObject.getInt("res_seq");
//
//                                    //adapter.addItem(diary_title, diary_dt, diary_temp, diary_humid, diary_percent, diary_cnt, diary_pesti);
//
//                                    adapter.addItem1(diary_title, diary_content, diary_dt, diary_temp, diary_humid, diary_percent, diary_cnt, diary_pesti);
//                                    Log.v("adpater", String.valueOf(adapter));
//                                }
//                                calendar_list_view.setAdapter(adapter);
//                            }
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

                        params.put("diary_id", id);
                        params.put("diary_dt", shot_Day);

                        Log.v("idvalues", params.toString());

                        return params;
                    }
                };
                stringRequest1.setTag("main");
                requestQueue.add(stringRequest1);

                // list
//                adapter.addCalendarItem("abcde","20200220","skfjkfwe","aaaa",22,32,33,2,"pesti");
//                calendar_list.setAdapter(adapter);
//
//                calendar_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//                });



            }


        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1004 && requestCode == RESULT_OK){
            Log.v("result","돌아옴");
            Log.v("myData",data.getStringExtra("diary_title"));
        }
    }

    // inner method -> Apisimulator
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>>{
        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            // speicific date pointed by dot in calendar
            // month -> 0 => 1 month
            // Time_Reslut string split by ',' and return as int type
            for (int i=0; i< Time_Result.length; i++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day1 = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,day1);
            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()){
                return;
            }

            calendarView.addDecorators(new EventDecorator(Color.RED, calendarDays, DiaryMain.this));
        }
    }
    public void sendRequest2(){

    }
}