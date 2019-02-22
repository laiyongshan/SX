package suncere.shanxi.androidapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import suncere.shanxi.androidapp.R;
import suncere.shanxi.androidapp.customview.TipView;
import suncere.shanxi.androidapp.presenter.UpdataAppPresenter;
import suncere.shanxi.androidapp.ui.iview.IView;

/**
 * @author lys
 * @time 2019/2/15 11:25
 * @desc:
 */
public class SetFragment2 extends MvpFragment<UpdataAppPresenter> implements IView {
    TipView mtip;
    private View view;

    UpdataAppPresenter mUpdataAppPresenter;
    @Override
    protected UpdataAppPresenter createPresenter() {
        mUpdataAppPresenter=new UpdataAppPresenter(this);
        return mUpdataAppPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_set2,null);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView(){
        mtip=new TipView(getActivity(),R.layout.tip_view);
        TextView set_app_version= (TextView) mtip.findViewById(R.id.set_app_version);
        set_app_version.setText("版本号："+MyApplication.getMyApplicationVersionName());
    }


    @OnClick({R.id.set_AQIlevel_layout,R.id.set_fabu_layout,R.id.set_checkUpdata_layout,R.id.set_about_layout})
    public  void click(View view){
        switch (view.getId()){
            case R.id.set_AQIlevel_layout:
                Intent intent1=new Intent(getActivity(),AQIDesActivity.class);
                startActivity(intent1);
                break;

            case R.id.set_fabu_layout:
                Intent  intent2=new Intent(getActivity(),SystemDesActivity.class);
                startActivity(intent2);
                break;

            case R.id.set_checkUpdata_layout:

                break;

            case R.id.set_about_layout:
                mtip.show();
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
}
