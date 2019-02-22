package suncere.shanxi.androidapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.customview.TabBar;
import suncere.shanxi.androidapp.presenter.UpdataAppPresenter;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.updataapp.UpdateManager;
import suncere.shanxi.androidapp.utils.Tools;


public class MainActivity extends MvpActivity<UpdataAppPresenter> implements IView {

    UpdataAppPresenter mUpdataAppPresenter;
    TabBar mTabBar;
    int mFragmentPage = 0;
    Tools mtools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        initView();
    }

    @Override
    protected UpdataAppPresenter createPresenter() {
        mUpdataAppPresenter = new UpdataAppPresenter(this);
        return mUpdataAppPresenter;
    }


    @Override
    protected void onStart() {
        super.onStart();

        mUpdataAppPresenter.updataAPP(MyApplication.getMyApplicationVersionName());
    }

    private void initView() {
        mtools = new Tools(this);
        Intent intent = getIntent();


        boolean isJPush = intent.getBooleanExtra("isJPush", false);// 从消息栏进入APP
        if (isJPush) mFragmentPage = 4;//跳到预警界面

        mTabBar = (TabBar) findViewById(R.id.main_tabView);
        mTabBar.mSelectTextColor = Color.parseColor("#4AC0D2");
        mTabBar.mTabBarViewBackgroundColor=Color.parseColor("#FFFFFF");

        mTabBar.mTextVTitleArray=new String[]{"首页","地图","排名","对比","设置"};//公众版
        mTabBar.mSelectImageVIconArray = new int[]{R.mipmap.icon_home_active, R.mipmap.icon_map_active, R.mipmap.icon_paiming_active,R.mipmap.icon_duibi_active, R.mipmap.icon_setting_active};
        mTabBar.mUnSelectImageVIconArray = new int[]{R.mipmap.icon_home, R.mipmap.icon_map, R.mipmap.icon_paiming,R.mipmap.icon_duibi, R.mipmap.icon_setting};
        //,SetFragment.class  ,R.drawable.icon_setting  ,R.drawable.icon_setting_active
        mTabBar.mTabFragmentClassArray = new Class<?>[]{HomeFragment3.class, MapFragment.class, ListFragment2.class,CompareAnalyzeFragment.class, SetFragment2.class};

        mTabBar.mDefaultSelectIndex = mFragmentPage;
        mTabBar.refreshTabBar();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //退出时的时间
    private long mExitTime;
    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2500){
            Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        }else{
            ShowExitAPP();
        }
    }


    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    public void getDataSuccess(Object response) {
        if (response != null) {
            String downloadUrl = response.toString();
            if (downloadUrl.startsWith("http") && downloadUrl.endsWith(".apk"))
                new UpdateManager(this).checkUpdate(downloadUrl);
        }
    }
}
