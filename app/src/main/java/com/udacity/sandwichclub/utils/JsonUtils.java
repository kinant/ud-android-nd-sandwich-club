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

        try {
            // Get the sandwich JSON Object
            JSONObject sandwich = new JSONObject(json);

            // get the main name
            JSONObject name = sandwich.getJSONObject("name");
            String mainName = name.getString("mainName");

            // get the also known as array
            List<String> knownAs = jsonArrayToList(name.getJSONArray("alsoKnownAs"));

            // get the place of origin
            String origin = sandwich.getString("placeOfOrigin");

            // get the description
            String desc = sandwich.getString("description");

            // get the image url
            String imgUrl = sandwich.getString("image");

            // get the ingredients array
            List<String> ingredients = jsonArrayToList(sandwich.getJSONArray("ingredients"));

            // create and return the sandwich object
            Sandwich newSandwich = new Sandwich(
                    mainName,
                    knownAs,
                    origin,
                    desc,
                    imgUrl,
                    ingredients
            );

            Log.d("SANDWICH: ", newSandwich.toString());
            return newSandwich;

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
