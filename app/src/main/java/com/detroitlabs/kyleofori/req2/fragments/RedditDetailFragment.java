package com.detroitlabs.kyleofori.req2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.detroitlabs.kyleofori.req2.R;
import com.detroitlabs.kyleofori.req2.models.RedditEntry;

/**
 * Created by bobbake4 on 11/13/14.
 */
public class RedditDetailFragment extends Fragment {

    private static final String ARG_REDDIT_ENTRY = "arg_reddit_entry";

    public static RedditDetailFragment newInstance(RedditEntry redditEntry) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_REDDIT_ENTRY, redditEntry);

        RedditDetailFragment redditDetailFragment = new RedditDetailFragment();
        redditDetailFragment.setArguments(args);

        return redditDetailFragment;
    }

    private TextView authorTextView, titleTextView, contentTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reddit_detail, container, false);

        authorTextView = (TextView) view.findViewById(R.id.authorTextView);
        titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        contentTextView = (TextView) view.findViewById(R.id.contentTextView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RedditEntry redditEntry = getArguments().getParcelable(ARG_REDDIT_ENTRY);

        if (redditEntry != null) {

            authorTextView.setText(redditEntry.getAuthor());
            titleTextView.setText(redditEntry.getTitle());
            contentTextView.setText(redditEntry.isSelf() ? redditEntry.getSelftext() : redditEntry.getUrl());

        } else {
            throw new IllegalStateException("Must supply a RedditEntry to RedditDetailFragment");
        }
    }
}
