package com.detroitlabs.kyleofori.req2.parsers;

import com.detroitlabs.kyleofori.req2.models.KhanAcademyPlaylist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyleofori on 11/26/14.
 */
public class KhanAcademyJSONParser {

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
}
