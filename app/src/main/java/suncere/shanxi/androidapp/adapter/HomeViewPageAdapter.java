package suncere.shanxi.androidapp.adapter;

import android.content.Context;
import android.support.v4.util.Pools;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.roundview.RoundRelativeLayout;
import com.flyco.roundview.RoundTextView;
import com.shinelw.library.ColorArcProgressBar;

import java.util.ArrayList;
import java.util.List;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.model.entity.HomeAllCitiesBean;
import suncere.shanxi.androidapp.ui.TipsDialog;
import suncere.shanxi.androidapp.utils.ColorUtils;

/**
 * Created by Hjo on 2017/4/1.
 */

public class HomeViewPageAdapter<T> extends PagerAdapter {

    private List<T> list;
    private int layoutId;
    private int BRId;
    private Pools.Pool<View> pool = new Pools.SimplePool<>(4); //造一个池，提高加载效率，与复用率，
    private ViewpagerOnBindView mViewpagerOnBindView;
    private Context context;

    public HomeViewPageAdapter(Context context,List<T> list, int layoutId) {
        this.list=new ArrayList<>();
//        this.list.clear();
        this.list.addAll(list);
//        this.list=list;
        this.layoutId = layoutId;

        this.context=context;
    }

    public void setData(List<T> list){

        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void setViewpagerOnBindView(ViewpagerOnBindView viewpagerOnBindView){
        this.mViewpagerOnBindView=viewpagerOnBindView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = pool.acquire();
        if (view == null) {
//            view = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), layoutId, container, false).getRoot();
            view= LayoutInflater.from(container.getContext()).inflate( layoutId, container, false);
        }

        final HomeAllCitiesBean bean=((HomeAllCitiesBean)list.get(position));

        TextView cityName_tv=view.findViewById(R.id.cityName_tv);
        cityName_tv.setText(bean.getCityName()+""+bean.getTimePoint());
        ColorArcProgressBar aqi_capbar=view.findViewById(R.id.aqi_capbar);
        try {
            aqi_capbar.setCurrentValues(Float.valueOf(bean.getAQI()));
            aqi_capbar.setTextSize(14);
            aqi_capbar.setUnit(bean.getQuality()+"");
            aqi_capbar.setBgArcWidth(120);
        }catch (Exception e){
        }

        TextView primary_pollutant_tv=view.findViewById(R.id.primary_pollutant_tv);
        primary_pollutant_tv.setText(ColorUtils.PollutantChiness2Eglish(bean.getPrimaryPollutant()+""));

        TextView PM25_value_tv=view.findViewById(R.id.PM25_value_tv);
        PM25_value_tv.setText(bean.getPM2_5()+"");
        RoundRelativeLayout pm25_level_rl=view.findViewById(R.id.pm25_level_rl);
        pm25_level_rl.setBackgroundColor(ColorUtils.getColorWithLevel(bean.getPm2_5Level()));

        TextView PM10_value_tv=view.findViewById(R.id.PM10_value_tv);
        PM10_value_tv.setText(bean.getPM10()+"");

        TextView SO2_value_tv=view.findViewById(R.id.SO2_value_tv);
        SO2_value_tv.setText(bean.getSO2()+"");

        TextView NO2_value_tv=view.findViewById(R.id.NO2_value_tv);
        NO2_value_tv.setText(bean.getNO2()+"");

        TextView O3_value_tv=view.findViewById(R.id.O3_value_tv);
        O3_value_tv.setText(bean.getO3()+"");

        TextView CO_value_tv=view.findViewById(R.id.CO_value_tv);
        CO_value_tv.setText(bean.getCO()+"");

        final RoundTextView tips_tv=view.findViewById(R.id.tips_tv);
        tips_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TipsDialog tipsDialog=new TipsDialog(context,R.style.dialog,bean.getAQI()+"");
                tipsDialog.show();
            }
        });

        TextView temperature_tv=view.findViewById(R.id.temperature_tv);
        temperature_tv.setText(bean.getTemp()+"  "+bean.getWeather());

        TextView wind_humidity_tv=view.findViewById(R.id.wind_humidity_tv);
        wind_humidity_tv.setText(bean.getFX()+bean.getWindLevel()+"   "+"湿度"+bean.getHumidity());



//        ViewDataBinding bind = DataBindingUtil.bind(view);
//        bind.setVariable(BRId, list.get(position));
//        if (mViewpagerOnBindView!=null){
//            mViewpagerOnBindView.onBindingView( view,list.get(position) );
//        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        pool.release(view);
    }

    public interface  ViewpagerOnBindView{
       void onBindingView(View view ,Object object);
    }

    // 以下解决调用notifyDataSetChanged不刷新的问题
    private int mChildCount = 0;
    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object)   {
        if ( mChildCount > 0) {
            mChildCount --;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

}
