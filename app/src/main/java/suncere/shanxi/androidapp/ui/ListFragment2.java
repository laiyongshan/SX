package suncere.shanxi.androidapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.flyco.roundview.RoundTextView;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.customview.PollutantNameTextView;
import suncere.shanxi.androidapp.customview.PollutantsView;
import suncere.shanxi.androidapp.presenter.ListPresenter;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.IndicatorLineUtil;
import suncere.shanxi.androidapp.utils.TimerPikerTools;

/**
 * @author lys
 * @time 2019/2/19 09:30
 * @desc:
 */
public class ListFragment2 extends MvpFragment<ListPresenter> implements IView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.time_tabLayout)
    TabLayout time_tabLayout;

    @BindView(R.id.national_province_stl)
    SegmentTabLayout national_province_stl;

    @BindView(R.id.switch_rtv)
    RoundTextView switch_rtv;

    @BindView(R.id.sort_PollutantsView)
    PollutantsView sort_PollutantsView;

    @BindView(R.id.list_type_stl)
    SegmentTabLayout list_type_stl;

    @BindView(R.id.list_sore_way_ll)
    LinearLayout list_sore_way_ll;

    @BindView(R.id.list_sore_img)
    ImageView list_sore_img;

    @BindView(R.id.list_title_tab_city_tv)
    TextView list_title_tab_city_tv;

    @BindView(R.id.tab_pollutant_tv)
    PollutantNameTextView tab_pollutant_tv;

    @BindView(R.id.choose_date_rtv)
    RoundTextView choose_date_rtv;

    private View view;

    private String[] mTitles = {"省内", "全国"};
    private String[] time_titles = {"实时", "日", "月", "年"};

    private String[] province_titles = {"11", "122", "通道城市", "汾渭平原"};
    private String[] national_titles = {"169", "338", "74", "2+26城市", "京津冀"};


    boolean isUp;//是否升序 false：升序  true:降序

    TimePickerView mTimePikerView;//时间选择器
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//日时间格式
    String mDate;
    boolean isShowDay=true;

    @Override
    protected ListPresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list2, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        for (int i = 0; i < time_titles.length; i++) {
            time_tabLayout.addTab(time_tabLayout.newTab());
            time_tabLayout.getTabAt(i).setText(time_titles[i]);
        }

        time_tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

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
                IndicatorLineUtil.setIndicator(time_tabLayout, 10, 10);
            }
        });

        national_province_stl.setTabData(mTitles);
        national_province_stl.setCurrentTab(0);
        national_province_stl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {//省内
                    switch_rtv.setVisibility(View.VISIBLE);
                    initListTypeStl1();
                } else {//全国
                    switch_rtv.setVisibility(View.GONE);
                    initListTypeStl2();
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        initListTypeStl1();


        sort_PollutantsView.setmSelceTextListener(new PollutantsView.SelceTextListener() {
            @Override
            public void onSelect(String pollutantName, String pollutantCode) {

            }
        });
    }


    private void initListTypeStl1() {
        list_type_stl.setTabData(province_titles);
        list_type_stl.setCurrentTab(0);
        list_type_stl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    private void initListTypeStl2() {
        list_type_stl.setTabData(national_titles);
        list_type_stl.setCurrentTab(0);
        list_type_stl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }


    @OnClick({R.id.list_sore_way_ll, R.id.switch_rtv, R.id.choose_date_rtv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.list_sore_way_ll:
                isUp = !isUp;
                if (isUp) {
                    list_sore_img.setImageResource(R.mipmap.icon_sort_down);
                } else {
                    list_sore_img.setImageResource(R.mipmap.icon_sort_up);
                }

                break;

            case R.id.switch_rtv:

                break;

            case R.id.choose_date_rtv://选择日期
                mTimePikerView = TimerPikerTools.creatTimePickerView(getActivity(), "选择时间", true, true, isShowDay, new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        mDate = dateFormat.format(date);
                        choose_date_rtv.setText(mDate + "");
                    }
                });
                mTimePikerView.show();
                break;
        }
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

    @Override
    public void onRefresh() {

    }
}
