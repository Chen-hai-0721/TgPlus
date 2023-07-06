package com.easy.tgPlus;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Button;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;
import android.content.SharedPreferences;

public class MainActivity extends Activity{ 

	private View.OnClickListener myOnclick = new View.OnClickListener(){

		@Override
		public void onClick(View v){
			int oid = v.getId();

			if (oid == R.id.Btn_start){
                ModuleConfigs modConf = ModuleConfigs.getInstance();
				SharedPreferences.Editor edit = modConf.getConf().edit();
				edit.putString("Test", "test");
				edit.apply();
				//Toast.makeText(modConf.con,"测试:配置写入",Toast.LENGTH_LONG).show();
			}else if (oid == R.id.Btn_stop){
				ModuleConfigs modConf = ModuleConfigs.getInstance();
				SharedPreferences sp = modConf.getConf();
				String str = sp.getString("Test", null);
				Toast.makeText(modConf.con, str == null ? "测试配置不存在": str, Toast.LENGTH_SHORT).show();
			}else if (oid == R.id.Btn_test){
                Toast.makeText(MainActivity.this, "测试" + getApplicationContext().getPackageResourcePath() + 10 / 0, Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(MainActivity.this, "此ID(" + oid + ")事件未注册", Toast.LENGTH_SHORT).show();
			}
		}

	};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        LinearLayout rootlayout = new LinearLayout(this);

		TextView info = new TextView(MainActivity.this);
		info.setText(isActivate() ?"模块已激活": "模块未激活");
		rootlayout.addView(info);

		Button btn = new Button(MainActivity.this);
		btn.setAllCaps(false);
		btn.setText("Start");
		btn.setId(R.id.Btn_start);
		btn.setOnClickListener(myOnclick);
		rootlayout.addView(btn);

		btn = new Button(MainActivity.this);
		btn.setText("stop");
		btn.setId(R.id.Btn_stop);
		btn.setOnClickListener(myOnclick);
		rootlayout.addView(btn);

		btn = new Button(MainActivity.this);
		btn.setText("test");
		btn.setId(R.id.Btn_stop);
		btn.setOnClickListener(myOnclick);
		rootlayout.addView(btn);

		Button untest = new Button(MainActivity.this);
		untest.setText("未注册的按钮");
		untest.setId(3);
		untest.setOnClickListener(myOnclick);
		rootlayout.addView(untest);

		rootlayout.setGravity(Gravity.CENTER);
        rootlayout.setOrientation(LinearLayout.VERTICAL);
        rootlayout.setBackgroundColor(0xFF545454);

		setContentView(rootlayout);
    }

	private static boolean isActivate(){
		return false;
	}

} 
