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
	public Candidate(float pCiv, float pEcon, float pSoc) {
		super(pCiv, pEcon, pSoc);
		setVotes(0);
	}
	public Candidate(float pCiv, float pEcon, float pSoc, int pVotes) {
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
	public Candidate(float pCiv, float pEcon, float pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setVotes(0);
	}
	public Candidate(float pCiv, float pEcon, float pSoc, int pVotes, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setVotes(pVotes);
	}
	public Candidate(float pAppRad) {
		super(pAppRad);
		setVotes(0);
	}	
	public Candidate(int pVotes, float pAppRad) {
		super(pAppRad);
		setVotes(pVotes);
	}	
	public Candidate(float pCiv, float pEcon, float pSoc, float pAppRad) {
		super(pCiv, pEcon, pSoc, pAppRad);
		setVotes(0);
	}
	public Candidate(float pCiv, float pEcon, float pSoc, float pAppRad, int pVotes) {
		super(pCiv, pEcon, pSoc, pAppRad);
		setVotes(pVotes);
	}
	public Candidate(float pAppRad , Party pParty) {
		super(pAppRad, pParty);
		setVotes(0);
	}	
	public Candidate(float pAppRad , int pVotes, Party pParty) {
		super(pAppRad, pParty);
		setVotes(pVotes);
	}	
	public Candidate(float pCiv, float pEcon, float pSoc, float pAppRad, Party pParty) {
		super(pCiv, pEcon, pSoc, pAppRad, pParty);
		setVotes(0);
	}
	public Candidate(float pCiv, float pEcon, float pSoc, float pAppRad, int pVotes, Party pParty) {
		super(pCiv, pEcon, pSoc, pAppRad, pParty);
		setVotes(pVotes);
	}
	public Candidate(float pCiv, float pEcon, float pSoc, String s) {
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
	
	//print method
	@Override
	public String toString() {
		return "Candidate: " + super.toString() +","+ getVotes();// +" - "+ getParty();
	}
	
	@Override
	public Party findParty(ArrayList<Party> partyList) {
		Party newParty = super.findParty(partyList);
		
		if (newParty == null) {
			Party indyParty = new Party(getCiv(), getEcon(), getSoc(), true);
			newParty = indyParty;
		}
		
		return newParty;
	}

}
