package com.detroitlabs.kyleofori.req2.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobbake4 on 11/13/14.
 */
public class KhanAcademyPlaylist /*implements Parcelable*/ {

    public static List<KhanAcademyPlaylist> parseJSONObject(JSONArray jsonArray) {

        try {
            JSONArray playlistsArray = jsonArray.getJSONArray(0);

            List<KhanAcademyPlaylist> khanAcademyPlaylists = new ArrayList<KhanAcademyPlaylist>();

            for (int index = 0; index < playlistsArray.length(); index++) {

                JSONObject playlistObject = playlistsArray.getJSONObject(index);

                KhanAcademyPlaylist newKhanAcademyPlaylist = new KhanAcademyPlaylist(playlistObject);

                khanAcademyPlaylists.add(newKhanAcademyPlaylist);
            }

            return khanAcademyPlaylists;
        } catch (JSONException e) {
            return new ArrayList<KhanAcademyPlaylist>();
        }
    }

    private String title, ka_url, description;

    private KhanAcademyPlaylist(JSONObject playlistObject) throws JSONException {
        ka_url = playlistObject.optString("url", "Unknown URL");
        title = playlistObject.optString("title", "Unknown Title");
        description = playlistObject.optString("description", "Unknown Description");
    }

}
