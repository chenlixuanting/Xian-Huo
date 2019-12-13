package cn.edu.guet.xianhuo.feature.settings;


import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import cn.edu.guet.xianhuo.R;

/**
 * 设置Fragment
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }

}
