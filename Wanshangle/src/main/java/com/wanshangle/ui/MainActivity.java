package com.wanshangle.ui;



import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.*;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.wanshangle.R;
import com.wanshangle.api.ApiFault;
import com.wanshangle.api.OnApiCompleteListener;
import com.wanshangle.api.request.BaseResponse;
import com.wanshangle.base.BaseActivity;
import com.wanshangle.main.LocationInfo;
import com.wanshangle.main.WSLApplication;
import com.wanshangle.ui.preferences.PrefsActivity;
import com.wanshangle.utils.LocationUtils;
import com.wanshangle.utils.LogUtils;
import com.wanshangle.utils.NetUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity implements OnApiCompleteListener, ActionBar.OnNavigationListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private ArrayAdapter mSpinnerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initActionBar();
    }

    private void initActionBar() {
        // init ActionBar Spinner Adapter
        mSpinnerAdapter =  ArrayAdapter.createFromResource(this, R.array.support_cities_array, R.layout.action_lcoation_list_item_1);
        mSpinnerAdapter.setDropDownViewResource(R.layout.action_locate_list_item_checked);

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
        //
//        getActionBar().setSelectedNavigationItem(2);

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

                ProgressBar mProgress = new ProgressBar(this);
                mProgress.setScrollBarStyle(android.R.attr.progressBarStyleSmall);
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setPadding(18, 18, 18, 18);
                linearLayout.addView(mProgress, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                item.setActionView(linearLayout);
        }
        return super.onMenuItemSelected(featureId, item);
    }

    //demo method
    public void showTitle(View view)
    {
        int change = getActionBar().getDisplayOptions() ^ ActionBar.DISPLAY_SHOW_TITLE;
        getActionBar().setDisplayOptions(change , ActionBar.DISPLAY_SHOW_TITLE);
    }

    public void onMovie(View view)
    {
        if (NetUtils.isNetworkAvailable(this))
        {
            showShortToast("Network Is Available");
        }
        else
        {
            showShortToast("Network Is Not Available");
        }
    }

    public void onKTV(View view)
    {
        if (NetUtils.isNetworkConnected(this))
        {
            showShortToast("Network Is Connected");
        }
        else
        {
            showShortToast("Network Is Not Connected");
        }
    }

    public void onBar(View view)
    {
        if (LocationUtils.isGPSAvailable(this))
        {
            showShortToast("WIFI Is Available");
        }
        else
        {
            showShortToast("WIFI Is Not Available");
        }
    }


    public void onShow(View view)
    {




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


    class SupportCitiesAdapter extends ArrayAdapter
    {
        private Context mContext;
        private LayoutInflater mInflater;
        private int mStringsId;



        public SupportCitiesAdapter(Context context, int resource, int stringsId) {
            super(context, resource, stringsId);
            mContext = context;
            mStringsId = stringsId;
            if (null != mContext)
                mInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            clear();
            for (String s : getResources().getStringArray(mStringsId))
            {
                add(s);
            }
        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return createDropDownViewFromRes(position, convertView, parent, R.layout.activity_main_action_bar_dropdown_item);
        }



        private View createDropDownViewFromRes(int _position, View _convertView, ViewGroup _parent, int resId)
        {
            ViewHolder holder;
            if ( null == _convertView)
            {
                _convertView = mInflater.inflate(resId, _parent, false);
                holder = new ViewHolder();
                holder.tvCity = (TextView) _convertView.findViewById(R.id.activity_main_action_bar_dropdown_tv);
                holder.ivIndicator = (ImageView) _convertView.findViewById(R.id.activity_main_action_bar_dropdown_iv);
                _convertView.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) _convertView.getTag();
            }

            int selectIndex = getActionBar().getSelectedNavigationIndex();

            holder.tvCity.setText(getItem(_position).toString());
            holder.ivIndicator.setBackgroundResource(selectIndex == _position ? R.drawable.shape_circle_blue : Color.TRANSPARENT);

            return _convertView;
        }



    }

    static class ViewHolder
    {
        ImageView ivIndicator;
        TextView tvCity;
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

        }
    }
}
