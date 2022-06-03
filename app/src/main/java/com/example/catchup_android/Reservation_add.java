package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
import java.util.HashMap;
import java.util.Map;

public class Reservation_add extends AppCompatActivity {

    private EditText edt_rsvtime, edt_pesticide;
    private Button btn_rsv_add;
    private ImageView img_res_add_back;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_add);

        edt_rsvtime = findViewById(R.id.edt_rsvtime);
        edt_pesticide = findViewById(R.id.edt_pesticide);
        btn_rsv_add = findViewById(R.id.btn_rsv_add);
        img_res_add_back = findViewById(R.id.img_res_add_back);

        img_res_add_back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Reservation_list.class );
                startActivity(intent);
                finish();
            }
        }));

        btn_rsv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
                Intent intent = new Intent(getApplicationContext(),Reservation_list.class );
                startActivity(intent);
                finish();
            }
        });

    }
    public void sendRequest(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://211.223.37.232:8081/app/Reservation_add.do";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("result", response);

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
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
            protected Map<String, String> getParams() throws AuthFailureError {
                // id, password 같은 값을 보낼때
                Map<String, String> params = new HashMap<String, String>();

                UserVo vo = LoginCheck.info;
                String id = vo.getId();
                String res_rsvtime = edt_rsvtime.getText().toString();
                String res_pesticide = edt_pesticide.getText().toString();

                params.put("user_id", id);
                params.put("res_pesticide", res_pesticide);
                params.put("res_rsvtime", res_rsvtime);


                Log.v("idvalues", params.toString());

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);
    }
}