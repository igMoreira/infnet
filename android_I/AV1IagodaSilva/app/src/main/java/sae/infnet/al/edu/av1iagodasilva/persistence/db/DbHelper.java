package sae.infnet.al.edu.av1iagodasilva.persistence.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by igMoreira on 09/12/17.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Infnet.db";

    private static final String CREATE_CURRICULUM_TABLE =
            "CREATE TABLE Curriculum ( _id TEXT PRIMARY KEY NOT NULL, " +
                    " name TEXT NOT NULL, " +
                    " address TEXT NOT NULL, " +
                    " state TEXT NOT NULL, " +
                    " city TEXT NOT NULL, " +
                    " phone TEXT NOT NULL, " +
                    " email TEXT NOT NULL, " +
                    " edu_institution TEXT, " +
                    " exp_position TEXT, " +
                    " exp_company TEXT, " +
                    " course_name TEXT, " +
                    " course_desc TEXT, " +
                    " pub_desc TEXT );";

    public DbHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CURRICULUM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
