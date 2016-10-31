package chan.android.app.pocketnote;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements View.OnClickListener
{

    private ViewPager pager;
    private List<ImageView> image;
    private List<View> dots;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);

        pager=(ViewPager)findViewById(R.id.media_actions) ;
        but=(Button)findViewById(R.id.action_bar_activity_content) ;
        image =new ArrayList<ImageView>();
        dots =new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        pager.addOnPageChangeListener(listener);
        but.setOnClickListener(this);

        int[] lists={R.drawable.guidance1,R.drawable.guidance2,R.drawable.guidance3,R.drawable.guidance4};
        for (int i=0;i<lists.length;i++){
            ImageView view=new ImageView(GuideActivity.this);
            view.setBackgroundResource(lists[i]);
            image.add(view);

        }


        MyPagerAdapter madapter=new MyPagerAdapter();
        pager.setAdapter(madapter);

       // sp("filename","test","");
    }

    ViewPager.OnPageChangeListener listener=new ViewPager.OnPageChangeListener(){
        int oldPosition=0;
        @Override
        public void onPageSelected(int position) {
            dots.get(position).setBackgroundResource(R.drawable.guidance);
            dots.get(oldPosition).setBackgroundResource(R.drawable.guidance0);
            oldPosition=position;//为下一页准备，
            if(position==3){//最后一页
                but.setVisibility(View.VISIBLE);
            }else {
                but.setVisibility(View.GONE);
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };



    @Override
    public void onClick(View v) {
        Intent intent =new Intent(GuideActivity.this,MainActivity.class);
        startActivity(intent);
    }

//    /**
//     * 保存数据
//     */
//    public void sp(String spname,String Keyname,String KeyCotent){
//        //SharedPreferencesCompat mySharedPreferencesCompat=getSharedPreferences(spname,Keyname,KeyCotent);
//    }


    /**
     * 适配器
     */
    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return image.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ((ViewPager)container).addView(image.get(position),0);
            return  image.get(position);

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(image.get(position));
        }
    }


}


