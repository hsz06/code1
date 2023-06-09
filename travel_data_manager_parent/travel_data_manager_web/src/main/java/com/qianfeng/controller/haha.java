package com.qianfeng.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class haha {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));
        //$2a$10$jqWYDhqTxRzyaApu2Sn20umAHak..S93seEdu5mWHciZwU1wAF0L6//密文

    }
}
