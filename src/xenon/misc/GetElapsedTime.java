/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xenon.misc;

/**
 *
 * @author hanna
 */
public class GetElapsedTime {
    
    public static String get(long timeToConvert) {
         // UPTIME
        long different = System.currentTimeMillis() - timeToConvert;
                          
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;
        long weeksInMilli = daysInMilli * 7;
                          
        long elapsedWeeks = different / weeksInMilli;
        different = different % weeksInMilli;
                          
         long elapsedDays = different / daysInMilli;
         different = different % daysInMilli;

         long elapsedHours = different / hoursInMilli;
         different = different % hoursInMilli;

         long elapsedMinutes = different / minutesInMilli;
         different = different % minutesInMilli;

         long elapsedSeconds = different / secondsInMilli;
         
         return String.format("§d%d w§b - §d%d d§b - §d%d h§b - §d%d m§b - §d%d s", elapsedWeeks,elapsedDays,elapsedHours,elapsedMinutes,elapsedSeconds);

    }
    
}
