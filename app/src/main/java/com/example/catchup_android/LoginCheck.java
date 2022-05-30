package com.example.catchup_android;

import android.content.Intent;
import android.util.Log;

public class LoginCheck {
    // 로그인정보를 계속해서 저장하고 있는 변수
    public static UserVo info;
    
    // 로그아웃 기능
    public static void logout() {

        Log.v("resultValue", "[로그아웃 진입]  "+info);
        if(info !=null){
            info=null;

        }
        Log.v("resultValue", "[로그아웃 이탈]  "+info);


    }
}
