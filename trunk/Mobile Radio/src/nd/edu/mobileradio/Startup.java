package nd.edu.mobileradio;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Startup extends Activity {

	private Button signupButton;
	private Button loginButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_startup, menu);
        return true;
    }
    
    public void onStart() {
		super.onStart();
        
		signupButton = (Button) findViewById(R.id.signupButton);
		signupButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), Signup.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
		loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(v.getContext(), Login.class);
                startActivityForResult(myIntent, 0);
            }
        });
        
	}
}
