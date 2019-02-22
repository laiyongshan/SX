package suncere.shanxi.androidapp.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.presenter.BasePresenter;

/**
 * Created by Hjo on 2017/5/11.
 */

public abstract  class MvpActivity<P extends BasePresenter> extends BaseActivity {

    protected  P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        mPresenter=createPresenter();
    }

    protected  abstract  P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();//RXjava取消注册，以避免内存泄露
        }
    }
}
