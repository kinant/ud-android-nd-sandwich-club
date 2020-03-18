package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    // for populating UI
    TextView mOrigin;
    TextView mKnownAs;
    TextView mIngredients;
    TextView mDescription;
    TextView mLabelAlsoKnown;
    TextView mLabelOrigin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        // Use findViewById to get reference to our textviews from the xml
        mOrigin = findViewById(R.id.origin_tv);
        mKnownAs = findViewById(R.id.also_known_tv);
        mIngredients = findViewById(R.id.ingredients_tv);
        mDescription = findViewById(R.id.description_tv);

        mLabelAlsoKnown = findViewById(R.id.lbl_also_known_tv);
        mLabelOrigin = findViewById(R.id.lbl_origin_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        // Populate UI Text Views with sandwich details
        mOrigin.setText(sandwich.getPlaceOfOrigin());
        mDescription.setText(sandwich.getDescription());

        // if the place of origin is empty
        // we remove it using View.GONE
        // https://stackoverflow.com/questions/3805599/add-delete-view-from-layout
        if(sandwich.getPlaceOfOrigin().isEmpty()){
            mLabelOrigin.setVisibility(View.GONE);
            mOrigin.setVisibility(View.GONE);
        }

        // if the array of also known as has 0 elements,
        // then we remove the UI elements (just so it is prettier)
        if (sandwich.getAlsoKnownAs().isEmpty()){
            mKnownAs.setVisibility(View.GONE);
            mLabelAlsoKnown.setVisibility(View.GONE);
        }

        // Note: We could check for all elements to see
        // if any is empty and remove/hide them, but
        // in this case, only the origin and also known as
        // are missing for some sandwiches

        // iterate over the lists
        for(String knownAs: sandwich.getAlsoKnownAs()){
            mKnownAs.append((knownAs) + "\n");
        }

        for(String ing: sandwich.getIngredients()){
            mIngredients.append((ing) + "\n");
        }
    }
}
