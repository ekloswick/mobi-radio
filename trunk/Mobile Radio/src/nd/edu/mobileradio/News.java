package nd.edu.mobileradio;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import nd.edu.mobileradio.R;
import nd.edu.mobileradio.RssItem;
import nd.edu.mobileradio.ListListener;
import nd.edu.mobileradio.RssReader;

public class News extends Activity {
	/** 
	 * This method creates main application view
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.activity_news);

		try {
			// Create RSS reader
			RssReader rssReader = new RssReader("http://www.southbendtribune.com/news/mrss.xml");
			//RssReader rssReader = new RssReader("http://www.southbendtribune.com/ws/content/collection/?collection=sbt_sports_photogalleries&key=d3IwcUN6Ym9NVEV0MWkzcEJsVEU2UFp3bWxmbVRzWFBxbVNyc0pjd1BOWjk4alBLSEpOdHRJVlhXK1dscWhvZg&version=1.0&limit=20");
			
			// Get a ListView from main view
			ListView Items = (ListView) findViewById(R.id.listMainView);
			
			// Create a list adapter
			ArrayAdapter<RssItem> adapter = new ArrayAdapter<RssItem>(this,android.R.layout.simple_list_item_1, rssReader.getItems());
			// Set list adapter for the ListView
			Items.setAdapter(adapter);
			
			// Set list view item click listener
			Items.setOnItemClickListener(new ListListener(rssReader.getItems(), this));
			
		} catch (Exception e) {
			Log.e("RssReader", e.getMessage());
		}
		
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
}
