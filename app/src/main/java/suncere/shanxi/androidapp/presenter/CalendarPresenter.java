package suncere.shanxi.androidapp.presenter;

import java.util.List;

import suncere.shanxi.androidapp.api.ApiNetCallBack;
import suncere.shanxi.androidapp.model.AQDayCountModel;
import suncere.shanxi.androidapp.model.entity.AQIDayInfoEty;
import suncere.shanxi.androidapp.ui.MyApplication;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.NetWorkUtil;

import static suncere.shanxi.androidapp.utils.CatchManager.getCatchData;

/**
 * @author lys
 * @time 2018/8/29 18:26
 * @desc:
 */

public class CalendarPresenter extends BasePresenter<IView> {
    public CalendarPresenter(IView iView){
        attrchIView(iView);
    }

    String mKey="calendar";
    public void getCityDayLevelInfoData(String cityName,String pollutantName,String year, String month){
        mIView.showRefresh();
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getMyApplicationContext())){
            getNetCityDayLevelInfoData(cityName,pollutantName,year,month);
        }else{
            mIView.getDataFail("无网络连接！");
            mIView.getDataSuccess( getCatchData(mKey));
            mIView.finishRefresh();
        }
    }

    public void getCityDayCount(String cityName,String countType,String year,String countIndex){
        String index=countIndex.replaceAll("[\u4e00-\u9fa5]", "");//去掉中文字符
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getMyApplicationContext())){
            getNetCityDayCount(cityName,countType,year,index);
        }else{
            mIView.getDataFail("无网络连接！");
//            mIView.getDataSuccess( getCatchData(mKey));
            mIView.finishRefresh();
        }
    }

    private void getNetCityDayCount(String cityName,String countType,String year,String countIndex){
        addSubscription(mRetrofitService.getCityDayCount(cityName,countType,year,countIndex), new ApiNetCallBack<AQDayCountModel>() {
            @Override
            public void onSuccess(AQDayCountModel response) {
                mIView.getDataSuccess(response);
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



    private void  getNetCityDayLevelInfoData(String cityName,String pollutantName,String year, String month){
        addSubscription(mRetrofitService.getCityDayLevelInfo(cityName,pollutantName,year,month), new ApiNetCallBack<List<AQIDayInfoEty>>() {
            @Override
            public void onSuccess(List<AQIDayInfoEty> response) {
                mIView.getDataSuccess(response);
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
