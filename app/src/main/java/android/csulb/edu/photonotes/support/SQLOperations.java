package android.csulb.edu.photonotes.support;

import android.content.Context;
import android.csulb.edu.photonotes.R;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Keval on 27-02-2017.
 */

public class SQLOperations extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static String insert_Query="INSERT INTO "
            + R.string.table_name
            + " (Caption, ImagePath)"
            + " VALUES (?, ?);";
    private static String select_Query= "SELECT * FROM " + R.string.table_name;
    private static String createTable_Query = "CREATE TABLE IF NOT EXISTS "
            + R.string.table_name
            + " (Caption VARCHAR, ImagePath VARCHAR);";


    public SQLOperations(Context context){
        super(context, String.valueOf(R.string.DATABASE_NAME), null, DATABASE_VERSION);
    }

    public Map<String,String> getAllEntries(){
        return null;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable_Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
