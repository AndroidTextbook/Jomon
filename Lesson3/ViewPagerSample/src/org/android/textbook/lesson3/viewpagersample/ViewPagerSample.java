
package org.android.textbook.lesson3.viewpagersample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ViewPagerSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_sample);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = 
                new ScreenPagerAdapter(getApplicationContext());

        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_pager_sample, menu);
        return true;
    }

    private class ScreenPagerAdapter extends PagerAdapter {

        private LayoutInflater mLayoutInflater = null;
        private final int[] PAGE_RES = new int[] {
                R.layout.page1,
                R.layout.page2,
                R.layout.page3
        };

        public ScreenPagerAdapter(Context context) {
            super();
            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return PAGE_RES.length;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LinearLayout page = (LinearLayout) mLayoutInflater.inflate(
                    PAGE_RES[position], null);
            container.addView(page);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}
