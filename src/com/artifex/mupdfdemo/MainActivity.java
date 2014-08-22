package com.artifex.mupdfdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends Activity implements View.OnClickListener{

	static int book_type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button StoryButton = (Button)findViewById(R.id.button_story);
		StoryButton.setOnClickListener((android.view.View.OnClickListener) this);
		
	
		Button PoemButton = (Button)findViewById(R.id.button_poem);
		PoemButton.setOnClickListener((android.view.View.OnClickListener) this);
		
	
		Button ArticleButton = (Button)findViewById(R.id.button_article);
		ArticleButton.setOnClickListener((android.view.View.OnClickListener) this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_story:
			book_type = 1;
			break;
		case R.id.button_poem:
			book_type = 2;
			break;
		case R.id.button_article:
			book_type = 3;
			break;

		default:
			break;
		}
	Intent intent = new Intent(MainActivity.this, ChooseDocActivity.class);
	intent.putExtra("BOOK_TYPE", book_type);
	startActivity(intent);
	}
	
	
}
