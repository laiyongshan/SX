package suncere.shanxi.androidapp.ui;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.adapter.TrendAdapter;
import suncere.shanxi.androidapp.customview.PollutantsView;
import suncere.shanxi.androidapp.model.HomeDataChart24Model;
import suncere.shanxi.androidapp.model.entity.HomeStationChartBean;
import suncere.shanxi.androidapp.model.entity.TrendBean;
import suncere.shanxi.androidapp.presenter.HomePresenter;
import suncere.shanxi.androidapp.ui.iview.IHomeView;
import suncere.shanxi.androidapp.utils.ColorUtils;
import suncere.shanxi.androidapp.utils.IndicatorLineUtil;
import suncere.shanxi.androidapp.utils.ToolUtils;

/**
 * @author lys
 * @time 2019/2/19 15:10
 * @desc:
 */
public class TrendActivity extends MvpActivity<HomePresenter> implements IHomeView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.time_tabLayout)
    TabLayout time_tabLayout;

    @BindView(R.id.trend_chart)
    ColumnChartView trend_chart;

    @BindView(R.id.trend_PollutantsView)
    PollutantsView trend_PollutantsView;

    @BindView(R.id.pollutant_tv)
    TextView pollutant_tv;

    @BindView(R.id.trend_rv)
    RecyclerView trend_rv;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    private String[] time_titles = {"12小时", "24小时", "30日"};

    String mCityCode;
    int mDateType = 0;
    int mCount = 12;
    String mPollutantCode = "99";

    TrendAdapter mTrendAdapter;

    List<TrendBean> trendBeanList = new ArrayList<>();
    List<HomeStationChartBean> Chartdatas = new ArrayList<>();

    private ColumnChartData barData;
    private static final int DEFAULT_DATA = 0;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = true;
    private int dataType = DEFAULT_DATA;

    HomePresenter mHomePresenter;

    @Override
    protected HomePresenter createPresenter() {
        mHomePresenter = new HomePresenter(this);
        return mHomePresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trend);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getTrendChartDatas();
    }

    private void initView() {

        mCityCode = getIntent().getStringExtra("CityCode") + "";

        refresh_layout.setOnRefreshListener(this);
        refresh_layout.setColorSchemeColors(ColorUtils.Colors);

        trend_rv.setLayoutManager(new LinearLayoutManager(this));
        mTrendAdapter = new TrendAdapter(trendBeanList);
        trend_rv.setAdapter(mTrendAdapter);

        for (int i = 0; i < time_titles.length; i++) {
            time_tabLayout.addTab(time_tabLayout.newTab());
            time_tabLayout.getTabAt(i).setText(time_titles[i]);
        }

        time_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    mDateType = 0;
                    mCount = 12;
                } else if (tab.getPosition() == 1) {
                    mDateType = 0;
                    mCount = 24;
                } else {
                    mDateType = 1;
                    mCount = 30;
                }

                getTrendChartDatas();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        time_tabLayout.post(new Runnable() {
            @Override
            public void run() {
                IndicatorLineUtil.setIndicator(time_tabLayout, 20, 20);
            }
        });

        trend_PollutantsView.setmSelceTextListener(new PollutantsView.SelceTextListener() {
            @Override
            public void onSelect(String pollutantName, String pollutantCode) {
                pollutant_tv.setText(pollutantName + "");
                mPollutantCode = pollutantCode;

                getTrendChartDatas();
            }
        });
    }


    @OnClick({R.id.back_rl})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.back_rl:
                finish();
                break;
        }
    }


    private void getTrendChartDatas() {
        mHomePresenter.getChartData(mCityCode, mPollutantCode, mCount, mDateType);
    }

    @Override
    public void onRefresh() {
        getTrendChartDatas();
    }


    @Override
    public void getDataSuccess(Object response) {

    }

    @Override
    public void getChartSuccess(Object response) {
        if (response != null) {
            Chartdatas.clear();
            trendBeanList.clear();
            Chartdatas = ((HomeDataChart24Model) response).getChartDatas();
            if (Chartdatas != null)
                bindDatas(Chartdatas);
        }
    }

    @Override
    public void getCityStationSuccess(Object response) {

    }

    private void bindDatas(List<HomeStationChartBean> Chartdatas) {
        for (HomeStationChartBean bean : Chartdatas) {
                trendBeanList.add(new TrendBean(ColorUtils.stringToData(bean.getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "MM.dd HH:00"), bean.getYValue()));

        }
        mTrendAdapter.notifyDataSetChanged();
        generateBarData(Chartdatas);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void generateBarData(List<HomeStationChartBean> Chartdatas) {
        List<Column> columns = new ArrayList<Column>();
        List<AxisValue> axisValueList = new ArrayList<>();
        List<SubcolumnValue> values = new ArrayList<>();

        for (int i = 0; i < Chartdatas.size(); ++i) {
            if(mDateType==0) {
                axisValueList.add(new AxisValue(i, ColorUtils.stringToData(Chartdatas.get(i).getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "HH:00").toCharArray()));
            }else{
                axisValueList.add(new AxisValue(i,ColorUtils.stringToData(Chartdatas.get(i).getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "MM.dd").toCharArray()));
            }
            values = new ArrayList<SubcolumnValue>();
            if (Chartdatas.get(i) == null || Chartdatas.get(i).equals("—") || !ToolUtils.isNum(Chartdatas.get(i).getYValue())
                    || Float.valueOf(Chartdatas.get(i).getYValue()) <= 0) {
                values.add(new SubcolumnValue(0, Color.GRAY));
            } else {
                values.add(new SubcolumnValue(Float.valueOf(Chartdatas.get(i).getYValue()), ColorUtils.getColorWithLevel(Chartdatas.get(i).getLevel())));
            }

            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);
        }


        barData = new ColumnChartData(columns);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(false);
            if (hasAxesNames) {
                axisX.setName("");
                axisX.setValues(axisValueList);
                axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
                axisX.setLineColor(Color.WHITE);
                axisY.setName("");
                axisY.setLineColor(Color.WHITE);
            }
            barData.setAxisXBottom(axisX);
            barData.setAxisYLeft(axisY);
        } else {
            barData.setAxisXBottom(null);
            barData.setAxisYLeft(null);
        }

        trend_chart.setColumnChartData(barData);

//        trend_chart.setZoomLevelWithAnimation(24,0,1f);

        Viewport viewportMax = new Viewport(-0.7f, trend_chart.getMaximumViewport().height() * 1.25f, 12, 0);
//        trend_chart.setMaximumViewport(viewportMax);
        trend_chart.setCurrentViewport(viewportMax);
        trend_chart.setZoomEnabled(false);
        trend_chart.moveTo(24, 0);
//        trend_chart.moveTo(24,0);

    }


    @Override
    public void getDataFail(String msg) {
        refresh_layout.setRefreshing(false);
    }

    @Override
    public void showRefresh() {
        refresh_layout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        refresh_layout.setRefreshing(false);
    }
}
