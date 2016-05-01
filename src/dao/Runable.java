package dao;

import java.util.Date;
import java.util.StringTokenizer;

public class Runable {

	public static void main(String[] args) {
		System.out.println(FeeDAO.getByDate("20-3-2016", "20-4-2016") == null);
	}
	
	private String getDate(String date){
		StringTokenizer st = new StringTokenizer(date, " ");
		int index = st.countTokens();
		
		String day = "";
		String month = "";
		String year = "";
		if(index == 2){
			day = st.nextToken();
			month = st.nextToken();
			year = new Date().getYear() + 1900 + "";
		} else if (index == 3){
			day = st.nextToken();
			month = st.nextToken();
			year = st.nextToken();
		}
		String formatedDate = day + "-" + month + "-" + year;
		return formatedDate;
	}

}
