package com.wanshangle.ui.movie;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.wanshangle.R;
import com.wanshangle.utils.DesityUtils;
import com.wanshangle.utils.LogUtils;

public class MovieActivity extends Activity implements RadioGroup.OnCheckedChangeListener{

    public static final String TAG = MovieActivity.class.getSimpleName();

    private View mSegment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie);
        
        initActionBar();

        if (null == getFragmentManager().findFragmentById(android.R.id.content))
        {
            MovieSearchFragment mF = MovieSearchFragment.newInstance();
            getFragmentManager().beginTransaction().add(android.R.id.content, mF).commit();

        }
    }

    private void initActionBar() {
        final ActionBar bar = getActionBar();


        mSegment = getLayoutInflater().inflate(R.layout.movie_action_segment, null);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        int newGravity = Gravity.CENTER_HORIZONTAL;
        lp.gravity = lp.gravity & ~Gravity.RELATIVE_HORIZONTAL_GRAVITY_MASK | newGravity;
        lp.bottomMargin = DesityUtils.Dp2Px(this, 5);
        lp.topMargin = DesityUtils.Dp2Px(this, 5);
        ((RadioGroup)mSegment.findViewById(R.id.movie_action_segment)).setOnCheckedChangeListener(this);
        bar.setCustomView(mSegment, lp);
        bar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_HOME);
//        bar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie, menu);
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId)
        {
            case android.R.id.button1:
                LogUtils.d(TAG, "Search");
                getFragmentManager().beginTransaction().replace(android.R.id.content, MovieSearchFragment.newInstance()).commit();
                break;
            case android.R.id.button2:
                LogUtils.d(TAG, "List");
                getFragmentManager().beginTransaction().replace(android.R.id.content, MovieListFragment.newInstance()).commit();
                break;
        }
    }
}
