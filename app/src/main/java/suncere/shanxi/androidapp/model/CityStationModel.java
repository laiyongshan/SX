package suncere.shanxi.androidapp.model;

import java.util.List;

import suncere.shanxi.androidapp.model.entity.BaseBean;

/**
 * @author lys
 * @time 2019/2/21 16:52
 * @desc:
 */
public class CityStationModel extends BaseBean {

    /**
     * CityCode : 140100
     * CityName : null
     * Temp : null
     * FX : null
     * WindLevel : null
     * Weather : null
     * Humidity : null
     * Unheathful : —
     * CoLevel : null
     * No2Level : null
     * So2Level : null
     * O3Level : null
     * Pm10Level : null
     * Pm2_5Level : null
     * StationList : [{"TimePoint":"2019-02-21T16:00:00","StationCode":"1043A","PositionName":"尖草坪","Longitude":"112.522","Latitude":"37.8873","AreaCode":"140108","UniqueCode":"140100053","CityName":"尖草坪区","AQI":"84","Measure":"\u2014","Unheathful":"\u2014","PrimaryPollutant":"颗粒物(PM10)","Quality":"良","SO2":"22","NO2":"36","O3_8h":"\u2014","O3":"81","CO":"1.227","PM10":"118","PM2_5":"36","SO2_Mark":"\u2014","NO2_Mark":"\u2014","O3_Mark":"\u2014","O3_8h_Mark":"\u2014","CO_Mark":"\u2014","PM10_Mark":"\u2014","PM2_5_Mark":"\u2014","SumIndex":null,"GoodDay":null},{"TimePoint":"2019-02-21T16:00:00","StationCode":"1042A","PositionName":"金胜","Longitude":"112.488","Latitude":"37.7805","AreaCode":"140110","UniqueCode":"140100054","CityName":"晋源区","AQI":"77","Measure":"\u2014","Unheathful":"\u2014","PrimaryPollutant":"细颗粒物(PM2.5)","Quality":"良","SO2":"41","NO2":"31","O3_8h":"\u2014","O3":"96","CO":"0.945","PM10":"99","PM2_5":"56","SO2_Mark":"\u2014","NO2_Mark":"\u2014","O3_Mark":"\u2014","O3_8h_Mark":"\u2014","CO_Mark":"\u2014","PM10_Mark":"\u2014","PM2_5_Mark":"\u2014","SumIndex":null,"GoodDay":null},{"TimePoint":"2019-02-21T16:00:00","StationCode":"1041A","PositionName":"南寨","Longitude":"112.549","Latitude":"37.9854","AreaCode":"140108","UniqueCode":"140100056","CityName":"尖草坪区","AQI":"70","Measure":"\u2014","Unheathful":"\u2014","PrimaryPollutant":"细颗粒物(PM2.5)","Quality":"良","SO2":"42","NO2":"52","O3_8h":"\u2014","O3":"65","CO":"1.531","PM10":"65","PM2_5":"51","SO2_Mark":"\u2014","NO2_Mark":"\u2014","O3_Mark":"\u2014","O3_8h_Mark":"\u2014","CO_Mark":"\u2014","PM10_Mark":"\u2014","PM2_5_Mark":"\u2014","SumIndex":null,"GoodDay":null},{"TimePoint":"2019-02-21T16:00:00","StationCode":"1037A","PositionName":"桃园","Longitude":"112.537","Latitude":"37.8692","AreaCode":"140107","UniqueCode":"140100057","CityName":"杏花岭区","AQI":"63","Measure":"\u2014","Unheathful":"\u2014","PrimaryPollutant":"颗粒物(PM10),细颗粒物(PM2.5)","Quality":"良","SO2":"20","NO2":"\u2014","O3_8h":"\u2014","O3":"70","CO":"0.801","PM10":"76","PM2_5":"45","SO2_Mark":"\u2014","NO2_Mark":"\u2014","O3_Mark":"\u2014","O3_8h_Mark":"\u2014","CO_Mark":"\u2014","PM10_Mark":"\u2014","PM2_5_Mark":"\u2014","SumIndex":null,"GoodDay":null},{"TimePoint":"2019-02-21T16:00:00","StationCode":"1040A","PositionName":"坞城","Longitude":"112.57","Latitude":"37.8195","AreaCode":"140105","UniqueCode":"140100059","CityName":"小店区","AQI":"64","Measure":"\u2014","Unheathful":"\u2014","PrimaryPollutant":"颗粒物(PM10)","Quality":"良","SO2":"25","NO2":"44","O3_8h":"\u2014","O3":"76","CO":"0.968","PM10":"77","PM2_5":"45","SO2_Mark":"\u2014","NO2_Mark":"\u2014","O3_Mark":"\u2014","O3_8h_Mark":"\u2014","CO_Mark":"\u2014","PM10_Mark":"\u2014","PM2_5_Mark":"\u2014","SumIndex":null,"GoodDay":null},{"TimePoint":"2019-02-21T16:00:00","StationCode":"1039A","PositionName":"小店","Longitude":"112.477","Latitude":"37.7814","AreaCode":"140105","UniqueCode":"140100062","CityName":"小店区","AQI":"72","Measure":"\u2014","Unheathful":"\u2014","PrimaryPollutant":"颗粒物(PM10)","Quality":"良","SO2":"35","NO2":"34","O3_8h":"\u2014","O3":"93","CO":"1.028","PM10":"93","PM2_5":"49","SO2_Mark":"\u2014","NO2_Mark":"\u2014","O3_Mark":"\u2014","O3_8h_Mark":"\u2014","CO_Mark":"\u2014","PM10_Mark":"\u2014","PM2_5_Mark":"\u2014","SumIndex":null,"GoodDay":null}]
     */

