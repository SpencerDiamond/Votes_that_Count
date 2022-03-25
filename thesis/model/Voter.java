package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Voter extends Citizen{
	//instance variables
	private float mSatisfaction;
	private ArrayList<Candidate> mPrefList;
	
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
	public Voter(float pAppRad) { 
		super(pAppRad);
	}	
	public Voter(float pCiv, float pEcon, float pSoc) {
		super(pCiv, pEcon, pSoc);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, float pAppRad) {
		super(pCiv, pEcon, pSoc, pAppRad);
	}
	public Voter(Party pParty) { 
		super(pParty);
	}	
	public Voter(float pAppRad, Party pParty) { 
		super(pParty);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, float pAppRad, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
	}
	public Voter(ArrayList<Candidate> pPrefList) { 
		super();
		setPrefList(pPrefList);
	}	
	public Voter(float pAppRad, ArrayList<Candidate> pPrefList) { 
		super(pAppRad);
		setPrefList(pPrefList);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc);
		setPrefList(pPrefList);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, float pAppRad, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc, pAppRad);
		setPrefList(pPrefList);
	}
	public Voter(Party pParty, ArrayList<Candidate> pPrefList) { 
		super(pParty);
		setPrefList(pPrefList);
	}	
	public Voter(float pAppRad, Party pParty, ArrayList<Candidate> pPrefList) { 
		super(pParty);
		setPrefList(pPrefList);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, Party pParty, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc, pParty);
		setPrefList(pPrefList);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, float pAppRad, Party pParty, ArrayList<Candidate> pPrefList) {
		super(pCiv, pEcon, pSoc, pParty);
		setPrefList(pPrefList);
	}
	
	//mutator methods
	public void setSatisfaction(float pSatisfaction) {
		mSatisfaction = pSatisfaction;
	}
	public void setPrefList(ArrayList<Candidate> pPrefList) {
		mPrefList = pPrefList;
	}
	
	//accessor method
	public float getSatisfaction() {
		return mSatisfaction;
	}
	public ArrayList<Candidate> getPrefList() {
		return mPrefList;
	}
	
	//print method
	@Override
	public String toString() {
		return "Voter: " + super.toString() +","+ getAppRad();// +","+ getSatisfaction() +" - "+ getParty();
	}
	
	public ArrayList<Candidate> findPrefList(ArrayList<Candidate> candList) {
		ArrayList<Candidate> cList = new ArrayList<>(candList);
		ArrayList<Candidate> rList = new ArrayList<>();
		
		cList.sort(new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate o1, Candidate o2) {
	        	float thisNorm = dNorm(o1);
        		float thatNorm = dNorm(o2);
        		float thisPNorm;
        		float thatPNorm;
        		
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
		
		for (Candidate c: cList) {
			if (dNorm(c) > getAppRad()) {
				rList.add(c);
			}
		}
		for (Candidate r: rList) {
			cList.remove(r);
		}
		
		return cList;
	}
	
}
