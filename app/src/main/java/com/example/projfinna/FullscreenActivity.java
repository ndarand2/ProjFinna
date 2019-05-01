package com.example.projfinna;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TextClock;
import android.media.MediaPlayer;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;
    /**current tone
     * 0 if its spotify.
     */
    public static int TONE = 0;
    /**AlarmTime.*/
    public static String ALARMTIME;
    /**Spotify thingy.*/
    public static String SPOTIFYSONG;
    public static MediaPlayer alarm;
    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        android.content.SharedPreferences preferences = getSharedPreferences("sharedPrefs", 0);
        ALARMTIME = preferences.getString("ALARMTIME", "00:00");

        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        findViewById(R.id.SpotifyButton).setOnTouchListener(mDelayHideTouchListener);
        findViewById(R.id.ToneButton).setOnTouchListener(mDelayHideTouchListener);
        setUpButtons();
        startService(new Intent(this, BackgroundBS.class));
        //to push comment
    }

    private void setUpButtons() {
        android.widget.Button spotifyBut = findViewById(R.id.SpotifyButton);
        spotifyBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(FullscreenActivity.this, SPOTIFY API CALLS))
            }
        });
        android.widget.Button toneBut = findViewById(R.id.ToneButton);
        toneBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new android.content.Intent(FullscreenActivity.this, DefaultTones.class));
            }
        });
        TextClock clock = findViewById(R.id.textClock);
        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new android.content.Intent(FullscreenActivity.this, SetTime.class ));
            }
        });
        AnalogClock clock2 = findViewById(R.id.simpleAnalogClock);
        clock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new android.content.Intent(FullscreenActivity.this, SetTime.class ));
            }
        });
        MediaPlayer mediaPlayer1 = MediaPlayer.create(getBaseContext(), R.raw.tycho);
        MediaPlayer mediaPlayer2 = MediaPlayer.create(getBaseContext(), R.raw.tornado);
        MediaPlayer mediaPlayer3 = MediaPlayer.create(getBaseContext(), R.raw.train);
        MediaPlayer mediaPlayer4 = MediaPlayer.create(getBaseContext(), R.raw.jeff);
        //mediaPlayer1.start();
        //comment bs2.
    }
    public void onPause()
    {
        super.onPause();
        android.content.SharedPreferences preferences = getSharedPreferences("sharedPrefs", 0);
        android.content.SharedPreferences.Editor pref = preferences.edit();
        pref.putString("ALARMTIME", ALARMTIME);
        pref.putInt("ALARMTYPE", TONE);
        if (SPOTIFYSONG != null) {
            pref.putString("SPOTIFYSONG", SPOTIFYSONG);
        }
        // Commit to storage
        pref.commit();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay.
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public static String getALARMTIME() {
        return ALARMTIME;
    }

}
