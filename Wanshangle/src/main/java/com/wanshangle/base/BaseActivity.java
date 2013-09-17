package com.wanshangle.base;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Zhangneixian on 13-8-29.
 */
public class BaseActivity extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void showShortToast(String content)
    {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void showShortToast(int string_id)
    {
        Toast.makeText(this, string_id, Toast.LENGTH_SHORT).show();
    }

    protected void startActivityNoArguements(Class <? extends Activity>  cls)
    {
        startActivity(new Intent(this, cls));
    }

    
}