package nd.edu.mobileradio;


import com.parse.*;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Signup extends Activity {

	private Button submitInfoButton;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    
    public void onStart() {
		super.onStart();
			
		// Initialize the buttons with listeners for click events
		submitInfoButton = (Button) findViewById(R.id.submitInfoButton);
		submitInfoButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             Log.d("Click","Button Clicked");
             EditText mEdit;
             TextView settingsText = (TextView)findViewById(R.id.settingsView);
             
             mEdit   = (EditText)findViewById(R.id.editName);
             String userName = mEdit.getText().toString();
            	 
             mEdit   = (EditText)findViewById(R.id.editPassword);
             String passWord = mEdit.getText().toString();
             
             mEdit   = (EditText)findViewById(R.id.editEmail);
             String email = mEdit.getText().toString();
             
             if (userName.length() < 4 || passWord.length() < 4 || email.length() < 4)
            	 settingsText.setText("One of the fields has 3 or less characters, please try again");
             else {
	             ParseUser user = new ParseUser();
	             user.setUsername(userName);
	             user.setPassword(passWord);
	             user.setEmail(email);
	         
	             user.signUpInBackground(new SignUpCallback() {
					public void done(ParseException e) {
	            	    if (e == null) {
	            	      // Hooray! Let them use the app now.
	            	    	Log.d("SIGNUP SUCCESS","good job buddy");
	            	    } else {
	            	      // Sign up didn't succeed. Look at the ParseException
	            	      // to figure out what went wrong
	            	    	Log.d("SIGNUP FAILURE","something went wrong..");
	            	    }
	            	  }
	            	});  
             }
            }
        });    
}   
}

