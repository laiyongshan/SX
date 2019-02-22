package suncere.shanxi.androidapp.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.model.entity.TrendBean;

/**
 * @author lys
 * @time 2019/2/21 15:37
 * @desc:
 */
public class TrendAdapter extends BaseQuickAdapter<TrendBean, BaseViewHolder> {
    public TrendAdapter( List<TrendBean> data) {
        super(R.layout.item_trend_rv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TrendBean item) {
        ((TextView)helper.getView(R.id.timepoint_tv)).setText(item.getTimePoint()+"");
        ((TextView)helper.getView(R.id.value_tv)).setText(item.getValue()+"");
    }
}
