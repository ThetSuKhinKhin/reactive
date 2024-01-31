package com.example.webfluxdemo1.util;

import java.io.FileNotFoundException;

public class SleepUtil {

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e) {   // check exception must have throwable so try{ have sth}
            e.printStackTrace();
        }
    }
}
