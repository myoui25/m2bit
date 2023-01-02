package ss.week6.dictionaryattack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class DictionaryAttack {
	private Map<String, String> passwordMap = new HashMap<String, String>();
	private Map<String, String> hashDictionary = new HashMap<String, String>();
	private static final String PATH = "./src/ss/week6/test/";

	/**
	 * Reads a password file. Each line of the password file has the form:
	 * username: encodedpassword
	 * 
	 * After calling this method, the passwordMap class variable should be
	 * filled with the content of the file. The key for the map should be
	 * the username, and the password hash should be the content.
	 * @param filename the full path to the file
	 * @throws IOException 
	 */
	public void readPasswords(String filename) throws IOException {

			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split(": ");
				passwordMap.put(split[0], split[1]);
			}
			reader.close();

	}
	

	/**
	 * Given a password, return the MD5 hash of a password. The resulting
	 * hash (or sometimes called digest) should be hex-encoded in a String.
	 * @param password String
	 * @return MD5 hash of given password
	 * @throws NoSuchAlgorithmException 
	 */
	public String getPasswordHash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passBytes = password.getBytes();
			byte[] passMD = md.digest(passBytes);
			String passHex = Hex.encodeHexString(passMD);
			
			return passHex;
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * Checks the password for the user the password list. If the user
	 * does not exist, returns false.
	 * @param user
	 * @param password
	 * @return whether the password for that user was correct.
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean checkPassword(String user, String password) {
		String userValue = passwordMap.get(user);
        String passHash = getPasswordHash(password);
        if (userValue.equals(passHash)) {
        	return true;
        } else {
        	return false;
        }	
	}

	/**‰
	 * Reads a dictionary from file (one line per word) and use it to add®®
	 * entries to a dictionary that maps password hashes (hex-encoded) to
     * the original password.
	 * @param filename filename of the dictionary (full path)
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
    public void addToHashDictionary(String filename) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line;
		while ((line = reader.readLine()) != null) {
		String hash = getPasswordHash(line);
			hashDictionary.put(line, hash);
		}
	    reader.close();
		
    }
	/**
	 * Do the dictionary attack.
	 * @throws IOException 
	 */
	public void doDictionaryAttack() {
		try {
			this.readPasswords(PATH + "LeakedPasswords.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			this.addToHashDictionary(PATH + "easyPwr.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Map.Entry<String,String> leaked : passwordMap.entrySet()){
			for (Map.Entry<String,String> dictionary : hashDictionary.entrySet()){
				if(leaked.getValue().equals(dictionary.getValue())){
					System.out.println(leaked.getKey() + ": " + dictionary.getKey());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		DictionaryAttack da = new DictionaryAttack();
		da.doDictionaryAttack();
	}

}
