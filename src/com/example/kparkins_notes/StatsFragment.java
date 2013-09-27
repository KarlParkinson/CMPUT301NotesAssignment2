package com.example.kparkins_notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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
 * That StatsFragment is responsible for displaying the character count, word count, and number of notes that
 * have been saved. The character and word count only count characters and words in the bodies of notes, not the title.
 * This information is fetched from the GlobalClass and then displayed to the user. The StatsFragment also spawns 
 * CommonWordActivity and WordCloudActivity when prompted by the user.
 */

public class StatsFragment extends Fragment {
	
	private TextView numCharsTextView;
	private TextView numWordsTextView;
	private TextView numEntriesTextView;
	
	
	private GlobalClass state;

	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); // Fragment has menu items to contribute
		state = ((GlobalClass) getActivity().getApplicationContext());
		
	}
	
	public void onStart() {
		
		super.onStart();
		numCharsTextView = (TextView) getView().findViewById(R.id.numCharsTextView);
		numWordsTextView = (TextView) getView().findViewById(R.id.numWordsTextView);
		numEntriesTextView = (TextView) getView().findViewById(R.id.numEntriesTextView);
		setText();
		
	}
	
	public void onResume() {
		super.onResume();
		setText();
	}
	
	private void setText() {
		
		numCharsTextView.setText(String.valueOf(state.getNumChars())); // Display the character count
		numWordsTextView.setText(String.valueOf(state.getNumWords())); // Display the word count
		numEntriesTextView.setText(String.valueOf(state.getNotes().size())); // Display how many notes are saved.
		
	}
	
	private void launchWordCloudActivity() {
		
		Intent intent = new Intent(getActivity(), WordCloudActivity.class);
		startActivity(intent); // start a WordCloudActivity
		
	}
	
	private void launchCommonWordsActivity() {
		
		Intent intent = new Intent(getActivity(), CommonWordsActivity.class);
		startActivity(intent); // Start a CommonWordsActivity
	}


	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//Inflate the layout for this fragment
		return inflater.inflate(R.layout.stats, container, false);
	}
	
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		//Inflate the menu; this adds items to the action bar if it is present.
		inflater.inflate(R.menu.stats_menu, menu);

	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (R.id.word_cloud):
			launchWordCloudActivity(); // User wants to see a word cloud
		return true;
		case (R.id.common_words):
			launchCommonWordsActivity(); // User wants to see a list of common words
		return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}




}