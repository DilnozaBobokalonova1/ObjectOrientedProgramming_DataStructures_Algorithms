package lab06;

import java.util.Comparator;

	public class OrderStrings implements Comparator<String> {
		public int compare(String firstInput, String secondInput) {
			String thesorted1 = AnagramUtil.sort(firstInput);
			String thesorted2 = AnagramUtil.sort(secondInput);
			return thesorted1.compareTo(thesorted2);
		}
	}