package suncere.shanxi.androidapp.model.entity;

/**
 * @author lys
 * @time 2018/11/16 10:44
 * @desc:
 */

public class PolluteCompareBean extends BaseBean {

    /**
     * CityName : 金昌市
     * TimePoint : 2018-11-12T09:00:00+08:00
     * Val : 157
     * CityName  : 金昌市
     * TimePoint  : 2018 - 11 - 14 T19: 00: 00 + 08: 00
     * Val  : 3
     */

    private String TimePoint;
    private String Val;
    private String Val_D;


    public void setTimePoint(String TimePoint) {
        this.TimePoint = TimePoint;
    }

    public String getTimePoint() {
        return TimePoint;
    }

    public void setVal(String val) {
        Val = val;
    }

    public String getVal() {
        return Val;
    }

    public void setVal_D(String val_D) {
        Val_D = val_D;
    }

    public String getVal_D() {
        return Val_D;
    }
}
