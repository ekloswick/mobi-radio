package nd.edu.mobileradio;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Listen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_listen, menu);
        return true;
    }
}
