package com.example.kparkins_notes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class StatsFragment extends Fragment {
	
	private TextView numCharsTextView;
	private TextView numWordsTextView;
	private TextView numEntriesTextView;
	
	private int charCount;
	private int wordCount;
	
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

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//Inflate the layout for this fragment
		return inflater.inflate(R.layout.stats, container, false);
	}

}