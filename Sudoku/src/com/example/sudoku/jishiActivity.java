package com.example.sudoku;

import com.example.sudoku.R;

import android.R.color;
import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class jishiActivity extends Activity implements OnClickListener {

	private int num1, num2, num3, result, trueResult, a, b, c;
	private Button btn_star, btn_next, btn_PD;
	private EditText text; // 输入答案
	private TextView textView; // 题目
	private TextView textViewtip; // 提示
	private int startTime = 0;
	Core core = new Core();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jishi_layout);
		btn_next = (Button) findViewById(R.id.b2);
		btn_next.setOnClickListener(this);
		btn_PD = (Button) findViewById(R.id.button1);
		btn_PD.setOnClickListener(this);
		text = (EditText) findViewById(R.id.editText1);
		textView = (TextView) findViewById(R.id.textView2);
		textViewtip = (TextView) findViewById(R.id.textView5);
		ActionBar actionBar = getActionBar();//获取ActionBar对象
		actionBar.setDisplayShowHomeEnabled(true);//显示应用程序图标
		actionBar.setDisplayHomeAsUpEnabled(true);//将应用程序图标转变为可点击图标，并添加一个返回箭头

		CreatCal();
		final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);

		Button btnStart = (Button) findViewById(R.id.btnStart);

		Button btnStop = (Button) findViewById(R.id.btnStop);

		Button btnRest = (Button) findViewById(R.id.btnReset);

		final EditText edtSetTime = (EditText) findViewById(R.id.edt_settime);

		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("--开始记时---");
				String ss = edtSetTime.getText().toString();
				if (!(ss.equals("") && ss != null)) {
					startTime = Integer.parseInt(edtSetTime.getText()
							.toString());
				}
				// 设置开始讲时时间
				chronometer.setBase(SystemClock.elapsedRealtime());
				// 开始记时
				chronometer.start();

			}
		});

		btnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 停止
				chronometer.stop();
			}

		});

		// 重置
		btnRest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chronometer.setBase(SystemClock.elapsedRealtime());
				a = b = c = 0;
			}

		});
		chronometer
				.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
					@Override
					public void onChronometerTick(Chronometer chronometer) {
						// 如果开始计时到现在超过了startime秒
						if (SystemClock.elapsedRealtime()
								- chronometer.getBase() > startTime * 1000) {
							chronometer.stop();
							// 给用户提示
							showDialog();
						}
					}
				});
	}

	protected void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setIcon(R.drawable.eb28d25);
		builder.setTitle("警告")
				.setMessage("时间到,完成题目个数为" + c + "正确题目个数为" + a + "错误题目个数为" + b)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});

		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			checkout(trueResult);
			// btn_PD.setBackgroundResource(R.drawable.bgpress);
			break;
		case R.id.b2:
			text.setText(null);
			textViewtip.setText(null);
			CreatCal();
			// btn_next.setBackgroundResource(R.drawable.bgpress);
			break;
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

	public void CreatCal() {
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
				a++;
			} else {
				this.textViewtip.setText("错误，正确答案是:" + this.trueResult);
				b++;
			}
			c++;
			// 处理代码
		} else { // 可以弹出警告
			Toast.makeText(this, "输入不能为空！", Toast.LENGTH_LONG).show();
		}

	}
}