package nd.edu.mobileradio;

/*import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.PushService;
import com.parse.SignUpCallback;*/
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
import android.widget.TextView;

public class Main extends Activity {

	private Button newsButton;
	private Button contestButton;
	private Button settingsButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize the Parse thing
        Parse.initialize(this, "jfji0P8sAF26LoXmYVuoMCA86g0XG32FYtA3uPMn", "3xGdnBcGpHAr7XS21JPn3XlEl9Xj1mCKqXOBTlc0");       
      
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
         // do stuff with the user
        	Log.d("Success","the user is signed in");
        	TextView usernameText;
        	usernameText = (TextView)findViewById(R.id.userNameView);
        	usernameText.setText(currentUser.getUsername());
        	
        	TextView useremailText;
        	useremailText = (TextView)findViewById(R.id.userEmailView);
        	useremailText.setText(currentUser.getEmail());
        	
        } else {
        	// go to Signup activity if they don't have login info
        	Log.d("Error","no sign in");
        	Intent myIntent = new Intent(Main.this, Signup.class);
        	Main.this.startActivity(myIntent);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    
    public void onStart() {
		super.onStart();
        
        newsButton = (Button) findViewById(R.id.newsButton);
        newsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), News.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        contestButton = (Button) findViewById(R.id.contestButton);
        contestButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), Contests.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), Settings.class);
                startActivityForResult(myIntent, 0);
            }
        });
	}
    
}
