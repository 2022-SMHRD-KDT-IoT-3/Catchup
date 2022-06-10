package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryContents extends AppCompatActivity {

    TextView tv_content_temp, tv_content_humid, tv_content_disease, tv_content_pesti, tv_content_cnt, tv_content_date, tv_content_title, tv_content_content;
    ImageView img_diary_content_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_contents);
        tv_content_temp = findViewById(R.id.tv_content_temp);
        tv_content_humid = findViewById(R.id.tv_content_humid);
        tv_content_disease = findViewById(R.id.tv_content_disease);
        tv_content_pesti = findViewById(R.id.tv_content_pesti);
        tv_content_cnt = findViewById(R.id.tv_content_cnt);
        tv_content_date = findViewById(R.id.tv_content_date);
        tv_content_title = findViewById(R.id.tv_content_title);
        tv_content_content = findViewById(R.id.tv_content_content);

        img_diary_content_back = findViewById(R.id.img_diary_content_back);

        Intent intent = getIntent();
        DiaryVO vo =  (DiaryVO) intent.getSerializableExtra("vo");
        Log.v("diary_content", vo.toString());

        tv_content_temp.setText(vo.getDiary_temp()+"도");
        tv_content_humid.setText(vo.getDiary_humid()+"%");
        tv_content_disease.setText(vo.getDiary_percent()+"%");
        tv_content_pesti.setText(vo.getDiary_pesti());
        tv_content_cnt.setText(vo.getDiary_cnt()+"번");
        tv_content_date.setText(vo.getDiary_dt());
        tv_content_title.setText(vo.getDiary_title());
        tv_content_content.setText(vo.getDiary_title());

        img_diary_content_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), DiaryList.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}