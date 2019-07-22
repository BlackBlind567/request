package blackblind.volleyShrink.request;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class VolleyShrink {

    private Context context;

    public VolleyShrink(Context context) {
        this.context = context;
    }

    public VolleyShrink Url(String url){
        this.Url(url);
        return this;
    }

    public void jsonRequest(Context context, String url, HashMap params,
                            final String responseTAG, final String errorTAG,
                            final String[] objectName){

        final RequestQueue requestQueue = Volley.newRequestQueue(context);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(responseTAG, "methodResponse: "  + response.toString());

                        try {
                            String statusResponseObject = response.getString("status");
                            String msgObject = response.getString("msg");

//                            objectName name = {"image", "sendimage", "text", "text1", "text2"};

                            if (statusResponseObject.equals("200")) {

                                JSONArray jsonArray = response.getJSONArray("response");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject inviteFragResponse = jsonArray.getJSONObject(i);
                                    String[] object = new String[inviteFragResponse.length()];

                                    for (int j = 0; j < inviteFragResponse.length(); j++) {
                                        object[j] = inviteFragResponse.getString(objectName[j]);
                                        Log.d(responseTAG + j + " ", object[j]);
                                    }

                                }
                            }else {
                                Log.d(responseTAG, "else: " + msgObject);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(errorTAG, "methodError: "  + error.getMessage());
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}