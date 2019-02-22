package suncere.shanxi.androidapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.utils.ColorUtils;

/**
 * @author lys
 * @time 2018/9/3 17:30
 * @desc:
 */

public class TipsDialog extends Dialog {

    String AQI;

    @BindView(R.id.fw_dialogViewTitle)
    TextView fw_dialogViewTitle;

    @BindView(R.id.dialogView_content_tv1)
    TextView fw_dialogView_content1;

    @BindView(R.id.dialogView_content_tv2)
    TextView fw_dialogView_content2;

    @BindView(R.id.fw_dialogView_colse)
    ImageView fw_dialogView_colse;

    public TipsDialog(@NonNull Context context, int themeResId, String AQI) {
        super(context, themeResId);
        this.AQI=AQI;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_tips);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        fw_dialogView_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        fw_dialogView_content1.setText("健康影响："+ColorUtils.getHealthWithAQI(AQI) +"");
        fw_dialogView_content2.setText("建议措施："+ColorUtils.getSuggeWithAQI(AQI)+"");
    }
}
