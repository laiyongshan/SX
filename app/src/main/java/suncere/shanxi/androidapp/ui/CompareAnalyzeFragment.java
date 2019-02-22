package suncere.shanxi.androidapp.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.adapter.StationCompareAdapter;
import suncere.shanxi.androidapp.adapter.TagAdapter;
import suncere.shanxi.androidapp.customview.kjchart.Plot;
import suncere.shanxi.androidapp.model.entity.CityCharBean;
import suncere.shanxi.androidapp.model.entity.StationChartBean;
import suncere.shanxi.androidapp.presenter.CityComparePresenter;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.ColorUtils;
import suncere.shanxi.androidapp.utils.ToolUtils;
import suncere.shanxi.androidapp.utils.Tools;

/**
 * @author lys
 * @time 2019/2/11 17:38
 * @desc:
 */
public class CompareAnalyzeFragment extends MvpFragment<CityComparePresenter> implements IView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refresh_layout;


    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.timeType_stl)
    SegmentTabLayout timeType_stl;

    @BindView(R.id.flow_layout)
    FlowTagLayout mFlowLayout;

    @BindView(R.id.cityName1_sp)
    Spinner cityName1_sp;
    @BindView(R.id.cityName2_sp)
    Spinner cityName2_sp;
    @BindView(R.id.cityName3_sp)
    Spinner cityName3_sp;

    @BindView(R.id.city_compare_chart)
    LineChartView city_compare_chart;

    @BindView(R.id.city1_tv)
    TextView city1_tv;
    @BindView(R.id.city2_tv)
    TextView city2_tv;
    @BindView(R.id.city3_tv)
    TextView city3_tv;

    @BindView(R.id.danwei_tv)
    TextView danwei_tv;

    @BindView(R.id.city_compare_rv)
    RecyclerView city_compare_rv;
    StationCompareAdapter stationCompareAdapter;
    List<StationChartBean> cityCharBean2List = new ArrayList<>();

    List<Plot> plots = new ArrayList<>();
    List<String> city1_mLines, city2_mLines, city3_mLines;
    List<String> city1_mLeves, city2_mLeves, city3_mLeves;
    List<String> xAxisValues = new ArrayList<>();
    List<Integer> mBarColors = new ArrayList<>();

    private TagAdapter<String> mTagAdapter;
    private String[] mTitles = {"24小时", "48小时", "30日"};

    String citysArr[];
    String cityName1 = "";
    String cityName2 = "";
    String cityName3 = "";
    String TimeType = "0";
    String countIndex = "24";
    String PollutantCode = "99";

    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 3;

    List<Line> lines = new ArrayList<Line>();

    private boolean hasAxes = true;
    private boolean hasAxesNames = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = true;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = true;
    private boolean pointsHaveDifferentColor;
    private boolean hasGradientToTransparent = true;

    Tools mTools;

    View view;

    CityComparePresenter mComparePresenter;

    @Override
    protected CityComparePresenter createPresenter() {
        mComparePresenter = new CityComparePresenter(this);
        return mComparePresenter;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_analysis, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {

        mTools = new Tools(getActivity());

        city_compare_chart.setOnValueTouchListener(new ValueTouchListener());
        city_compare_chart.setZoomType(ZoomType.HORIZONTAL);


        citysArr = mTools.getAllCities().split(",");

        city1_mLines = new ArrayList<>();
        city2_mLines = new ArrayList<>();
        city3_mLines = new ArrayList<>();

        city1_mLeves = new ArrayList<>();
        city2_mLeves = new ArrayList<>();
        city3_mLeves = new ArrayList<>();

        refresh_layout.setColorSchemeColors(ColorUtils.Colors);
        refresh_layout.setOnRefreshListener(this);

        scrollView.setEnabled(false);

        //污染物
        mTagAdapter = new TagAdapter<>(getContext());
        mFlowLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        mFlowLayout.setAdapter(mTagAdapter);
        mFlowLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {

                if (selectedList != null && selectedList.size() > 0) {
                    for (int i : selectedList) {
//                        PollutantCode = (int) parent.getAdapter().getItemId(i);
                        Log.i("lys", "parent.getAdapter().getItemId(i)" + parent.getAdapter().getItemId(i));
                        switch ((int) parent.getAdapter().getItemId(i)) {
                            case 0://AQI
                                if (TimeType.equals("2")) {
                                    PollutantCode = "98";//综合指数
                                } else {
                                    PollutantCode = "99";//AQI
                                }
                                break;
                            case 1://PM2.5
                                PollutantCode = "105";
                                break;

                            case 2://pm10
                                PollutantCode = "104";
                                break;

                            case 3://so2
                                PollutantCode = "100";
                                break;

                            case 4://o3
                                PollutantCode = "102";
                                break;

                            case 5://co
                                PollutantCode = "103";
                                break;

                            case 6://NO2
                                PollutantCode = "101";
                                break;
                        }

                        getCityCompareDatas();
                    }
                }
            }
        });

        initFlowData();

        timeType_stl.setTabData(mTitles);
        timeType_stl.setCurrentTab(0);
        timeType_stl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switch (position) {
                    case 0://24小时
                        TimeType = "0";
                        countIndex = "24";
                        break;
                    case 1://48小时
                        TimeType = "0";
                        countIndex = "48";
                        break;
                    case 2://30日
                        TimeType = "1";
                        countIndex = "30";
                        break;
                }

                getCityCompareDatas();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        ChangeSpinner();

        city_compare_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        city_compare_rv.setHasFixedSize(true);
        city_compare_rv.setNestedScrollingEnabled(false);
        stationCompareAdapter = new StationCompareAdapter(cityCharBean2List);
        city_compare_rv.setAdapter(stationCompareAdapter);

    }


    /**
     * 初始化数据
     */
    private void initFlowData() {
        List<String> dataSource = new ArrayList<>();
        dataSource.add("AQI");
        dataSource.add("PM2.5");
        dataSource.add("PM10");
        dataSource.add("SO2");
        dataSource.add("O3");
        dataSource.add("CO");
        dataSource.add("NO2");
        mTagAdapter.clearAndAddAll(dataSource);
        mTagAdapter.notifyDataSetChanged();
    }

