package pe.shu.weather.rest;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pe.shu.weather.model.Forecast;
import pe.shu.weather.model.forecast.Astronomy;
import pe.shu.weather.model.forecast.Atmosphere;
import pe.shu.weather.model.forecast.Condition;
import pe.shu.weather.model.forecast.ForecastDay;
import pe.shu.weather.model.forecast.Location;
import pe.shu.weather.model.forecast.Units;
import pe.shu.weather.model.forecast.Wind;

/**
 * Created by William on 4/10/2017.
 */

public class ResponseAdapter extends TypeAdapter<Forecast> {

    @Override
    public void write(JsonWriter out, Forecast value) throws IOException {
        //TODO: Implement if needed
    }

    @Override
    public Forecast read(JsonReader in) throws IOException {
        in.beginObject();

        while (in.hasNext()) {
            String name = in.nextName();

            if (name.equals("query")) {
                return readQueryObject(in);
            } else {
                in.skipValue();
            }
        }

        return null;
    }

    private Forecast readQueryObject(JsonReader in) throws IOException {
        Forecast forecast = null;

        in.beginObject();

        while (in.hasNext()) {
            String name = in.nextName();

            if (name.equals("results")) {
                forecast = readResultsObject(in);
            } else {
                in.skipValue();
            }
        }

        in.endObject();

        return forecast;
    }

    private Forecast readResultsObject(JsonReader in) throws IOException {
        Forecast forecast = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("channel")) {
                    forecast = readChannelObject(in);
                } else {
                    in.skipValue();
                }
            }

            in.endObject();
        }

        return forecast;
    }

    private Forecast readChannelObject(JsonReader in) throws IOException {
        Forecast forecast = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            forecast = new Forecast();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("item")) {
                    readItemObject(in, forecast);
                } else if (name.equals("units")) {
                    forecast.setUnits(readUnits(in));
                } else if (name.equals("wind")) {
                    forecast.setWind(readWind(in));
                } else if (name.equals("atmosphere")) {
                    forecast.setAtmosphere(readAtmosphere(in));
                } else if (name.equals("astronomy")) {
                    forecast.setAstronomy(readAstronomy(in));
                } else if (name.equals("location")) {
                    forecast.setLocation(readLocation(in));
                } else {
                    in.skipValue();
                }
            }

            in.endObject();
        }

        return forecast;
    }

    private Location readLocation(JsonReader in) throws IOException {
        Location location = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            location = new Location();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("city")) {
                    location.setCity(in.nextString());
                } else if (name.equals("region")) {
                    location.setRegion(in.nextString());
                } else {
                    in.skipValue();
                }
            }

            in.endObject();
        }

        return location;
    }

    private Units readUnits(JsonReader in) throws IOException {
        Units units = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            units = new Units();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("distance")) {
                    units.setDistanceUnits(in.nextString());
                } else if (name.equals("pressure")) {
                    units.setPressureUnits(in.nextString());
                } else if (name.equals("speed")) {
                    units.setSpeedUnits(in.nextString());
                } else if (name.equals("temperature")) {
                    units.setTemperatureUnits(in.nextString());
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
        }

        return units;
    }

    private Wind readWind(JsonReader in) throws IOException {
        Wind wind = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            wind = new Wind();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("chill")) {
                    wind.setChill(Integer.parseInt(in.nextString()));
                } else if (name.equals("direction")) {
                    wind.setDirection(Integer.parseInt(in.nextString()));
                } else if (name.equals("speed")) {
                    wind.setSpeed(Integer.parseInt(in.nextString()));
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
        }

        return wind;
    }

    private Atmosphere readAtmosphere(JsonReader in) throws IOException {
        Atmosphere atmosphere = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            atmosphere = new Atmosphere();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("humidity")) {
                    atmosphere.setHumidity(Integer.parseInt(in.nextString()));
                } else if (name.equals("pressure")) {
                    atmosphere.setPressure(Double.parseDouble(in.nextString()));
                } else if (name.equals("rising")) {
                    atmosphere.setRising(Integer.parseInt(in.nextString()));
                } else if (name.equals("visibility")) {
                    atmosphere.setVisibility(Double.parseDouble(in.nextString()));
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
        }

        return atmosphere;
    }

    private Astronomy readAstronomy(JsonReader in) throws IOException {
        Astronomy astronomy = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            astronomy = new Astronomy();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("sunrise")) {
                    String sunriseString = in.nextString().replace("am","AM").replace("pm","PM");
                    DateTime sunrise = DateTimeFormat.forPattern("H:m a").parseDateTime(sunriseString);
                    astronomy.setSunrise(sunrise);
                } else if (name.equals("sunset")) {
                    String sunsetString = in.nextString().replace("am","AM").replace("pm","PM");
                    DateTime sunset = DateTimeFormat.forPattern("h:m a").parseDateTime(sunsetString);
                    astronomy.setSunset(sunset);
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
        }

        return astronomy;
    }

    private void readItemObject(JsonReader in, Forecast forecast) throws IOException {
        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("forecast")) {
                    forecast.setForecast(readForecast(in));
                } else if (name.equals("condition")) {
                    forecast.setCondition(readCondition(in));
                } else {
                    in.skipValue();
                }
            }

            in.endObject();
        }
    }

    private Condition readCondition(JsonReader in) throws IOException {
        Condition condition = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginObject();

            condition = new Condition();

            while (in.hasNext()) {
                String name = in.nextName();

                if (name.equals("code")) {
                    condition.setCode(Integer.parseInt(in.nextString()));
                } else if (name.equals("date")) {
                    // Remove timezone to fix some date parsing errors (hacky I know...)
                    Pattern pattern = Pattern.compile("((.*)(AM|PM))");
                    Matcher matcher = pattern.matcher(in.nextString());
                    if (matcher.find()) {
                        String dateString = matcher.group(1);
                        DateTime date = DateTimeFormat.forPattern("EEE, dd MMM yyyy hh:mm a").parseDateTime(dateString);
                        condition.setDate(date);
                    }
                } else if (name.equals("temp")) {
                    condition.setTemp(Integer.parseInt(in.nextString()));
                } else if (name.equals("text")) {
                    condition.setText(in.nextString());
                } else {
                    in.skipValue();
                }
            }
            in.endObject();
        }

        return condition;
    }

    private ArrayList<ForecastDay> readForecast(JsonReader in) throws IOException {
        ArrayList<ForecastDay> forecast = null;

        if (in.peek() != JsonToken.NULL) {
            in.beginArray();

            forecast = new ArrayList<>();

            while (in.hasNext()) {
                forecast.add(readForecastDay(in));
            }

            in.endArray();
        }

        return forecast;
    }

    private ForecastDay readForecastDay(JsonReader in) throws IOException {
        ForecastDay forecastDay = new ForecastDay();

        in.beginObject();

        while (in.hasNext()) {
            String name = in.nextName();

            if (name.equals("code")) {
                forecastDay.setCode(Integer.parseInt(in.nextString()));
            } else if (name.equals("date")) {
                DateTime date = DateTimeFormat.forPattern("dd MMM yyyy").parseDateTime(in.nextString());
                forecastDay.setDate(date);
            } else if (name.equals("day")) {
                in.skipValue();
            } else if (name.equals("high")) {
                forecastDay.setHigh(Integer.parseInt(in.nextString()));
            } else if (name.equals("low")) {
                forecastDay.setLow(Integer.parseInt(in.nextString()));
            } else if (name.equals("text")) {
                forecastDay.setCondition(in.nextString());
            } else {
                in.skipValue();
            }
        }

        in.endObject();

        return forecastDay;
    }
}
