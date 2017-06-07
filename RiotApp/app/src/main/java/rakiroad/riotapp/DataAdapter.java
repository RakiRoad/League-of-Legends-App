package rakiroad.riotapp;

import java.io.IOException;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static android.R.attr.name;

/**
 * Created by steve on 6/6/2017.
 */

public class DataAdapter {

    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;

    public DataAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close()
    {
        mDbHelper.close();
    }

    public Cursor getAllDataCursor() {
        try {
            String query = "SELECT * FROM users";

            return mDb.rawQuery(query, null);
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    public boolean userExists(String name){
        try {
            String query = "SELECT username FROM users";

            Cursor c = mDb.rawQuery(query, null);

            while(c.moveToNext()){
                if(c.getString(c.getColumnIndex("Username")).equals(name)){
                    return true;
                }
            }

            return false;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    //Combine this with emailExists later to check both password and email with a single query
    public boolean correctPassword(String email, String passwordEntered){
        try {
            String query = "SELECT password FROM users WHERE email=" + email;

            Cursor c = mDb.rawQuery(query, null);

            while(c.moveToNext()){
                if(c.getString(c.getColumnIndex("Password")).equals(passwordEntered)){
                    return true;
                }
            }

            return false;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }

    //Combine this with correctPassword later to check both email and password with a single query
    public boolean emailExists(String email){
        try {
            String query = "SELECT email FROM users";

            Cursor c = mDb.rawQuery(query, null);

            while(c.moveToNext()){
                if(c.getString(c.getColumnIndex("Email")).equals(email)){
                    return true;
                }
            }

            return false;
        }
        catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }
}
