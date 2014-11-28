package com.detroitlabs.kyleofori.req2.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.detroitlabs.kyleofori.req2.R;
import com.detroitlabs.kyleofori.req2.fragments.SearchResultsListFragment;
import com.detroitlabs.kyleofori.req2.interfaces.FragmentController;

public class KhanAcademyNavigationActivity extends Activity implements FragmentController {

    public static final String EXTRA_SUBREDDIT = "extra_subreddit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khan_academy_navigation);
        loadSubredditFragment();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void changeFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }

    private void loadSubredditFragment() {
        String subreddit = getIntent().getStringExtra(EXTRA_SUBREDDIT);

        if (subreddit != null) {

            SearchResultsListFragment searchResultsListFragment = SearchResultsListFragment.newInstance(subreddit);

            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, searchResultsListFragment)
                    .commit();

        } else {
            throw new IllegalStateException("Must supply a subreddit to RedditNavigationActivity");
        }
    }
}