//    /**
//     * 初始化数据
//     */
//    private void initFlowData2() {
//        List<String> dataSource = new ArrayList<>();
//        dataSource.add("综指");
//        dataSource.add("PM2.5");
//        dataSource.add("PM10");
//        dataSource.add("SO2");
//        dataSource.add("O3");
//        dataSource.add("CO");
//        dataSource.add("NO2");
//        mTagAdapter.clearAndAddAll(dataSource);
//        mTagAdapter.notifyDataSetChanged();
//    }


    /**
     * Spinner自定义样式
     * 1、Spinner内的TextView样式：item_select
     * 2、Spinner下拉中每个item的TextView样式：item_drop
     * 3、Spinner下拉框样式，属性设置
     */
    public void ChangeSpinner() {
        //mSpinnerSimple.setBackgroundColor(AppUtil.getColor(instance,R.color.wx_bg_gray)); //下拉的背景色
        //spinner mode ： dropdown or dialog , just edit in layout xml
        //mSpinnerSimple.setPrompt("Spinner Title"); //弹出框标题，在dialog下有效

        //自定义选择填充后的字体样式
        //只能是textview样式，否则报错：ArrayAdapter requires the resource ID to be a TextView
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_spinner_selected, citysArr);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cityName1_sp.setAdapter(spinnerAdapter);
        cityName1_sp.setSelection(0);
        cityName1_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityName1 = "" + citysArr[position];
                city1_tv.setText(cityName1);
                getCityCompareDatas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cityName2_sp.setAdapter(spinnerAdapter);
        cityName2_sp.setSelection(1);
        cityName2_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityName2 = "" + citysArr[position];
                city2_tv.setText(cityName2);
                getCityCompareDatas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cityName3_sp.setAdapter(spinnerAdapter);
        cityName3_sp.setSelection(2);
        cityName3_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityName3 = "" + citysArr[position];
                city3_tv.setText(cityName3);
                getCityCompareDatas();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void getCityCompareDatas() {
//        if(TimeType.equals("2")&&PollutantCode.equals("99")){
//            PollutantCode = "98";//综合指数
//        }else if(!TimeType.equals("2")&&PollutantCode.equals("98")){
//            PollutantCode = "99";//AQI
//        }
        mComparePresenter.getCityCompareData(cityName1 + "," + cityName2 + "," + cityName3, TimeType, countIndex, PollutantCode);
    }

    @Override
    public void onRefresh() {

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
    public void getDataSuccess(Object response) {
        if (response != null) {
            List<CityCharBean> cityCharList = (List<CityCharBean>) response;
            bindChartData(cityCharList);
        }
    }

    private void bindChartData(List<CityCharBean> cityCharList) {

        lines.clear();

        city1_mLines.clear();
        city2_mLines.clear();
        city3_mLines.clear();
        xAxisValues.clear();
        plots.clear();
        cityCharBean2List.clear();

//        city_compare_chart.setOnValueTouchListener(new ValueTouchListener());


        for (CityCharBean cityCharBean : cityCharList) {
            if (cityCharBean.getCityName().equals(cityName1)) {
                if (TimeType.equals("0")) {
                    xAxisValues.add(ToolUtils.stringToData(cityCharBean.getTimePoint(), "yyyy-MM-dd HH:mm:ss", "dd日HH时") + "");
                } else if (TimeType.equals("1")) {
                    xAxisValues.add(ToolUtils.stringToData(cityCharBean.getTimePoint(), "yyyy-MM-dd HH:mm:ss", "MM-dd") + "");
                } else if (TimeType.equals("2")) {
                    xAxisValues.add(ToolUtils.stringToData(cityCharBean.getTimePoint(), "yyyy-MM-dd HH:mm:ss", "yyyy-MM") + "");
                }
                city1_mLines.add(cityCharBean.getVal() + "");
                city1_mLeves.add(cityCharBean.getLevel() + "");
            } else if (cityCharBean.getCityName().equals(cityName2)) {
                city2_mLines.add(cityCharBean.getVal() + "");
                city2_mLeves.add(cityCharBean.getLevel() + "");
            } else if (cityCharBean.getCityName().equals(cityName3)) {
                city3_mLines.add(cityCharBean.getVal() + "");
                city3_mLeves.add(cityCharBean.getLevel() + "");
            }
        }

        generateData(xAxisValues, city1_mLines, Color.parseColor("#F1C55F"));
        generateData(xAxisValues, city2_mLines, Color.parseColor("#47F646"));
        generateData(xAxisValues, city3_mLines, Color.parseColor("#42DAFC"));

//        city_compare_chart.setZoomLevelWithAnimation(28,2,4f);


        for (int i = 0; i < xAxisValues.size(); i++) {
            cityCharBean2List.add(new StationChartBean(xAxisValues.get(i), city1_mLines.get(i), city1_mLeves.get(i), city2_mLines.get(i), city2_mLeves.get(i), city3_mLines.get(i), city3_mLeves.get(i)));
        }
        stationCompareAdapter.notifyDataSetChanged();

//
//        plots.add(new Plot(city1_mLines, Color.parseColor("#F0E98C"), Color.parseColor("#F0E98C")));
//        plots.add(new Plot(city2_mLines, Color.parseColor("#F6A15D"), Color.parseColor("#F6A15D")));
//        plots.add(new Plot(city3_mLines, Color.parseColor("#8ED15F"), Color.parseColor("#8ED15F")));
//
//        city_compare_chart.bindManyLineChart(plots, xAxisValues);
//
//        city_compare_chart.mIsShowPointColor = true;
//        city_compare_chart.mLineColor = Color.WHITE;
////        city_compare_chart.mIsShowPiontToBottomBg = true;
////        city_compare_chart.mIsShowPointText=true;
//        city_compare_chart.mYLineColor = Color.parseColor("#ffffff");
//
//        city_compare_chart.mXLineColor = Color.WHITE;
//        city_compare_chart.mIsShowPointColor = true;
//        city_compare_chart.mdefaulYValueTextColor = Color.WHITE;
//        city_compare_chart.mXAxisEveryFewParagraphs = 2;
//        city_compare_chart.mXScaleWidth=200;
//        city_compare_chart.mIsShowYAxis = true;
//        city_compare_chart.mXAxisTextColor = Color.WHITE;
//        city_compare_chart.mYAxisTextColor = Color.WHITE;
//        city_compare_chart.mIs_AccordingTo_PointLabelValue_JudgmentColor = false;
//        city_compare_chart.refreshChartView();
    }


    private void generateData(List<String> xAxisValues, List<String> mLines, int LineColor) {

        List<AxisValue> axisValues = new ArrayList<>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int j = 0; j < xAxisValues.size(); ++j) {
            axisValues.add(new AxisValue(j + 1, xAxisValues.get(j).toCharArray()));
            values.add(new PointValue(j + 1, Float.valueOf(mLines.get(j)), LineColor));
        }

        Line line = new Line(values);
        line.setColor(LineColor);
        line.setShape(shape);
        line.setStrokeWidth(3);
        line.setPointRadius(3);
        line.setCubic(isCubic);
        line.setFilled(isFilled);
        line.setHasLabels(hasLabels);
        line.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line.setHasLines(hasLines);
        line.setHasPoints(hasPoints);
//        line.setHasGradientToTransparent(hasGradientToTransparent);
        line.setPointColor(LineColor);

        lines.add(line);

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis().setHasLines(false);
            Axis axisY = new Axis().setHasLines(false);

            if (hasAxesNames) {
                axisX.setName("");
                axisY.setName("");
            }
            axisX.setValues(axisValues);
            axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        city_compare_chart.setLineChartData(data);

//        city_compare_chart.setZoomLevelWithAnimation(24, 2, 22f);
//        Viewport tempViewport = new Viewport(0, city_compare_chart.getMaximumViewport().height(), 9, 0);
//        city_compare_chart.setCurrentViewport(tempViewport);//setCurrentViewport(tempViewport1, false);

//用来控制柱形图视图窗口的缩放。
        Viewport viewport = new Viewport(0, city_compare_chart.getMaximumViewport().height() * 1.6f, xAxisValues.size() > 6 ? 6 : xAxisValues.size(), 0);

        city_compare_chart.setCurrentViewport(viewport);
        city_compare_chart.moveTo(48, 20);
    }


    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
//            Toast.makeText(getActivity(), "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

    }

}
