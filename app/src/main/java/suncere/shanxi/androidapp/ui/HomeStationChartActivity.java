package suncere.shanxi.androidapp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.customview.PollutantsView;
import suncere.shanxi.androidapp.databinding.HomeStationchartActivityBinding;
import suncere.shanxi.androidapp.model.HomeDataChart24Model;
import suncere.shanxi.androidapp.model.entity.HomeStationBean;
import suncere.shanxi.androidapp.model.entity.HomeStationChartBean;
import suncere.shanxi.androidapp.presenter.HomeStationChartPresenter;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.ColorUtils;
import suncere.shanxi.androidapp.utils.ToolUtils;


/**
 *  Created by Hjo on 2017/5/23.
 */
public class HomeStationChartActivity extends MvpActivity<HomeStationChartPresenter> implements IView {
    HomeStationChartPresenter  mhomeStationChartPresenter;
    HomeStationchartActivityBinding mBinding;

    List<String > mYvalue;
    List<String > mXvalue;
    List<Integer> mBarColors;

    String mUniqueCode;
    String mpollutantCode="99"; //AQI
    String mcount="24";  //返回72条数据
    String Type="2" ;//0 城市 1 区县 2 站点

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this,R.layout.home_stationchart_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //设置修改状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏的颜色，和你的app主题或者标题栏颜色设置一致就ok了
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        mYvalue=new ArrayList<>();
        mXvalue=new ArrayList<>();
        mBarColors=new ArrayList<>();

        Intent intent=getIntent();
        HomeStationBean bean = (HomeStationBean) intent.getSerializableExtra("homeStationBean");
        Type=intent.getStringExtra("Type");
        mUniqueCode=bean.getUniqueCode();
        mBinding.homestationchartTitleText.setText(bean.getPositionName()!=null?bean.getPositionName()+"":bean.getCityName()+"");

        mBinding.homePollutantsView.setmSelceTextListener(new PollutantsView.SelceTextListener() {
            @Override
            public void onSelect(String pollutantName, String pollutantCode) {
//                Log.e("hjooooo","pollutantName="+pollutantName + "  pollutantCode="+pollutantCode);
                mpollutantCode=pollutantCode;
                if (mpollutantCode.equals("99")){
                    mBinding.homeChartTab.setText("过去24小时AQI指数");
                }else{
                    mBinding.homeChartTab.setText("过去24小时"+pollutantName+"浓度值");
                }

                getData();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }

    @Override
    protected HomeStationChartPresenter createPresenter() {
        mhomeStationChartPresenter=new HomeStationChartPresenter(this);
        return mhomeStationChartPresenter;
    }

    private void getData(){
        mhomeStationChartPresenter.getChartData(mUniqueCode,mpollutantCode,mcount,Type);
    }


    @Override
    public void getDataSuccess(Object response) {
        if(response!=null) {
            mBinding.setStationBean((HomeDataChart24Model) response);
            chartDataBind(((HomeDataChart24Model) response).getChartDatas());
        }
    }
    private void chartDataBind( List<HomeStationChartBean> Chartdatas){
        mYvalue.clear();
        mXvalue.clear();
        mBarColors.clear();
        if (Chartdatas!=null && Chartdatas.size()>0) {
            for (HomeStationChartBean bean : Chartdatas) {
                mYvalue.add(bean.getYValue());
                mBarColors.add(ColorUtils.getColorWithLevel(bean.getLevel()));
                mXvalue.add(ColorUtils.stringToData(bean.getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "HH:mm"));
            }
        }

        mBinding.homeChart.mPointColors=mBarColors;
        mBinding.homeChart.bindBarChart(mYvalue,mXvalue);
        mBinding.homeChart.mIs_AccordingTo_List_SetMin = true;
        mBinding.homeChart.isNeedMinValueMoreSmall = true;
        mBinding.homeChart.mYLineColor = Color.WHITE;
        mBinding.homeChart.mXLineColor = Color.WHITE;
        mBinding.homeChart. mIsShowPointColor=true;
        mBinding.homeChart.mLineColor=Color.WHITE;
        mBinding.homeChart.mdefaulYValueTextColor=Color.WHITE;
        mBinding.homeChart.mXAxisEveryFewParagraphs=1;

        mBinding.homeChart.mBarChartSize= mBinding.homeChart.dp2px(13);
        mBinding.homeChart.mXScaleWidth= mBinding.homeChart.dp2px(17.6f);
        mBinding.homeChart.mXAxisEveryFewParagraphs = 3;

        mBinding.homeChart.mXAxisTextColor = Color.WHITE;
        mBinding.homeChart.mYAxisTextColor = Color.WHITE;

        mBinding.homeChart.refreshChartView();
        mBinding.homeChart.mIs_AccordingTo_PointLabelValue_JudgmentColor=false;

//         mBinding.homeChart.bindBarChart(mYvalue,mXvalue);
//         mBinding.homeChart.mIs_AccordingTo_List_SetMin = true;
//         mBinding.homeChart.isNeedMinValueMoreSmall = true;
//         mBinding.homeChart.mYLineColor = Color.WHITE;
//         mBinding.homeChart.mXLineColor = Color.WHITE;
//         mBinding.homeChart. mIsShowPointColor=true;
//         mBinding.homeChart.mIsBarChartRotatePointText90=false;
//
//         mBinding.homeChart.mDefaultPointColor= Color.rgb(22,189,62);
//         mBinding.homeChart.mBarChartSize= mBinding.homeChart.dp2px(17);
//         mBinding.homeChart.mdefaulYValueTextColor=Color.WHITE;
//         mBinding.homeChart.mXAxisEveryFewParagraphs=1;
//
//         mBinding.homeChart.mXAxisTextColor = Color.WHITE;
//         mBinding.homeChart.mYAxisTextColor = Color.WHITE;
//         mBinding.homeChart.mIs_AccordingTo_PointLabelValue_JudgmentColor=false;
//         mBinding.homeChart.mIsShowYAxis=false;
//         mBinding.homeChart.refreshChartView();
    }

    @OnClick({R.id.homestationchart_title_back,R.id.homestationchart_title_refresh_rela})
    public void On_Click (View view){
        switch (view.getId()){
            case R.id.homestationchart_title_back:
                this.finish();
                break;
            case R.id.homestationchart_title_refresh_rela:
                getData();
                break;
        }
    }

    @Override
    public void getDataFail(String msg) {

    }

    @Override
    public void showRefresh() {
        mBinding.homestationchartTitleRefreshImage.startAnimation(ToolUtils.getRefreshAnimation());
    }

    @Override
    public void finishRefresh() {
        mBinding.homestationchartTitleRefreshImage.clearAnimation();
    }


}
