package com.detroitlabs.kyleofori.req2.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.detroitlabs.kyleofori.req2.R;

public class SearchActivity extends Activity implements View.OnClickListener {

    private EditText subredditEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        subredditEntry = (EditText) findViewById(R.id.subredditEntry);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitButton:
                trySubredditSubmit();
                break;
        }
    }

    private void trySubredditSubmit() {
        String subredditEntryText = subredditEntry.getText().toString().trim();

        if (subredditEntryText.isEmpty()) {
            Toast.makeText(this, getString(R.string.subreddit_entry_error), Toast.LENGTH_SHORT).show();
        } else {
            openRedditNavigationActivity(subredditEntryText);
        }
    }

    private void openRedditNavigationActivity(String subredditEntryText) {
        Intent startRedditNavigationActivity = new Intent(this, KhanAcademyNavigationActivity.class);
        startRedditNavigationActivity.putExtra(KhanAcademyNavigationActivity.EXTRA_SUBREDDIT, subredditEntryText);
        startActivity(startRedditNavigationActivity);
    }
}
