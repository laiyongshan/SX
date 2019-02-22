package suncere.shanxi.androidapp.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.model.entity.StationChartBean;
import suncere.shanxi.androidapp.utils.ColorUtils;

/**
 * @author lys
 * @time 2018/11/15 14:29
 * @desc:
 */

public class StationCompareAdapter extends BaseQuickAdapter<StationChartBean,BaseViewHolder> {

    public StationCompareAdapter(List<StationChartBean> data) {
        super(R.layout.item_stationcompare_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StationChartBean item) {
        ((TextView)helper.getView(R.id.time_tv)).setText(item.getTimePoint());
        ((TextView)helper.getView(R.id.city1_val_tv)).setText(item.getVal1());
        ((TextView)helper.getView(R.id.city1_val_tv)).setBackground(ColorUtils.getDrawableBgFromLevel(item.getLevel1()));
        ((TextView)helper.getView(R.id.city2_val_tv)).setText(item.getVal2());
        ((TextView)helper.getView(R.id.city2_val_tv)).setBackground(ColorUtils.getDrawableBgFromLevel(item.getLevel2()));
        ((TextView)helper.getView(R.id.city3_val_tv)).setText(item.getVal3());
        ((TextView)helper.getView(R.id.city3_val_tv)).setBackground(ColorUtils.getDrawableBgFromLevel(item.getLevel3()));

    }
}
