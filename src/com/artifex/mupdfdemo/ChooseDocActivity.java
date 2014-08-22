package com.artifex.mupdfdemo;
//Download by http://www.codefans.net
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ChooseDocActivity extends Activity {

    private ShelfAdapter mAdapter;
    private Button shelf_image_button;
    private ListView shelf_list;
    private Button button_1 , button_2 , button_3;
    
    private int[] image_ids = {
    	R.drawable.test1, R.drawable.test2, R.drawable.test3,
    	R.drawable.test4, R.drawable.test5, R.drawable.empty,
    	R.drawable.empty, R.drawable.empty, R.drawable.empty
    };

    @ Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.shelf );
        init ();
        mAdapter = new ShelfAdapter ();//new adapter���������
        shelf_list.setAdapter ( mAdapter );
    }

    private void init () {
        //shelf_image_button = ( Button ) findViewById ( R.id.shelf_image_button );
        shelf_list = ( ListView ) findViewById ( R.id.shelf_list );
    }

    public class ShelfAdapter extends BaseAdapter {

        int[ ] size = new int[ 3 ];

        public ShelfAdapter () {

        }

        @ Override
        public int getCount () {

            if ( size.length > 3 ) {
                return size.length;
            } else {
                return 3;
            }

        }

        @ Override
        public Object getItem ( int position ) {

            return size[ position ];
        }

        @ Override
        public long getItemId ( int position ) {

            return position;
        }

        @ Override
        public View getView ( int position , View convertView , ViewGroup parent ) {
            LayoutInflater layout_inflater = ( LayoutInflater ) ChooseDocActivity.this.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            View layout = layout_inflater.inflate ( R.layout.shelf_list_item , null );
            Button button_1 , button_2 , button_3;
            button_1 = ( Button ) layout.findViewById ( R.id.button_1 );
            button_2 = ( Button ) layout.findViewById ( R.id.button_2 );
            button_3 = ( Button ) layout.findViewById ( R.id.button_3 );
            button_1.setId(position * 3);            
            button_1.setBackgroundResource(image_ids[position*3]);
            button_2.setId(position * 3 + 1);
            button_2.setBackgroundResource(image_ids[position*3+1]);
            button_3.setId(position * 3 + 2);
            button_3.setBackgroundResource(image_ids[position*3+2]);
            button_1.setOnClickListener ( new ButtonOnClick () );
            button_2.setOnClickListener ( new ButtonOnClick () );
            button_3.setOnClickListener ( new ButtonOnClick () );
            return layout;
        }
    };

    public class ButtonOnClick implements OnClickListener {
        @ Override
        public void onClick ( View v ) {
           int _id = v.getId();                    
           //Toast.makeText ( ChooseDocActivity.this , Integer.toString(_id) , Toast.LENGTH_SHORT ).show ();   
           if(_id<5){
			Intent intent = new Intent(ChooseDocActivity.this, MuPDFActivity.class);
			//intent.putExtra("BOOK_TYPE", book_type);
			intent.putExtra("BOOK_NO", _id);
			startActivity(intent);
        	   
           }
           
        }
    }
}