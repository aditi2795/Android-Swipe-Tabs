package com.example.aditipaul.tabswithswipe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.aditipaul.tabswithswipe.TopRatedFragment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class new_activity extends AppCompatActivity {
    String selecteditem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);
        Intent intent = getIntent();
        selecteditem = intent.getExtras().getString("selected");
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setHomeButtonEnabled(true);
        setTitle("Document Scanner");
        refresh();

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
                                final EditText input = new EditText(new_activity.this);
                                input.setId(0);
                                final String ss = input.getText().toString();
                                switch (which) {
                                    case 0:
                                        final AlertDialog.Builder build = new AlertDialog.Builder(new_activity.this);
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
                                                        .getExternalStorageDirectory().toString() + "/NEW_FOLDER/" + selecteditem + "/" + s;
                                                File folder = new File(extStorageDirectory);
                                                folder.mkdir();
                                                refresh();
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
    public void refresh() {
        String extStorageDirectory =  Environment
                .getExternalStorageDirectory().toString() + "/NEW_FOLDER/" +selecteditem;
        File f = new File(extStorageDirectory);
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {

                return dir.isDirectory();
            }

            public boolean accept(File file) {
                return file.isDirectory();
            }
        };
        ArrayList<String> af = new ArrayList<String>();
        File[] filess = f.listFiles(fileFilter);
        for (File file_doc : filess) {
            af.add(file_doc.getName());
        }
        ListView lv =(ListView)findViewById(R.id.lv1);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, af);
        lv.setAdapter(listAdapter);
    }
}
