package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import plugs.Shorthand;

public class PasswordManager implements Shorthand {
	
	public PasswordManager() throws IOException {
		
		String s;
		String u;
		BufferedReader br;
		br = new BufferedReader(new FileReader("pass.txt"));
		if ((s = br.readLine()) != null)
			password = s.toCharArray();
		br.close();
		br = new BufferedReader(new FileReader("user.txt"));
		if ((u = br.readLine()) != null)
			username = u;
		br.close();
		
	}
	
	private static String username = null;
	private static char[] password = null;
	private static boolean loggedIn = false;
	
	public void say(String s) {
		
		System.out.println(s);
		
	}
	
	private void signUp() {
		
		Console c = System.console();
		say("Please input your new username.");
		username = c.readLine();
		say("Please input your new password.");
		password = c.readPassword();
		if (username == null || password == null) {
			say("System error. Please restart program.");
			System.exit(0);
		} else {
			try {
				logWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void login() {
		
		Console c = System.console();
		say("Please input your username.");
		while (true) {
			if (c.readLine().equals(username) != true) {
				say("Username is not correct. Please try again.");
			} else {
				break;
			}
		}
		say("Please input your password.");
		while (true) {
			if (Arrays.equals(c.readPassword(), password) != true) {
				say("Password is not correct. Please try again.");
			} else {
				break;
			}
		}
		say("Logged in!");
		loggedIn = true;
		
	}
	
	private boolean logStatus() {
		
		return loggedIn;
		
	}
	
	private static void logWriter() throws IOException {
		
		BufferedWriter bw;
		bw = new BufferedWriter(new FileWriter("pass.txt"));
		bw.write(password);
		bw.close();
		bw = new BufferedWriter(new FileWriter("user.txt"));
		bw.write(username);
		bw.close();
		
	}

}