package lib;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class Convert {

	public static String stringToNumber(String s) {
		return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(s));
	}

	public static String numberToString(String number) {
		String s = "";
		StringTokenizer st = new StringTokenizer(number, ",");
		while (st.hasMoreTokens()) {
			s += st.nextToken();
		}
		return s;
	}

	public static void main(String[] args) {
		System.out.println(stringToNumber("9500000"));
	}

}
