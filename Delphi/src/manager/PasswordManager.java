package manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class PasswordManager {
	
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
	
	private void logStatus() {
		
		if (loggedIn)
			say("Logged in -----> TRUE");
		
	}
	
	private static void logWriter() throws IOException {
		
		BufferedWriter bw = null;
		bw = new BufferedWriter(new FileWriter("pass.txt"));
		bw.write(password);
		bw.close();
		bw = new BufferedWriter(new FileWriter("user.txt"));
		bw.write(username);
		bw.close();
		
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		PasswordManager pm = null;
		try {
			pm = new PasswordManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
		say("Do you already have an account? (y/n)");
		while (true) {
		if (s.nextLine().charAt(0) == 'n') {
			pm.signUp();
			break;
		}
		if (s.nextLine().charAt(0) == 'y')
			break;
		}
		say("Would you like to log in? (y/n)");
		if (s.nextLine().charAt(0) != 'y')
			System.exit(0);
		pm.login();
		if (s.nextLine().equals("check"))
			pm.logStatus();
		s.close();
		
	}

}