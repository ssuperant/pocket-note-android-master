package chan.android.app.pocketnote;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import chan.android.app.pocketnote.util.PrefUtils;

public class SplashActivity extends Activity {
    //显示图片
    ImageView imageView;

    AnimationDrawable myanimation;//声明AnimationDrawable类（可以自定义名字）
    Resources res;//声明resources资源
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        startSplash();
       // gotoNextPage();
    }

    private void setAnimationDrable(){
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.animationdrable);
        myanimation=(AnimationDrawable)imageView.getBackground();
        myanimation.start();
    }



    private  void startSplash(){

        setAnimationDrable();


        //1.  5秒的延迟
        //初始化计时器
        Timer timer=new Timer();
        //初始化计时器任务
        TimerTask task =new TimerTask() {
            @Override
            public void run() {

                //startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                myanimation.stop();
                gotoNextPage();
            }
        };
        //启动定时器
        timer.schedule(task,4000);
    }

    //闪屏跳转到下一个界面
    private void gotoNextPage(){
        Boolean isFirstIn= PrefUtils.getBoolean(this,"isFirstIn",true);
        if (isFirstIn){
            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
            PrefUtils.setBoolean(this,"isFirstIn",false);
        }else {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
        }
        finish();
    }

}
