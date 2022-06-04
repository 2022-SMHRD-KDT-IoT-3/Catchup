package com.example.catchup_android;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.catchup_android.decorators.EventDecorator;
import com.example.catchup_android.decorators.OneDayDecorator;
import com.example.catchup_android.decorators.SaturdayDecorator;
import com.example.catchup_android.decorators.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class DiaryMain extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    Cursor cursor;
    private LinearLayout content_layout;
    private ListView calendar_list;
    private DiaryAdapter adapter;
    private ImageView img_cal_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_main);

        calendarView = findViewById(R.id.calendarView);
        content_layout = findViewById(R.id.content_layout);
        calendar_list = findViewById(R.id.calendar_list);
        img_cal_back = findViewById(R.id.img_cal_back);

        // 뒤로가기
        img_cal_back.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MyFarm.class );
                startActivity(intent);
                finish();
            }
        }));

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT /* layout_width */, LinearLayout.LayoutParams.WRAP_CONTENT /* layout_height */, 1f /* layout_weight */);

        // 달력 최소, 최대 날짜 지정
        calendarView.state().edit()
                .setMinimumDate(CalendarDay.from(2020,0,1)) // calendar start
                .setMaximumDate(CalendarDay.from(2030,11,31))   // calendar end
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        // 공휴일, 일요일, 월요일 달력 커스텀
        calendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        String[] result = {"2020,03,18","2020,04,18","2020,05,18","2020,06,18"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());

        /*setOnDateChangedListener -> 해당 날짜 콜백*/
        calendarView.setOnDateChangedListener(new OnDateSelectedListener(){
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = Year + "-" + Month + "-" + Day;

                Log.i("shot_Day test", shot_Day + "");


                Toast.makeText(getApplicationContext(), shot_Day , Toast.LENGTH_SHORT).show();

                //calendarView.clearSelection();

                // add plus image
                content_layout.removeAllViews();    // remove layout
                ImageView imgadd = new ImageView(DiaryMain.this);
                imgadd.setImageResource(R.drawable.add_resize);
                content_layout.addView(imgadd);  // add layout

                imgadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), DiaryWrite.class);
                        intent.putExtra("diary_time",shot_Day);
                        startActivityForResult(intent,1004);
//                        finish();
                    }
                });
                // list
//                adapter.addCalendarItem("abcde","20200220","skfjkfwe","aaaa",22,32,33,2,"pesti");
//                calendar_list.setAdapter(adapter);
//
//                calendar_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                    }
//                });



            }


        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1004 && requestCode == RESULT_OK){
            Log.v("result","돌아옴");
            Log.v("myData",data.getStringExtra("diary_title"));
        }
    }

    // inner method -> Apisimulator
    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>>{
        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            // speicific date pointed by dot in calendar
            // month -> 0 => 1 month
            // Time_Reslut string split by ',' and return as int type
            for (int i=0; i< Time_Result.length; i++){
                CalendarDay day = CalendarDay.from(calendar);
                String[] time = Time_Result[i].split(",");
                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int day1 = Integer.parseInt(time[2]);

                dates.add(day);
                calendar.set(year,month-1,day1);
            }
            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()){
                return;
            }

            calendarView.addDecorators(new EventDecorator(Color.RED, calendarDays, DiaryMain.this));
        }
    }
}