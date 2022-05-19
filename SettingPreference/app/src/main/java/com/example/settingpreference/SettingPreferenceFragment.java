package com.example.settingpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.ListMenuPresenter;

import java.util.List;

public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences prefs;

    ListPreference soundPreference;
    ListPreference keywordSoundPreference;
    PreferenceScreen keywordScreen;

    public void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        addPreferencesFromResource(R.xml.settings_preference);

        soundPreference = (ListPreference) findPreference("sound_list");
        keywordSoundPreference = (ListPreference) findPreference("keyword_sound_list");
        keywordScreen = (PreferenceScreen) findPreference("keyword_screen");

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        if(!prefs.getString("keyword_sound_list", "").equals("")){
            keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
        }
        if(prefs.getBoolean("keyword", false)){
            keywordScreen.setSummary("사용");
        }

        prefs.registerOnSharedPreferenceChangeListener(preListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener preListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            if(s.equals("sound_list")){
                soundPreference.setSummary(prefs.getString("sound_list", "카톡"));
            }
            if(s.equals("keyword_sound_list")){
                keywordSoundPreference.setSummary(prefs.getString("keyword_sound_list", "카톡"));
            }
        }
    };
}
