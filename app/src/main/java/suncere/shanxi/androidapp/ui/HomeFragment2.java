/*
package suncere.shanxi.androidapp.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okhttputils.callback.StringCallback;
import com.shinelw.library.ColorArcProgressBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.customview.AlwaysMarqueeTextView;
import suncere.shanxi.androidapp.customview.PollutantsView;
import suncere.shanxi.androidapp.customview.kjchart.ChartView2;
import suncere.shanxi.androidapp.model.HomeDataChart24Model;
import suncere.shanxi.androidapp.model.entity.HomeAllCitiesBean;
import suncere.shanxi.androidapp.model.entity.HomeStationChartBean;
import suncere.shanxi.androidapp.model.entity.WeatherBean;
import suncere.shanxi.androidapp.presenter.HomePresenter;
import suncere.shanxi.androidapp.ui.iview.IHomeView;
import suncere.shanxi.androidapp.utils.ColorUtils;
import suncere.shanxi.androidapp.utils.ToolUtils;

*/
/**
 * @author lys
 * @time 2019/1/30 17:47
 * @desc:
 *//*


public class HomeFragment2 extends MvpFragment<HomePresenter> implements IHomeView, SwipeRefreshLayout.OnRefreshListener {

    HomePresenter mHomePresenter;
    String mCityCode;

    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;

    @BindView(R.id.selected_city_iv)
    ImageView selected_city_iv;

    @BindView(R.id.home_refresh_SwipeRefreshLayout)
    SwipeRefreshLayout refresh_SwipeRefreshLayout;


    @BindView(R.id.homedata_updata_time_tv)
    TextView homedata_updata_time_tv;

    @BindView(R.id.aqi_capbar)
    ColorArcProgressBar aqi_capbar;

    @BindView(R.id.Pollution_levels_tv)
    TextView Pollution_levels_tv;

    @BindView(R.id.temperature_tv)
    TextView temperature_tv;

    @BindView(R.id.analy_lbHealth_amtv)
    AlwaysMarqueeTextView analy_lbHealth_amtv;

    @BindViews({R.id.pm10_value_tv, R.id.pm25_value_tv, R.id.co2_value_tv, R.id.so2_value_tv, R.id.o3_value_tv, R.id.co_value_tv})
    TextView[] pollutantTvs;


    @BindView(R.id.home_chart24)
    ChartView2 chart24;

    @BindView(R.id.home_TimeRange_CP)
    SegmentTabLayout home_TimeRange_CP;

    @BindView(R.id.home_PollutantsView)
    PollutantsView home_PollutantsView;

    @BindView(R.id.linechart_ibtn)
    ImageButton linechart_ibtn;

    @BindView(R.id.barchart_ibtn)
    ImageButton barchart_ibtn;

    private List<HomeAllCitiesBean> mHomeAllCities = new ArrayList<>();
    private CharSequence[] mTitleCityNames;

    String mpollutantCode = "99";
    int ChartType = 0;//1  折线图   0 柱状图

    List<HomeStationChartBean> Chartdatas = new ArrayList<>();
    List<String> mYvalue=new ArrayList<>();
    List<String> mXvalue=new ArrayList<>();
    List<Integer> mColors=new ArrayList<>();
    int mindex = 0;
    List<TextView> mpollutantViews=new ArrayList<>();

    private String[] mTitles = {"过去24小时", "过去30天", "过去12个月"};

    @Override
    protected HomePresenter createPresenter() {
        mHomePresenter = new HomePresenter(this);
        return mHomePresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getCityDatas();
    }

    private void initView() {

        refresh_SwipeRefreshLayout.setColorSchemeColors(ColorUtils.Colors);
        refresh_SwipeRefreshLayout.setOnRefreshListener(this);

        xTablayout.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                setAQIData(mHomeAllCities.get(tab.getPosition()));
                getWeatherData(mHomeAllCities.get(tab.getPosition()).getCityName());
                mCityCode = mHomeAllCities.get(tab.getPosition()).getCityCode() + "";

                getChartDatas();
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {

            }
        });

        home_TimeRange_CP.setTabData(mTitles);
        home_TimeRange_CP.setCurrentTab(0);
        home_TimeRange_CP.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mindex = position;
                switch (position) {
                    case 0://过去24小时
                        changPollutantViewText("AQI", "O3");
                        break;

                    case 1://过去30天
                        changPollutantViewText("AQI", "O3_8h");
                        break;

                    case 2://过去12个月
                        changPollutantViewText("综合指数", "O3_8h");
                        break;
                }
                getChartDatas();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        home_PollutantsView.setmSelceTextListener(new PollutantsView.SelceTextListener() {
            @Override
            public void onSelect(String pollutantName, String pollutantCode) {
                mpollutantCode = pollutantCode;
                if (mindex == 1 || mindex == 2) {
                    if (mpollutantCode.equals("102"))
                        mpollutantCode = "1028";
                }
                getChartDatas();
            }
        });
    }


    private void changPollutantViewText(String AQIText, String O3Text) {
        if (mpollutantViews != null && mpollutantViews.size() > 4) {
            if (mindex == 2) {
                mpollutantViews.get(0).setTextSize(10f);
            } else {
                mpollutantViews.get(0).setTextSize(14f);
            }
            mpollutantViews.get(0).setText(AQIText);
            mpollutantViews.get(3).setText(O3Text);
        }
    }


    @OnClick({R.id.selected_city_iv, R.id.station_detail_rtv, R.id.barchart_ibtn,R.id.linechart_ibtn})
    public void on_Click(View view) {
        switch (view.getId()) {
            case R.id.selected_city_iv:
                selected_city_iv.setImageResource(R.mipmap.icon_shangla);
                showCitySelectAlertDialog();
                break;

            case R.id.station_detail_rtv:
                Intent intent = new Intent(getActivity(), HomeStationActivity.class);
                intent.putExtra("CityCode", mCityCode);
                startActivity(intent);
                break;

            case R.id.linechart_ibtn:
                linechart_ibtn.setBackgroundResource(R.drawable.bg_imagebubtton);
                barchart_ibtn.setBackgroundResource(R.color.luncy);
                ChartType = 1;
                bindChartDatas(ChartType, Chartdatas);
                break;


            case R.id.barchart_ibtn:
                barchart_ibtn.setBackgroundResource(R.drawable.bg_imagebubtton);
                linechart_ibtn.setBackgroundResource(R.color.luncy);
                ChartType = 0;
                bindChartDatas(ChartType, Chartdatas);
                break;

        }
    }


    //获取城市数据
    private void getCityDatas() {
        mHomePresenter.getHomeAllCitiesData();
    }

    //获取天气数据
    private void getWeatherData(String City) {
        mHomePresenter.getWeatherData(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                if (s != null) {
                    WeatherBean.ResultsBean.DailyBean dailyBean = WeatherBean.objectFromData(s).getResults().get(0).getDaily().get(0);
                    if (dailyBean != null) {
                        temperature_tv.setText(dailyBean.getLow() + "～" + dailyBean.getHigh() + "℃ " + dailyBean.getText_day());
                    }
                }
            }
        }, City);
    }

    //获取城市图表数据         //mHomePresenter.getChartData(mCityCode, mpollutantCode, mindex);
    private void getChartDatas() {
        if (mindex == 2 && mpollutantCode.equals("99")) {
            mpollutantCode = "98";
        } else if (mindex != 2 && mpollutantCode.equals("98")) {
            mpollutantCode = "99";
        }

    }


    @Override
    public void onRefresh() {
        getCityDatas();
        getChartDatas();
    }

    @Override
    public void getDataFail(String msg) {
        refresh_SwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showRefresh() {
        refresh_SwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void finishRefresh() {
        refresh_SwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getDataSuccess(Object response) {
        mHomeAllCities.clear();
        xTablayout.removeAllTabs();
        if (response != null)
            mHomeAllCities.addAll((List<HomeAllCitiesBean>) response);

        for (int i = 0; i < mHomeAllCities.size(); i++) {
            HomeAllCitiesBean bean = mHomeAllCities.get(i);
            xTablayout.addTab(xTablayout.newTab().setText(bean.getCityName()));
        }

        if (!mHomeAllCities.isEmpty()) {
            setAQIData(mHomeAllCities.get(0));
            getWeatherData(mHomeAllCities.get(0).getCityName() + "");
            mCityCode = mHomeAllCities.get(0).getCityCode() + "";
        }

        mTitleCityNames = new CharSequence[mHomeAllCities.size()];
        for (int i = 0; i < mHomeAllCities.size(); i++) {
            mTitleCityNames[i] = mHomeAllCities.get(i).getCityName();
        }
    }

    private void setAQIData(HomeAllCitiesBean homeData) {
        homedata_updata_time_tv.setText(ToolUtils.stringToData(homeData.getTimePoint(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm") + " 更新");
        if (ToolUtils.isNum(homeData.getAQI() + "")) {
            aqi_capbar.setCurrentValues(Integer.valueOf(homeData.getAQI()));
        } else {
            aqi_capbar.setCurrentValues(0);
        }
        analy_lbHealth_amtv.setText(homeData.getUnheathful() + "");
        pollutantTvs[0].setText(homeData.getPM10() + getString(R.string.ug));
        pollutantTvs[1].setText(homeData.getPM2_5() + getString(R.string.ug));
        pollutantTvs[2].setText(homeData.getNO2() + getString(R.string.ug));
        pollutantTvs[3].setText(homeData.getSO2() + getString(R.string.ug));
        pollutantTvs[4].setText(homeData.getO3() + getString(R.string.ug));
        pollutantTvs[5].setText(homeData.getCO() + getString(R.string.mg));

        Pollution_levels_tv.setText(homeData.getQuality() + "");
    }

    private void showCitySelectAlertDialog() {
        new AlertDialog.Builder(getActivity())
                .setTitle("请选择城市")
                .setItems(mTitleCityNames,
                        new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface sender, int index) {
                                mCityCode = mHomeAllCities.get(index).getCityCode();
                                xTablayout.getTabAt(index).select();
                            }
                        })
                .setCancelable(true)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected_city_iv.setImageResource(R.mipmap.icon_xiala);
                        dialog.dismiss();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        selected_city_iv.setImageResource(R.mipmap.icon_xiala);
                    }
                }).show();

    }


    @Override
    public void getChartSuccess(Object response) {
        if(response!=null) {
            Chartdatas.clear();
            Chartdatas=((HomeDataChart24Model) response).getChartDatas();
            bindChartDatas(ChartType, Chartdatas);
        }
    }


    private void bindChartDatas(int ChartType, List<HomeStationChartBean> Chartdatas) {
        mYvalue.clear();
        mXvalue.clear();
        mColors.clear();
        if (Chartdatas != null && Chartdatas.size() > 0) {
            for (HomeStationChartBean bean : Chartdatas) {

                if (bean.getYValue() == null) {
                    mYvalue.add("—");
                } else if (ToolUtils.isNum(bean.getYValue())
                        && Float.valueOf(bean.getYValue()) <= 0) {
                    mYvalue.add("0");
                } else {
                    mYvalue.add(bean.getYValue());
                }
//                mYvalue.add(bean.getYValue());
                if (mindex == 0) {
                    mXvalue.add(ColorUtils.stringToData(bean.getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "dd日HH时"));
                } else if (mindex == 1) {
                    mXvalue.add(ColorUtils.stringToData(bean.getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "MM-dd"));
                } else {
                    mXvalue.add(ColorUtils.stringToData(bean.getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM"));
                }
                if (mindex == 2 && mpollutantCode.equals("98")) {//综合指数
                    mColors.add(Color.parseColor("#16BC3E"));
                } else {
                    mColors.add(ColorUtils.getColorWithLevel(bean.getLevel()));
                }
            }
        }

        if (mYvalue != null && !mYvalue.isEmpty()) {
            if (ChartType == 1) {
                chart24.bindSingleLineChart(mYvalue, mXvalue);
                chart24.mIsShowPointText = true;
            } else {
                chart24.bindBarChart_v2(mYvalue, mXvalue, "");
                if (mindex == 0 || mindex == 1) {
                    chart24.mBarChartSize = chart24.dp2px(13);
                    chart24.mXScaleWidth = chart24.dp2px(17.6f);
                } else if (mindex == 2) {
                    chart24.mBarChartSize = chart24.dp2px(16);
                    chart24.mXScaleWidth = chart24.dp2px(22);
                }
                chart24.mIsShowPointText = false;
            }

            chart24.mPointColors = mColors;

            chart24.mYLineColor = Color.WHITE;
            chart24.mXLineColor = Color.WHITE;
            chart24.mLineColor = Color.WHITE;
            chart24.mdefaulYValueTextColor = Color.WHITE;
            chart24.mIsShowPointColor = true;
            chart24.mIsShowPiontToBottomBg = true;

            chart24.mIs_AccordingTo_List_SetMin = true;
            chart24.isNeedMinValueMoreSmall = true;

            chart24.mXAxisTextColor = Color.WHITE;
            chart24.mYAxisTextColor = Color.WHITE;

            chart24.mIs_AccordingTo_PointLabelValue_JudgmentColor = false;

            chart24.mXAxisEveryFewParagraphs = 3;

            chart24.refreshChartView();
        }
    }


}
*/
