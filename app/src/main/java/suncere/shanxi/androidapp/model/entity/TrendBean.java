package suncere.shanxi.androidapp.model.entity;

/**
 * @author lys
 * @time 2019/2/21 15:38
 * @desc:
 */
public class TrendBean extends BaseBean {

    String TimePoint;
    String Value;

    public TrendBean( String TimePoint, String Value){
        this.TimePoint=TimePoint;
        this.Value=Value;
    }

    public void setTimePoint(String timePoint) {
        TimePoint = timePoint;
    }

    public String getTimePoint() {
        return TimePoint;
    }

    public void setValue(String value) {
        Value = value;
    }


    public String getValue() {
        return Value;
    }
}
