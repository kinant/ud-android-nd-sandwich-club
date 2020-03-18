package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Log.d("JSON", "in json function!");

        try {
            // Get the sandwich JSON Object
            JSONObject sandwich = new JSONObject(json);
            Log.d("JSON 1:", sandwich.toString());
        } catch(JSONException e){
            e.printStackTrace();
        }

        return null;
    }
}
