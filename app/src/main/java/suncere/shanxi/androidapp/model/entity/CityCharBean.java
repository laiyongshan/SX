package suncere.shanxi.androidapp.model.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lys
 * @time 2018/11/14 16:55
 * @desc:
 */

public class CityCharBean extends BaseBean {


    /**
     * CityName : 太原市
     * TimePoint : 2019-01-20T00:00:00+08:00
     * Val : 275
     * Level : 5
     */

    private String CityName;
    private String TimePoint;
    private String Val;
    private String Level;

    public static CityCharBean objectFromData(String str) {

        return new Gson().fromJson(str, CityCharBean.class);
    }

    public static List<CityCharBean> arrayCityCharBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CityCharBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public String getTimePoint() {
        return TimePoint;
    }

    public void setTimePoint(String TimePoint) {
        this.TimePoint = TimePoint;
    }

    public String getVal() {
        return Val;
    }

    public void setVal(String Val) {
        this.Val = Val;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
    }
}
