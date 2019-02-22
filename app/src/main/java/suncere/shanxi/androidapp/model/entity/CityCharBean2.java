package suncere.shanxi.androidapp.model.entity;

/**
 * @author lys
 * @time 2018/11/15 14:15
 * @desc:
 */

public class CityCharBean2 extends BaseBean {

    /**
     * CityName : 金昌市
     * TimePoint : 2018-11-12T09:00:00+08:00
     * Val : 157
     * CityName  : 金昌市
     * TimePoint  : 2018 - 11 - 14 T19: 00: 00 + 08: 00
     * Val  : 3
     */

    private String TimePoint;
    private String Val1;
    private String Val2;
    private String Val3;


    public CityCharBean2(String TimePoint,String Val1,String Val2,String Val3){
        this.TimePoint=TimePoint;
        this.Val1=Val1;
        this.Val2=Val2;
        this.Val3=Val3;
    }

    public void setTimePoint(String TimePoint) {
        this.TimePoint = TimePoint;
    }

    public String getTimePoint() {
        return TimePoint;
    }

    public void setVal1(String val1) {
        Val1 = val1;
    }

    public String getVal1() {
        return Val1;
    }

    public void setVal2(String val2) {
        Val2 = val2;
    }

    public String getVal2() {
        return Val2;
    }

    public void setVal3(String val3) {
        Val3 = val3;
    }

    public String getVal3() {
        return Val3;
    }
}
