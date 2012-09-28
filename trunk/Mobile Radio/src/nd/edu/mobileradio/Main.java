package nd.edu.mobileradio;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onStart() {
		super.onStart();
		
		// Initialize the buttons with listeners for click events
        listenButton = (Button) findViewById(R.id.listenButton);
        listenButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), Listen.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        // Initialize the buttons with listeners for click events
        newsButton = (Button) findViewById(R.id.newsButton);
        newsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), News.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        // Initialize the buttons with listeners for click events
        contestButton = (Button) findViewById(R.id.contestButton);
        contestButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), Contests.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
        // Initialize the buttons with listeners for click events
        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), Settings.class);
                startActivityForResult(myIntent, 0);
            }
        });
	}
}
