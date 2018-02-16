import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCase {

	private static final String MESSAGE = "2018/12/2 10:00:00:345 8=FIX.4.4,9=126,35=A,49=theBroker.12345,56=CSERVER,34=1,52=20170117- 08:03:04,";
	private static final String FIELD_SEPARATOR = "|";
	private static final String VALUE_SEPARATOR = "=";
	private static final int interval = 5;
	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	
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

	
		String t1 ="10:00:21";
		Date dtime = sdf.parse(t1);
		Date window = dateExperiment(dtime);
		if(window == null) {
			System.out.println("Out of Range=");
		}else {
			System.out.println("Time="+dtime.toString());
			System.out.println("window="+sdf.format(window));
		}
		
		
		String t2 ="10:10:21";
		dtime = sdf.parse(t2);
		window = dateExperiment(dtime);
		if(window == null) {
			System.out.println("Out of Range=");
		}else {
			System.out.println("Time="+dtime.toString());
			System.out.println("window="+sdf.format(window));
		}
		
	}

	private static Date dateExperiment(Date dtime) throws ParseException {
		
		Date d1 = sdf.parse("10:00:00");
		Date d2 = sdf.parse("10:00:30");
		
		
		while (d1.before(d2) || d1.equals(d2) ) {
			
			if(dtime.before(d1)) {
				return d1;
			}
			d1.setSeconds(d1.getSeconds() + interval);
		}
		return null;
	}
}
