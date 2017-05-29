package com.example.khlopunov.convertermoney.interfaces;

/**
 * Created by Admin on 23.12.2016.
 */

public interface TaskInterface {
    void onTaskFinish(String rate);
    void onTaskStarted() ;
    void onTaskProgress();
}
