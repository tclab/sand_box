package com.innova.android.holaMundo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HolaMundo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView tv = new TextView(this);
        tv.setText("Hola mundo!!");        
        setContentView(tv);
    }
}