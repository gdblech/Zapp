import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
import java.util.HashMap;

@XmlRootElement(name = "Availability")
public class Availability {
    String[][] availability;

    public Availability(){
        availability = new String[7][2];
    }

    public String[][] getAvailability() {
        return availability;
    }

    public void setAvailability(String[][] availability) {
        this.availability = availability;
    }

    public String[][] formatSchedule(boolean[] days, String[] hours1, String[] hours2){
        String[] dayArray = this.formatDays(days);
        String[] startHours = this.formatHours(days, hours1);
        String[] endHours = this.formatHours(days, hours2);
        String[][] formatedArray = new String[dayArray.length][2];

        for (int i = 0; i < dayArray.length; i++) {
            formatedArray[i][0] = dayArray[i] + " " + startHours[i];
            formatedArray[i][1] = dayArray[i] + " " + endHours[i];
        }

        for (int i = 0; i < formatedArray.length; i++) {
            for (int j = 0; j < formatedArray[0].length; j++) {
                System.out.println(formatedArray[i][j]);
            }
        }

        return formatedArray;
    }

    public String[] formatHours(boolean[] days, String[] hours){
        int a = 0;
        for (int i = 0; i < days.length; i++) {
            if(days[i] == true){
                a++;
            }
        }
        String[] array = new String[a];
        a = 0;
        for (int i = 0; i < days.length; i++) {
            if(days[i] == true){
                //do stuff here
                array[a] = hours[i];
                a++;
            }
        }
        return array;
    }

    public String[] formatDays(boolean[] days){
        int a = 0;
        for (int i = 0; i < days.length; i++) {
            if(days[i] == true){
                a++;
            }
        }
        String[] array = new String[a];
        a = 0;
        for (int i = 0; i < days.length; i++) {
            if(days[i] == true){
                //do stuff here
                array[a] = this.intToDay(i);
                a++;
            }
        }
        /*Prints out array to ensure it works
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        */
        return array;
    }

    public String intToDay(int number){
        String day = "";
        switch (number){
            case 0: day = "Sun";
            break;
            case 1: day = "Mon";
            break;
            case 2: day = "Tue";
            break;
            case 3: day = "Wed";
            break;
            case 4: day = "Thu";
            break;
            case 5: day = "Fri";
            break;
            case 6: day = "Sat";
            break;
        }
        return day;
    }

    // Convert a given 3D array of strings to a 3D array of Unix timestamps
    public long[][] toTimestamps(String[][] input) {
        // Mapping of days to their int values
        HashMap<String, Integer> daysLookup = new HashMap<>();
        daysLookup.put("Sun", 0);
        daysLookup.put("Mon", 1);
        daysLookup.put("Tue", 2);
        daysLookup.put("Wed", 3);
        daysLookup.put("Thu", 4);
        daysLookup.put("Fri", 5);
        daysLookup.put("Sat", 6);

        long[][] timestamps = new long[input.length][2];
        for (int i = 0; i < input.length; i++) {
            // Convert the start time
            Calendar startTime = Calendar.getInstance();

            // Split the element so we can pick out the weekday and the hour
            String[] startTimeSplit = input[i][0].split(" ");

            // Determines if the given day of the week has already been passed
            // For example, if we run this on Tuesday but want a Monday then it'll get next week's Monday -- neat!
            int diff = daysLookup.get(startTimeSplit[0]) - startTime.get(Calendar.DAY_OF_WEEK);
            if (diff < 0) {
                diff += 7;
            }

            // Goes to the next day by adding the diff
            startTime.add(Calendar.DAY_OF_MONTH, diff);
            // Set the hour of the day
            startTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startTimeSplit[1]));
            // Minute and second get set to 0 for ease of use
            startTime.set(Calendar.MINUTE, 0);
            startTime.set(Calendar.SECOND, 0);

            // Generate a Unix timestamp adjusted for our timezone -- UTC is 18000 ahead
            long startTimestamp = (startTime.getTimeInMillis() / 1000) - 18000;

            // Add to the table
            timestamps[i][0] = startTimestamp;

            // Same thing for the stop time
            String[] stopTimeSplit = input[i][1].split(" ");
            Calendar stopTime = Calendar.getInstance();
            stopTime.add(Calendar.DAY_OF_MONTH, diff);
            stopTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(stopTimeSplit[1]));
            stopTime.set(Calendar.MINUTE, 0);
            stopTime.set(Calendar.SECOND, 0);

            long stopTimestamp = (stopTime.getTimeInMillis() / 1000) - 18000;

            timestamps[i][1] = stopTimestamp;
        }

        return timestamps;
    }
}
