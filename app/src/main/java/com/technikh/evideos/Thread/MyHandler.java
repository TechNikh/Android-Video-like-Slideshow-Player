package com.technikh.evideos.Thread;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Stack;

import static android.content.ContentValues.TAG;

public class MyHandler extends Handler {
    Stack<Message> s = new Stack<>();
    boolean is_paused = false;

    public synchronized void pause() {
        is_paused = true;
    }

    public synchronized void resume() {
        is_paused = false;
        while (!s.empty()) {
            sendMessageAtFrontOfQueue(s.pop());
        }
    }

    @Override
    public void handleMessage(Message msg) {
        if (is_paused) {
            s.push(Message.obtain(msg));
            return;
        } else {
            Log.d(TAG, "handleMessage: " + msg.getData());
            super.handleMessage(msg);
        }
    }
}