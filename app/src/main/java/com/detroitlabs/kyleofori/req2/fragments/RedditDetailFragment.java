package com.detroitlabs.kyleofori.req2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.detroitlabs.kyleofori.req2.R;
import com.detroitlabs.kyleofori.req2.models.KhanAcademyPlaylist;

/**
 * Created by bobbake4 on 11/13/14.
 */
public class RedditDetailFragment extends Fragment {

    private static final String ARG_REDDIT_ENTRY = "arg_reddit_entry";

    public static RedditDetailFragment newInstance(KhanAcademyPlaylist redditEntry) {

        Bundle args = new Bundle();
        args.putParcelable(ARG_REDDIT_ENTRY, redditEntry);

        RedditDetailFragment redditDetailFragment = new RedditDetailFragment();
        redditDetailFragment.setArguments(args);

        return redditDetailFragment;
    }

    private TextView titleTextView, kaUrlTextView, descriptionTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reddit_detail, container, false);

        titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        kaUrlTextView = (TextView) view.findViewById(R.id.kaUrlTextView);
        descriptionTextView = (TextView) view.findViewById(R.id.descriptionTextView);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        KhanAcademyPlaylist redditEntry = getArguments().getParcelable(ARG_REDDIT_ENTRY);

        if (redditEntry != null) {

            titleTextView.setText(redditEntry.getTitle());
            kaUrlTextView.setText(redditEntry.getKaUrl());
            descriptionTextView.setText(redditEntry.getDescription());

        } else {
            throw new IllegalStateException("Must supply a RedditEntry to RedditDetailFragment");
        }
    }
}
