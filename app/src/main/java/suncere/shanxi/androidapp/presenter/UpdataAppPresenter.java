package suncere.shanxi.androidapp.presenter;


import suncere.shanxi.androidapp.api.ApiNetCallBack;
import suncere.shanxi.androidapp.ui.MyApplication;
import suncere.shanxi.androidapp.ui.iview.IView;
import suncere.shanxi.androidapp.utils.NetWorkUtil;

/**
 * Created by Hjo on 2017/6/28.
 */

public class UpdataAppPresenter extends BasePresenter<IView> {

    public UpdataAppPresenter(IView Iview){
        attrchIView(Iview);
    }

    public void updataAPP(String version){
        mIView.showRefresh();
        if (NetWorkUtil.isNetWorkAvailable(MyApplication.getMyApplicationContext())){
            getNetupdataAPP(version);
        }else{
            mIView.getDataFail("无网络连接！");
            mIView.getDataSuccess( null);
            mIView.finishRefresh();
        }
    }
    private void getNetupdataAPP(String version ){
        addSubscription(mRetrofitService.updataAPP(version), new ApiNetCallBack<String>() {
            @Override
            public void onSuccess(String response) {
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
