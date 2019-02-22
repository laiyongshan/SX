package suncere.shanxi.androidapp.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.model.entity.AnalysisItemBean;

/**
 * @author lys
 * @time 2018/9/28 11:56
 * @desc:
 */

public class AnalysisItemAdapter extends BaseQuickAdapter<AnalysisItemBean,BaseViewHolder> {

    public AnalysisItemAdapter(List<AnalysisItemBean> data) {
        super(R.layout.item_analysis, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnalysisItemBean item) {

        ((TextView)helper.getView(R.id.itemName_tv)).setText(item.getItemName()+"");
        ((TextView)helper.getView(R.id.itemName_sub_tv)).setText(item.getItemSubName());
        ((ImageView)helper.getView(R.id.item_icon_iv)).setImageResource(item.getImageId());

    }
}
