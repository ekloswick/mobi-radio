package nd.edu.mobileradio;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Contests extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_contests, menu);
        return true;
    }
}
