package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Voter extends Citizen{
	//instance variables
	private double mSatisf;
	private ArrayList<Candidate> mPrefList = new ArrayList<>();
	
	//constructors
	public Voter() { 
		super();
	}
	public Voter(PolEntity pe) {
		super(pe);
		if (pe instanceof Voter) {
			setPrefList(new ArrayList<>(((Voter) pe).getPrefList()));
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
		super(pCiv, pEcon, pSoc, pAppRad, pParty);
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
	public void setSatisf(double pSatisf) {
		mSatisf = pSatisf;
	}
	public void setPrefList(ArrayList<Candidate> pPrefList) {
		mPrefList = pPrefList;
	}
	
	//accessor method
	public double getSatisf() {
		return mSatisf;
	}
	public ArrayList<Candidate> getPrefList() {
		return mPrefList;
	}
	
	//print method
	@Override
	public String toString() {
		return "Voter: " + super.toString() +","+ getAppRad() +","+ getSatisf();// +" - "+ getParty();
	}
	
	public void reset() {
		setSatisf(0);
		setPrefList(new ArrayList<>());
	}
	
	public void nudge(PolEntity dHat) {
		double newCiv = getCiv() + (dHat.getCiv() * (getSatisf() / 4));
		double newEcon = getEcon() + (dHat.getEcon() * (getSatisf() / 4));
		double newSoc = getSoc() + (dHat.getSoc() * (getSatisf() / 4));
		
		if (Math.abs(newCiv) <= 100) {
			setCiv(newCiv);
		} else {
			setCiv(100 * (newCiv/Math.abs(newCiv)));//if newCiv is negative newCiv / |newCiv| will be -1 and if newCiv is positive it will be +1
		}
		if (Math.abs(newEcon) <= 100) {
			setEcon(newEcon);
		} else {
			setEcon(100 * (newEcon/Math.abs(newEcon)));//if newEcon is negative newEcon / |newEcon| will be -1 and if newEcon is positive it will be +1
		}
		if (Math.abs(newSoc) <= 100) {
			setSoc(newSoc);
		} else {
			setSoc(100 * (newSoc/Math.abs(newSoc)));//if newSoc is negative newSoc / |newSoc| will be -1 and if newSoc is positive it will be +1
		}//keeps values within the bounds of the coordinate system
	}
	
	public ArrayList<Candidate> findPrefList(ArrayList<Candidate> cList, ArrayList<Party> pList) {
		ArrayList<Party> npList = new ArrayList<>(pList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Candidate> rList = new ArrayList<>();
		
		ncList.sort(new Comparator<Candidate>() {
	        public int compare(Candidate o1, Candidate o2) {
	        	Voter virtVoter;
	        	double thisNorm=0;
        		double thatNorm=0;
        		double thisPNorm=0;
        		double thatPNorm=0;
        		double thisCPNorm=0;
        		double thatCPNorm=0;

	        	thisNorm = dNorm(o1);
        		thatNorm = dNorm(o2);
        		
        		if (getParty() != null) { //If Voter has a Party
	        		thisPNorm = getParty().dNorm(o1);
	        		thatPNorm = getParty().dNorm(o2);
        		} else {
        			virtVoter = new Voter(getCiv(), getEcon(), getSoc(), (double) 400);
	        		virtVoter.setParty(virtVoter.findParty(npList));
        			thisPNorm = virtVoter.getParty().dNorm(o1);
	        		thatPNorm = virtVoter.getParty().dNorm(o2);
        		}
    			thisCPNorm = dNorm(o1.getParty());
    			thatCPNorm = dNorm(o2.getParty());
        		
        		if (thisNorm > thatNorm) {
        			return 1;
        		} else if (thisNorm == thatNorm) {//Voter is on the line of symmetry of the Candidates 
        			if (thisPNorm > thatPNorm) {
        				return 1;
        			} else if (thisPNorm == thatPNorm) {//Party of the Voter is also on the line of symmetry of the Candidates
        				if (thisCPNorm > thatCPNorm) {
            				return 1;
            			} else if (thisCPNorm == thatCPNorm) {//Voter is also on the line of symmetry of the Candidates' Parties
                			throw new ArithmeticException("Catistrphic Vote-Level Tie");
            			} else {
            				return -1;
            			}
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
