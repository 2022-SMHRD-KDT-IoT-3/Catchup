package com.example.catchup_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private ArrayList<BoardVo> mData = null;

    public BoardAdapter(ArrayList<BoardVo> data) {
        mData = data;
    }

    // onCreateViewHolder : 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.board_layout, parent, false);
        BoardAdapter.ViewHolder vh = new BoardAdapter.ViewHolder(view);

        return vh;
    }

    // onBindViewHolder : position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoardVo item = mData.get(position);
        
        // BoardVo의 게터

        holder.tv_seq_items.setInputType(item.getBoard_seq());
        holder.tv_type_items.setText(item.getBoard_type());
        holder.tv_title_items.setText(item.getBoard_title());
        //holder.tv_content_items.setText(item.getBoard_content());
        holder.tv_cnt_post_items.setInputType(item.getBoard_cnt());
        holder.tv_commcnt_items.setInputType(item.getBoard_commcnt());
        holder.tv_user_id_items.setText(item.getUser_id());

    }

    // getItemCount : 전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        // board_layout의 객체를 선언
        TextView tv_seq_items, tv_type_items, tv_title_items, tv_cnt_post_items, tv_commcnt_items, tv_user_id_items;


        ViewHolder(View itemView) {
            super(itemView);

            // 뷰 객체에 대한 참조.  board_layout의 객체를 연결
            tv_seq_items = itemView.findViewById(R.id.tv_seq_items);
            tv_type_items = itemView.findViewById(R.id.tv_type_items);
            tv_title_items = itemView.findViewById(R.id.tv_title_items);
            //tv_content_items = itemView.findViewById(R.id.tv_content_items);
            tv_cnt_post_items = itemView.findViewById(R.id.tv_cnt_post_items);
            tv_commcnt_items = itemView.findViewById(R.id.tv_commcnt_items);
            tv_user_id_items = itemView.findViewById(R.id.tv_user_id_items);

        }
    }
}

























   /* private static final String TAG = "BoardAdapter";


    //리스트는 무조건 데이터를 필요로함
    private List<BoardVo> items = new ArrayList<>();

    public void addItem(BoardVo vo) {
        items.add(vo);
    }

    //껍데기만 만듬. 1번 실행

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.board_layout, parent, false);
        return new MyViewHolder(view);
    }

    //껍데기에 데이터 바인딩. 2번 실행
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        BoardVo vo = items.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return items.size();
    }

    //ViewHolder : 뷰들의 책꽂이
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //규칙1

        private TextView tv_seq, tv_type, tv_title, tv_content, tv_cnt_post, tv_commcnt, tv_user_id;


        public MyViewHolder(View itemView) {
            super(itemView);
            //규칙2
            tv_seq = itemView.findViewById(R.id.tv_seq);
            tv_type = itemView.findViewById(R.id.tv_type);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_type);
            tv_cnt_post = itemView.findViewById(R.id.tv_cnt_post);
            tv_commcnt = itemView.findViewById(R.id.tv_commcnt);
            tv_user_id = itemView.findViewById(R.id.tv_user_id);

        }

        //규칙3
        public void setItem(BoardVo vo) {
            Log.d(TAG, "MyViewHolder: ");
            tv_seq.setText(vo.getBoard_seq());
            tv_type.setText(vo.getBoard_type());
            tv_title.setText(vo.getBoard_title());
            tv_content.setText(vo.getBoard_content());
            tv_cnt_post.setText(vo.getBoard_cnt());
            tv_commcnt.setText(vo.getBoard_commcnt());
            tv_user_id.setText(vo.getUser_id());


        }
    }
}
*/