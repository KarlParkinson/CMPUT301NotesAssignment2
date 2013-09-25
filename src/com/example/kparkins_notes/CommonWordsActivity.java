package com.example.kparkins_notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;

/*
 * Need to sort the hash map that gets returned by state.
 */
public class CommonWordsActivity extends ListActivity {
	
	//private TreeMap<String, Integer> map;
	private HashMap<String, Integer> wordMap;
	private GlobalClass state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		state = (GlobalClass) getApplicationContext();
		wordMap = state.getSortedWordsMap();
		Log.d("WordsActivity: ", "New words activity");
		createListView();
	}
	
	protected void onResume() {
		super.onResume();
	
	}

	private void createListView() {
		int mapSize = wordMap.size();
		ArrayList<String> commonWords;
		Log.d("Word map: ", wordMap.toString());
		if (mapSize>100) {
			 commonWords = getCommonWordsMax(wordMap);
		} else {
			commonWords = getCommonWords(wordMap);
		}
		
		Log.d("Size of common words list: ", String.valueOf(commonWords.size()));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commonWords);
		setListAdapter(adapter);
		
	}

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
	
	

}
