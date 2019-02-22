package suncere.shanxi.androidapp.adapter;

import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.model.CityStationModel;
import suncere.shanxi.androidapp.utils.ColorUtils;

/**
 * @author lys
 * @time 2019/2/21 17:14
 * @desc:
 */
public class HomeStationListAdapter extends BaseQuickAdapter<CityStationModel.StationListBean, BaseViewHolder> {
    public HomeStationListAdapter(List<CityStationModel.StationListBean> data) {
        super(R.layout.item_home_city_station, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityStationModel.StationListBean item) {
        ((TextView) helper.getView(R.id.station_name_tv)).setText(item.getPositionName()+"");
        ((TextView) helper.getView(R.id.time_point_tv)).setText(ColorUtils.stringToData(item.getTimePoint(), "yyyy-MM-dd HH:mm:ss", "MM.dd HH:00"+""));
        ((TextView) helper.getView(R.id.value_tv)).setText(item.getAQI()+"");
        ((TextView) helper.getView(R.id.value_tv)).setBackground(ColorUtils.getBgFromQualitys(item.getQuality()+""));
        Log.i("lys","执行这里吗？");
    }
}
