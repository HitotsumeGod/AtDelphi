package manager;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class PasswordManager {
	
	private static String username = null;
	private static char[] password = null;
	private static boolean loggedIn = false;
	
	public static void say(String s) {
		
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
	
	private void logStatus() {
		
		if (loggedIn)
			say("Logged in -----> TRUE");
		
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		PasswordManager pm = new PasswordManager();
		pm.signUp();
		say("Would you like to log in? (y/n)");
		if (s.nextLine().charAt(0) != 'y')
			System.exit(0);
		pm.login();
		if (s.nextLine().equals("check"))
			pm.logStatus();
		s.close();
		
	}

}