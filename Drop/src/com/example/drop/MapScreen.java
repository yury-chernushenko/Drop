package com.example.drop;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MapScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_map_screen, menu);
        return true;
    }
}
