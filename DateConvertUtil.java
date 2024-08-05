public class DateConvertUtil {
    private static final String FORMAT_TYPE_1 = "DD/MM/YYYY to YYYY/MM/DD";
    private static final String FORMAT_TYPE_2 = "YYYY/MM/DD to DD/MM/YYYY";

    // Converts Data to either DD/MM/YYYY or YYYY/MM/DD due to MYSQL only suppport YYYY/MM/DD.
    // Meaning You have to convert to and back.
    public static String convertDate(String formatType, String date) {
        // Split the input date string into parts
        String[] parts = date.split("/");
        
        // Ensure that the input date string has the correct format
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format. Please use the correct format.");
        }
        
        // Extract day, month, and year from the input date string
        String part1 = parts[0];
        String part2 = parts[1];
        String part3 = parts[2];
        
        String convertedDate;

        if (formatType.equals("DD/MM/YYYY to YYYY/MM/DD")) {
            // Convert from DD/MM/YYYY to YYYY/MM/DD
            convertedDate = part3 + "/" + part2 + "/" + part1;
        } else if (formatType.equals("YYYY/MM/DD to DD/MM/YYYY")) {
            // Convert from YYYY/MM/DD to DD/MM/YYYY
            convertedDate = part3 + "/" + part2 + "/" + part1;
        } else {
            throw new IllegalArgumentException("Invalid format type. Please use 'DD/MM/YYYY to YYYY/MM/DD' or 'YYYY/MM/DD to DD/MM/YYYY'.");
        }
        
        return convertedDate;
    }

    public static String getFormatType1() {
        return FORMAT_TYPE_1;
    }

    public static String getFormatType2() {
        return FORMAT_TYPE_2;
    }
}
