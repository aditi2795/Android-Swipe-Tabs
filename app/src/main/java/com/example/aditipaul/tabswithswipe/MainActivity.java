package com.example.aditipaul.tabswithswipe;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Stack;


public class MainActivity extends AppCompatActivity implements ActionBar.TabListener  {
    SectionsPagerAdapter mSectionsPagerAdapter;
     ViewPager viewpager;

    //Tab title

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = (ViewPager) findViewById(R.id.pager);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setHomeButtonEnabled(true);
        setTitle("Document Scanner");
        String extStorageDirectory;

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
       mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mSectionsPagerAdapter);
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {

            actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.name:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Add New");
                builder = builder.setItems(new CharSequence[]
                                {"Folder", "Document"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                final EditText input = new EditText(MainActivity.this);
                                input.setId(0);
                                final String ss=input.getText().toString();
                                switch (which) {
                                    case 0:
                                        String extStorageDirectory = Environment
                                                .getExternalStorageDirectory().toString() + "/NEW_FOLDER";
                                        File folder = new File(extStorageDirectory);
                                        folder.mkdir();
                                        Toast.makeText(MainActivity.this, "Folder Created at : " + extStorageDirectory, Toast.LENGTH_LONG).show();
                                        String[] items = new String[]{"Business Card", "Document", "Receipts"};
                                        for (int i = 0; i <= 2; i++) {
                                            String extstr = extStorageDirectory + "/"+ items[i];
                                            File folder_inside_root = new File(extstr);
                                            folder_inside_root.mkdir();
                                        }
                                        final AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                                        build.setTitle("Folder name");

                                        build.setView(input);
                                        build.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }

                                        });

                                        build.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                final String s = input.getText().toString();
                                                String extStorageDirectory = Environment
                                                        .getExternalStorageDirectory().toString() + "/NEW_FOLDER/" +s;
                                                File folder = new File(extStorageDirectory);
                                                folder.mkdir();
                                            }
                                        });

                                        build.show();
                                        /*-----------------ROOT FOLDER-------------------*/
                                        /*String extStorageDirectory = Environment
                                                .getExternalStorageDirectory().toString() + "/NEW_FOLDER1";
                                        File folder = new File(extStorageDirectory);
                                        folder.mkdir();
                                        Toast.makeText(MainActivity.this, "Folder Created at : " + extStorageDirectory, Toast.LENGTH_LONG).show();
                                        String[] items = new String[]{"Business Card", "Document", "Receipts"};
                                        for (int i = 0; i <= 2; i++) {
                                            String extstr = extStorageDirectory + "/"+ items[i];
                                            File folder_inside_root = new File(extstr);
                                            folder_inside_root.mkdir();
                                       }*/



                                }

                                }



                        });

                builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                builder.create().show();
                break;


            default:

        }

        return true;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
                case 0:
                    // Top Rated fragment activity
                    return new TopRatedFragment();
                case 1:
                    // Games fragment activity
                    return new GamesFragment();

            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);

            }
            return null;
        }
    }
}
