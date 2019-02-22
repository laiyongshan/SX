package suncere.shanxi.androidapp.presenter;

import java.util.List;

import suncere.shanxi.androidapp.api.ApiNetCallBack;
import suncere.shanxi.androidapp.model.entity.CompareBean2;
import suncere.shanxi.androidapp.ui.MyApplication;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.CatchManager;
import suncere.shanxi.androidapp.utils.NetWorkUtil;

import static suncere.shanxi.androidapp.utils.CatchManager.getCatchData;

/**
 * Created by Hjo on 2017/5/12.
 */

public class ComparePresenter extends BasePresenter<IView> {

    public ComparePresenter(IView iView){
        attrchIView(iView);
    }

    String mKey;
    public void getCompareData(String type,String BeginTime,String endTime){
        mKey=type+BeginTime+endTime;
        mIView.showRefresh();
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getMyApplicationContext())){
            getNetCompareData(type,BeginTime,endTime);
        }else{
            mIView.getDataFail("无网络连接！");
            mIView.getDataSuccess( getCatchData(mKey));
            mIView.finishRefresh();
        }
    }

    private void  getNetCompareData(String type,String BeginTime,String endTime){
        addSubscription(mRetrofitService.getCompareBean2Date(type,BeginTime,endTime), new ApiNetCallBack<List<CompareBean2>>() {
            @Override
            public void onSuccess(List<CompareBean2> response) {
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
