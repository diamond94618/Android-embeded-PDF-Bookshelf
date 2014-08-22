package com.artifex.mupdfdemo;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.util.Log;

public class DataDelActivity extends Activity{
	
    private int[] image_ids = {
        	R.drawable.test1, R.drawable.test2, R.drawable.test3,
        	R.drawable.test4, R.drawable.test5, R.drawable.empty,
        	R.drawable.empty, R.drawable.empty, R.drawable.empty
        };
	
    int currentBookNo;		
		//private ArrayList<String> results = new ArrayList<String>();
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();        
        
		
		//private String tableName = MySQLiteHelper.tableName;
		
		private SQLiteDatabase newDB;
		
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.bookmark_layout);        
	      
	        //super.onCreate(savedInstanceState);
	        currentBookNo = getIntent().getIntExtra("BOOK_NO", -1);
	        
	        openAndQueryDatabase();
	        
	        displayResultList();
	        
	        //ListView lv = getListView();
            
	        //Own row layout
	        //lv.setOnItemClickListener(this);  	        
	    }
	    
	    
		private void displayResultList() {
			
			//TextView tView = new TextView(this);
	        
			//tView.setText("Please select bookmark");
	        
			//getListView().addHeaderView(tView);
	        String[] from = { "bookno","title","page" };
	        
	        // Ids of views in listview_layout
	        int[] to = { R.id.bookcover, R.id.title, R.id.page};        
	        
	        // Instantiating an adapter to store each items
	        // R.layout.listview_layout defines the layout of each item
	        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.listview_layout, from, to);
	        
	        // Getting a reference to listview of main.xml layout file
	        ListView listView = ( ListView ) findViewById(R.id.bookmark_listview);
	        
	        // Setting the adapter to the listView
	        listView.setAdapter(adapter);                                
	        
	        listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
					// TODO Auto-generated method stub
					final int pos=position;
					try{
						
			//------------------------
							AlertDialog.Builder alert = new AlertDialog.Builder(DataDelActivity.this);

							alert.setTitle("Delete Bookmark");
							alert.setMessage("Do you really want to delete current Bookmark?");

							alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int whichButton) {
				
									MySQLiteHelper dbHelper = new MySQLiteHelper(DataDelActivity.this.getApplicationContext());
									newDB = dbHelper.getWritableDatabase();
									Cursor c = newDB.rawQuery("SELECT id, bookno, title, page FROM books WHERE bookno='"+Integer.toString(currentBookNo)+"'", null);
									if(c!=null){
										c.moveToPosition(pos);
										  // Do something with value!
										String rowId = c.getString(c.getColumnIndex("id")); 
										aList.clear();
										newDB.delete("books", "id=?",  new String[]{rowId});
								        newDB.close();
								        
										openAndQueryDatabase();
								        
								        displayResultList();

									}

								 }
							});

							alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
							  public void onClick(DialogInterface dialog, int whichButton) {
							    // Canceled.
							  }
							});

							alert.show();		

							//-----------------

				            //Toast.makeText(getApplicationContext(), c.getString(c.getColumnIndex("page")),Toast.LENGTH_SHORT).show();
					}
					 catch (SQLiteException se ) {
			        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
			        } finally {
			        	if (newDB != null) 
			        		//newDB.execSQL("DELETE FROM books");
			        		newDB.close();
			        }
					
				}
			});
			
		}
		
		
		private void openAndQueryDatabase() {
			try {
				MySQLiteHelper dbHelper = new MySQLiteHelper(this.getApplicationContext());
				newDB = dbHelper.getWritableDatabase();
				Cursor c = newDB.rawQuery("SELECT id, bookno, title, page FROM books WHERE bookno='"+Integer.toString(currentBookNo)+"'", null);

		    	if (c != null ) {
		    		if  (c.moveToFirst()) {
		    			do {
		    				int bookno=c.getInt(c.getColumnIndex("bookno"));
		    				
		    				String title = c.getString(c.getColumnIndex("title"));
		    				
		    				int page = c.getInt(c.getColumnIndex("page"));
		    				
		    		        	HashMap<String, String> hm = new HashMap<String,String>();
		    		            hm.put("bookno", Integer.toString(image_ids[bookno]));
		    		            hm.put("title",title);
		    		            hm.put("page", Integer.toString(page) );            
		    		            aList.add(hm);        
		    				//.add(title + "(" + page+"page)");
		    				
		    			}while (c.moveToNext());
		    		} 
		    	}			
			} catch (SQLiteException se ) {
	        	Log.e(getClass().getSimpleName(), "Could not create or Open the database");
	        } finally {
	        	if (newDB != null) 
	        		//newDB.execSQL("DELETE FROM books");
	        		newDB.close();
	        }

		}
		
	
}