package com.example.aditipaul.tabswithswipe;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
 /*----------------------------------------- scanner section-----------------------------*/

public class GamesFragment extends Fragment {
Button btn;
    String extStorageDirectory;
    private final int CAMERA_REQUEST_CODE = 2222;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_games, container, false);


       final ListView listview = (ListView) rootView.findViewById(R.id.listt);
        String[] listitems = new String[]{"QR Code", "Barcode", "Document"};

        // Create ArrayAdapter using the items list.
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.simplerow, listitems);
        listview.setAdapter(listAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long position) {

                    if (position == 2) {

                    final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg"; //Name of photo

                    final Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);  // TO open Camera
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);

                }
            }


        });


        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // To Handle Camera Result
        if (data != null && requestCode == CAMERA_REQUEST_CODE) {
            final Bitmap pictureObject = (Bitmap) data.getExtras().get("data");

            AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
            build.setTitle("Save To");
            build = build.setItems(new CharSequence[]{"Document", "Receipts", "Business Card"}, new DialogInterface.OnClickListener() {
            File file;
                FileOutputStream out = null;

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    final String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg"; //Name of photo
                    switch (which) {
                        case 0:
                            extStorageDirectory = Environment
                                    .getExternalStorageDirectory().toString() + "/NEW_FOLDER/Document/" + timeStamp;

                             file = new File(extStorageDirectory);
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                out = new FileOutputStream(file);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            pictureObject.compress(Bitmap.CompressFormat.JPEG, 85, out);


                            Toast.makeText(getActivity(), "Saved to Document", Toast.LENGTH_SHORT).show();

                        case 1:
                            extStorageDirectory = Environment
                                    .getExternalStorageDirectory().toString() + "/NEW_FOLDER/Receipts/" + timeStamp;
                            file = new File(extStorageDirectory);
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                out = new FileOutputStream(file);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            pictureObject.compress(Bitmap.CompressFormat.JPEG, 85, out);

                            Toast.makeText(getActivity(), "Saved to Receipts", Toast.LENGTH_SHORT).show();
                        case 2:

                            extStorageDirectory = Environment
                                    .getExternalStorageDirectory().toString() + "/NEW_FOLDER/Business Card/" + timeStamp;
                            file = new File(extStorageDirectory);
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            try {
                                out = new FileOutputStream(file);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            pictureObject.compress(Bitmap.CompressFormat.JPEG, 85, out);


                            Toast.makeText(getActivity(), "Saved to Business Card", Toast.LENGTH_SHORT).show();
                        default :
                    }

                }


            });
            build.setPositiveButton("Cancel", new DialogInterface.OnClickListener()

                    {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked Cancel button
                        }
                    }

            );
            build.create().

            show();

        }
    }
}