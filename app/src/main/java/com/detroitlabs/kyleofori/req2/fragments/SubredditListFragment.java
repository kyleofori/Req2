package com.detroitlabs.kyleofori.req2.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.detroitlabs.kyleofori.req2.adapters.SubredditListAdapter;
import com.detroitlabs.kyleofori.req2.interfaces.FragmentController;
import com.detroitlabs.kyleofori.req2.models.RedditEntry;
import com.detroitlabs.kyleofori.req2.khanacademyapi.KhanAcademyApi;
import com.detroitlabs.kyleofori.req2.khanacademyapi.KhanAcademyApiCallback;

import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by bobbake4 on 11/13/14.
 */
public class SubredditListFragment extends ListFragment implements KhanAcademyApiCallback {

    private static final String ARG_SUBREDDIT = "arg_subreddit";
    private static final long REFRESH_INTERVAL = TimeUnit.MINUTES.toMillis(1);

    public static SubredditListFragment newInstance(String subreddit) {

        Bundle args = new Bundle();
        args.putString(ARG_SUBREDDIT, subreddit);

        SubredditListFragment subredditListFragment = new SubredditListFragment();
        subredditListFragment.setArguments(args);

        return subredditListFragment;
    }

    private SubredditListAdapter subredditListAdapter;
    private KhanAcademyApi redditApi = KhanAcademyApi.getRedditApi();
    private Timer refreshTimer;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        subredditListAdapter = new SubredditListAdapter(getActivity());
        setListAdapter(subredditListAdapter);
        loadRedditEntries();
    }

    @Override
    public void onResume() {
        super.onResume();
        startRefreshTimer();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopRefreshTimer();
    }

    @Override
    public void onListItemClick(ListView listView, View row, int position, long id) {

        if (getActivity() instanceof FragmentController) {

            RedditEntry redditEntry = (RedditEntry) listView.getAdapter().getItem(position);
            RedditDetailFragment redditDetailFragment = RedditDetailFragment.newInstance(redditEntry);

            FragmentController fragmentController = (FragmentController) getActivity();
            fragmentController.changeFragment(redditDetailFragment, true);

        } else {
            throw new IllegalArgumentException("Your activity must implement the FragmentController interface");
        }

    }

    @Override
    public void onSuccess(JSONObject response) {
        if (isAdded()) {
            List<RedditEntry> redditEntries = RedditEntry.parseJSONObject(response);
            subredditListAdapter.clear();
            subredditListAdapter.addAll(redditEntries);
        }
    }

    @Override
    public void onError() {
        if (isAdded()) {
            Toast.makeText(getActivity(), "Error loading Subreddit list", Toast.LENGTH_SHORT).show();
        }
    }

    private void startRefreshTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                autoRefreshList();
            }
        };

        refreshTimer = new Timer();
        refreshTimer.schedule(timerTask, REFRESH_INTERVAL, REFRESH_INTERVAL);
    }

    private void stopRefreshTimer() {
        refreshTimer.cancel();
        refreshTimer = null;
    }

    private void autoRefreshList() {
        if (isAdded()) {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), "Auto refreshing list", Toast.LENGTH_SHORT).show();
                }
            });

            loadRedditEntries();
        }
    }

    private void loadRedditEntries() {
        String subreddit = getArguments().getString(ARG_SUBREDDIT);

        if (subreddit != null) {
            redditApi.getSubredditEntries(subreddit, this);
        } else {
            throw new IllegalStateException("Must supply a subreddit to SubredditListFragment");
        }
    }
}
