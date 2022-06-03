package com.example.catchup_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservationAdapter extends BaseAdapter {

    private ArrayList<ReservationVO> items = new ArrayList<ReservationVO>();

    public void addItem(String rsvtime, String pesticide, int rsv_seq){
        ReservationVO vo = new ReservationVO(rsvtime, pesticide, rsv_seq);
        items.add(vo);
    }


    public void removeItem(){
        items.clear();
    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        // 어댑터에게 해당 i 번째의 아이템을 요청하는 메소드
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){

        Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.reservation_layout, viewGroup, false);


        }

        TextView rsv_seq = view.findViewById(R.id.rsv_seq);
        TextView rsvtime = view.findViewById(R.id.rsvtime);
        TextView pesticide = view.findViewById(R.id.pesticide);

        ReservationVO vo = items.get(i);
        rsv_seq.setText("예약 "+(i+1));
        rsvtime.setText(vo.getRsvtime());
        pesticide.setText(vo.getPesticide());

        return view;


    }

}
