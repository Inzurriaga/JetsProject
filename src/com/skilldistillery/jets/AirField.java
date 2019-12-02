package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	private List<Jet> jets;
	
	public AirField() {
		this.jets = new ArrayList<Jet>();
	}
	
	public void addJet(Jet jet) {
		this.jets.add(jet);
	}

	public List<Jet> getJets() {
		return jets;
	}

	public void setJets(List<Jet> jets) {
		this.jets = jets;
	}

}
