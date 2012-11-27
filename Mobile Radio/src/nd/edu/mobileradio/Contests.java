package nd.edu.mobileradio;

import com.parse.CountCallback;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class Contests extends Activity {

	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;
	   private Handler mHandler = new Handler();
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
             	//on shake enter the contest
        	  
        	  ParseUser userName=ParseUser.getCurrentUser();
              //enter the contest
              ParseObject contestData = new ParseObject("contestData");
              contestData.put("User", userName.getUsername());
              contestData.saveInBackground();                	
             
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
  					  
  					/*  //pause for 3 seconds and return to previous screen
  					  mHandler.postDelayed(new Runnable() {
  		                  public void run() {
  		                     //pause
							Intent myIntent = new Intent(getBaseContext(), Main.class);
  		                  startActivityForResult(myIntent, 0);
  		                  }
  		              }, 3000);*/
  					  
          	    }      	  
          	    else {
          	      Log.d("ERROR", "An error occured"); 
          	    }
      
          	  }
          	});  
          }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
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
		
	}
}
