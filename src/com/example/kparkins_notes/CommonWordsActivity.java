package com.example.kparkins_notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.os.Bundle;
import android.app.ListActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

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
 * The CommonWordsActivity is responsible for displaying the 100 (or fewer) most common words. 
 * First, a sorted HashMap of words and a count of how many times they appear is fetched from the GlobalClass.
 * The top 100 words from this HashMap are then put into an ArrayList of Strings, and then this ArrayList is 
 * passed to an ArrayAdapter which displays the words to the user.
 */
public class CommonWordsActivity extends ListActivity {
	
	private HashMap<String, Integer> wordMap;

	private GlobalClass state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		state = (GlobalClass) getApplicationContext();
		wordMap = state.getSortedWordsMap(); // Sorted by value from highest to lowest
		wordMap.remove("");
		setupActionBar();
		createListView();
	}
	
	protected void onResume() {
		super.onResume();
	
	}

	// turn the wordMap into an ArrayList and pass to an ArrayAdapter
	private void createListView() {
		int mapSize = wordMap.size();
		ArrayList<String> commonWords;
		if (mapSize>100) {
			 commonWords = getCommonWordsMax(wordMap); // Over 100 words in map
		} else {
			commonWords = getCommonWords(wordMap); // Less than 100 words in map. Want to display them all.
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commonWords);
		setListAdapter(adapter);
		
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	

	// Iterate over wordMap and add each entry to an ArrayList
	private ArrayList<String> getCommonWords(Map<String, Integer> wordMap2) {
		ArrayList<String> mcw = new ArrayList<String>();
		Iterator<?> entries = wordMap2.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) entries.next();
			String key = (String) entry.getKey();
			mcw.add(key);
		}
		
		Collections.reverse(mcw);
		return mcw;
	}

	// Iterate over top 100 entries in wordMap and add each entry to an ArrayList.
	private ArrayList<String> getCommonWordsMax(Map<String, Integer> wordMap2) {
		int max = 100;
		ArrayList<String> mcw = new ArrayList<String>();
		Iterator<?> entries = wordMap.entrySet().iterator();
		while(max > 0) {
			Entry<?, ?> entry = (Entry<?, ?>) entries.next();
			String key = (String) entry.getKey();
			mcw.add(key);
			max--;
		}
		
		Collections.reverse(mcw);
		return mcw;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.common_words, menu);
		return true;
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
