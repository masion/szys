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

public class xuanzheActivity extends Activity implements OnClickListener {

	private int num1, num2, num3, result, trueResult, rand, counter, all;
	private Button btn_star, btn_next, btn_FN, btnA, btnB, btnC, btnD;
	private TextView textView; // 题目
	private TextView textViewtip; // 提示
	private boolean datiflag=false;
	Core core = new Core();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xuanzeti_layout);
		ActionBar actionBar = getActionBar();//获取ActionBar对象
		actionBar.setDisplayShowHomeEnabled(true);//显示应用程序图标
		actionBar.setDisplayHomeAsUpEnabled(true);//将应用程序图标转变为可点击图标，并添加一个返回箭头
		btn_next = (Button) findViewById(R.id.buttonnext);
		btn_next.setOnClickListener(this);
		btn_FN = (Button) findViewById(R.id.buttonfinishe);
		btn_FN.setOnClickListener(this);
		textView = (TextView) findViewById(R.id.textViewtimu);
		textViewtip = (TextView) findViewById(R.id.textViewtip);
		btnA = (Button) findViewById(R.id.buttonA);
		btnA.setOnClickListener(this);
		btnB = (Button) findViewById(R.id.buttonB);
		btnB.setOnClickListener(this);
		btnC = (Button) findViewById(R.id.buttonC);
		btnC.setOnClickListener(this);
		btnD = (Button) findViewById(R.id.buttonD);
		btnD.setOnClickListener(this);
		CreatCal();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonA:
			if(datiflag==true){textViewtip.setText("你已答过此题!!");break;}datiflag=true;
			if (Integer.parseInt(this.btnA.getText().toString().substring(2)) == trueResult) {
				textViewtip.setText("选择正确！");
				counter++;
				all++;
			} else {
				textViewtip.setText("选择错误！");
				all++;
			}
			break;
		case R.id.buttonB:
			if(datiflag==true){textViewtip.setText("你已答过此题!!");break;}datiflag=true;
			if (Integer.parseInt(this.btnB.getText().toString().substring(2)) == trueResult) {
				textViewtip.setText("选择正确！");
				counter++;
				all++;
			} else {
				textViewtip.setText("选择错误！");
				all++;
			}
			break;
		case R.id.buttonC:
			if(datiflag==true){textViewtip.setText("你已答过此题!!");break;}datiflag=true;
			if (Integer.parseInt(this.btnC.getText().toString().substring(2)) == trueResult) {
				textViewtip.setText("选择正确！");
				counter++;
				all++;
			} else {
				textViewtip.setText("选择错误！");
				all++;
			}
			break;
		case R.id.buttonD:
			if(datiflag==true){textViewtip.setText("你已答过此题!!");break;}
			datiflag=true;
			if (Integer.parseInt(this.btnD.getText().toString().substring(2)) == trueResult) {
				textViewtip.setText("选择正确！");
				counter++;
				all++;
			} else {
				textViewtip.setText("选择错误！");
				all++;
			}
			break;
		case R.id.buttonnext:
			CreatCal();
			break;
		case R.id.buttonfinishe:
			textViewtip.setText("你一共答了" + all + "题,对了"+counter+"题");// +",正确率为："
			Toast.makeText(this, "答题结束！", Toast.LENGTH_LONG).show();
			break;

		}
	}

	public int CreatCal() {
		datiflag=false;
		int i1, i2;
		char[] h = { '+', '-', 'x', '÷' };
		i1 = (int) (Math.random() * 3);
		i2 = (int) (Math.random() * 4);
		num1 = (int) (Math.random() * 100 + 1);

		num3 = (int) (Math.random() * 100 + 1);
		if (i2 == 3)
			num2 = (int) (Math.random() * 10 + 1) * num3;
		else
			num2 = (int) (Math.random() * 100 + 1);
		textView.setText(num1 + "" + h[i1] + "" + num2 + h[i2] + num3 + "=");
		trueResult = core.Cal(i1, i2, num1, num2, num3);

		rand = (int) (Math.random() * 3);
		switch (rand) {
		case 0:
			btnA.setText("A." + trueResult);
			btnB.setText("B." + (trueResult + 10));
			btnC.setText("C." + (trueResult + 20));
			btnD.setText("D." + (trueResult + 30));
			break;
		case 1:
			btnA.setText("A." + (trueResult - 10));
			btnB.setText("B." + trueResult);
			btnC.setText("C." + (trueResult + 10));
			btnD.setText("D." + (trueResult + 20));
			break;
		case 2:

			btnA.setText("A." + (trueResult - 20));
			btnB.setText("B." + (trueResult - 10));
			btnC.setText("C." + trueResult);
			btnD.setText("D." + (trueResult + 10));
			break;
		case 3:
			btnA.setText("A." + (trueResult - 30));
			btnB.setText("B." + (trueResult - 20));
			btnC.setText("C." + (trueResult - 10));
			btnD.setText("D." + this.CreatCal());
			break;
		}
		return trueResult;
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
