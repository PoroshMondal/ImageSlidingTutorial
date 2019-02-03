package com.example.user.imageslidingtutorial;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    List<ModelObject> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = getImageList();

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(this,list));

        CircleIndicator circleIndicator = findViewById(R.id.circle_indicate);
        circleIndicator.setViewPager(viewPager);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);

    }

    private List<ModelObject> getImageList(){
        List<ModelObject> imageList = new ArrayList<>();

        imageList.add(new ModelObject(R.drawable.image_one));
        imageList.add(new ModelObject(R.drawable.image_two));
        imageList.add(new ModelObject(R.drawable.image_three));
        imageList.add(new ModelObject(R.drawable.image_three));

        return imageList;
    }


    // need to work on this timer task
    // reset the timer after a swipe by user
    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < list.size() - 1) {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
