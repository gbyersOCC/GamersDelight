package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.List;

public class GameListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;
    private EditText nameEditText;
    private EditText descText;
    private LinearLayout layout;
    private RatingBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);

        db.addGame(new Game("League of Legends", "Multiplayer online battle arena", 4.5f, "lol.png"));
        db.addGame(new Game("Dark Souls III", "Action role-playing", 4.0f, "ds3.png"));
        db.addGame(new Game("The Division", "Single player experience", 3.5f, "division.png"));
        db.addGame(new Game("Doom FLH", "First person shooter", 2.5f, "doomflh.png"));
        db.addGame(new Game("Battlefield 1", "Single player campaign", 5.0f, "battlefield1.png"));

        gamesList = db.getAllGames();
        // TODO:  Populate all games from the database into the list
        gamesListAdapter = new GameListAdapter(this,R.layout.game_list_item, gamesList);
        // TODO:  Create a new ListAdapter connected to the correct layout file and list
        gamesListView = (ListView)findViewById(R.id.gameListView);
        gamesListView.setAdapter(gamesListAdapter);
        // TODO:  Connect the ListView with the ListAdapter
    }

    public void viewGameDetails(View view) {
        layout = (LinearLayout) findViewById(R.id.gameListLinearLayout) ;
        Game recievedGame = (Game) layout.getTag();
        Intent detailIntent = new Intent(this, GameDetailsActivity.class);

        detailIntent.putExtra("NAME", recievedGame.getName());
        detailIntent.putExtra("DESC", recievedGame.getDescription());
        //needs rating
        detailIntent.putExtra("IMAGE", recievedGame.getImageName());

        startActivity(detailIntent);



        // TODO: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
    }

    public void addGame(View view)
    {

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        descText = (EditText) findViewById(R.id.descriptionEditText);
        bar = (RatingBar) findViewById(R.id.gameListRatingBar) ;

       float barValue = bar.getRating();

        String gameName = nameEditText.getText().toString();

       String description = descText.getText().toString();


        if(description.isEmpty()||gameName.isEmpty())
        {
            Toast.makeText(this, "Description invalid! Does not have text", Toast.LENGTH_SHORT).show();
        }else
        {
            Game newGame = new Game(gameName,description,barValue);
            db.addGame(newGame);
            gamesListAdapter.add(newGame);
        }
        bar.setRating(0);
        nameEditText.setText("");
        descText.setText("");

//            Task newTask = new Task(description, 0);
//
//            //add task to ListAdapter
//            taskListAdapter.add(newTask);
//
//            //add to DataBase
//            database.addTask(newTask);
//
//            taskEditTask.setText("");

        // TODO:  Add a game to the database, list, list adapter
        // TODO:  Make sure the list adapter is updated

        // TODO:  Clear all entries the user made (edit text and rating bar)
    }

    public void clearAllGames(View view)
    {
        gamesList.clear();
        db.deleteAllGames();
        // TODO:  Delete all games from the database and lists
    }

}
