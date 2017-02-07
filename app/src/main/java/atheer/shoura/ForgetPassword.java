package atheer.shoura;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import atheer.forgotpassword.R;

public class ForgetPassword extends AppCompatActivity {

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //check all function how to do it ??
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        setTitle(" نسيت كلمة المرور ");

        final Button button = (Button) findViewById(R.id.send);
        final EditText username = (EditText) findViewById(R.id.name);
        final MyDB db = new MyDB(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get Email from user name
                Cursor cr =  db.getUserEmail(username.getText().toString());
                //check if Exist ?

                if(cr==null){
                    //if not Exist
                    Toast.makeText(getBaseContext()," لا يوجد اسم مستخدم ",Toast.LENGTH_LONG).show();
                }
                else {
                    //if Exist true !
                    int passwordLength = 10;
                    String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                    char[] allowedCharsArray = allowedChars.toCharArray();
                    char[] chars = new char[passwordLength];
                    Random random = new Random();

                    for (int i = 0; i < passwordLength; i++) {
                        chars[i] = allowedCharsArray[random.nextInt(allowedChars.length())];}
                    final String newPassword = String.valueOf(chars).substring(0,passwordLength);
                    Log.d("Password",newPassword);
                    if (cr.moveToFirst()) {
                    email = cr.getString(0);
                    }
                    sendEmail(newPassword);
                 /*Intent i = new Intent(AdviserProfile.this,MainActivity.class); back to login page
                  i.putExtra("رقم المستشار ","");
                  startActivity(i);*/
                }
            }

        });
    }

    private void sendEmail(String password) {
        //Getting content for email
        String subject = "Shura";
        String message = "Your new password is " + password;
        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);
        //Executing sendmail to send email
        sm.execute();
    }

    public boolean isExist(String username) {
        //find if username exist in Database
        return true;
    }




}
