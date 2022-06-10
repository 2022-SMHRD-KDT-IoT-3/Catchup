package com.example.catchup_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DiaryListAdapter extends BaseAdapter {
    private ArrayList<DiaryVO> items = new ArrayList<>();

    public void addItem(int diary_seq, String diary_title, String diary_content, String diary_dt, int diary_temp, int diary_humid, int diary_percent, int diary_cnt, String diary_pesti) {
        DiaryVO vo = new DiaryVO(diary_seq, diary_title, diary_content, diary_dt, diary_temp, diary_humid, diary_percent, diary_cnt, diary_pesti);
        items.add(vo);
        Log.v("ffff", items.toString());
    }
//    public void addItem(int diary_seq, String diary_title, String diary_content, String diary_dt, String diary_id){
//
//    }

    // itmes의 생성자
    public DiaryListAdapter(){

    }

    // Adapter에 사용되는 데이터 개수 리턴턴
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // 위치에 데이터를 화면에 출력한느데 사용될 view를 리턴
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.v("result","들어옴");
        Context context = viewGroup.getContext();

        // calendar_list
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.diary_list,viewGroup,false);
        }

        TextView diary_list_title = view.findViewById(R.id.diary_list_title);
        TextView diary_title_content = view.findViewById(R.id.diary_title_content);
        TextView diary_list_date = view.findViewById(R.id.diary_list_date);
        TextView diary_list_temp = view.findViewById(R.id.diary_list_temp);
        TextView diary_list_humid = view.findViewById(R.id.diary_list_humid);
        TextView diary_list_disease = view.findViewById(R.id.diary_list_disease);
        TextView diary_list_cnt = view.findViewById(R.id.diary_list_cnt);
        TextView diary_list_pesti = view.findViewById(R.id.diary_list_pesti);
//        TextView diary_temp = view.findViewById(R.id.diary_temp);
//        TextView diary_humid = view.findViewById(R.id.diary_humid);
//        TextView diary_disease = view.findViewById(R.id.diary_disease);
//        TextView diary_cnt = view.findViewById(R.id.diary_cnt);
//        TextView diary_pesti = view.findViewById(R.id.diary_pesti);

//        EnvironmentVO env_vo = env_items.get(i);
//        MonitoringVO moni_vo = moni_items.get(i);
//        ReservationVO reserv_vo = reserve_items.get(i);
        DiaryVO vo = items.get(i);
        Log.v("diary_list",vo.toString());

        diary_list_title.setText(vo.getDiary_title());
        diary_title_content.setText(vo.getDiary_content());
        diary_list_date.setText(vo.getDiary_dt());
        diary_list_temp.setText("최고온도 : "+vo.getDiary_temp()+"도");
        diary_list_humid.setText("최고습도 : "+vo.getDiary_humid()+"%");
        diary_list_disease.setText("전염도 : "+vo.getDiary_percent()+"%");
        diary_list_cnt.setText("방제횟수 : "+vo.getDiary_cnt()+"번");
        diary_list_pesti.setText("방제약품 : "+vo.getDiary_pesti());

        return view;

    }


}
