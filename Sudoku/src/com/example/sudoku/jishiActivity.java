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
	private EditText text; // �����
	private TextView textView; // ��Ŀ
	private TextView textViewtip; // ��ʾ
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
		ActionBar actionBar = getActionBar();//��ȡActionBar����
		actionBar.setDisplayShowHomeEnabled(true);//��ʾӦ�ó���ͼ��
		actionBar.setDisplayHomeAsUpEnabled(true);//��Ӧ�ó���ͼ��ת��Ϊ�ɵ��ͼ�꣬�����һ�����ؼ�ͷ

		CreatCal();
		final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);

		Button btnStart = (Button) findViewById(R.id.btnStart);

		Button btnStop = (Button) findViewById(R.id.btnStop);

		Button btnRest = (Button) findViewById(R.id.btnReset);

		final EditText edtSetTime = (EditText) findViewById(R.id.edt_settime);

		btnStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				System.out.println("--��ʼ��ʱ---");
				String ss = edtSetTime.getText().toString();
				if (!(ss.equals("") && ss != null)) {
					startTime = Integer.parseInt(edtSetTime.getText()
							.toString());
				}
				// ���ÿ�ʼ��ʱʱ��
				chronometer.setBase(SystemClock.elapsedRealtime());
				// ��ʼ��ʱ
				chronometer.start();

			}
		});

		btnStop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ֹͣ
				chronometer.stop();
			}

		});

		// ����
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
						// �����ʼ��ʱ�����ڳ�����startime��
						if (SystemClock.elapsedRealtime()
								- chronometer.getBase() > startTime * 1000) {
							chronometer.stop();
							// ���û���ʾ
							showDialog();
						}
					}
				});
	}

	protected void showDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setIcon(R.drawable.eb28d25);
		builder.setTitle("����")
				.setMessage("ʱ�䵽,�����Ŀ����Ϊ" + c + "��ȷ��Ŀ����Ϊ" + a + "������Ŀ����Ϊ" + b)
				.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
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
			//��������MainActivity��Intent
			Intent intent=new Intent(this,chooseActivity.class);
			//��Ӷ����Flag����Activityջ�д���MainActivity֮�ϵ�Activity����
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
		char[] h = { '+', '-', 'x', '��' };
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
				this.textViewtip.setText("����ȷ!");
				a++;
			} else {
				this.textViewtip.setText("������ȷ����:" + this.trueResult);
				b++;
			}
			c++;
			// �������
		} else { // ���Ե�������
			Toast.makeText(this, "���벻��Ϊ�գ�", Toast.LENGTH_LONG).show();
		}

	}
}