package skibidi;

public class TimeUtil {
    public static String convertStandardToMilitary(String standardTime) {
        String[] parts = standardTime.split(" ");
        String time = parts[0];
        String period = parts[1];
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        if (period.equalsIgnoreCase("PM") && hour != 12) {
            hour += 12;
        } else if (period.equalsIgnoreCase("AM") && hour == 12) {
            hour = 0;
        }

        return String.format("%02d%02d", hour, minute);

    }
}
