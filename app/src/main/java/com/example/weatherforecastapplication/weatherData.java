package com.example.weatherforecastapplication;


import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {

    private String mTemperature,micon,mcity,mWeatherType;
    private String humidity;
    private int mCondition;

    private String mWind;
    private String mCloud;
    public static weatherData fromJson(JSONObject jsonObject)
    {

        try
        {

            weatherData weatherD=new weatherData();
            weatherD.mcity=jsonObject.getString("name");
            weatherD.mCondition=jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherD.mWeatherType=jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherD.micon=updateWeatherIcon(weatherD.mCondition);

            double wind = jsonObject.getJSONObject("wind").getDouble("speed");
            weatherD.mWind = Double.toString(wind);

            double cloud = jsonObject.getJSONObject("clouds").getDouble("all");
            weatherD.mCloud = Double.toString(cloud);

            double tempResult=jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            int roundedValue=(int)Math.rint(tempResult);
            weatherD.mTemperature=Integer.toString(roundedValue);

            int humid = jsonObject.getJSONObject("main").getInt("humidity");
            weatherD.humidity = Integer.toString(humid);

            return weatherD;
        }


        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }


    private static String updateWeatherIcon(int condition)
    {
        if(condition>=0 && condition<=299)
        {
            return "thunderstrom1";
        }
        else if(condition>=300 && condition<=499)
        {
            return "lightrain";
        }
        else if(condition>=500 && condition<=599)
        {
            return "shower";
        }
        else  if(condition>=600 && condition<=699)
        {
            return "snow2";
        }
        else if(condition>=700 && condition<=771)
        {
            return "fog";
        }

        else if(condition>=772 && condition<=799)
        {
            return "overcast";
        }
        else if(condition==800)
        {
            return "sunny";
        }
        else if(condition>=801 && condition<=804)
        {
            return "cloudy";
        }
        else  if(condition>=900 && condition<=902)
        {
            return "thunderstrom1";
        }
        if(condition==903)
        {
            return "snow1";
        }
        if(condition==904)
        {
            return "sunny";
        }
        if(condition>=905 && condition<=1000)
        {
            return "thunderstrom2";
        }

        return "dunno";


    }

    public String getmTemperature() {
        return mTemperature+"°C";
    }

    public String getMicon() {
        return micon;
    }

    public String getMcity() {
        return mcity;
    }

    public String getmWeatherType() {
        return mWeatherType;
    }

    public String getmWind() {
        return mWind;
    }

    public String getmCloud() {
        return mCloud;
    }

    public String getHumidity() {
        return humidity;
    }

}