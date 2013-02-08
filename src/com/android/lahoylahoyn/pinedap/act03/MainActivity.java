package com.android.lahoylahoyn.pinedap.act03;

import java.io.InputStream;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	private String[] Names;
	private String[] Urls;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		String[] Names = {"Facebook","Gmail","Google Play","LinkedIn","ToDo"};
		this.Names = Names;
		String[] Urls = {
				"http://snapknot.com/blog/wp-content/uploads/2010/03/facebook-icon-copy.jpg",
				"http://cdn4.iosnoops.com/wp-content/uploads/2011/08/Icon-Gmail_large-250x250.png",
				"https://lh3.googleusercontent.com/-ycDGy_fZVZc/AAAAAAAAAAI/AAAAAAAAAAc/Q0MmjxPCOzk/s250-c-k/photo.jpg",
				"http://kelpbeds.files.wordpress.com/2012/02/lens17430451_1294953222linkedin-icon.jpg?w=450",
				"http://ww1.prweb.com/prfiles/2010/05/11/1751474/gI_TodoforiPadAppIcon512.png.jpg"
		};
		this.Urls = Urls;
		
		ListAdapter a = new ListAdapter(this,Names, Urls);
        setListAdapter(a);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	}
	
	public class ListAdapter extends ArrayAdapter<String> {
    	private final Context c;
    	private final String[] Urls;
    	private final String[] Names;
    	
		public ListAdapter(Context c, String[] Names, String[] Urls) {
			super(c, R.layout.activity_main, Names);
			this.Names =Names;
			this.Urls = Urls;
			this.c = c;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater x = (LayoutInflater)c.getSystemService(LAYOUT_INFLATER_SERVICE);
			View getrow = (View)x.inflate(R.layout.activity_main, parent, false);
			
			TextView title = (TextView)getrow.findViewById(R.id.text1);
			ImageView pic = (ImageView)getrow.findViewById(R.id.logo1);
			
			title.setText(Names[position]);
			new DownloadImageTask(pic).execute(Urls[position]);
						
			return getrow;
		}
	}

}
