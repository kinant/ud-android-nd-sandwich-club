package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Log.d("JSON", "in json function!");

        try {
            // Get the sandwich JSON Object
            JSONObject sandwich = new JSONObject(json);
            Log.d("JSON 1:", sandwich.toString());

            // get the main name
            JSONObject name = sandwich.getJSONObject("name");
            String mainName = name.getString("mainName");
            Log.d("JSON mainName:", mainName);

            // get the also known as array
            JSONArray knownAs = name.getJSONArray("alsoKnownAs");
            List<String> kAs = jsonArrayToList(knownAs);
            Log.d("JSON knownAs:", kAs.toString());

            // get the place of origin
            String origin = sandwich.getString("placeOfOrigin");
            Log.d("JSON origin:", origin);

            // get the description
            String desc = sandwich.getString("description");
            Log.d("JSON desc:", desc);

            // get the image url
            String imgUrl = sandwich.getString("image");
            Log.d("JSON img:", imgUrl);

            // get the ingredients array
            JSONArray ingredients = sandwich.getJSONArray("ingredients");
            List<String> ings = jsonArrayToList(ingredients);
            Log.d("JSON ingredients:", ings.toString());

        } catch(JSONException e){
            e.printStackTrace();
        }

        return null;
    }

    // this function will receive a JSONArray (of strings) and return a list of strings
    private static List<String> jsonArrayToList(JSONArray array){

        List<String> values = new ArrayList<String>();

        // iterate over all JSON array values
        for(int i = 0; i < array.length(); i++){

            try {
                values.add(array.getString(i));
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

        return values;
    }

}
