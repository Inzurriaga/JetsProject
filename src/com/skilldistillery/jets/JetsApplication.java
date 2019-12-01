package com.skilldistillery.jets;

import java.util.Scanner;

public class JetsApplication {
	private AirField airfield;
	private Scanner input;
	
	public JetsApplication() {
		this.airfield = new AirField();
		this.input = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		JetsApplication jetApp = new JetsApplication();
		jetApp.launch();
	}
	
	private void launch() {
		
	}
	
	private void displayUserMenu() {
		
	}

}
