package nd.edu.mobileradio;


import com.parse.*;


import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends Activity {

	private Button submitInfoButton;
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }*/
    
    
    public void onStart() {
		super.onStart();
			
		// Initialize the buttons with listeners for click events
		submitInfoButton = (Button) findViewById(R.id.submitInfoButton);
		submitInfoButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             Log.d("Click","Button Clicked");
             EditText mEdit;
             mEdit   = (EditText)findViewById(R.id.editName);
             String userName = mEdit.getText().toString();
             mEdit   = (EditText)findViewById(R.id.editPassword);
             String passWord = mEdit.getText().toString();
             mEdit   = (EditText)findViewById(R.id.editEmail);
             String email = mEdit.getText().toString();
              
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
        });    
}   
}

