package org.echoed.methods;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/21/12
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CalculationsEX {
    public static double perHour(final double qty, final long startTime){
        return ((qty) * 3600000D / (System.currentTimeMillis() - startTime));
    }
    public static String parseTime(long millis) {
        long time = millis / 1000;
        String seconds = Integer.toString((int) (time % 60));
        String minutes = Integer.toString((int) ((time % 3600) / 60));
        String hours = Integer.toString((int) (time / 3600));
        for (int i = 0; i < 2; i++) {
            if (seconds.length() < 2) {
                seconds = "0" + seconds;
            }
            if (minutes.length() < 2) {
                minutes = "0" + minutes;
            }
            if (hours.length() < 2) {
                hours = "0" + hours;
            }
        }
        return hours + ":" + minutes + ":" + seconds;
    }


}
