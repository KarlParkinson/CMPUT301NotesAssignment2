package com.example.kparkins_notes;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;

public class NoteActivity extends Activity implements 
DatePicker.OnDateChangedListener{

	private EditText titleEntryET;
	private EditText logEntryET;
	private TextView charCountTextView;
	
	private DatePicker datePicker;
	
	private int charCount;
	private int wordCount;
	private int notePosition;
	
	private GlobalClass state;
	private Note note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		// Show the Up button in the action bar.
		setupActionBar();
		
		state = (GlobalClass) getApplicationContext();
		notePosition = getIntent().getIntExtra("Position", 0);
		note = state.retrieveLogEntry(notePosition);
		
		logEntryET = (EditText) findViewById(R.id.EditLogEntryET);
		titleEntryET = (EditText) findViewById(R.id.editTitleET);
		charCountTextView = (TextView) findViewById(R.id.charCountTextView2);
		setUpTextWatcher();
		
		datePicker = (DatePicker) findViewById(R.id.editDatePicker);
		datePicker.setCalendarViewShown(false);
		datePicker.updateDate(note.getDate().get(Calendar.YEAR), note.getDate().get(Calendar.MONTH), note.getDate().get(Calendar.DAY_OF_MONTH));
		
		titleEntryET.setText(note.getTitle());
		logEntryET.setText(note.getBody());
		


	}
	/*
	protected void onResume() {
		super.onResume();
		
		state = (GlobalClass) getApplicationContext();
		notePosition = getIntent().getIntExtra("Position", 0);
		
		logEntryET = (EditText) findViewById(R.id.EditLogEntryET);
		titleEntryET = (EditText) findViewById(R.id.editTitleET);
		charCountTextView = (TextView) findViewById(R.id.charCountTextView2);
		setUpTextWatcher();
		note = state.retrieveLogEntry(notePosition);
		
		titleEntryET.setText(note.getTitle());
		logEntryET.setText(note.getBody());
	}*/

	private void setUpTextWatcher() {
		
		logEntryET.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				charCountTextView.setText(String.valueOf(s.length()));
				charCount = s.length();
				
			}
			
		});
		
	}

	private void saveAndExit() {
		
		String body = logEntryET.getText().toString();
		wordCount = countWords(body);
		onDateChanged(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
		state.editEntry(notePosition, body, titleEntryET.getText().toString(), charCount, wordCount);
		finish();
		
	}
	
	private void deleteAndExit() {
	
		state.deleteEntry(notePosition);
		finish();
		
	}

	// taken from: http://stackoverflow.com/questions/5864159/count-words-in-a-string-method
	private int countWords(String body) {
		String trim = body.trim();
		if (trim.isEmpty()) return 0;
		return trim.split("\\s+").length; //separate string around spaces
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */


	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case (R.id.save_new_edit):
			saveAndExit();
		return true;
		
		case (R.id.delete_note):
			deleteAndExit();
		return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		
		note.getDate().set(year, monthOfYear, dayOfMonth);
		
	}



}