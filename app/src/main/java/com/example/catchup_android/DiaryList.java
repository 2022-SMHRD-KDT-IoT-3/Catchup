package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiaryList extends AppCompatActivity {

    private ListView listView;
    private DiaryListAdapter adapter = new DiaryListAdapter();
    private ImageView img_diary_list_back;
    DiaryVO vo = new DiaryVO();

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        img_diary_list_back = findViewById(R.id.img_diary_list_back);
        listView = findViewById(R.id.diary_listview);

        sendRequest();

        img_diary_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyFarm.class );
                startActivity(intent);
                finish();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sendRequest();
                Intent intent = new Intent(getApplicationContext(),DiaryContents.class);
                DiaryVO vo = (DiaryVO) adapter.getItem(i);
                Log.v("listview",vo.toString());
                intent.putExtra("vo",vo);
                startActivity(intent);
                finish();
            }
        });


    }

    public void sendRequest(){

        // RequestQueue ?????? ??????
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        // ????????? ????????? ??????
        String url = "http://192.168.131.151:8081/app/allDiaryList.do";
        // ????????? ????????? ????????? ??????(get or post, ????????? ??????, ????????? ?????????) ???????????? post
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // ??????????????? ???????????? ???
            @Override
            public void onResponse(String response) {
                Log.v("rsvtime", "??????");
                Log.v("result", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
//                    Array array = new Array(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int diary_seq = jsonObject.getInt("diary_seq");
                        String diary_title = jsonObject.getString("diary_title");
                        String diary_content = jsonObject.getString("diary_content");
                        String diary_dt = jsonObject.getString("diary_dt");
                        int diary_temp = jsonObject.getInt("diary_temp");
                        int diary_humid = jsonObject.getInt("diary_humid");
                        int diary_percent = jsonObject.getInt("diary_percent");
                        // ????????? ????????? json?????? ????????? ??????????????
                        String diary_pesti = jsonObject.getString("diary_pesti");
                        int diary_cnt = jsonObject.getInt("diary_cnt");

                        Log.v("diary_t", diary_title);
                        Log.v("diary_c", diary_content);
                        Log.v("diary_d", diary_dt);
                        Log.v("diary_c", String.valueOf(diary_temp));
                        Log.v("diary_c", String.valueOf(diary_humid));
                        Log.v("diary_c", String.valueOf(diary_percent));
                        Log.v("diary_c", String.valueOf(diary_cnt));
                        Log.v("diary_p", diary_pesti);

                        adapter.addItem(diary_seq,diary_title, diary_content, diary_dt, diary_temp, diary_humid, diary_percent, diary_cnt, diary_pesti);
                        Log.v("ggggg", adapter.getCount()+"");
                    }
                    Log.v("result", "??????");
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
            @Override //response??? UTF8??? ??????????????? ????????????
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
            // ?????? ???????????? ???????????? ???
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // id, password ?????? ?????? ?????????
                Map<String, String> params = new HashMap<String, String>();

                UserVo vo = LoginCheck.info;
                String id = vo.getId();
                params.put("diary_id", id);

                Log.v("idvalues", params.toString());

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);
    }
}