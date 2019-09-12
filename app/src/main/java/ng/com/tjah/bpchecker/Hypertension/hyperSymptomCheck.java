package ng.com.tjah.bpchecker.Hypertension;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import ng.com.tjah.bpchecker.MainActivity;
import ng.com.tjah.bpchecker.R;
import ng.com.tjah.bpchecker.ResultActivity;
import ng.com.tjah.bpchecker.ViewPagerAdapter;


public class hyperSymptomCheck extends AppCompatActivity implements FragmentOne.SendMessage,
        FragmentTwo.SendMessage, FragmentThree.SendMessage, FragmentFour.SendMessage, FragmentFive.SendMessage {

    Button btnPrev, backBtn;
    Fragment fragment = null;
    String data, data1, data2, data3, data4;

    private ViewPager viewPager;
    private ViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private Fragment[] layouts;
    private Button btnNext;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hyper_symptom_check);

        viewPager = findViewById(R.id.view_pager);
        dotsLayout = findViewById(R.id.layoutDots);
        btnPrev = findViewById(R.id.btn_prevQ);
        btnNext = findViewById(R.id.btn_nextQ);
        backBtn = findViewById(R.id.btn_back);

        layouts = new Fragment[]{
                fragment = new FragmentOne(),
                fragment = new FragmentTwo(),
                fragment = new FragmentThree(),
                fragment = new FragmentFour(),
                fragment = new FragmentFive()

        };

        addBottomDots(0);


        myViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);


        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(-1);
                if (current < layouts.length) {
                    // move to prev screen
                    viewPager.setCurrentItem(current);

                }
            }
        });



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    if(data == null || data1 == null  ||
                            data3 == null || data4 == null)
                    {
                        Toast.makeText(hyperSymptomCheck.this, "please answer all questions", Toast.LENGTH_LONG).show();
                    }else {

                        launchResultScreen();
                    }


                    }



            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(hyperSymptomCheck.this, MainActivity.class));
                finish();

            }
        });


    }


    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active_quiz);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive_quiz);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchResultScreen() {

        Intent intent = new Intent(hyperSymptomCheck.this, ResultActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putString("ans1", data);
        mBundle.putString("ans2", data1);
        mBundle.putString("ans3", data2);
        mBundle.putString("ans4", data3);
        mBundle.putString("ans5", data4);
        intent.putExtras(mBundle);

        startActivity(intent);
        finish();

    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to Skip Test
                btnNext.setText("Analyze");

            } else {
                // still pages are left
                btnNext.setText("Next");
                btnPrev.setVisibility(View.VISIBLE);
            }

            if (position == 0) {
                btnPrev.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };


    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.view_pager + ":" + 1;
        FragmentTwo f = (FragmentTwo) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
        dataEntered(message);
    }

    @Override
    public void sendData1(String message) {
        String tag = "android:switcher:" + R.id.view_pager + ":" + 2;
        FragmentThree f = (FragmentThree) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
        dataEntered1(message);
    }

    @Override
    public void sendData2(String message) {
        String tag = "android:switcher:" + R.id.view_pager + ":" + 3;
        FragmentFour f = (FragmentFour) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
        dataEntered2(message);
    }

    @Override
    public void sendData4(String message) {
        String tag = "android:switcher:" + R.id.view_pager + ":" + 4;
        FragmentFive f = (FragmentFive) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
        dataEntered3(message);
    }

    public void dataEntered(String m) {
        data = m;
        //return data;
    }

    public void dataEntered1(String m) {
        data1 = m;
        //return data;
    }

    public void dataEntered2(String m) {
        data2 = m;
        //return data;
    }

    public void dataEntered3(String m) {
        data3 = m;
        //return data;
    }

    public void dataEntered4(String m) {
        data4 = m;
        //return data;
    }

    @Override
    public void sendData3(String message) {
        String tag = "android:switcher:" + R.id.view_pager + ":" + 4;
        FragmentFive f = (FragmentFive) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
        dataEntered4(message);
    }


    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate((XmlPullParser) layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    @Override
    public void onBackPressed() {
//        startActivity(new Intent(hyperSymptomCheck.this, MainActivity.class));
        finish();
        super.onBackPressed();
    }

}
