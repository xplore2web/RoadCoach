package com.alcord.config;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

	private static final String seperator = "$";
	private static final String prefix = "pbkdf2_sha256";
	private static final int iterations = 20000;
	private static final int keyLength = 64 * 4;

	private String getSalt() throws NoSuchAlgorithmException {
		return RandomStringUtils.randomAlphanumeric(12);
	}

	@Override
	public String encode(CharSequence rawPassword) {
		try {

			char[] chars = rawPassword.toString().toCharArray();
			String originalSalt = getSalt();
			byte[] salt = originalSalt.getBytes();
			PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, keyLength);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] hash = skf.generateSecret(spec).getEncoded();
			String hashAsB64 = Base64.getEncoder().encodeToString(hash);

			StringBuilder builder = new StringBuilder().append(prefix).append(seperator).append(iterations)
					.append(seperator).append(originalSalt).append(seperator).append(hashAsB64);
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			String[] parts = encodedPassword.split(Pattern.quote(seperator));
			// 0 is the algorithm
			int iterations = Integer.parseInt(parts[1]);
			byte[] salt = parts[2].getBytes();
			String hash = parts[3];

			PBEKeySpec spec = new PBEKeySpec(rawPassword.toString().toCharArray(), salt, iterations, keyLength);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

			byte[] testHash = skf.generateSecret(spec).getEncoded();
			String asB64 = Base64.getEncoder().encodeToString(testHash);
			if (hash.equals(asB64))
				return true;
			else
				return false;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
