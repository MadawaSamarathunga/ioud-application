package com.nextapp.dto;

public class Weather {
    public static class WeatherInner{
        private double temp_c;

        public double getTemp_c() {
            return temp_c;
        }
        public void setTemp_c(double temp_c) {
            this.temp_c = temp_c;
        }

        @Override
        public String toString() {
            return "WeatherInner{" +
                    "tempC=" + temp_c +
                    '}';
        }
    }

    private WeatherInner current;

    public WeatherInner getCurrent() {
        return current;
    }
    public void setCurrent(WeatherInner current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "current=" + current +
                '}';
    }
}
