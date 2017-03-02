package android.csulb.edu.photonotes.support;

import android.content.Context;
import android.csulb.edu.photonotes.R;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Keval on 27-02-2017.
 */

public class SQLOperations extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;


    private static String insert_Query = "INSERT INTO "
            + "PhotoNote"
            + " (Caption, ImagePath)"
            + " VALUES (?, ?);";
    private static String select_Query = "SELECT * FROM " + "PhotoNote";
    private static String createTable_Query = "CREATE TABLE "
            + "PhotoNote"
            + " (_id Integer PRIMARY KEY AUTOINCREMENT , Caption TEXT , ImagePath TEXT  );";


    public SQLOperations(Context context) {
        super(context, String.valueOf(R.string.DATABASE_NAME), null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(createTable_Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "PhotoNote");
        onCreate(db);
    }

    public Cursor getAllEntries() {

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res = db.rawQuery("select * from PhotoNote", null);

            return res;
    }

    public boolean saveNote(String caption, String picPath) {
        SQLiteDatabase db=null;
            db = this.getWritableDatabase();

            db.execSQL(insert_Query, new Object[]{caption, picPath});
        return true;
    }


}
