package com.example.kparkins_notes;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

	private static final int numFragments = 2;
	private List<Object> fragmentList = new ArrayList<Object>();

	public MyFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
		// Add fragments to list
		fragmentList.add(new NotesFragment());
		fragmentList.add(new StatsFragment());

	}

	@Override
	public Fragment getItem(int i) {
		if (i == 0) {
			return (ListFragment) fragmentList.get(i);
		} else {
			return (Fragment) fragmentList.get(i);
		}
	}

	@Override
	public int getCount() {
		return numFragments;
	}

}