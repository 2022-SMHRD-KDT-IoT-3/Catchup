package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiaryWrite extends AppCompatActivity {

    private TextView tv_temp, tv_humid, tv_disease, tv_pesti, tv_cnt;
    private ImageView img_diary_write_back;
    private EditText edt_title, edt_content;
    private Button btn_write;
    String diary_date;
    private DiaryVO diary_vo = new DiaryVO();
    UserVo info= LoginCheck.info;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_write);

        Log.v("result","[diary_write]");

        tv_temp = findViewById(R.id.tv_temp);
        tv_humid = findViewById(R.id.tv_humid);
        tv_disease = findViewById(R.id.tv_disease);
        tv_pesti = findViewById(R.id.tv_pesti);
        tv_cnt = findViewById(R.id.tv_cnt);
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_content);
        btn_write = findViewById(R.id.btn_write);
        img_diary_write_back = findViewById(R.id.img_diary_write_back);

        // 화면에 DB에 담겨있는 값을 꺼내옴
        Intent intent = getIntent();
        diary_date = intent.getStringExtra("diary_time");
        diary_vo = (DiaryVO) intent.getSerializableExtra("diary_vo");
        Log.v("result",diary_date);
        Log.v("diary_write_vo",diary_vo.toString());
        ArrayList<String> pesti_list =  intent.getStringArrayListExtra("pesti_list");
        Log.v("pesti_list",pesti_list.toString());

        // 화면에 값 표시해줌
        tv_temp.setText(diary_vo.getDiary_temp()+"도");
        tv_humid.setText(diary_vo.getDiary_humid()+"%");
        tv_disease.setText(diary_vo.getDiary_percent()+"%");
        tv_pesti.setText(diary_vo.getDiary_pesti());
        tv_cnt.setText(diary_vo.getDiary_cnt()+"번");
        for (int i=0; pesti_list.size()>i; i++){
            tv_pesti.setText(pesti_list.get(i));
        }

        img_diary_write_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),DiaryMain.class);
                startActivity(intent1);
                finish();
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
                diary_vo.setDiary_dt(diary_date);
                diary_vo.setDiary_title(edt_title.getText().toString());
                diary_vo.setDiary_content(edt_content.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("diary_vo",diary_vo);

                setResult(RESULT_OK,intent);

                finish();
            }
        });
    }

    public void sendRequest(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://222.102.104.159:8081/app/diaryWrite.do";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("결과","연결 성공");
                Log.v("결과", response);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("결과","연결실패");
                error.printStackTrace();
            }
        }){

            // encoding
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (Exception e) {
                    return Response.error(new ParseError(e));
                }
            }
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                /*UserVo vo = LoginCheck.info;
                String id = vo.getId();*/
                String diary_title = edt_title.getText().toString();
                String diary_content = edt_content.getText().toString();

                //params.put("diary_seq","0");
                params.put("diary_title",diary_title);
                params.put("diary_content",diary_content);
                params.put("diary_dt", diary_vo.getDiary_dt());
                params.put("diary_id",info.getId());
                params.put("diary_temp",tv_temp.getText().toString());
                params.put("diary_humid",tv_humid.getText().toString());
                params.put("diary_percent",tv_disease.getText().toString());
                params.put("diary_pesti",tv_pesti.getText().toString());
                params.put("diary_cnt",tv_cnt.getText().toString());

                Log.v("idvalues",params.toString());

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);
    }
}