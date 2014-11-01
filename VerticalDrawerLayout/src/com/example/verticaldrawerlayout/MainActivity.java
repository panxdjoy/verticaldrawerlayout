package com.example.verticaldrawerlayout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private VerticalDrawerLayout verticalDrawerLayout;
	private View mOpen;
	private View mClose;
	private ListView mListView;
	private String[] mPlanetTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();
	}

	private void initComponents() {
		verticalDrawerLayout = (VerticalDrawerLayout) findViewById(R.id.test_drawer_layout);
		if (verticalDrawerLayout != null) {
			verticalDrawerLayout.setDrawerLockMode(VerticalDrawerLayout.LOCK_MODE_LOCKED_CLOSED);
		}
		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		mListView = (ListView) findViewById(R.id.test_drawer_content);
		mListView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mPlanetTitles));
		mOpen = findViewById(R.id.open);
		mOpen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDrawer();
			}
		});

		mClose = findViewById(R.id.close);
		mClose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				closeDrawer();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		boolean drawerOpen = verticalDrawerLayout.isDrawerOpen(mListView);
		if (drawerOpen) {
			verticalDrawerLayout.closeDrawer(mListView);
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			boolean drawerOpen = verticalDrawerLayout.isDrawerOpen(mListView);
			if (drawerOpen) {
				verticalDrawerLayout.closeDrawer(mListView);
				return true;
			}

		}
		return super.onKeyDown(keyCode, event);
	}

	private void showDrawer() {
		if (verticalDrawerLayout != null) {
			boolean drawerOpen = verticalDrawerLayout.isDrawerOpen(mListView);
			if (!drawerOpen) {
				verticalDrawerLayout.openDrawer(mListView);
			}
		}
	}

	private void closeDrawer() {
		if (verticalDrawerLayout != null) {
			boolean drawerOpen = verticalDrawerLayout.isDrawerOpen(mListView);
			if (drawerOpen) {
				verticalDrawerLayout.closeDrawer(mListView);
			} else {
				finish();
			}
		}
	}

}
