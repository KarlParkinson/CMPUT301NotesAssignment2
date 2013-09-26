package com.example.kparkins_notes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;
import org.mcavallo.opencloud.formatters.HTMLFormatter;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class WordCloudActivity extends Activity {
	
	private Cloud cloud;
	private WebView webView;
	//private TextView cloudTextView;
	private GlobalClass state;
	private HashMap<String, Integer> wordMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_word_cloud);
		webView = new WebView(this);
		setContentView(webView);
		setupActionBar();
	}
	
	protected void onStart() {
		super.onStart();
		
	}
	
	protected void onResume() {
		super.onResume();
		state = (GlobalClass) getApplicationContext();
		wordMap = state.getWordsMap();
		cloud = new Cloud();
		cloud.setMaxWeight(38.0);
		cloud.setMaxTagsToDisplay(wordMap.size());
		cloud.setDefaultLink("www.google.ca");
		cloud.setTagCase(Cloud.Case.UPPER);
		Iterator<?> entries = wordMap.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) entries.next();
			String key = (String) entry.getKey();
			Integer value = (Integer) entry.getValue();
			cloud.addTag(new Tag(key, (double) value));
		}
		displayCloud();
	}

	private void displayCloud() {
		
		String html = "<html><body>";
		for (Tag t : cloud.tags()) {
			html += "<a"+ " style=\"font-size: " + t.getWeight() + "px;\">" +t.getName() + "</a>" + " ";
		}
		html += "</html></body>";
		webView.loadData(html, "text/html", null);
		//cloudTextView.setText(Html.fromHtml(html));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.word_cloud, menu);
		return true;
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (android.R.id.home):
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
		return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
