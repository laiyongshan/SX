package suncere.shanxi.androidapp.presenter;

import suncere.shanxi.androidapp.api.ApiNetCallBack;
import suncere.shanxi.androidapp.model.HomeDataChart24Model;
import suncere.shanxi.androidapp.ui.MyApplication;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.CatchManager;
import suncere.shanxi.androidapp.utils.NetWorkUtil;

import static suncere.shanxi.androidapp.utils.CatchManager.getCatchData;

/**
 * Created by Hjo on 2017/5/23.
 */

public class HomeStationChartPresenter extends BasePresenter<IView> {

    private String mKey;
    public HomeStationChartPresenter(IView iView){
        attrchIView(iView);
    }

    public void getChartData(String uniqueCode,String pollutantCode,String count,String Type){
        mKey=uniqueCode+pollutantCode+count;
        mIView.showRefresh();
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getMyApplicationContext())){
            getNetChartData(uniqueCode,pollutantCode,count,Type);
        }else{
            mIView.getDataFail("无网络连接！");
            mIView.getDataSuccess( getCatchData(mKey));
            mIView.finishRefresh();
        }
    }

    private   void getNetChartData(String uniqueCode,String pollutantCode,String count,String Type){
        addSubscription(mRetrofitService.getHomeStationChartData(uniqueCode,pollutantCode,count,Type),
                new ApiNetCallBack<HomeDataChart24Model>() {
                    @Override
                    public void onSuccess(HomeDataChart24Model response) {
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
