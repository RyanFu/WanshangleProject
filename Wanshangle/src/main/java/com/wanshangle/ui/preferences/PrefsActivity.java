package com.wanshangle.ui.preferences;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Toast;

import com.wanshangle.R;
import com.wanshangle.utils.VersionUtils;

/**
 * Created by Zhangneixian on 13-9-9.
 */
public class PrefsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (null == getFragmentManager().findFragmentById(android.R.id.content))
        {
            getFragmentManager().beginTransaction().add(android.R.id.content, new SettingFragment()).commit();
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);

    }




    public static class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener
    {

        Preference mEmptyCachePref;
        Preference mCheckForPref;
        Preference mShare;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_appconfig);
        }


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);



            mEmptyCachePref = getPreferenceScreen().findPreference(getResources().getString(R.string.pref_empty_cache_key));
            mEmptyCachePref.setSummary(getCacheSize());
            mEmptyCachePref.setOnPreferenceClickListener(this);

            mCheckForPref = getPreferenceScreen().findPreference(getResources().getString(R.string.pref_check_for_updates_key));
            StringBuffer verName = new StringBuffer();
            verName.append("v").append(VersionUtils.getVersionName(getActivity()));
            mCheckForPref.setSummary(verName.toString());
            mCheckForPref.setOnPreferenceClickListener(this);

            mShare = getPreferenceScreen().findPreference(getResources().getString(R.string.pref_share_key));
            mShare.setOnPreferenceClickListener(this);

        }

        private CharSequence getCacheSize() {
            return "10.3M";
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            String key = preference.getKey();
            if (!TextUtils.isEmpty(key))
            {
                if (key.equals(getResources().getString(R.string.pref_empty_cache_key)))
                {
                    Toast.makeText(getActivity(), R.string.pref_empty_cache_key, Toast.LENGTH_SHORT).show();
                }
                else if (key.equals(getResources().getString(R.string.pref_check_for_updates_key)))
                {
                    Toast.makeText(getActivity(), R.string.pref_check_for_updates_key, Toast.LENGTH_SHORT).show();
                }
                else if (key.equals(getResources().getString(R.string.pref_share_key)))
                {
                    Toast.makeText(getActivity(), R.string.pref_share_key, Toast.LENGTH_SHORT).show();
                }
                return true;

            }
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}