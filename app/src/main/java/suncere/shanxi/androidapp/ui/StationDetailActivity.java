package suncere.shanxi.androidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.shinelw.library.ColorArcProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.model.CityStationModel;
import suncere.shanxi.androidapp.presenter.BasePresenter;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.ColorUtils;

/**
 * @author lys
 * @time 2019/2/19 15:46
 * @desc:
 */
public class StationDetailActivity extends MvpActivity implements IView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.station_title_tv)
    TextView station_title_tv;

    @BindView(R.id.updata_time_tv)
    TextView updata_time_tv;

    @BindView(R.id.aqi_capbar)
    ColorArcProgressBar aqi_capbar;

    @BindView(R.id.primary_pollutant_tv)
    TextView primary_pollutant_tv;

    @BindView(R.id.PM2_5_value_tv)
    TextView PM2_5_value_tv;
    @BindView(R.id.PM10_value_tv)
    TextView PM10_value_tv;
    @BindView(R.id.NO2_value_tv)
    TextView NO2_value_tv;
    @BindView(R.id.SO2_value_tv)
    TextView SO2_value_tv;
    @BindView(R.id.O3_value_tv)
    TextView O3_value_tv;
    @BindView(R.id.CO_value_tv)
    TextView CO_value_tv;

    CityStationModel.StationListBean stationListBean;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_detail);
        ButterKnife.bind(this);
        initView();
    }

    //初始化控件
    private void initView() {
        stationListBean = (CityStationModel.StationListBean) getIntent().getSerializableExtra("StationBean");
        if (stationListBean != null) {
            station_title_tv.setText("" + stationListBean.getPositionName());
            updata_time_tv.setText(ColorUtils.stringToData(stationListBean.getTimePoint(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:00更新"));
            PM2_5_value_tv.setText(stationListBean.getPM2_5() + "");
            PM10_value_tv.setText(stationListBean.getPM10() + "");
            NO2_value_tv.setText(stationListBean.getNO2() + "");
            SO2_value_tv.setText(stationListBean.getSO2() + "");
            O3_value_tv.setText(stationListBean.getO3() + "");
            CO_value_tv.setText(stationListBean.getCO()+"");

            primary_pollutant_tv.setText(ColorUtils.PollutantChiness2Eglish(stationListBean.getPrimaryPollutant()+""));

            try {
                aqi_capbar.setCurrentValues(Float.valueOf(stationListBean.getAQI()));
                aqi_capbar.setTextSize(14);
                aqi_capbar.setUnit(stationListBean.getQuality() + "");
                aqi_capbar.setBgArcWidth(180);
            } catch (Exception e) {
            }

        }
    }

    @OnClick({R.id.back_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void getDataSuccess(Object response) {

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
}
