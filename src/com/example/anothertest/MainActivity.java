package com.example.anothertest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

import com.example.anothertest.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private final String LOG_TAG = getClass().getSimpleName();

    public ListModelAdapter arrayAdapter;
    private ListModel listModel;
    private ArrayList<ListModel> list = new ArrayList<ListModel>();
    private Dao<ListModel, Integer> simpleDao;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dbHelper = OpenHelperManager.getHelper((Context)this, DatabaseHelper.class);
        dbHelper = new DatabaseHelper((Context)this);

        // retrieve data from DB and populate listview
        try {
            // get our dao
            simpleDao = dbHelper.getListDao();
            // query for all of the data objects in the database
            list = (ArrayList<ListModel>) simpleDao.queryForAll();
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to query data from database", e);
        }

        final ListView listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ListModelAdapter(this, android.R.layout.simple_list_item_1, list);

        // By using setAdapter method, you plugged the ListView with adapter
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    /**
     * On click listener function called when Submit button is clicked (see activitiy_main.xml)
     * This Function will take the text from the EditText field, create a ListItem object with
     * the field text and add the ListItem object to the ArrayList
     * @param v - The view is passed by the onClick event
     */
    public void storeInput(View v) {
        EditText inputField = (EditText) findViewById(R.id.userInput);
        String inputText = inputField.getText().toString();

        if (inputText != null && !inputText.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Input text is " +inputText, Toast.LENGTH_SHORT).show();
            listModel = new ListModel(inputText);
            try {
				simpleDao.createOrUpdate(listModel);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            list.add(listModel);

            final ListView listView = (ListView) findViewById(R.id.listView);
            arrayAdapter = new ListModelAdapter(this, android.R.layout.simple_list_item_1, list);

            listView.setAdapter(arrayAdapter);

            inputField.setText("");
        } else {
            Toast.makeText(getApplicationContext(), "Input text is empty", Toast.LENGTH_SHORT).show();
        }
    }
    
}
