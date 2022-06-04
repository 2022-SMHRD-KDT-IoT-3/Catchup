package com.example.catchup_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class DiaryWrite extends AppCompatActivity {

    private TextView tv_temp, tv_humid, tv_disease, tv_pesti, tv_cnt;
    private EditText edt_title, edt_content;
    private EnvironmentVO env_vo = new EnvironmentVO();
    private ReservationVO reserve_vo = new ReservationVO();
    private MonitoringVO moni_vo = new MonitoringVO();
    private Button btn_write;
    String diary_date;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_write);

        tv_temp = findViewById(R.id.tv_temp);
        tv_humid = findViewById(R.id.tv_humid);
        tv_disease = findViewById(R.id.tv_disease);
        tv_pesti = findViewById(R.id.tv_pesti);
        tv_cnt = findViewById(R.id.tv_cnt);
        edt_title = findViewById(R.id.edt_title);
        edt_content = findViewById(R.id.edt_content);
        btn_write = findViewById(R.id.btn_write);

        /*tv_temp.setText(env_vo.getEnvr_temp());
        tv_humid.setText(env_vo.getEnvr_humid());
        tv_disease.setText(moni_vo.getMonit_percent());
        tv_pesti.setText(reserve_vo.getPesticide());
        tv_cnt.setText(moni_vo.getMonit_cnt());*/

        Intent intent = getIntent();
        diary_date = intent.getStringExtra("diary_time");
        Log.v("result",diary_date);
        Log.v("result",tv_cnt.getText().toString());
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
                Intent intent = new Intent();
                intent.putExtra("diary_title",edt_title.getText());
                intent.putExtra("diary_content",edt_content.getText());
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
                params.put("diary_dt",diary_date);
                params.put("diary_id","1");
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