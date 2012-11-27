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
	
	private String feedURL = "http://www.southbendtribune.com/news/nationworld/rss2.0.xml";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set view
		setContentView(R.layout.activity_news);

		setupFeed(feedURL);
	}
	
	public void setupFeed(String feed)
	{
		try {
			// Create RSS reader
			RssReader rssReader = new RssReader(feed);
			
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
        inflater.inflate(R.menu.activity_news, menu);
        return true;
    }
}
