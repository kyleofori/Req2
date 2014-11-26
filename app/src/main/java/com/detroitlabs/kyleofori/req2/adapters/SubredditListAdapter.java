package com.detroitlabs.kyleofori.req2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detroitlabs.kyleofori.req2.R;
import com.detroitlabs.kyleofori.req2.models.RedditEntry;

/**
 * Created by bobbake4 on 11/13/14.
 */
public class SubredditListAdapter extends ArrayAdapter<RedditEntry> {

    public SubredditListAdapter(Context context) {
        super(context, R.layout.include_reddit_entry_row);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.include_reddit_entry_row, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RedditEntry redditEntry = getItem(position);

        viewHolder.titleTextView.setText(redditEntry.getTitle());
        viewHolder.authorTextView.setText(redditEntry.getAuthor());

        return convertView;
    }

    private static class ViewHolder {

        private TextView titleTextView;
        private TextView authorTextView;

        public ViewHolder(View rootView) {
            this.titleTextView = (TextView) rootView.findViewById(R.id.titleTextView);
            this.authorTextView = (TextView) rootView.findViewById(R.id.authorTextView);
        }

    }
}