    private String CityCode;
    private String CityName;
    private String Temp;
    private String FX;
    private String WindLevel;
    private String Weather;
    private String Humidity;
    private String Unheathful;
    private String CoLevel;
    private String No2Level;
    private String So2Level;
    private String O3Level;
    private String Pm10Level;
    private String Pm2_5Level;
    private List<StationListBean> StationList;


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

    public String getTemp() {
        return Temp;
    }

    public void setTemp(String Temp) {
        this.Temp = Temp;
    }

    public String getFX() {
        return FX;
    }

    public void setFX(String FX) {
        this.FX = FX;
    }

    public String getWindLevel() {
        return WindLevel;
    }

    public void setWindLevel(String WindLevel) {
        this.WindLevel = WindLevel;
    }

    public String getWeather() {
        return Weather;
    }

    public void setWeather(String Weather) {
        this.Weather = Weather;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String Humidity) {
        this.Humidity = Humidity;
    }

    public String getUnheathful() {
        return Unheathful;
    }

    public void setUnheathful(String Unheathful) {
        this.Unheathful = Unheathful;
    }

    public String getCoLevel() {
        return CoLevel;
    }

    public void setCoLevel(String CoLevel) {
        this.CoLevel = CoLevel;
    }

    public String getNo2Level() {
        return No2Level;
    }

    public void setNo2Level(String No2Level) {
        this.No2Level = No2Level;
    }

    public String getSo2Level() {
        return So2Level;
    }

    public void setSo2Level(String So2Level) {
        this.So2Level = So2Level;
    }

    public String getO3Level() {
        return O3Level;
    }

    public void setO3Level(String O3Level) {
        this.O3Level = O3Level;
    }

    public String getPm10Level() {
        return Pm10Level;
    }

    public void setPm10Level(String Pm10Level) {
        this.Pm10Level = Pm10Level;
    }

    public String getPm2_5Level() {
        return Pm2_5Level;
    }

    public void setPm2_5Level(String Pm2_5Level) {
        this.Pm2_5Level = Pm2_5Level;
    }

    public List<StationListBean> getStationList() {
        return StationList;
    }

    public void setStationList(List<StationListBean> StationList) {
        this.StationList = StationList;
    }

    public static class StationListBean extends BaseBean{
        /**
         * TimePoint : 2019-02-21T16:00:00
         * StationCode : 1043A
         * PositionName : 尖草坪
         * Longitude : 112.522
         * Latitude : 37.8873
         * AreaCode : 140108
         * UniqueCode : 140100053
         * CityName : 尖草坪区
         * AQI : 84
         * Measure : —
         * Unheathful : —
         * PrimaryPollutant : 颗粒物(PM10)
         * Quality : 良
         * SO2 : 22
         * NO2 : 36
         * O3_8h : —
         * O3 : 81
         * CO : 1.227
         * PM10 : 118
         * PM2_5 : 36
         * SO2_Mark : —
         * NO2_Mark : —
         * O3_Mark : —
         * O3_8h_Mark : —
         * CO_Mark : —
         * PM10_Mark : —
         * PM2_5_Mark : —
         * SumIndex : null
         * GoodDay : null
         */

