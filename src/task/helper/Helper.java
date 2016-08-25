package task.helper;

import java.security.MessageDigest;

public class Helper {
	public static String hasher(String pass) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(pass.getBytes());
		byte[] hashMd5 = md.digest();
		String password = stringHexa(hashMd5);
		return password;
	}

	private static String stringHexa(byte[] bytes) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int highPart = ((bytes[i] >> 4) & 0xf) << 4;
			int lowPart = bytes[i] & 0xf;
			if (highPart == 0)
				s.append('0');
			s.append(Integer.toHexString(highPart | lowPart));
		}
		return s.toString();
	}
}
