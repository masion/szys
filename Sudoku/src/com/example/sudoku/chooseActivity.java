package com.example.sudoku;

import com.example.sudoku.R;

import android.R.color;
import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class chooseActivity extends Activity {
	private Button btn1, btn2, btn3;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_layout);
		ActionBar actionBar = getActionBar();// 获取ActionBar对象
		actionBar.setDisplayShowHomeEnabled(true);// 显示应用程序图标
		actionBar.setDisplayHomeAsUpEnabled(true);// 将应用程序图标转变为可点击图标，并添加一个返回箭头
		btn1 = (Button) findViewById(R.id.tiankong);
		btn2 = (Button) findViewById(R.id.xuanzhe);
		btn3 = (Button) findViewById(R.id.jishi);
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chooseActivity.this, MainActivity.class);
				startActivity(intent);
				chooseActivity.this.finish();

			}
		});
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chooseActivity.this, xuanzheActivity.class);
				startActivity(intent);
				chooseActivity.this.finish();

			}
		});
		btn3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(chooseActivity.this, jishiActivity.class);
				startActivity(intent);
				chooseActivity.this.finish();

			}
		});
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			//创建启动MainActivity的Intent
			Intent intent=new Intent(this,FirstActivity.class);
			//添加额外的Flag，将Activity栈中处于MainActivity之上的Activity弹出
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
