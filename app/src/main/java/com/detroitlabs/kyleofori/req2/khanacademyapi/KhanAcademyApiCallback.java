package com.detroitlabs.kyleofori.req2.khanacademyapi;

import org.json.JSONObject;

/**
 * Created by bobbake4 on 11/13/14.
 */
public interface KhanAcademyApiCallback {

    public void onSuccess(JSONObject response);
    public void onError();

}
