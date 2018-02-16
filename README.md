
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCase {

	private static final String MESSAGE = "2018/12/2 10:00:00:345 8=FIX.4.4,9=126,35=A,49=theBroker.12345,56=CSERVER,34=1,52=20170117- 08:03:04,";
	private static final String FIELD_SEPARATOR = "|";
	private static final String VALUE_SEPARATOR = "=";
	private static final int interval = 5;

	public static void main(String[] args) throws ParseException {
		int index = MESSAGE.indexOf(" ");
		String date = MESSAGE.substring(0, index).trim();
		System.out.println("Date=" + date);
		String time = MESSAGE.substring(index + 1, index + 9).trim();
		System.out.println("Time=" + time);

		String fixMessage = MESSAGE.substring(MESSAGE.indexOf("8=FIX.4.4"));
		String[] parts = fixMessage.split(",");
		for (String part : parts) {
			final String[] subparts = part.split(VALUE_SEPARATOR);
			final int fieldId = Integer.parseInt(subparts[0]);
			if (fieldId == 56) {
				System.out.println("Value=" + subparts[1]);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date dtime = sdf.parse(time);
		dateExperiment(dtime);
	}

	private static boolean dateExperiment(Date dtime) throws ParseException {
		String startTime = "10:00:00";
		String endTime = "10:00:30";
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d1 = sdf.parse(startTime);
		Date d2 = sdf.parse(endTime);
		
		while (d1.before(d2) || d1.equals(d2) ) {
			System.out.println("Timevalue=" + d1.toString());
			d1.setSeconds(d1.getSeconds() + interval);
		}

		return true;
	}
}
