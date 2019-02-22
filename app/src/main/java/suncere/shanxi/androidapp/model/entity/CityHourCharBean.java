package suncere.shanxi.androidapp.model.entity;

/**
 * @author lys
 * @time 2018/10/10 11:35
 * @desc:
 */

public class CityHourCharBean extends BaseBean {


    /**
     * CityCode : null
     * CityName : 兰州市
     * TimePoint : 2018-10-09T00:00:00+08:00
     * AQI : null
     * SO2 : 9
     * NO2 : 72
     * CO : 0.7
     * O3 : 6
     * PM10 : 77
     * PM2_5 : 40
     * PM10_D : —
     * PM2_5_D : —
     */

    private String CityCode;
    private String CityName;
    private String TimePoint;
    private String AQI;
    private String SO2;
    private String NO2;
    private String CO;
    private String O3;
    private String PM10;
    private String PM2_5;
    private String PM10_D;
    private String PM2_5_D;


    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String CityCode) {
        this.CityCode = CityCode;
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

    public String getAQI() {
        return AQI;
    }

    public void setAQI(String AQI) {
        this.AQI = AQI;
    }

    public String getSO2() {
        return SO2;
    }

    public void setSO2(String SO2) {
        this.SO2 = SO2;
    }

    public String getNO2() {
        return NO2;
    }

    public void setNO2(String NO2) {
        this.NO2 = NO2;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getO3() {
        return O3;
    }

    public void setO3(String O3) {
        this.O3 = O3;
    }

    public String getPM10() {
        return PM10;
    }

    public void setPM10(String PM10) {
        this.PM10 = PM10;
    }

    public String getPM2_5() {
        return PM2_5;
    }

    public void setPM2_5(String PM2_5) {
        this.PM2_5 = PM2_5;
    }

    public String getPM10_D() {
        return PM10_D;
    }

    public void setPM10_D(String PM10_D) {
        this.PM10_D = PM10_D;
    }

    public String getPM2_5_D() {
        return PM2_5_D;
    }

    public void setPM2_5_D(String PM2_5_D) {
        this.PM2_5_D = PM2_5_D;
    }
}
