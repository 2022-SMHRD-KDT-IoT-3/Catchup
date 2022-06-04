package com.example.catchup_android;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DiaryList extends AppCompatActivity {

    private ListView listView;
    private DiaryAdapter adapter = new DiaryAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        listView = findViewById(R.id.diary_listview);

        adapter.addItem(1,"오늘의 글","글 작성중", "2022.06.01","aaaa");
        adapter.addItem(2,"오늘의 글2","글 작성중2글 작성중2글 작성중2글 작성중2글 작성중2글 작성중2글 작성중2글 작성중2","2022.06.02","bbbb");

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DiaryVO vo = (DiaryVO) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(),vo.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}