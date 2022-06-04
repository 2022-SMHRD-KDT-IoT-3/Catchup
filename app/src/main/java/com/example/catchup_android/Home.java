package com.example.catchup_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private Button btn_toMyfarm,btn_toMyInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btn_toMyfarm=findViewById(R.id.btn_toMyfarm);
        btn_toMyInfo=findViewById(R.id.btn_toMyInfo);

        btn_toMyfarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyFarm.class );
                startActivity(intent);
                finish();
            }
        });

        btn_toMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyInfo.class );
                startActivity(intent);
                finish();
            }
        });



    }
}