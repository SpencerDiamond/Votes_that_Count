package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Citizen extends PolEntity {
	//instance variables
	private Party mParty;
	private double mAppRad; //approval radius
	
	public Citizen() {
		super();
		setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
	}
	public Citizen(PolEntity pe) {
		super(pe);
		if (pe instanceof Citizen) {
			setAppRad(((Citizen) pe).getAppRad());
			setParty(((Citizen) pe).getParty());
		} else {
			setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
		}
	}
	public Citizen(double pAppRad) {
		super();
		setAppRad(pAppRad);
	}
	public Citizen(double pCiv, double pEcon, double pSoc) {
		super(pCiv, pEcon, pSoc);
		setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
	}
	public Citizen(double pCiv, double pEcon, double pSoc, double pAppRad) {
		super(pCiv, pEcon, pSoc);
		setAppRad(pAppRad);
	}
	public Citizen(Party pParty) {
		super();
		setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
		setParty(pParty);
	}
	public Citizen(double pAppRad, Party pParty) {
		super();
		setAppRad(pAppRad);
		setParty(pParty);
	}
	public Citizen(double pCiv, double pEcon, double pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc);
		setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
		setParty(pParty);
	}
	public Citizen(double pCiv, double pEcon, double pSoc, double pAppRad, Party pParty) {
		super(pCiv, pEcon, pSoc);
		setAppRad(pAppRad);
		setParty(pParty);
	}
	
	//mutator methods
	public void setParty(Party pParty) {
		mParty = pParty;
	}
	public void setAppRad(double pAppRad) {
		mAppRad = pAppRad;
	}
	
	//accessor methods
	public Party getParty(){
		return mParty;
	}
	public double getAppRad() {
		return mAppRad;
	}
	
	//print method
	@Override
	public String toString() {
		return super.toString();
	}
	
	public Party findParty(ArrayList<Party> pList) {
		ArrayList<Party> npList = new ArrayList<>(pList);
		Party newParty = null;
		
		npList.sort(new Comparator<Party>() {
	        public int compare(Party o1, Party o2) {
	        	double thisNorm = dNorm(o1);
        		double thatNorm = dNorm(o2);
        		
        		if (thisNorm > thatNorm) {
        			return 1;
        		} else if (thisNorm == thatNorm) {
        			return 0;
        		} else {
        			return -1;
        		}
        	}
	    });
		
		if (dNorm(npList.get(0)) <= getAppRad()) {
			newParty = npList.get(0);
		}
		
		return newParty;
	}

}
