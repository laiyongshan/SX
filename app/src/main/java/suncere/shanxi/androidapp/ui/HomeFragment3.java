package suncere.shanxi.androidapp.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.roundview.RoundTextView;

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
import suncere.shanxi.androidapp.adapter.HomeStationListAdapter;
import suncere.shanxi.androidapp.adapter.HomeViewPageAdapter;
import suncere.shanxi.androidapp.customview.FullyLinearLayoutManager;
import suncere.shanxi.androidapp.customview.MyUIPagerControlView;
import suncere.shanxi.androidapp.model.CityStationModel;
import suncere.shanxi.androidapp.model.HomeDataChart24Model;
import suncere.shanxi.androidapp.model.entity.HomeAllCitiesBean;
import suncere.shanxi.androidapp.model.entity.HomeStationChartBean;
import suncere.shanxi.androidapp.presenter.HomePresenter;
import suncere.shanxi.androidapp.ui.iview.IHomeView;
import suncere.shanxi.androidapp.utils.ColorUtils;
import suncere.shanxi.androidapp.utils.ToolUtils;
import suncere.shanxi.androidapp.utils.Tools;

/**
 * @author lys
 * @time 2019/2/19 14:03
 * @desc:
 */
public class HomeFragment3 extends MvpFragment<HomePresenter> implements IHomeView, SwipeRefreshLayout.OnRefreshListener, ViewPager.OnPageChangeListener, HomeViewPageAdapter.ViewpagerOnBindView {

    private View view;

    @BindView(R.id.home_viewPager)
    ViewPager home_viewPager;
    HomeViewPageAdapter<HomeAllCitiesBean> homeViewPageAdapter;

    @BindView(R.id.home_UIpager)
    MyUIPagerControlView home_UIpager;

    @BindView(R.id.check_detail_rtv)
    RoundTextView check_detail_rtv;

    @BindView(R.id.selected_follow_rl)
    RelativeLayout selected_follow_rl;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;

    @BindView(R.id.station_rv)
    RecyclerView station_rv;

    @BindView(R.id.home_AQI_chart)
    ColumnChartView home_AQI_chart;
    private ColumnChartData barData;
    private static final int DEFAULT_DATA = 0;
    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLabels = false;
    private boolean hasLabelForSelected = true;
    private int dataType = DEFAULT_DATA;


    HomeStationListAdapter mHomeStationListAdapter;
    List<HomeStationChartBean> Chartdatas = new ArrayList<>();

    private List<HomeAllCitiesBean> mHomeAllCities = new ArrayList<>();
    private List<HomeAllCitiesBean> mShowCities = new ArrayList<>();
    private List<CityStationModel.StationListBean> stationList = new ArrayList<>();


    String mCityCode = "140100";//当前城市代码

    HomePresenter mHomePresenter;

    Tools mTool;


    @Override
    protected HomePresenter createPresenter() {
        mHomePresenter = new HomePresenter(this);
        return mHomePresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home3, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCityDatas();//获取城市数据
    }

