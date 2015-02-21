package com.teamname.invaders;

import android.view.View;
import android.graphics.Rect;

/**
 * Created by Lenovo-USER on 2/17/2015.
 */
public class Game extends Thread {

    private DrawView parentView;

    private final MainActivity mainActivity;

    private Rect rect;
    private int width;
    private int height;

    public Game(DrawView parent, MainActivity mainActivity) {
        this.parentView = parent;
        this.mainActivity = mainActivity;
        rect = new Rect(0, 0, 150, 150);
        width = 150;
        height = 150;
    }

    public void moveX(int dx) {

        if (dx < 0) {
            dx = Math.max(0 - rect.left, dx);
        } else if (dx >= 0) {
            dx = Math.min(1080 - rect.right, dx);
        }
        rect.left += dx;
        rect.right += dx;
    }

    public void moveY(int dy) {
        if (dy < 0) {
            dy = Math.max(0 - rect.top, dy);
        } else if (dy >= 0) {
            dy = Math.min(1920 - rect.bottom, dy);
        }
        rect.top += dy;
        rect.bottom += dy;
    }

    public int getX() {
        return rect.left;
    }

    public int getY() {
        return rect.top;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void center() {
        int dx = parentView.getWidth() / 2 - rect.left;
        int dy = parentView.getHeight() / 2 - rect.top;

        moveX(dx);
        moveY(dy);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (parentView) {
                mainActivity.runOnUiThread(new Runnable() {
                    public void run() {
                        parentView.invalidate();
                    }
                });
            }
            delay(20);
        }
    }

    private void delay(long milliseconds) {
        Thread current = Thread.currentThread();
        try {
            current.sleep(milliseconds);
        } catch (Exception e) {

        }
    }
}
