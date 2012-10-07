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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {

	// Buttons
	private Button listenButton;
	private Button newsButton;
	private Button contestButton;
	private Button settingsButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize the Parse thingy
        Parse.initialize(this, "jfji0P8sAF26LoXmYVuoMCA86g0XG32FYtA3uPMn", "3xGdnBcGpHAr7XS21JPn3XlEl9Xj1mCKqXOBTlc0");
        
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }*/
    
    public void onStart() {
		super.onStart();
		
		// Initialize the buttons with listeners for click events
        listenButton = (Button) findViewById(R.id.listenButton);
        listenButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

            	ParseUser user = new ParseUser();
            	user.setUsername("E-fresh");
            	user.setPassword("The Ancient Onez");
            	user.setEmail("ekloswic@nd.edu");
            	
            	// other fields can be set just like with ParseObject
            	user.put("phone", "789-456-1230");
            	 
            	user.signUpInBackground(new SignUpCallback() {
            	  public void done(ParseException e) {
            	    if (e == null) {
            	    	listenButton.setText("Success!");
            	    } else {
            	    	listenButton.setText("FAILURE!!!");
            	    }
            	  }
            	});
            	
                /*ParseObject testObject = new ParseObject("Bacon");
                testObject.put("lettuce", "test");
                testObject.saveInBackground();
            	
                
            	Intent myIntent = new Intent(v.getContext(), Listen.class);
                startActivityForResult(myIntent, 0);*/

              
            }
        });
        
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
