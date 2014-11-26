package com.detroitlabs.kyleofori.req2.khanacademyapi;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bobbake4 on 11/13/14.
 */
public class KhanAcademyApi {

    private static final String ROOT_URL = "www.reddit.com";

    private static KhanAcademyApi redditApi;

    public static KhanAcademyApi getRedditApi() {
        if (redditApi == null) {
            redditApi = new KhanAcademyApi();
        }

        return redditApi;
    }

    private KhanAcademyApi() {
    }

    public void getSubredditEntries(String subreddit, KhanAcademyApiCallback callback) {

        Uri uri = new Uri.Builder()
                .scheme("http")
                .authority(ROOT_URL)
                .appendPath("r")
                .appendPath(subreddit)
                .appendPath(".json")
                .build();

        new LoadDataInBackground(callback).execute(uri);
    }

    private JSONObject getJSONObjectFromUri(Uri uri) throws IOException, JSONException {
        URLConnection httpURLConnection = new URL(uri.toString()).openConnection();

        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        int bytesRead;
        StringBuilder stringBuilder = new StringBuilder();

        while ((bytesRead = bufferedInputStream.read()) != -1) {
            stringBuilder.append((char)bytesRead);
        }

        return new JSONObject(stringBuilder.toString());
    }

    private class LoadDataInBackground extends AsyncTask<Uri, Void, JSONObject> {

        private KhanAcademyApiCallback redditApiCallback;

        private LoadDataInBackground(KhanAcademyApiCallback redditApiCallback) {
            this.redditApiCallback = redditApiCallback;
        }

        @Override
        protected JSONObject doInBackground(Uri... params) {

            try {
                Uri uri = params[0];
                return getJSONObjectFromUri(uri);
            } catch (IOException e) {
                return null;
            } catch (JSONException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject result) {
            if (result != null) {
                this.redditApiCallback.onSuccess(result);
            } else {
                this.redditApiCallback.onError();
            }
        }
    }

}
