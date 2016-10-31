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

    private Context context;
    private List<Game> gamesList = new ArrayList<>();
    private int resourceId;

    private ImageView listItemImageView;
    private TextView listItemNameTextView;
    private TextView listItemDescriptionTextView;
    private RatingBar listItemRatingBar;
    private LinearLayout gameListLinearLayout;
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
        context = c;
        resourceId = rId;
        gamesList = games;
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

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(resourceId, null);

        //TODO:  Code for getting the view of a list item correctly inflated.
        Game game = gamesList.get(pos);

        listItemImageView = (ImageView) view.findViewById(R.id.gameListImageView);
        listItemNameTextView = (TextView) view.findViewById(R.id.gameListNameTextView);
        listItemDescriptionTextView = (TextView) view.findViewById(R.id.gameListDescriptionTextView);
        listItemRatingBar = (RatingBar) view.findViewById(R.id.gameListRatingBar);
        gameListLinearLayout = (LinearLayout) view.findViewById(R.id.gameListLinearLayout);

        AssetManager am = context.getAssets();

        try {
            InputStream stream = am.open(game.getImageName());
            Drawable picture = Drawable.createFromStream(stream, game.getName());
           listItemImageView.setImageDrawable(picture);
        }
        catch (IOException ex)
        {
            Log.e("GameListAdapter.class ","error loading: " + game.getImageName(), ex);
        }
        //set the
       listItemNameTextView.setText(game.getName());
        listItemDescriptionTextView.setText(game.getDescription());
        listItemRatingBar.setRating(game.getRating());
        //        bar.setIsIndicator(true);

        gameListLinearLayout.setTag(game);
        return view;
    }
}
