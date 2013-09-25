package com.example.kparkins_notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

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
	
	private TreeMap<String, Integer> map;
	private HashMap<String, Integer> map2;
	private GlobalClass state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	protected void onResume() {
		super.onResume();
		state = (GlobalClass) getApplicationContext();
		map2 = state.getWordsMap2();
		createListView();
	}

	private void createListView() {
		int mapSize = map2.size();
		ArrayList<String> commonWords;
		if (mapSize>100) {
			 commonWords = getCommonWordsMax();
		} else {
			commonWords = getCommonWords();
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commonWords);
		//Log.d("First entry: ", commonWords.get(0));
		setListAdapter(adapter);
		
	}

	private ArrayList<String> getCommonWords() {
		ArrayList<String> mcw = new ArrayList<String>();
		Iterator entries = map2.entrySet().iterator();
		while (entries.hasNext()) {
			Entry entry = (Entry) entries.next();
			String key = (String) entry.getKey();
			mcw.add(key);
		}
		
		Collections.reverse(mcw);
		return mcw;
	}

	private ArrayList<String> getCommonWordsMax() {
		int max = 100;
		ArrayList<String> mcw = new ArrayList<String>();
		Iterator entries = map2.entrySet().iterator();
		while(max > 0) {
			Entry entry = (Entry) entries.next();
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
