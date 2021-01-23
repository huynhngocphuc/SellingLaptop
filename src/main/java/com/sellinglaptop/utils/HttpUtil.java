package com.sellinglaptop.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class
HttpUtil {

    private  String value;

    public HttpUtil(String value){
        this.value=value;

    }
    //Mapping string(type json) with model
    public <T> T toModel(Class<T> tClass){
        try{
            return new ObjectMapper().readValue(value,tClass);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    // Convert json to string
    public static HttpUtil of (BufferedReader reader){
        StringBuffer sb = new StringBuffer();
        try{

            String line;
            while ((line=reader.readLine()) !=null){
                sb.append(line);
            }
            System.out.println(sb.toString());
            return new HttpUtil(sb.toString());
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }
}
