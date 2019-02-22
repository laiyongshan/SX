package suncere.shanxi.androidapp.model.entity;

/**
 * @author lys
 * @time 2018/11/15 14:15
 * @desc:
 */

public class StationChartBean extends BaseBean {

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
    private String Level1;
    private String Val2;
    private String Level2;
    private String Val3;
    private String Level3;


    public StationChartBean(String TimePoint, String Val1,String Level1, String Val2,String Level2, String Val3,String Level3){
        this.TimePoint=TimePoint;
        this.Val1=Val1;
        this.Level1=Level1;
        this.Val2=Val2;
        this.Level2=Level2;
        this.Val3=Val3;
        this.Level3=Level3;
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

    public void setLevel1(String level1) {
        Level1 = level1;
    }

    public String getLevel1() {
        return Level1;
    }

    public void setVal2(String val2) {
        Val2 = val2;
    }

    public void setLevel2(String level2) {
        Level2 = level2;
    }

    public String getLevel2() {
        return Level2;
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

    public void setLevel3(String level3) {
        Level3 = level3;
    }

    public String getLevel3() {
        return Level3;
    }
}
