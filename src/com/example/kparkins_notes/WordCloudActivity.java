package com.example.kparkins_notes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Tag;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

/*kparkins-notes. Stores and displays a list of notes along with some statistics.
Copyright (C) 2013 Karl Parkinson.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

/*
 * The WordCloudActivity is responsible for displaying a word cloud to the user. It takes advantage of the Open Cloud library.
 * First, the HashMap of words to their count is fetched from the GlobalClass. Then, a new tag object is created for each
 * word with the word count used as a weighting, Then HTML is created and rendered using a WebView
 */

// See: http://opencloud.mcavallo.org/
// See: http://opencloud.mcavallo.org/documentation/getting_started_guide.html



public class WordCloudActivity extends Activity {
	
	private Cloud cloud; // The word cloud
	private WebView webView;
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
		//cloud.setDefaultLink("www.google.ca");
		cloud.setTagCase(Cloud.Case.UPPER);
		Iterator<?> entries = wordMap.entrySet().iterator();
		// Create a new tag object for each word in the map
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
			html += "<a"+ " style=\"font-size: " + t.getWeight() + "px;\">" +t.getName() + "</a>" + " "; // HTML needed to properly display words
		}
		html += "</html></body>";
		webView.loadData(html, "text/html", null); // Render the HTML and display to the user.
		
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
