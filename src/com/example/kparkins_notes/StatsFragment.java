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


/**
 * @author  kparkins
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
		
		numCharsTextView.setText(String.valueOf(state.getNumChars()));
		numWordsTextView.setText(String.valueOf(state.getNumWords()));
		numEntriesTextView.setText(String.valueOf(state.getNotes().size()));
		
	}
	
	private void launchWordCloudActivity() {
		
		Intent intent = new Intent(getActivity(), WordCloudActivity.class);
		String cloud = state.aggregateNotes();
		intent.putExtra("text", cloud);
		startActivity(intent);
		
	}
	
	private void launchCommonWordsActivity() {
		
		Intent intent = new Intent(getActivity(), CommonWordsActivity.class);
		startActivity(intent);
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
			launchWordCloudActivity();
		return true;
		case (R.id.common_words):
			launchCommonWordsActivity();
		return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}




}