    private void initView() {

        mTool = new Tools(getActivity());

        refresh_layout.setColorSchemeColors(ColorUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        station_rv.setNestedScrollingEnabled(false);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(getActivity());
        station_rv.setItemAnimator(new DefaultItemAnimator());
        if (null == mHomeStationListAdapter) {
            //设置布局样式
            station_rv.setLayoutManager(mLayoutManager);
            //设置适配器
            mHomeStationListAdapter = new HomeStationListAdapter(stationList);

            station_rv.setAdapter(mHomeStationListAdapter);

        }


        homeViewPageAdapter = new HomeViewPageAdapter<>(getActivity(), mHomeAllCities, R.layout.home_viewpage_item);
        homeViewPageAdapter.setViewpagerOnBindView(this);
        home_viewPager.setAdapter(homeViewPageAdapter);
        home_viewPager.addOnPageChangeListener(this);


    }

    @Override
    public void onBindingView(View view, Object object) {

    }

    Intent intent = null;

    @OnClick({R.id.check_detail_rtv, R.id.selected_follow_rl})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.check_detail_rtv://变化趋势详情
                intent = new Intent(getActivity(), TrendActivity.class);
                intent.putExtra("CityCode", mCityCode);
                startActivity(intent);
                break;

            case R.id.selected_follow_rl:
                intent = new Intent(getActivity(), HomeSelectFollowCityActivity.class);
                startActivity(intent);
                break;
        }

    }

    //获取城市数据
    private void getCityDatas() {
        mHomePresenter.getHomeAllCitiesData();
    }

    //获取AQI过去24小时变化趋势
    private void getAQIChartDatas() {
        mHomePresenter.getChartData(mCityCode, "99", 24, 0);
    }

    //获取城市站点数据
    private void getCityStationDatas() {
        mHomePresenter.getCityStationDatas(mCityCode);
    }

    @Override
    public void onRefresh() {
        getCityDatas();
    }


    @Override
    public void getDataSuccess(Object response) {
        mHomeAllCities.clear();
        mShowCities.clear();
        if (response != null)
            mHomeAllCities.addAll((List<HomeAllCitiesBean>) response);

        StringBuilder allcities = new StringBuilder();
        StringBuilder allcitiesCode = new StringBuilder();
        String[] followcities = mTool.getFollowCities().split(",");
        for (int i = 0; i < mHomeAllCities.size(); i++) {
            HomeAllCitiesBean bean = mHomeAllCities.get(i);
            for (int k = 0; k < followcities.length; k++) {  // 查找已关注的城市列表
                if (bean.getCityName().equals(followcities[k])) {
                    mShowCities.add(bean);
                    break;
                }
            }

            if (i == mHomeAllCities.size() - 1) { // 存储所有的城市列表
                allcities.append(bean.getCityName());
                allcitiesCode.append(bean.getCityCode());
            } else {
                allcities.append(bean.getCityName() + ",");
                allcitiesCode.append(bean.getCityCode() + ",");
            }
        }
        mTool.setCities(allcities.toString());
        mTool.setCitiesCode(allcitiesCode.toString());

        homeViewPageAdapter.setData(mShowCities);

        // 保持在特定的位置不变
        home_UIpager.setCount(home_viewPager.getAdapter().getCount());
        if (mShowCities != null && mShowCities.size() > 0) {
            if (mTool.getPageIndex() > mShowCities.size() - 1) {
                home_viewPager.setCurrentItem(0);
                home_UIpager.setSelectedIndex(0);
                mCityCode = mShowCities.get(0).getCityCode();
            } else {
                home_viewPager.setCurrentItem(mTool.getPageIndex());
                home_UIpager.setSelectedIndex(mTool.getPageIndex());
                mCityCode = mShowCities.get(mTool.getPageIndex()).getCityCode();
            }
        }

        getCityStationDatas();


        getAQIChartDatas();

    }

    @Override
    public void getChartSuccess(Object response) {
        if (response != null) {
            Chartdatas.clear();
            Chartdatas = ((HomeDataChart24Model) response).getChartDatas();
            generateBarData(Chartdatas);
        }
    }

    @Override
    public void getCityStationSuccess(Object response) {
        if (response != null) {
            stationList.clear();
            stationList = ((CityStationModel) response).getStationList();
            mHomeStationListAdapter=new HomeStationListAdapter(stationList);
            //设置item点击事件
            mHomeStationListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent=new Intent(getActivity(),StationDetailActivity.class);
                    intent.putExtra("StationBean",stationList.get(position));
                    startActivity(intent);
                }
            });
            station_rv.setAdapter(mHomeStationListAdapter);
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void generateBarData(List<HomeStationChartBean> Chartdatas) {
        List<Column> columns = new ArrayList<Column>();
        List<AxisValue> axisValueList = new ArrayList<>();
        List<SubcolumnValue> values = new ArrayList<>();

        for (int i = 0; i < Chartdatas.size(); ++i) {
            axisValueList.add(new AxisValue(i, ColorUtils.stringToData(Chartdatas.get(i).getLabelXValue(), "yyyy-MM-dd HH:mm:ss", "HH:00").toCharArray()));
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

        home_AQI_chart.setColumnChartData(barData);

//        home_AQI_chart.setZoomLevelWithAnimation(24,0,1f);

        Viewport viewportMax = new Viewport(-0.7f, home_AQI_chart.getMaximumViewport().height() * 1.25f, 12, 0);
//        home_AQI_chart.setMaximumViewport(viewportMax);
        home_AQI_chart.setCurrentViewport(viewportMax);
        home_AQI_chart.setZoomEnabled(false);
        home_AQI_chart.moveTo(24, 0);
//        home_AQI_chart.moveTo(24,0);


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


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mCityCode = mShowCities.get(position).getCityCode();
        home_UIpager.setSelectedIndex(position);
        mTool.setPageIndex(position);
        refresh_layout.setEnabled(true);

        getAQIChartDatas();
        getCityStationDatas();
    }

    @Override
    public void onPageSelected(int position) {
        refresh_layout.setEnabled(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
