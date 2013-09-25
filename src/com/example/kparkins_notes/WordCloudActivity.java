package com.example.kparkins_notes;

import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.formatters.HTMLFormatter;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WordCloudActivity extends Activity {
	
	private Cloud cloud;
	String text;
	private TextView cloudTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_cloud);
		setupActionBar();
	}
	
	protected void onStart() {
		super.onStart();
		
	}
	
	protected void onResume() {
		super.onResume();
		cloud = new Cloud();
		text = getIntent().getStringExtra("text");
		cloud.setMaxTagsToDisplay(text.length());
		cloud.setDefaultLink("www.google.ca");
		cloud.setTagCase(Cloud.Case.UPPER);
		cloud.addText(text);
		cloudTextView = (TextView) findViewById(R.id.cloudTextView);
		displayCloud();
	}

	private void displayCloud() {
	
		cloudTextView.setText(Html.fromHtml(new HTMLFormatter().html(cloud)));
		
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
