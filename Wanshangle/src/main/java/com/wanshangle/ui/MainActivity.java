package com.wanshangle.ui;



import android.app.ActionBar;
import android.os.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.wanshangle.R;
import com.wanshangle.api.ApiFault;
import com.wanshangle.api.OnApiCompleteListener;
import com.wanshangle.api.request.BaseResponse;
import com.wanshangle.base.BaseActivity;
import com.wanshangle.main.LocationInfo;
import com.wanshangle.main.WSLApplication;
import com.wanshangle.ui.bar.BarActivity;
import com.wanshangle.ui.ktv.KtvActivity;
import com.wanshangle.ui.movie.MovieActivity;
import com.wanshangle.ui.preferences.PrefsActivity;
import com.wanshangle.ui.show.ShowActivity;
import com.wanshangle.utils.LogUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements OnApiCompleteListener, ActionBar.OnNavigationListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayAdapter mSpinnerAdapter;
    private boolean hasResgisterListener = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initActionBar();

    }

    private void initActionBar() {
        // init ActionBar Spinner Adapter
        mSpinnerAdapter =  ArrayAdapter.createFromResource(this, R.array.support_cities_array, R.layout.main_action_lcoation_list_item_1);
        mSpinnerAdapter.setDropDownViewResource(R.layout.main_action_locate_list_item_checked);

        // Hides the title
        getActionBar().setDisplayShowTitleEnabled(true);
        // Set Spinner mode
        getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        //
        getActionBar().setListNavigationCallbacks(mSpinnerAdapter, this);
        //
        getActionBar().setDisplayShowHomeEnabled(true);
        //
        getActionBar().setDisplayUseLogoEnabled(false);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId())
        {

            case R.id.action_settings:
                startActivityNoArguements(PrefsActivity.class);
                return true;
            case R.id.action_locate:

                ProgressBar mProgress = (ProgressBar)View.inflate(this, R.layout.main_action_locate_progress, null);
                item.setActionView(mProgress);
                final MenuItem locateItem = item;
                LocationClient mLocClient = ((WSLApplication) getApplication()).getLocationClient();

                // This Block need just execute once.
                if (!hasResgisterListener)
                {
                    mLocClient.registerLocationListener(new BDLocationListener() {
                        @Override
                        public void onReceiveLocation(BDLocation bdLocation) {
                            LogUtils.d(TAG, "onReceiveLocation()");
                            LogUtils.d(TAG, "ErrorCode = " + (bdLocation != null ? bdLocation.getLocType() : -1));
                            locateItem.setActionView(null);
                            locateItem.setIcon(android.R.drawable.ic_menu_mylocation);

                        }

                        @Override
                        public void onReceivePoi(BDLocation bdLocation) {

                        }
                    });
                    hasResgisterListener = true;
                }

                if (mLocClient.isStarted())
                {
                    mLocClient.requestLocation();
                }
                else
                {
                    mLocClient.start();
                    mLocClient.requestLocation();
                }


        }
        return super.onMenuItemSelected(featureId, item);
    }


    public void onMovie(View view)
    {
        startActivityNoArguements(MovieActivity.class);
    }

    public void onKTV(View view)
    {
        startActivityNoArguements(KtvActivity.class);
    }

    public void onBar(View view)
    {
        startActivityNoArguements(BarActivity.class);
    }


    public void onShow(View view)
    {
         startActivityNoArguements(ShowActivity.class);
    }


    @Override
    public void onApiError(List<ApiFault> _errorList) {
        LogUtils.d(TAG, "[ detail = " + _errorList.get(0).getErrorDetail() + " ]");
    }

    @Override
    public void onApiDone(BaseResponse _bean) {

    }



    @Override
    public void onApiProcessError() {
        LogUtils.d(TAG, "API Process ERROR");

    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        String [] SUPPORT_CITIES = getResources().getStringArray(R.array.support_cities_array);
        showShortToast(SUPPORT_CITIES[itemPosition]);
        return true;
    }


    static int backTappedCount = 0;
    @Override
    public void onBackPressed() {

        switch (backTappedCount ++)
        {
            case 0:
                showShortToast("Press Back Again To exit !");
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        backTappedCount = 0;
                    }
                };
                timer.schedule(task, 2000);
                break;
            case 1:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLocation();
    }

    private void checkLocation() {
        LocationInfo mLocInfo = ((WSLApplication)getApplication()).getLocation();
        if (mLocInfo != null)
        {
            boolean isContainCity = false;
            int navigationItemPos = 0;
            for (String city : getResources().getStringArray(R.array.support_cities_array))
            {

                if (mLocInfo.getCity().contains(city))
                {
                    isContainCity = true;
                    break;
                }
                navigationItemPos ++;
            }

            if (isContainCity)
            {
                getActionBar().setSelectedNavigationItem(navigationItemPos);
            }
            else
            {
                showShortToast(R.string.toast_choose_a_city_which_was_supported);
            }

        }
    }
}
