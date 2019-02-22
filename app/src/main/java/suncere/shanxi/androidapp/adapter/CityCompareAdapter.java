package suncere.shanxi.androidapp.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.model.entity.CityCharBean2;

/**
 * @author lys
 * @time 2018/11/15 14:29
 * @desc:
 */

public class CityCompareAdapter extends BaseQuickAdapter<CityCharBean2,BaseViewHolder> {

    public CityCompareAdapter( List<CityCharBean2> data) {
        super(R.layout.item_citycompare_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CityCharBean2 item) {
        ((TextView)helper.getView(R.id.time_tv)).setText(item.getTimePoint());
        ((TextView)helper.getView(R.id.city1_val_tv)).setText(item.getVal1());
        ((TextView)helper.getView(R.id.city2_val_tv)).setText(item.getVal2());
        ((TextView)helper.getView(R.id.city3_val_tv)).setText(item.getVal3());

    }
}
