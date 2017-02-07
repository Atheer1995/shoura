package atheer.shoura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.ByteArrayOutputStream;
import java.io.File;

import atheer.forgotpassword.R;

/**
 * Created by Reem on 2/6/2017.
 */

public class MyDB extends SQLiteOpenHelper {
    Bitmap icon;

    public MyDB (Context context) {
        super(context, "ee", null, 2);
        icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ad);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User (username text primary key,email text,password text);");
        db.execSQL("insert into User values ('Reem', 'reem.aljunaid@hotmail.com', 'Reem123')");
        db.execSQL("create table Expert (name text primary key,field text,bio text, photo BLOB);");
        ContentValues cv = new  ContentValues();
        cv.put("name", "غادة");
        cv.put("field", "نفسي");
        cv.put("bio", "jbhfjvn njrugh4uj u 4nrijhufhru bjrbufbjrfj rr fjgvojr");
        cv.put("photo", getBytes(icon));
        db.insert( "Expert", null, cv );
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users;");
        onCreate(db);
    }

    public Cursor getUserEmail(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select email from User where username = ?", new String[] {username});
        return res;
    }

    public Cursor getProfile(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Expert where name = ?", new String[] {name});
        return res;
    }

}
