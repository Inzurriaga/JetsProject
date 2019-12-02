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
		List<Jet> jets = airfield.getJets();
		boolean quit = false;
		addInitialJets();
		do {
			displayUserMenu();
			int choice = 0;
			try {
				System.out.print("User Input: ");
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("invalid input please pick a number");
				input.nextLine();
			}
			switch (choice) {
				case 1:
					listFleet(jets);
					break;
				case 2:
					flyAllJets(jets);
					break;
				case 3:
					viewFastestJets(jets);
					break;
				case 4:
					viewJetswithLongestRange(jets);
					break;
				case 5:
					loadAllCargoJets(jets);
					break;
				case 6:
					dogFight(jets);
					break;
				case 7:
					addAJetToFleet();
					break;
				case 8:
					removeAJetFromFleet(jets);
					break;
				case 9:
					quit = true;
					System.out.println("Thank You!!!!!");
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

	private void listFleet(List<Jet> jets) {
		System.out.println("\n----------------------------- List of Jets -----------------------------");
		for (Jet jet : jets) {
			System.out.println(jet);
		}
		System.out.println("------------------------------------------------------------------------");
	}

	private void flyAllJets(List<Jet> jets) {
		System.out.println("\n-------------------------------- Jets flying --------------------------------");
		for (Jet jet : jets) {
			jet.fly();
		}
		System.out.println("-----------------------------------------------------------------------------");
	}

	private void viewFastestJets(List<Jet> jets) {
		double speed = Double.MIN_VALUE;
		for (Jet jet : jets) {
			if (jet.getSpeed() > speed) {
				speed = jet.getSpeed();
			}
		}
		System.out.println("\n---------------------- fastest Jet(s) ----------------------");
		for (Jet jet : jets) {
			if (jet.getSpeed() == speed) {
				System.out.println(jet.getModel() + " with the speed of " + jet.getSpeed() + " MPH");
			}
		}
		System.out.println("------------------------------------------------------------\n");
	}

	private void viewJetswithLongestRange(List<Jet> jets) {
		double range = Integer.MIN_VALUE;
		for (Jet jet : jets) {
			if (jet.getRange() > range) {
				range = jet.getRange();
			}
		}
		System.out.println("\n---------------- Jet(s) with longest range ----------------");
		for (Jet jet : jets) {
			if (jet.getRange() == range) {
				System.out.println(jet.getModel() + " with the range of " + jet.getRange() + " miles");
			}
		}
		System.out.println("-----------------------------------------------------------\n");
	}

	private void loadAllCargoJets(List<Jet> jets) {
		System.out.println("\n---------------------- Loading Cargo Jets ----------------------");
		for (Jet jet : jets) {
			if (jet.getClass().getSimpleName().equals("CargoPlane")) {
				CargoPlane cargo = (CargoPlane) jet;
				cargo.loadCargo();
			}
		}
		System.out.println("----------------------------------------------------------------\n");
	}

	private void dogFight(List<Jet> jets) {
		System.out.println("\n-------------------- Jets Entering Combet  --------------------");
		for (Jet jet : jets) {
			if (jet.getClass().getSimpleName().equals("FighterJet")) {
				FighterJet fighter = (FighterJet) jet;
				fighter.fight();
			}
		}
		System.out.println("---------------------------------------------------------------\n");
	}

	private void addAJetToFleet() {
		System.out.println("model: ");
		String model = input.next();
		System.out.println("speed(mph): ");
		double speed = input.nextDouble();
		System.out.println("range(miles): ");
		int range = input.nextInt();
		System.out.println("price: ");
		long price = input.nextLong();
		String planeType = new StringBuilder(model).substring(0, 2).toString().toUpperCase();
		if (planeType.equals("C-")) {
			airfield.addJet(new CargoPlane(model, speed, range, price));
		} else if (planeType.equals("F-") || planeType.equals("A-")) {
			airfield.addJet(new FighterJet(model, speed, range, price));
		} else {
			airfield.addJet(new JetImpl(model, speed, range, price));
		}
	}

	private void removeAJetFromFleet(List<Jet> jets) {
		int userChoice = 0;
		System.out.println("\nPick plane to delete\n");
		for (int i = 0; i < jets.size(); i++) {
			System.out.println((i + 1) + ") " + jets.get(i).getModel());
		}
		while (true) {
			System.out.print("User Input: ");
			try {
				userChoice = input.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("invalid input please pick a number");
				input.nextLine();
			}
			if (userChoice > 0 && userChoice <= jets.size()) {
				jets.remove(userChoice - 1);
				break;
			} else {
				System.out.println("Please pick a number from the list");
			}
		}

	}

}
