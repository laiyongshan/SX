package suncere.shanxi.androidapp.presenter;

import java.util.List;

import suncere.shanxi.androidapp.api.ApiNetCallBack;
import suncere.shanxi.androidapp.model.entity.CityHourCharBean;
import suncere.shanxi.androidapp.ui.MyApplication;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.CatchManager;
import suncere.shanxi.androidapp.utils.NetWorkUtil;

import static suncere.shanxi.androidapp.utils.CatchManager.getCatchData;

/**
 * @author lys
 * @time 2018/10/10 11:29
 * @desc:
 */

public class MeanPresenter extends BasePresenter<IView>  {

    public MeanPresenter(IView iview){
        attrchIView(iview);
    }


    String mKey;
    public void getMeanChartData(String cityName , String Stime , String Etime){
        mKey=cityName+Stime+Etime;
        mIView.showRefresh();
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getMyApplicationContext())){
            getNetMeanChartData(cityName,Stime,Etime);
        }else{
            mIView.getDataFail("无网络连接！");
            mIView.getDataSuccess( getCatchData(mKey));
            mIView.finishRefresh();
        }
    }

    private void getNetMeanChartData(String cityName , String Stime , String Etime){
        addSubscription(mRetrofitService.getCityHourChar(cityName,Stime,Etime), new ApiNetCallBack<List<CityHourCharBean>>() {
            @Override
            public void onSuccess(List<CityHourCharBean> response) {
                mIView.getDataSuccess(response);
                CatchManager.putData2Cache(mKey,response);
            }

            @Override
            public void onError(String msg) {
                mIView.getDataFail(msg);
            }
            @Override
            public void onFinish() {
                mIView.finishRefresh();
            }
        });
    }

}
