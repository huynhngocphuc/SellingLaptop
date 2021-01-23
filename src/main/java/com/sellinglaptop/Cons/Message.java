package com.sellinglaptop.Cons;

public class Message {
    public static String errUser = "";
    public static String errEmail = "";
    public  static String errPhone = "";
    public  static String errPassword = "";
    public static String errAddress = "";

    public static  void reset() {
        Message.errUser="";
        Message.errAddress = "";
        Message.errPassword = "";
        Message.errPhone = "";
        Message.errEmail = "";
    }
}
