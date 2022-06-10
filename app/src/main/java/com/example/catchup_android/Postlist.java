package com.example.catchup_android;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.util.ArrayList;


public class Postlist extends AppCompatActivity {

    RecyclerView mRecyclerView = null;
    BoardAdapter mAdapter = null;
    ArrayList<BoardVo> mList;


    // Vo 내용을 보여주는

    private String type,title,content,user_id;
    private int seq,cnt,cmmmcnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        mRecyclerView = findViewById(R.id.recycler_view);
        mList = new ArrayList<>();

        mAdapter = new BoardAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false)); // VERTICAL: 세로 호라이즌 : 가로

        // 직접

        //mImageDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.farmer, null); // 이미지를 넣을 때
        title = "Crocus";
        //content = "www.crocus.co.kr";

        addItem(1,"1",title,3,20,"pbk" );
        addItem(2,"2",title,5,20,"pbk1" );
        addItem(3,"2",title,7,20,"pbk3" );
        addItem(3,"2",title,8,20,"pbk3" );
        addItem(3,"2",title,7,20,"pbk3" );
        addItem(3,"2",title,9,20,"pbk3" );
        addItem(3,"2",title,10,20,"pbk3" );
        addItem(3,"2",title,9,20,"pbk3" );
        addItem(3,"2",title,6,20,"pbk3" );


        //addItem(mImageDrawable, mMainText + " - 5", mSubText);

        mAdapter.notifyDataSetChanged();
    }
    // 23번째줄의 변수들을 생성자로.
    private void addItem(int seq,String type,String title,int cnt, int cmmmcnt, String user_id) {
        BoardVo item = new BoardVo();

        // BoardVo의 세터

        item.setBoard_seq(seq); // seq
        item.setBoard_type(type); // 타입
        item.setBoard_title(title); // 제목
        //item.setBoard_content(content); // 내용
        item.setBoard_cnt(cnt); // 조회수
        item.setBoard_commcnt(cmmmcnt); // 댓글 수
        item.setUser_id(user_id); // 유저 아이디


        mList.add(item);
    }
}




























   /* private RecyclerView recyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postlist);

        recyView = findViewById(R.id.recyView);

        BoardAdapter adapter = new BoardAdapter();
        adapter.addItem(new BoardVo(0, '1', "제목", "22-06-07", "내용", 10, 20, "pbk"));
       *//* adapter.addItem(new BoardVo("완득이",R.drawable.mov02));
        adapter.addItem(new BoardVo("괴물",R.drawable.mov03));
        adapter.addItem(new BoardVo("라디오스타",R.drawable.mov04));
        adapter.addItem(new BoardVo("비열한 거리",R.drawable.mov05));
        adapter.addItem(new BoardVo("왕의 남자",R.drawable.mov06));
        adapter.addItem(new BoardVo("아일랜드",R.drawable.mov07));
        adapter.addItem(new BoardVo("웰컴 투 동막골",R.drawable.mov08));
        adapter.addItem(new BoardVo("헬보이",R.drawable.mov09));
        adapter.addItem(new BoardVo("백 투더 퓨처",R.drawable.mov10));
        adapter.addItem(new BoardVo("여인의 향기",R.drawable.mov11));
        adapter.addItem(new BoardVo("쥬라기 공원",R.drawable.mov12));*//*

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyView.setLayoutManager(layoutManager);
        recyView.setAdapter(adapter);

        *//*this.board_seq = board_seq;
        this.board_type = board_type;
        this.board_title = board_title;
        this.board_time = board_time;
        this.board_content = board_content;
        this.board_cnt = board_cnt;
        this.board_commcnt = board_commcnt;
        this.user_id = user_id;*//*


    }
}*/



