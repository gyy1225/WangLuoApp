package com.example.wangluo.Utils;

import com.google.gson.Gson;

/**
 * Created by ASUS on 2018/7/7.
 */

public class GsonUtils {


        private static Gson instance;
        public GsonUtils(){}

        public static Gson getInstance(){
            if (instance==null){
                instance = new Gson();
            }
            return instance;
        }


    }