        private String TimePoint;
        private String StationCode;
        private String PositionName;
        private String Longitude;
        private String Latitude;
        private String AreaCode;
        private String UniqueCode;
        private String CityName;
        private String AQI;
        private String Measure;
        private String Unheathful;
        private String PrimaryPollutant;
        private String Quality;
        private String SO2;
        private String NO2;
        private String O3_8h;
        private String O3;
        private String CO;
        private String PM10;
        private String PM2_5;
        private String SO2_Mark;
        private String NO2_Mark;
        private String O3_Mark;
        private String O3_8h_Mark;
        private String CO_Mark;
        private String PM10_Mark;
        private String PM2_5_Mark;
        private String SumIndex;
        private String GoodDay;

        public String getTimePoint() {
            return TimePoint;
        }

        public void setTimePoint(String TimePoint) {
            this.TimePoint = TimePoint;
        }

        public String getStationCode() {
            return StationCode;
        }

        public void setStationCode(String StationCode) {
            this.StationCode = StationCode;
        }

        public String getPositionName() {
            return PositionName;
        }

        public void setPositionName(String PositionName) {
            this.PositionName = PositionName;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String Longitude) {
            this.Longitude = Longitude;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String Latitude) {
            this.Latitude = Latitude;
        }

        public String getAreaCode() {
            return AreaCode;
        }

        public void setAreaCode(String AreaCode) {
            this.AreaCode = AreaCode;
        }

        public String getUniqueCode() {
            return UniqueCode;
        }

        public void setUniqueCode(String UniqueCode) {
            this.UniqueCode = UniqueCode;
        }

        public String getCityName() {
            return CityName;
        }

        public void setCityName(String CityName) {
            this.CityName = CityName;
        }

        public String getAQI() {
            return AQI;
        }

        public void setAQI(String AQI) {
            this.AQI = AQI;
        }

        public String getMeasure() {
            return Measure;
        }

        public void setMeasure(String Measure) {
            this.Measure = Measure;
        }

        public String getUnheathful() {
            return Unheathful;
        }

        public void setUnheathful(String Unheathful) {
            this.Unheathful = Unheathful;
        }

        public String getPrimaryPollutant() {
            return PrimaryPollutant;
        }

        public void setPrimaryPollutant(String PrimaryPollutant) {
            this.PrimaryPollutant = PrimaryPollutant;
        }

        public String getQuality() {
            return Quality;
        }

        public void setQuality(String Quality) {
            this.Quality = Quality;
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

        public String getO3_8h() {
            return O3_8h;
        }

        public void setO3_8h(String O3_8h) {
            this.O3_8h = O3_8h;
        }

        public String getO3() {
            return O3;
        }

        public void setO3(String O3) {
            this.O3 = O3;
        }

        public String getCO() {
            return CO;
        }

        public void setCO(String CO) {
            this.CO = CO;
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

        public String getSO2_Mark() {
            return SO2_Mark;
        }

        public void setSO2_Mark(String SO2_Mark) {
            this.SO2_Mark = SO2_Mark;
        }

        public String getNO2_Mark() {
            return NO2_Mark;
        }

        public void setNO2_Mark(String NO2_Mark) {
            this.NO2_Mark = NO2_Mark;
        }

        public String getO3_Mark() {
            return O3_Mark;
        }

        public void setO3_Mark(String O3_Mark) {
            this.O3_Mark = O3_Mark;
        }

        public String getO3_8h_Mark() {
            return O3_8h_Mark;
        }

        public void setO3_8h_Mark(String O3_8h_Mark) {
            this.O3_8h_Mark = O3_8h_Mark;
        }

        public String getCO_Mark() {
            return CO_Mark;
        }

        public void setCO_Mark(String CO_Mark) {
            this.CO_Mark = CO_Mark;
        }

        public String getPM10_Mark() {
            return PM10_Mark;
        }

        public void setPM10_Mark(String PM10_Mark) {
            this.PM10_Mark = PM10_Mark;
        }

        public String getPM2_5_Mark() {
            return PM2_5_Mark;
        }

        public void setPM2_5_Mark(String PM2_5_Mark) {
            this.PM2_5_Mark = PM2_5_Mark;
        }

        public String getSumIndex() {
            return SumIndex;
        }

        public void setSumIndex(String SumIndex) {
            this.SumIndex = SumIndex;
        }

        public String getGoodDay() {
            return GoodDay;
        }

        public void setGoodDay(String GoodDay) {
            this.GoodDay = GoodDay;
        }
    }
}
