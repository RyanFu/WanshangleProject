package com.wanshangle.ui.preferences;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.wanshangle.R;

public class KTVsManagment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ktvs_managment);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ktvs_managment, menu);
        return true;
    }
    
}
