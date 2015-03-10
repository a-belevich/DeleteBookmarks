package com.abv.bookmarkcleaner;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View button) {
		if (button.getId() == R.id.button1) {
			
			String result = "";
			ContentResolver cr = getContentResolver();
			try 
			{
//				Uri uri = Uri.parse("content://com.sec.android.app.sbrowser/app_sbrowser/default/bookmarks");
				Uri uri = Uri.parse("content://com.sec.android.app.sbrowser/bookmarks");
				//Uri uri = Browser.BOOKMARKS_URI;
				
//			    Cursor c = cr.query(uri,
//			                        new String [] { Browser.BookmarkColumns._ID,
//			                                        Browser.BookmarkColumns.BOOKMARK,
//			                                        Browser.BookmarkColumns.VISITS },
//			                        "bookmark != 0",
//			                        null,
//			                        null);
//			    c.moveToFirst();
			    Integer deleted = cr.delete(uri, null, null);

			    result = "Success: " + deleted.toString() + " records deleted";
			} 
			catch (IllegalStateException e) 
			{
			    e.printStackTrace();
			    result = "Error: " + e.getMessage();
			}
			
			Context context = getApplicationContext();
			CharSequence text = result;
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}
}
