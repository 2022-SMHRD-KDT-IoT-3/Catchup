package com.example.catchup_android;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DiaryAdapter extends BaseAdapter {
    private ArrayList<DiaryVO> items = new ArrayList<>();
    private ArrayList<EnvironmentVO> env_items = new ArrayList<>();
    private ArrayList<MonitoringVO> moni_items = new ArrayList<>();
    private ArrayList<ReservationVO> reserve_items = new ArrayList<>();

    public void addItem(int diary_seq, String diary_title, String diary_content, String diary_dt, String diary_id){
        DiaryVO vo = new DiaryVO(diary_seq, diary_title, diary_content, diary_dt, diary_id);
        items.add(vo);
    }

    /*public void showItem(String diary_title, String diary_content, String diary_dt){
        DiaryVO vo = new DiaryVO(diary_title, diary_content, diary_dt);
        items.add(vo);
    }*/

//    public void addCalendarItem(String diary_title, String diary_dt, String diary_content, String diary_id, int env_temp, int env_humid,int monit_percent, int monit_cnt, String reserve_pesti){
//        DiaryVO vo = new DiaryVO(diary_title, diary_content, diary_dt, diary_id);
//        EnvironmentVO env_vo = new EnvironmentVO(env_temp, env_humid);
//        MonitoringVO moni_vo = new MonitoringVO(monit_percent, monit_cnt);
//        ReservationVO reserve_vo = new ReservationVO(pesticide);
//    }

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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        // diary_list
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.diary_list,viewGroup,false);
        }


        TextView diary_list_title = view.findViewById(R.id.diary_list_title);
        TextView diary_list_date = view.findViewById(R.id.diary_list_date);
        TextView diary_list_content = view.findViewById(R.id.diary_title_content);

        /*TextView diary_temp = view.findViewById(R.id.diary_temp);
        TextView diary_humid = view.findViewById(R.id.diary_humid);
        TextView diary_disease = view.findViewById(R.id.diary_disease);
        TextView diary_pesti = view.findViewById(R.id.diary_pesti);*/

        DiaryVO vo = items.get(i);

        Log.v("vo",vo.toString());
        String title = vo.getDiary_title();
        Log.v("vo", title);

        diary_list_title.setText(vo.getDiary_title());
        diary_list_date.setText(vo.getDiary_dt());
        diary_list_content.setText(vo.getDiary_content());

        // calendar_list
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.calendar_list,viewGroup,false);
        }

        TextView diary_title = view.findViewById(R.id.diary_title);
        TextView diary_date = view.findViewById(R.id.diary_date);
        TextView diary_temp = view.findViewById(R.id.diary_temp);
        TextView diary_humid = view.findViewById(R.id.diary_humid);
        TextView diary_disease = view.findViewById(R.id.diary_disease);
        TextView diary_cnt = view.findViewById(R.id.diary_cnt);
        TextView diary_pesti = view.findViewById(R.id.diary_pesti);

        EnvironmentVO env_vo = env_items.get(i);
        MonitoringVO moni_vo = moni_items.get(i);
        ReservationVO reserv_vo = reserve_items.get(i);

        diary_title.setText(vo.getDiary_title());
        diary_date.setText(vo.getDiary_dt());
        diary_temp.setText(env_vo.getEnvr_temp());
        diary_humid.setText(env_vo.getEnvr_humid());
        diary_disease.setText(moni_vo.getMonit_percent());
        diary_cnt.setText(moni_vo.getMonit_cnt());
        diary_pesti.setText(reserv_vo.getPesticide());

        return view;
    }
}
