package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    static final String DATABASE_NAME = "GamersDelight";
    private static final String DATABASE_TABLE = "Games";
    private static final int DATABASE_VERSION = 1;


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_RATING = "rating";
    private static final String FIELD_IMAGE_NAME = "image_name";


    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){
   String table = "CREATE TABLE " + DATABASE_NAME+" ( "+KEY_FIELD_ID+ " INTEGER AUTOINCREMENT "+
           FIELD_NAME + " TEXT " + FIELD_DESCRIPTION + " TEXT " + FIELD_RATING+ " REAL "+ FIELD_IMAGE_NAME+" Text )";
        database.execSQL(table);
        // TODO:  Define the SQL statement to create a new table with the fields above.
        // TODO:  Primary key should auto increment
        // TODO:  Exceute the SQL statement
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(database);

        // TODO:  Execute the SQL statment to drop the table
        // TODO:  Recreate the database
    }

    //********** DATABASE OPERATIONS:  ADD, GETALL, EDIT, DELETE

    public void addGame(Game game) {
 SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, game.getName());
        values.put(FIELD_DESCRIPTION, game.getDescription());
        values.put(FIELD_RATING, game.getRating());
        values.put(FIELD_IMAGE_NAME, game.getImageName());

        db.insert(DATABASE_TABLE,null, values);
        // TODO:  Write the code to add a new game to the database
        db.close();
    }

    public ArrayList<Game> getAllGames() {
        ArrayList<Game> gameList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results= db.query(DATABASE_TABLE, null, null, null, null, null, null);

               //use if else for values   moveToFirst       MoveTo NEXT
        if(results.moveToFirst())
        {do{
            //use Cursor to move thru the column headers
            int id = results.getInt(0);
            String name = results.getString(1);
            String desc = results.getString(2);
            float rating = results.getFloat(3);
            String imageName = results.getString(4);

            Game newGame = new Game(id, name, desc, rating, imageName);
        }while(results.moveToNext());
        }
db.close();
        // TODO:  Write the code to get all games from the database and return in ArrayList
//return arrayList
        return gameList;
    }

    public void deleteAllGames()
    {
        SQLiteDatabase db = this.getWritableDatabase();
db.delete(DATABASE_NAME, null, null);
        db.close();
        // TODO:  Write the code to delete all games from the database
    }

    public void updateGame(Game game){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIELD_NAME, game.getName());
        values.put(FIELD_DESCRIPTION, game.getDescription());
        values.put(FIELD_RATING, game.getRating());
        values.put(FIELD_IMAGE_NAME, game.getImageName());
//update uses table name values then search for keyField Id, new String array static valuesOf method (games.getId)
db.update(DATABASE_TABLE,values,KEY_FIELD_ID+"=?",new String[] {String.valueOf(game.getId())});

        // TODO:  Write the code to update a game in the database.
    }

    public Game getGame(int id) {
    SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_FIELD_ID,FIELD_NAME,FIELD_RATING,FIELD_IMAGE_NAME}, KEY_FIELD_ID+"=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if(cursor!= null)
        {
            cursor.moveToFirst();
        }
        Game cursGame = new Game(cursor.getInt(0),cursor.getString(1),cursor.getString(2), cursor.getFloat(3), cursor.getString(4));
        db.close();
        return cursGame;

        // TODO:  Write the code to get a specific game from the database
        // TODO:  Replace the return null statement below with the game from the database.

    }





}
