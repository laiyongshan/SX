package suncere.shanxi.androidapp.presenter;

import java.util.List;

import suncere.shanxi.androidapp.api.ApiNetCallBack;
import suncere.shanxi.androidapp.model.entity.PolluteCompareBean;
import suncere.shanxi.androidapp.ui.MyApplication;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.CatchManager;
import suncere.shanxi.androidapp.utils.NetWorkUtil;

import static suncere.shanxi.androidapp.utils.CatchManager.getCatchData;

/**
 * @author lys
 * @time 2018/11/15 16:47
 * @desc:
 */

public class PolluteComparePresenter extends BasePresenter<IView> {

    public PolluteComparePresenter(IView iView){
        attrchIView(iView);
    }

    String mKey;
    public void getPolluteCompareData(String cityName,String TimeType,String countIndex,String PollutantCode){
        mKey=cityName+TimeType+countIndex;
        mIView.showRefresh();
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getMyApplicationContext())){
            getNetPolluteCompareData(cityName,TimeType,countIndex,PollutantCode);
        }else{
            mIView.getDataFail("无网络连接！");
            mIView.getDataSuccess( getCatchData(mKey));
            mIView.finishRefresh();
        }
    }

    private void  getNetPolluteCompareData(String cityName,String TimeType,String countIndex,String PollutantCode){
        addSubscription(mRetrofitService.getPolluteCompareData(cityName,TimeType,countIndex,PollutantCode), new ApiNetCallBack<List<PolluteCompareBean>>() {
            @Override
            public void onSuccess(List<PolluteCompareBean> response) {
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
