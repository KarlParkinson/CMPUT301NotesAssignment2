package com.example.kparkins_notes;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

/**
 * @author  kparkins
 */
public class MainActivity extends FragmentActivity {
	
	private ActionBar actionBar;
	private ViewPager myViewPager; // view pager that handles swiping between fragments
	
	private MyFragmentPagerAdapter swipeAdapter; // custom implementation of FragmentPagerAdapter



	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		actionBar = getActionBar(); // The action bar that will have the tabs
		initializePagers();
		createActionBar(); // Set up the action bar.

	}
	



	private void initializePagers() {

		swipeAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		myViewPager = (ViewPager) findViewById(R.id.pager);
		myViewPager.setAdapter(swipeAdapter); // the ViewPager uses the FragmentPagerAdapter
		myViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			public void onPageSelected(int position) {
				// Select the proper tab when a page is swiped
				actionBar.setSelectedNavigationItem(position);
			}
		});

	}



	private void createActionBar() {

		// Display tabs in action bar
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Create a tab listener that is called when the user changes tabs.
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTabSelected(Tab tab,
					android.app.FragmentTransaction ft) {
				myViewPager.setCurrentItem(tab.getPosition());


			}

			@Override
			public void onTabUnselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub

			}
		};

		// Add tabs to action bar
		actionBar.addTab(actionBar.newTab().setText("Notes").setTabListener(tabListener));
		actionBar.addTab(actionBar.newTab().setText("Stats").setTabListener(tabListener));


	}

	protected void onStop() {
		super.onStop();
		GlobalClass state = (GlobalClass) getApplicationContext();
		state.saveNotes();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}