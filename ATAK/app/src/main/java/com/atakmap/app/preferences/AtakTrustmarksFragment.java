
package com.atakmap.app.preferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.atakmap.android.gui.WebViewer;


import androidx.core.app.NavUtils;

import com.atakmap.android.gui.HintDialogHelper;
import com.atakmap.android.ipc.AtakBroadcast;
import com.atakmap.android.preference.AtakPreferenceFragment;
import com.atakmap.android.preference.PreferenceSearchIndex;
import com.atakmap.android.user.feedback.UserFeedbackCollector;
import com.atakmap.app.ATAKActivity;
import com.atakmap.app.R;
import com.atakmap.coremap.filesystem.FileSystemUtils;
import com.atakmap.coremap.log.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AtakTrustmarksFragment extends AtakPreferenceFragment {

    private static final String TAG = "AtakTrustmarksFragment";
    private Context context;

    public AtakTrustmarksFragment() {
        super(R.xml.trustmarks, R.string.trustmarks);
    }

    public static java.util.List<PreferenceSearchIndex> index(Context context) {
        return index(context,
                AtakTrustmarksFragment.class,
                R.string.trustmarks,
                R.drawable.passphrase);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(getResourceID());

        context = getActivity();

        Preference showTrustmarkDetails = findPreference("showTrustmarkDetails");
        showTrustmarkDetails
                .setOnPreferenceClickListener(
                        new Preference.OnPreferenceClickListener() {
                            @Override
                            public boolean onPreferenceClick(
                                    Preference preference) {
/*
                                AlertDialog.Builder builder = new AlertDialog.Builder(
                                        context);
                                File f = FileSystemUtils
                                        .getItem(
                                                FileSystemUtils.SUPPORT_DIRECTORY
                                                        + File.separatorChar
                                                        + "support.inf");
                                View v = LayoutInflater.from(context)
                                        .inflate(R.layout.hint_screen, null);
                                TextView tv = v
                                        .findViewById(R.id.message);
                                tv.setText(Html.fromHtml(read(f)));
                                final CheckBox cb = v
                                        .findViewById(R.id.showAgain);
                                cb.setVisibility(View.GONE);

                                builder.setCancelable(true)
                                        .setTitle(R.string.trustmark_text001)
                                        .setView(v)
                                        .setPositiveButton(R.string.ok, null)
                                        .show();
*/
                                try {
                                    WebViewer.show(
                                            "file:///android_asset/trustmark/trustmark_test.xml",
                                            context, 250);
                                } catch (Exception e) {
                                    Log.e(TAG, "error loading Trustmark", e);
                                }

                                return true;
                            }
                        });

    }

    static public String read(File f) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            Log.w(TAG, "Failed to read", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                }
            }
        }
        return "";
    }

}
