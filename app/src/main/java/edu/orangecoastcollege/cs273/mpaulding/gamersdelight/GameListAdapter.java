package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to provide custom adapter for the <code>Game</code> list.
 */
public class GameListAdapter extends ArrayAdapter<Game> {

    private Context mContext;
    private List<Game> mGamesList = new ArrayList<>();
    private int mResourceId;
    private ImageView gameListImageView;
    private TextView gameListNameView;
    private TextView gameDescriptionView;
    private RatingBar bar;
    private LinearLayout layout;
    // private TextView gameListNameView;

    /**
     * Creates a new <code>GameListAdapter</code> given a mContext, resource id and list of games.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param games The list of games to display
     */
    public GameListAdapter(Context c, int rId, List<Game> games) {
        super(c, rId, games);
        mContext = c;
        mResourceId = rId;
        mGamesList = games;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the Game selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        Game newGame = mGamesList.get(0);
        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(mResourceId, null);

        //TODO:  Code for getting the view of a list item correctly inflated.
        gameListImageView =(ImageView) view.findViewById(R.id.gameListImageView);
        gameListNameView =(TextView) view.findViewById(R.id.gameDetailsNameTextView);
        gameDescriptionView =(TextView) view.findViewById(R.id.gameDetailsDescriptionTextView);
        layout = (LinearLayout) view.findViewById(R.id.gameListLinearLayout);

        bar = (RatingBar) view.findViewById(R.id.gameRatingBar);
layout.setTag(newGame);

        AssetManager am = getContext().getAssets();
        try {
            InputStream stream = am.open(newGame.getImageName());
            Drawable picture = Drawable.createFromStream(stream, newGame.getName());
           gameListImageView.setImageDrawable(picture);
        }
        catch (IOException ex)
        {
            Log.e("GameListAdapter.class ","R=error loading: " + newGame.getImageName(), ex);
        }

gameListNameView.setText(newGame.getName());
        gameDescriptionView.setText(newGame.getDescription());
        float rating = newGame.getRating();
        bar.setRating(rating);

        return view;
    }
}
