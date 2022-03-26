package thesis.model;

import java.util.ArrayList;

public class Candidate extends Citizen{
	//instance variables
	private int mVotes; //number of votes the candidate has received
	
	//constructors
	public Candidate() {
		super();
		setVotes(0);
	}
	public Candidate(PolEntity pe) {
		super(pe);
		if (pe instanceof Candidate) {
			setVotes(((Candidate) pe).getVotes());
		} else {
			setVotes(0);
		}
	}
	public Candidate(int pVotes) {
		super();
		setVotes(pVotes);
	}	
	public Candidate(double pCiv, double pEcon, double pSoc) {
		super(pCiv, pEcon, pSoc);
		setVotes(0);
	}
	public Candidate(double pCiv, double pEcon, double pSoc, int pVotes) {
		super(pCiv, pEcon, pSoc);
		setVotes(pVotes);
	}
	public Candidate(Party pParty) {
		super(pParty);
		setVotes(0);
	}	
	public Candidate(int pVotes, Party pParty) {
		super(pParty);
		setVotes(pVotes);
	}	
	public Candidate(double pCiv, double pEcon, double pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setVotes(0);
	}
	public Candidate(double pCiv, double pEcon, double pSoc, int pVotes, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setVotes(pVotes);
	}
	public Candidate(double pAppRad) {
		super(pAppRad);
		setVotes(0);
	}	
	public Candidate(int pVotes, double pAppRad) {
		super(pAppRad);
		setVotes(pVotes);
	}	
	public Candidate(double pCiv, double pEcon, double pSoc, double pAppRad) {
		super(pCiv, pEcon, pSoc, pAppRad);
		setVotes(0);
	}
	public Candidate(double pCiv, double pEcon, double pSoc, double pAppRad, int pVotes) {
		super(pCiv, pEcon, pSoc, pAppRad);
		setVotes(pVotes);
	}
	public Candidate(double pAppRad , Party pParty) {
		super(pAppRad, pParty);
		setVotes(0);
	}	
	public Candidate(double pAppRad , int pVotes, Party pParty) {
		super(pAppRad, pParty);
		setVotes(pVotes);
	}	
	public Candidate(double pCiv, double pEcon, double pSoc, double pAppRad, Party pParty) {
		super(pCiv, pEcon, pSoc, pAppRad, pParty);
		setVotes(0);
	}
	public Candidate(double pCiv, double pEcon, double pSoc, double pAppRad, int pVotes, Party pParty) {
		super(pCiv, pEcon, pSoc, pAppRad, pParty);
		setVotes(pVotes);
	}
	public Candidate(double pCiv, double pEcon, double pSoc, String s) {
		super(pCiv, pEcon, pSoc);
	}
	
	//mutator methods
	public void setVotes(int pVotes) {
		mVotes = pVotes;
	}
	public void addVote() {
		mVotes++;
	}
	
	//accessor methods
	public int getVotes() {
		return mVotes;
	}
	
	public void reset() {
		setVotes(0);
	}
	
	//print method
	@Override
	public String toString() {
		return "Candidate: " + super.toString() +","+ getVotes();// +" - "+ getParty();
	}
	
	@Override
	public Party findParty(ArrayList<Party> pList) {
		ArrayList<Party> npList = new ArrayList<>(pList);
		Party newParty = super.findParty(npList);
		
		if (newParty == null) {
			Party indyParty = new Party(getCiv(), getEcon(), getSoc(), true);
			newParty = indyParty;
		}
		
		return newParty;
	}

}
