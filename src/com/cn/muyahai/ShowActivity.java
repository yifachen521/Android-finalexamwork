package com.cn.muyahai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ShowActivity extends Activity {
	String citycode;
	ListView citylistview;
	TextView teView;
	Button button;
	Button btn_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		button = (Button)findViewById(R.id.btn_right);
		teView =(TextView)findViewById(R.id.textView1);
		btn_back = (Button)findViewById(R.id.button1);
		btn_back.setOnClickListener(mGoBack);
		button.setOnCreateContextMenuListener(this);
		final String[] citylist = {"绵阳","驻马店","郑州","成都","北京","上海","重庆"};
		citylistview = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShowActivity.this, R.layout.item3,citylist);
		citylistview.setAdapter(adapter);
		AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				parent.getAdapter().getItem(position);
				teView =(TextView)findViewById(R.id.textView1);
				if(teView.getText().equals("绵阳")) {
					Intent intent = new Intent(ShowActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101270401");
					intent.putExtras(bundle);
					startActivity(intent);
				}else if (teView.getText().equals("驻马店")) {
					Intent intent = new Intent(ShowActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101181601");
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else if (teView.getText().equals("重庆")) {
					Intent intent = new Intent(ShowActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101040100");
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else if (teView.getText().equals("郑州")) {
					Intent intent = new Intent(ShowActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101180101");
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else if (teView.getText().equals("上海")) {
					Intent intent = new Intent(ShowActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101020100");
					intent.putExtras(bundle);
					startActivity(intent);
				}else if (teView.getText().equals("北京")) {
					Intent intent = new Intent(ShowActivity.this,IndexActivity.class);
					Bundle bundle =new Bundle();
					bundle.putString("cname", "101010100");
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}
			
		};
		citylistview.setOnItemClickListener(onItemClickListener);
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
			Intent intent = new Intent(ShowActivity.this,ShowActivity.class);
			startActivity(intent);
			break;
		case R.id.action_2:
			Intent intent2 = new Intent(ShowActivity.this,IndexActivity.class);
			Bundle bundle =new Bundle();
			bundle.putString("cname", "101181601");
			intent2.putExtras(bundle);
			startActivity(intent2);
			break;
		case R.id.action_3:
			Intent intent3 = new Intent(ShowActivity.this,QueryActivity.class);
			startActivity(intent3);
			break;
		case R.id.action_4:
			Intent intent4 = new Intent(ShowActivity.this,MainActivity.class);
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

}
