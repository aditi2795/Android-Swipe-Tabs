package com.example.aditipaul.tabswithswipe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;

/*------------------------Browse File Section------------------------*/

public class TopRatedFragment extends Fragment {
String selecteditem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
        final ListView listview = (ListView) rootView.findViewById(R.id.listt);
       // final ListView lv = (ListView) rootView.findViewById(R.id.List);
        //String[] name = new String[];
        int i = 0;
        final String extStorageDirectory = Environment
                .getExternalStorageDirectory().toString() + "/NEW_FOLDER";
        File folder = new File(extStorageDirectory);
        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        ArrayList<String> arrayFiles = new ArrayList<String>();
        File[] files = folder.listFiles(fileFilter);
        for (File file : files) {
            arrayFiles.add(file.getName());
        }
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, arrayFiles);
        listview.setAdapter(listAdapter);
        // Click on list items to obtain internal files
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long position) {
                selecteditem = (listview.getItemAtPosition(arg2)).toString();

                Intent i = new Intent(getActivity(), new_activity.class);
                i.putExtra("selected", selecteditem);
                startActivity(i);

            }
        });
        return rootView;
}


}