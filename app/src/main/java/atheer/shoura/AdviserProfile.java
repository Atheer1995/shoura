package atheer.shoura;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import atheer.forgotpassword.R;

/**
 * Created by Reem on 12/11/2016.
 */

public class AdviserProfile extends AppCompatActivity {

    String username;
    TextView name, field, bio;
    ImageView img;
    Button Qestion,Chat,Provid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        setTitle("عرض المستشار");
        // to recive the number from previce page

        Intent intent = getIntent();
       // username = intent.getStringExtra("username");

         Qestion = (Button) findViewById(R.id.Qestionbutton);
        Qestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change the next page
                //just to try the forgetpasswrod
          Intent i = new Intent(AdviserProfile.this,ForgetPassword.class);
                i.getStringExtra("رقم المستشار ");
                startActivity(i);
            }
        });
        Chat = (Button) findViewById(R.id.Chatbutton);
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //change the next page
                /*
                Intent i = new Intent(AdviserProfile.this,MainActivity.class);
                i.putExtra("رقم المستشار ","");
                startActivity(i);*/
            }
        });
        Provid =(Button) findViewById(R.id.Providbutton);
        Provid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                //change the next page
                Intent i = new Intent(AdviserProfile.this,MainActivity.class);
                i.putExtra("رقم المستشار ","");
                startActivity(i);
           */ }
        });


        username = "غادة";
        name = (TextView) findViewById(R.id.name);
        field = (TextView) findViewById(R.id.field);
        bio = (TextView) findViewById(R.id.bio);
        img = (ImageView) findViewById(R.id.img);

        final MyDB db = new MyDB(this);
         //call get information
        // get user profile information from database
        Cursor cursor = db.getProfile(username);
        if (cursor.moveToFirst()) {
            name.setText(cursor.getString(0));
            field.setText(cursor.getString(1));
            bio.setText(cursor.getString(2));
            byte[] image = cursor.getBlob(3);
            Bitmap bitmap =  getPhoto(image);
            img.setImageBitmap(bitmap);
        }
    }

    //convert photo from byte array to bitmap to be shown in activity
    public Bitmap getPhoto(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


}
