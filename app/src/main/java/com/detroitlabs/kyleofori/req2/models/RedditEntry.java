package com.detroitlabs.kyleofori.req2.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobbake4 on 11/13/14.
 */
public class RedditEntry implements Parcelable {

    public static List<RedditEntry> parseJSONObject(JSONObject jsonObject) {

        try {
            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray childrenArray = dataObject.getJSONArray("children");

            List<RedditEntry> redditEntries = new ArrayList<RedditEntry>();

            for (int index = 0; index < childrenArray.length(); index++) {

                JSONObject childEntry = childrenArray.getJSONObject(index);

                RedditEntry newRedditEntry = new RedditEntry(childEntry);

                redditEntries.add(newRedditEntry);
            }

            return redditEntries;
        } catch (JSONException e) {
            return new ArrayList<RedditEntry>();
        }
    }

    private String author, title, url, selftext;
    private boolean isSelf;

    private RedditEntry(JSONObject jsonObject) throws JSONException {

        JSONObject redditEntryData = jsonObject.getJSONObject("data");

        author = redditEntryData.optString("author", "Unknown Author");
        title = redditEntryData.optString("title", "Unknown Title");
        isSelf = redditEntryData.optBoolean("is_self", false);

        if (isSelf) {
            selftext = redditEntryData.optString("selftext", "");
        } else {
            url = redditEntryData.optString("url", "");
        }
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSelftext() {
        return selftext;
    }

    public boolean isSelf() {
        return isSelf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.selftext);
        dest.writeByte(isSelf ? (byte) 1 : (byte) 0);
    }

    private RedditEntry(Parcel in) {
        this.author = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.selftext = in.readString();
        this.isSelf = in.readByte() != 0;
    }

    public static final Creator<RedditEntry> CREATOR = new Creator<RedditEntry>() {
        public RedditEntry createFromParcel(Parcel source) {
            return new RedditEntry(source);
        }

        public RedditEntry[] newArray(int size) {
            return new RedditEntry[size];
        }
    };
}
