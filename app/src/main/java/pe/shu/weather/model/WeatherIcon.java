package pe.shu.weather.model;

import android.support.annotation.DrawableRes;

import pe.shu.weather.R;

/**
 * Created by William on 4/11/2017.
 */

public class WeatherIcon {

    public static @DrawableRes int getIconFromCode(int code) {
        switch (code) {
            case 0: // tornado
                return R.drawable.storm;
            case 1: // tropical storm
                return R.drawable.storm;
            case 2: // hurricane
                return R.drawable.storm;
            case 3: // severe thunderstorms
                return R.drawable.thunderstorm;
            case 4: // thunderstorms
                return R.drawable.thunderstorm;
            case 5: // mixed rain and snow
                return R.drawable.rain_and_snow;
            case 6: // mixed rain and sleet
                return R.drawable.rain_and_snow;
            case 7: // mixed snow and sleet
                return R.drawable.rain_and_snow;
            case 8: // freezing drizzle
                return R.drawable.freezing_drizzle;
            case 9: // drizzle
                return R.drawable.drizzle;
            case 10: // freezing rain
                return R.drawable.rain;
            case 11: // showers
                return R.drawable.showers;
            case 12: // showers
                return R.drawable.showers;
            case 13: // snow flurries
                return R.drawable.flurries;
            case 14: // light snow showers
                return R.drawable.light_snow;
            case 15: // blowing snow
                return R.drawable.snow;
            case 16: // snow
                return R.drawable.snow;
            case 17: // hail
                return R.drawable.hail;
            case 18: // sleet
                return R.drawable.sleet;
            case 19: // dust
                return R.drawable.haze;
            case 20: // foggy
                return R.drawable.fog;
            case 21: // haze
                return R.drawable.haze;
            case 22: // smoky
                return R.drawable.haze;
            case 23: // blustery
                return R.drawable.fog;
            case 24: // windy
                return R.drawable.fog;
            case 25: // cold
                return R.drawable.cloudy;
            case 26: // cloudy
                return R.drawable.cloudy;
            case 27: // mostly cloudy (night)
                return R.drawable.mostly_cloudy;
            case 28: // mostly cloudy (day)
                return R.drawable.mostly_cloudy;
            case 29: // partly cloudy (night)
                return R.drawable.partly_cloudy;
            case 30: // partly cloudy (day)
                return R.drawable.partly_cloudy;
            case 31: // clear (night)
                return R.drawable.clear;
            case 32: // sunny
                return R.drawable.sunny;
            case 33: // fair (night)
                return R.drawable.mostly_sunny;
            case 34: // fair (day)
                return R.drawable.mostly_sunny;
            case 35: // mixed rain and hail
                return R.drawable.hail;
            case 36: // hot
                return R.drawable.clear;
            case 37: // isolated thunderstorms
                return R.drawable.scattered_thunderstorms;
            case 38: // scattered thunderstorms
                return R.drawable.scattered_thunderstorms;
            case 39: // scattered thunderstorms
                return R.drawable.scattered_thunderstorms;
            case 40: // scattered showers
                return R.drawable.scattered_showers;
            case 41: // heavy snow
                return R.drawable.snow;
            case 42: // scattered snow showers
                return R.drawable.light_snow;
            case 43: // heavy snow
                return R.drawable.snow;
            case 44: // partly cloudy
                return R.drawable.partly_cloudy;
            case 45: // thundershowers
                return R.drawable.thunderstorm;
            case 46: // snow showers
                return R.drawable.snow_showers;
            case 47: // isolated thundershowers
                return R.drawable.scattered_thunderstorms;
            default:
                return R.drawable.clear;
        }
    }
}
