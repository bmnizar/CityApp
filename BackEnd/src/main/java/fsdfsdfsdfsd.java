import java.util.Set;
import java.util.TreeSet;

/**
 ** @BMN 2021
 **
 **/
public class fsdfsdfsdfsd {

	public static void main(String[] args) {
		String zz = m("z<*zj");// j<*zz
		System.out.println(zz);
	}d e;

	private static String m(String S) {
		String[] reserved = new String[S.length()];
		int k = S.length() - 1;

		for (int i = 0; i < S.length(); i++) {
			Character charAt = S.charAt(i);
			boolean letter = Character.isLetter(charAt);
			if (!letter) {

				reserved[i] = charAt.toString();

			}

		}
		int h = 0;
		for (int m = S.length() - 1; m >= 0; m--) {
			Character charAt = S.charAt(m);
			 
			boolean letter = Character.isLetter(charAt);
			if (letter) {
				if (reserved[h] == null) {
					reserved[h] = charAt.toString();
					h++;
				} else {
					for (int j = h + 1; j < reserved.length; j++) {
						if (reserved[j] != null) {
							h++;
						} else {
							reserved[j] = charAt.toString();
							break;
						}
					}
				}

			}

		}
		String ret = "";
		for (int i = 0; i < reserved.length; i++) {
			ret = ret + reserved[i];
		}
		return ret;
	}

}
