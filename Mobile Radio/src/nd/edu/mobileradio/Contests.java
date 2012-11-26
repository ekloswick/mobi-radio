package nd.edu.mobileradio;

import com.parse.CountCallback;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Contests extends Activity {

	private Button enterContestButton;
	boolean enabled = true;
	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
        
        mSensorListener = new ShakeEventListener();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorManager.registerListener(mSensorListener,
            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_UI);


        mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

          public void onShake() {
        	  enterContestButton.setText("BLAKRFJKH");
          }
        });
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      mSensorManager.registerListener(mSensorListener,
          mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
          SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onStop() {
      mSensorManager.unregisterListener(mSensorListener);
      super.onStop();
    }
    
    public void onStart() {
		super.onStart();
		
		// Initialize the buttons with listeners for click events
		enterContestButton = (Button) findViewById(R.id.enterContestButton);
		enterContestButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {    
            	
            enabled = false;
            ParseUser userName=	ParseUser.getCurrentUser();
            
            //enter the contest and then disable the contest button
            ParseObject contestData = new ParseObject("contestData");
            contestData.put("User", userName);
            contestData.saveInBackground();
            enterContestButton.setText("Contest Entered!");
                   	
        	
            //query to determine how many people have entered the contest. If the user is the winner number then display to them that they have won!
            ParseQuery query = new ParseQuery("contestData");
        	query.countInBackground(new CountCallback() {
        	  public void done(int count, ParseException e) {
        	    if (e == null) {
        	      // The count request succeeded. Log the count
					  Log.d("SUCCESS", "There are " + count + " contest entries");
					  if (count == 10) //this is the winning user!
					  {
						  Log.d("SUCCESS","This is the winner!");  	
						  TextView tempview;
						  setContentView(R.layout.activity_contests);
						  tempview=(TextView)findViewById(R.id.contest_results);
						  tempview.setText("You won!");
					  }
					  else
					  {
						  Log.d("FAILURE","You did not win");
						  TextView tempview;
						  setContentView(R.layout.activity_contests);
						  tempview=(TextView)findViewById(R.id.contest_results);
						  tempview.setText("Sorry try again! You were entry #" +count + " in the contest");
					  }
        	    } 
        	    
        	    else {
        	      Log.d("ERROR", "An error occured"); 
        	    }
        		
        	  }
        	});           
        	 enterContestButton.setEnabled(enabled); 
            }
            
        });
	
	}
}
