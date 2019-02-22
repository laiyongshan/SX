package suncere.shanxi.androidapp.model.entity;

/**
 * @author lys
 * @time 2018/9/21 15:24
 * @desc:
 */

public class CompareBean3 extends BaseBean {

    /**
     * AreaCode : 620100
     * AreaName : 兰州市
     * TarGetPM10 : 112
     * TarGetPM25 : 49
     * TarGetGoodRate : 70.1
     * PM10Avg : 74
     * PM25Avg : 30
     * GoodRate : 91.35
     * ComparePM10 : -38
     * ComparePM25 : -19
     * CompareGoodRate : 21.25
     */

    private String AreaCode;
    private String AreaName;
    private String TarGetPM10;
    private String TarGetPM25;
    private String TarGetGoodRate;
    private String PM10Avg;
    private String PM25Avg;
    private String GoodRate;
    private int ComparePM10;
    private int ComparePM25;
    private double CompareGoodRate;

    public void setAreaCode(String AreaCode) {
        this.AreaCode = AreaCode;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }

    public void setTarGetPM10(String TarGetPM10) {
        this.TarGetPM10 = TarGetPM10;
    }

    public void setTarGetPM25(String TarGetPM25) {
        this.TarGetPM25 = TarGetPM25;
    }

    public void setTarGetGoodRate(String TarGetGoodRate) {
        this.TarGetGoodRate = TarGetGoodRate;
    }

    public void setPM10Avg(String PM10Avg) {
        this.PM10Avg = PM10Avg;
    }

    public void setPM25Avg(String PM25Avg) {
        this.PM25Avg = PM25Avg;
    }

    public void setGoodRate(String GoodRate) {
        this.GoodRate = GoodRate;
    }

    public void setComparePM10(int ComparePM10) {
        this.ComparePM10 = ComparePM10;
    }

    public void setComparePM25(int ComparePM25) {
        this.ComparePM25 = ComparePM25;
    }

    public void setCompareGoodRate(double CompareGoodRate) {
        this.CompareGoodRate = CompareGoodRate;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public String getTarGetPM10() {
        return TarGetPM10;
    }

    public String getTarGetPM25() {
        return TarGetPM25;
    }

    public String getTarGetGoodRate() {
        return TarGetGoodRate;
    }

    public String getPM10Avg() {
        return PM10Avg;
    }

    public String getPM25Avg() {
        return PM25Avg;
    }

    public String getGoodRate() {
        return GoodRate;
    }

    public int getComparePM10() {
        return ComparePM10;
    }

    public int getComparePM25() {
        return ComparePM25;
    }

    public double getCompareGoodRate() {
        return CompareGoodRate;
    }
}
