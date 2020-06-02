package com.cn.muyahai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class QueryActivity extends Activity {
	Button button;
	Button btn_back;
	EditText editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);
		button = (Button)findViewById(R.id.btn_right);
		editText = (EditText)findViewById(R.id.editText1);
		button.setOnCreateContextMenuListener(this);
		btn_back = (Button)findViewById(R.id.button1);
		btn_back.setOnClickListener(mGoBack);
	}
	 @Override
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
	        MenuInflater me = getMenuInflater();
	        me.inflate(R.menu.menu,menu);
	        super.onCreateContextMenu(menu, v, menuInfo);
	    }
	    @Override
	    public boolean onContextItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
			case R.id.action_1:
				Intent intent = new Intent(QueryActivity.this,ShowActivity.class);
				startActivity(intent);
				break;
			case R.id.action_2:
				Intent intent2 = new Intent(QueryActivity.this,IndexActivity.class);
				Bundle bundle =new Bundle();
				bundle.putString("cname", "101181601");
				intent2.putExtras(bundle);
				startActivity(intent2);
				break;
			case R.id.action_3:
				Intent intent3 = new Intent(QueryActivity.this,QueryActivity.class);
				startActivity(intent3);
				break;
			case R.id.action_4:
				Intent intent4 = new Intent(QueryActivity.this,MainActivity.class);
				startActivity(intent4);
				break;
			default:
				break;
			}
	        return super.onContextItemSelected(item);
	    }
	    public void showmenu(View v) {
	    	v.showContextMenu();
	    }

	public OnClickListener mGoBack = new OnClickListener() {

		public void onClick(View v) {
			finish();
		}
	};
	    	public void query(View v) {
	    		String ccode = editText.getText().toString();
				if(ccode.equals("绵阳")) {
					Intent intent = new Intent(QueryActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101270401");
					intent.putExtras(bundle);
					startActivity(intent);
				}else if (ccode.equals("驻马店")) {
					Intent intent = new Intent(QueryActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101181601");
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else if (ccode.equals("重庆")) {
					Intent intent = new Intent(QueryActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101040100");
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else if (ccode.equals("郑州")) {
					Intent intent = new Intent(QueryActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101180101");
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else if (ccode.equals("上海")) {
					Intent intent = new Intent(QueryActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101020100");
					intent.putExtras(bundle);
					startActivity(intent);
				}else if (ccode.equals("北京")) {
					Intent intent = new Intent(QueryActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101010100");
					intent.putExtras(bundle);
					startActivity(intent);
				}
	    	}

}