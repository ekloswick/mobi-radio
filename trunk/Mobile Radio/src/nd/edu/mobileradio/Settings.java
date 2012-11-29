package nd.edu.mobileradio;


import nd.edu.mobileradio.R.string;

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

public class Settings extends Activity {

	private Button saveChangesButton;
	private Button passwordResetButton;
	private Button removeButton;

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }*/
    
    public void onStart() {
		super.onStart();

		//fill in the username and email		
		 ParseUser currentUser = ParseUser.getCurrentUser();
	    EditText mEdit;
	       mEdit   = (EditText)findViewById(R.id.editName2);
	       mEdit.setText(currentUser.getUsername());
	       mEdit   = (EditText)findViewById(R.id.editEmail2);
	       mEdit.setText(currentUser.getEmail());
	       mEdit   = (EditText)findViewById(R.id.editAddress);
	       mEdit.setText(currentUser.getString("address"));
	       mEdit   = (EditText)findViewById(R.id.editPhone);
	       mEdit.setText(currentUser.getString("phone"));
	       mEdit   = (EditText)findViewById(R.id.editAge);
	       mEdit.setText(currentUser.getString("age"));
	       
           
		// Initialize the buttons with listeners for click events
		saveChangesButton = (Button) findViewById(R.id.saveChangesButton);
		saveChangesButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
             Log.d("Click","Button Clicked");
             EditText mEdit;
             TextView settingsText = (TextView)findViewById(R.id.settingsView);
             
             mEdit   = (EditText)findViewById(R.id.editName2);
             String userName = mEdit.getText().toString();        
             
             mEdit   = (EditText)findViewById(R.id.editEmail2);
             String email = mEdit.getText().toString();
             
             mEdit   = (EditText)findViewById(R.id.editAddress);
             String address = mEdit.getText().toString();
             
             mEdit   = (EditText)findViewById(R.id.editPhone);
             String phone = mEdit.getText().toString();
             
             mEdit   = (EditText)findViewById(R.id.editAge);
             String age = mEdit.getText().toString();
             
             if (userName.length() < 4 || email.length() < 4)
            	 settingsText.setText("One of the fields has 3 or less characters, please try again");
             else {
            	 ParseUser currentUser = ParseUser.getCurrentUser();
            	 currentUser.setUsername(userName);
            	 currentUser.setEmail(email);
	             currentUser.put("address", address);
	             currentUser.put("phone", phone);
	             currentUser.put("age", age);
            	 currentUser.saveInBackground();
            	 
            	   	Intent myIntent = new Intent(getBaseContext(), Main.class);
	                  startActivityForResult(myIntent, 0);
	    
             }
            }
        });    
		
		passwordResetButton = (Button) findViewById(R.id.passwordResetButton);
		passwordResetButton.setOnClickListener(new OnClickListener() {
			  public void onClick(View v) {
				  ParseUser currentUser = ParseUser.getCurrentUser();
				  String email = currentUser.getEmail().toString();
				  RequestPasswordResetCallback callback = null;
				ParseUser.requestPasswordResetInBackground(email, callback);
			  	Intent myIntent = new Intent(getBaseContext(), Main.class);
                startActivityForResult(myIntent, 0);
			  }
		  });  
		
		removeButton = (Button) findViewById(R.id.removeButton);
		removeButton.setOnClickListener(new OnClickListener() {
			  public void onClick(View v) {
				  ParseUser currentUser = ParseUser.getCurrentUser();
	         		currentUser.deleteInBackground();
	         	  	Intent myIntent = new Intent(getBaseContext(), Signup.class);
	                  startActivityForResult(myIntent, 0);
			  }
		  });    


}   
}

