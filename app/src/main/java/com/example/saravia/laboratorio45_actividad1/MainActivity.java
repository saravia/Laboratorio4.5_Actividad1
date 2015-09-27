package com.example.saravia.laboratorio45_actividad1;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button saltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        access();
        saltar = (Button)findViewById(R.id.saltar);
        saltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);

            }
        });


    }
    public void access(){
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query( Contacts.People.CONTENT_URI, null, null, null,
                null);
        if ( cur.getCount() > 0 ){
            while ( cur.moveToNext() ) {
                String id = cur.getString(cur.getColumnIndex(Contacts.People._ID));
                String name = cur.getString(cur.getColumnIndex(
                        Contacts.People.DISPLAY_NAME));
                Log.d("ContentProvider", name);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
