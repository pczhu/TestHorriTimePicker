/*
 * Copyright (c) 2012 Wireless Designs, LLC
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.example.testsurrondviewpager;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * PagerActivity: A Sample Activity for PagerContainer
 */
public class PagerActivity extends Activity implements OnClickListener {
	List<String> data = null;
    PagerContainer mContainer;
	private ViewPager pager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        data = new ArrayList<String>();
        data.add("2014年7月");
        data.add("2014年8月");
        data.add("2014年9月");
        data.add("2014年10月");
        data.add("2014年11月");
        data.add("2014年12月");
        data.add("2015年1月");
        data.add("2015年2月");
        data.add("2015年3月");

        mContainer = (PagerContainer) findViewById(R.id.pager_container);
        Button pre_btn = (Button) findViewById(R.id.pre_btn);
        Button next_btn = (Button) findViewById(R.id.next_btn);
        pager = mContainer.getViewPager();
        PagerAdapter adapter = new MyPagerAdapter();
        pager.setOffscreenPageLimit(1);
        pager.setAdapter(adapter);

        pager.setPageMargin(15);
        pre_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Toast.makeText(PagerActivity.this, arg0+";"+((TextView)pager.findViewWithTag(""+arg0)).getText(), 0).show();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
    }
    
    //Nothing special about this adapter, just throwing up colored views for demo
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
        	TextView textView = new TextView(PagerActivity.this);
        	textView.setGravity(Gravity.CENTER);
        	TextPaint tp = textView .getPaint();
        	tp.setFakeBoldText(true);
        	textView.setText(data.get(position));
        	textView.setTag(""+position);
            container.addView(textView);
            return textView;
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return data.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pre_btn:
			if(pager.getCurrentItem() > 0)
				pager.setCurrentItem(pager.getCurrentItem()-1);
			break;
		case R.id.next_btn:
			if(pager.getCurrentItem() < data.size())
				pager.setCurrentItem(pager.getCurrentItem()+1);
			break;

		default:
			break;
		}
		
	}
}