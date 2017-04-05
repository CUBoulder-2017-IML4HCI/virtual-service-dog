package com.vsd.virtualservicedog;

/**
 * Created by yue on 4/5/17.
 */

public interface Classifier {
    public class HeartRateData{
        private final Integer heart_rate;
        private final Float time_stamp; //TODO: use date?

        public HeartRateData(final Integer heart_rate, final Float time_stamp){
            this.heart_rate = heart_rate;
            this.time_stamp = time_stamp;
        }

        public int getHeartRate(){
            return heart_rate;
        }

        public float getTime(){
            return time_stamp;
        }

        @Override
        public String toString() {
            String resultString = "Your heart rate is: ";
            if(heart_rate>75) resultString+=String.format("(%d) ", heart_rate);

            return resultString.trim();
        }
    }
}
