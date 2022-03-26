package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Voter extends Citizen{
	//instance variables
	private double mSatisfaction;
	private ArrayList<Candidate> mPrefList = new ArrayList<>();
	
	//constructors
	public Voter() { 
		super();
	}
	public Voter(PolEntity pe) {
		super(pe);
		if (pe instanceof Voter) {
			setPrefList(((Voter) pe).getPrefList());
		}
	}
	public Voter(double pAppRad) { 
		super(pAppRad);
	}	
	public Voter(double pCiv, double pEcon, double pSoc) {
		super(pCiv, pEcon, pSoc);
	}	
	public Voter(double pCiv, double pEcon, double pSoc, double pAppRad) {
		super(pCiv, pEcon, pSoc, pAppRad);
	}
	public Voter(Party pParty) { 
		super(pParty);
	}	
	public Voter(double pAppRad, Party pParty) { 
		super(pParty);
	}	
	public Voter(double pCiv, double pEcon, double pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
	}	
	public Voter(double pCiv, double pEcon, double pSoc, double pAppRad, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
	}
	public Voter(ArrayList<Candidate> pPrefList) { 
		super();
		setPrefList(pPrefList);
	}	
	public Voter(double pAppRad, ArrayList<Candidate> pPrefList) { 
		super(pAppRad);
		setPrefList(pPrefList);
	}	
	public Voter(double pCiv, double pEcon, double pSoc, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc);
		setPrefList(pPrefList);
	}	
	public Voter(double pCiv, double pEcon, double pSoc, double pAppRad, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc, pAppRad);
		setPrefList(pPrefList);
	}
	public Voter(Party pParty, ArrayList<Candidate> pPrefList) { 
		super(pParty);
		setPrefList(pPrefList);
	}	
	public Voter(double pAppRad, Party pParty, ArrayList<Candidate> pPrefList) { 
		super(pParty);
		setPrefList(pPrefList);
	}	
	public Voter(double pCiv, double pEcon, double pSoc, Party pParty, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc, pParty);
		setPrefList(pPrefList);
	}	
	public Voter(double pCiv, double pEcon, double pSoc, double pAppRad, Party pParty, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc, pParty);
		setPrefList(pPrefList);
	}
	
	//mutator methods
	public void setSatisfaction(double pSatisfaction) {
		mSatisfaction = pSatisfaction;
	}
	public void setPrefList(ArrayList<Candidate> pPrefList) {
		mPrefList = pPrefList;
	}
	
	//accessor method
	public double getSatisfaction() {
		return mSatisfaction;
	}
	public ArrayList<Candidate> getPrefList() {
		return mPrefList;
	}
	
	public void reset() {
		setSatisfaction(0);
		setPrefList(new ArrayList<>());
	}
	
	//print method
	@Override
	public String toString() {
		return "Voter: " + super.toString() +","+ getAppRad();// +","+ getSatisfaction() +" - "+ getParty();
	}
	
	public ArrayList<Candidate> findPrefList(ArrayList<Candidate> cList) {
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Candidate> rList = new ArrayList<>();
		
		ncList.sort(new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate o1, Candidate o2) {
	        	double thisNorm = dNorm(o1);
        		double thatNorm = dNorm(o2);
        		double thisPNorm;
        		double thatPNorm;
        		
        		if (getParty() != null) { //If Voter has a Party
	        		thisPNorm = getParty().dNorm(o1);
	        		thatPNorm = getParty().dNorm(o2);
        		} else if (o1.getParty() == o2.getParty()) { //If Voter has no Party and both Candidates have the same Party
        			thisPNorm = o1.getParty().dNorm(o1);
        			thatPNorm = o2.getParty().dNorm(o2);
        		} else { //If Voter has no Party and Candidates do not have the same Party
        			thisPNorm = dNorm(o1.getParty());
        			thatPNorm = dNorm(o2.getParty());
        		}
        		
        		if (thisNorm > thatNorm) {
        			return 1;
        		} else if (thisNorm == thatNorm) { //Voter is on the line of symmetry of the Candidates 
        			if (thisPNorm > thatPNorm) {
        				return 1;
        			} else if (thisPNorm == thatPNorm) { //Either the Party of the Voter/Candidates is also on the line of 
        											//symmetry or the Candidates' Parties are also symmetrical about that line
            			return 0;
        			} else {
        				return -1;
        			}
        		} else {
        			return -1;
        		}
	        }
	    });
		
		for (Candidate c: ncList) {
			if (dNorm(c) > getAppRad()) {
				rList.add(c);
			}
		}
		for (Candidate r: rList) {
			ncList.remove(r);
		}
		
		return ncList;
	}
	
}
