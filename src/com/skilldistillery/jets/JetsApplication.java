package com.skilldistillery.jets;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		boolean quit = false;
		addInitialJets();
		do {
			displayUserMenu();
			int choice = 0;
			try {
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("invalid input please pick a number");
				input.nextLine();
			}
			switch (choice) {
				case 1:
					listFleet();
					break;
				case 2:
					flyAllJets();
					break;
				case 3:
					viewFastestJets();
					break;
				case 4:
					viewJetswithLongestRange();
					break;
				case 5:
					loadAllCargoJets();
					break;
				case 6:
					dogFight();
					break;
				case 7:
					addAJetToFleet();
					break;
				case 8:
					removeAJetFromFleet();
					break;
				case 9:
					quit = true;
					System.out.println("thank you");
					break;
				default:
					System.out.println("invalid input please pick one of the options");
			}
		} while (!quit);
		this.input.close();
	}

	private void displayUserMenu() {
		System.out.println(
				"1)List fleet\n2)Fly all jets\n3)View fastest jet\n4)View jet with longest range\n5)Load all Cargo Jets\n6)Dogfight!\n7)Add a jet to Fleet\n8)Remove a jet from Fleet\n9)Quit");
	}

	private void addInitialJets() {
		try (BufferedReader br = new BufferedReader(new FileReader("jets.txt"))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] info = line.split("\t");
				if (line.charAt(0) == 'C') {
					airfield.addJet(new CargoPlane(info[0], Double.parseDouble(info[1]), Integer.parseInt(info[2]),
							Long.parseLong(info[3])));
				} else {
					airfield.addJet(new FighterJet(info[0], Double.parseDouble(info[1]), Integer.parseInt(info[2]),
							Long.parseLong(info[3])));
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	private void listFleet() {
		List<Jet> jets = airfield.getJets();
		System.out.println("\n------------------------------- List of Jets -------------------------------");
		for(Jet jet : jets) {
			System.out.println(jet);
		}
		System.out.println("----------------------------------------------------------------------------");
	}
	
	private void flyAllJets() {
		List<Jet> jets = airfield.getJets();
		System.out.println("\n-------------------------------- Jets flying --------------------------------");
		for(Jet jet : jets) {
			jet.fly();
		}
		System.out.println("-----------------------------------------------------------------------------");
	}
	
	private void viewFastestJets() {
		List<Jet> jets = airfield.getJets();
		double speed = Double.MIN_VALUE;
		for(Jet jet : jets) {
			if(jet.getSpeed() > speed) {
				speed = jet.getSpeed();
			}
		}
		System.out.println("\n---------------------- fastest Jet(s) ----------------------");
		for(Jet jet : jets) {
			if(jet.getSpeed() == speed) {
				System.out.println(jet.getModel() + " with the speed of " + jet.getSpeed() + " MPH");
			}
		}
		System.out.println("------------------------------------------------------------\n");
	}
	
	private void viewJetswithLongestRange() {
		
	}
	
	private void loadAllCargoJets() {
		
	}
	
	private void dogFight() {
		
	}
	
	private void addAJetToFleet() {
		
	}
	
	private void removeAJetFromFleet() {
		
	}

}
