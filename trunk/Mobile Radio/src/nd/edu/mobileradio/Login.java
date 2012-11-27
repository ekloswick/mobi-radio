package nd.edu.mobileradio;


import com.parse.*;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

	private Button loginButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }*/
    
    public void onStart() {
		super.onStart();
			
		// Initialize the buttons with listeners for click events
		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             Log.d("Click","Button Clicked");
             EditText mEdit;
 
             mEdit   = (EditText)findViewById(R.id.userField);
             String userName = mEdit.getText().toString();
            	 
             mEdit   = (EditText)findViewById(R.id.passwordField);
             String passWord = mEdit.getText().toString();
             
             if (!(userName.length() < 4 || passWord.length() < 4))
             {
	             ParseUser.logInInBackground(userName, passWord, new LogInCallback() {
	            	 public void done(ParseUser user, ParseException e) {
	            		 if (user != null) {
	            			 // go to Main after login is validated
		            		Intent myIntent = new Intent(Login.this, Main.class);
		            	    Login.this.startActivity(myIntent);
	            		 } else {
	            			 // put in code to handle this
	            		 }
	            	 }
	             });
             }
            }
        });    
}   
}

