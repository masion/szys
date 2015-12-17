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

public class MainActivity extends Activity implements OnClickListener {

	private int num1, num2, num3, result, trueResult;
	private Button btn_star, btn_next, btn_PD;
	private EditText text; // 输入答案
	private TextView textView; // 题目
	private TextView textViewtip; // 提示
	Core core = new Core();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar actionBar = getActionBar();//获取ActionBar对象
		actionBar.setDisplayShowHomeEnabled(true);//显示应用程序图标
		actionBar.setDisplayHomeAsUpEnabled(true);//将应用程序图标转变为可点击图标，并添加一个返回箭头
		btn_next = (Button) findViewById(R.id.button2);
		btn_next.setOnClickListener(this);
		btn_PD = (Button) findViewById(R.id.button1);
		btn_PD.setOnClickListener(this);
		text = (EditText) findViewById(R.id.editText1);
		textView = (TextView) findViewById(R.id.textView2);
		textViewtip = (TextView) findViewById(R.id.textView5);
		CreatCal();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			checkout(trueResult);
			// btn_PD.setBackgroundResource(R.drawable.bgpress);
			break;
		case R.id.button2:
			text.setText(null);
			textViewtip.setText(null);
			CreatCal();
			// btn_next.setBackgroundResource(R.drawable.bgpress);
			break;

		}
	}

	public void CreatCal() {
		int i1, i2;
		char[] h = { '+', '-', 'x', '÷' };
		i1 = (int) (Math.random() * 3);
		i2 = (int) (Math.random() * 4);
		num1 = (int) (Math.random() * 100 + 1);
	
		num3 = (int) (Math.random() * 100 + 1);
		if(i2==3)
			num2 = (int) (Math.random() * 10 + 1)*num3;  
		else
			num2 = (int) (Math.random() * 100 + 1);  
		textView.setText(num1 + "" + h[i1] + "" + num2 + h[i2] + num3 + "=");
		trueResult = core.Cal(i1, i2, num1, num2, num3);
		// trueResult=core.Add(i1,i2,num1,num2,num3);
		/*
		 * switch (i1) { case 0: trueResult=core.Add(num1, num2);
		 * 
		 * break; case 1: trueResult=core.Subtraction(num1, num2); break; case
		 * 2: trueResult=core.multiplication(num1, num2); break; case 3:
		 * trueResult=core.Division(num1, num2); break; }
		 */
	}

	public void checkout(int trueResult) {
		String test = text.getText().toString();
		if (test != null && test.length() > 0) {
			result = Integer.parseInt(this.text.getText().toString());
			if (result == trueResult) {
				this.textViewtip.setText("答案正确!");
			} else {
				this.textViewtip.setText("错误，正确答案是:" + this.trueResult);
			}

			// 处理代码
		} else { // 可以弹出警告
			Toast.makeText(this, "输入不能为空！", Toast.LENGTH_LONG).show();
		}

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			//创建启动MainActivity的Intent
			Intent intent=new Intent(this,chooseActivity.class);
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
