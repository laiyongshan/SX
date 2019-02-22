package suncere.shanxi.androidapp.ui.iview;

/**
 * Created by Hjo on 2017/5/11.
 */

public interface IHomeView extends  IBaseView {

    void getDataSuccess(Object response);
    void getChartSuccess(Object response);
    void getCityStationSuccess(Object response);

}
