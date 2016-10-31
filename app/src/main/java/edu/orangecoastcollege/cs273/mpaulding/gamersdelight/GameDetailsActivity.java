package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    private Context context = (Context)this;

    private ImageView detailImageView;
    private TextView detailTextNameView;
    private TextView detailTextDescView;
    private RatingBar detailRBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // TODO:  Use the Intent object sent from GameListActivity to populate the Views on
        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.

        //call super OnCreate
        super.onCreate(savedInstanceState);

        //setContentView(the layout we want to show);
        setContentView(R.layout.activity_game_details);

        //get reference for UI widgets
        detailImageView = (ImageView) findViewById(R.id.gameDetailsImageView);
        detailTextNameView = (TextView) findViewById(R.id.gameDetailsNameTextView);
        detailTextDescView = (TextView) findViewById(R.id.gameDetailsDescriptionTextView);
        detailRBarView = (RatingBar) findViewById(R.id.gameDetailsRatingBar);

        String name, imageName, description;
        float ratingResults;

        //catch the Intent sent over from GameListActivity.java
        Intent recievedIntent = getIntent();

        name = recievedIntent.getStringExtra("NAME");
        description = recievedIntent.getStringExtra("DESC");
        imageName = recievedIntent.getStringExtra("IMAGE");
        ratingResults = recievedIntent.getFloatExtra("RATING",0);

        //set the rest of the widgets
        detailTextNameView.setText(name);
        detailTextDescView.setText(description);
        detailRBarView.setRating(ratingResults);

        //Asser
        AssetManager am = context.getAssets();

       try{

            InputStream in = am.open(imageName);
            Drawable picture = Drawable.createFromStream(in, imageName);
            detailImageView.setImageDrawable(picture);

            in.close();
       }catch(IOException e){
           Log.e("Gamers Delight","Error loading image" + e.getMessage());
       }

    }
}
