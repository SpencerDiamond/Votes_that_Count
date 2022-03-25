package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class Citizen extends PolEntity {
	//instance variables
	private Party mParty;
	private float mAppRad; //approval radius
	
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
	public Citizen(float pAppRad) {
		super();
		setAppRad(pAppRad);
	}
	public Citizen(float pCiv, float pEcon, float pSoc) {
		super(pCiv, pEcon, pSoc);
		setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
	}
	public Citizen(float pCiv, float pEcon, float pSoc, float pAppRad) {
		super(pCiv, pEcon, pSoc);
		setAppRad(pAppRad);
	}
	public Citizen(Party pParty) {
		super();
		setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
		setParty(pParty);
	}
	public Citizen(float pAppRad, Party pParty) {
		super();
		setAppRad(pAppRad);
		setParty(pParty);
	}
	public Citizen(float pCiv, float pEcon, float pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc);
		setAppRad(Math.abs(r.nextFloat() * 200)); //generates a random number in [0, 200]
		setParty(pParty);
	}
	public Citizen(float pCiv, float pEcon, float pSoc, float pAppRad, Party pParty) {
		super(pCiv, pEcon, pSoc);
		setAppRad(pAppRad);
		setParty(pParty);
	}
	
	//mutator methods
	public void setParty(Party pParty) {
		mParty = pParty;
	}
	public void setAppRad(float pAppRad) {
		mAppRad = pAppRad;
	}
	
	//accessor methods
	public Party getParty(){
		return mParty;
	}
	public float getAppRad() {
		return mAppRad;
	}
	
	//print method
	@Override
	public String toString() {
		return super.toString();
	}
	
	public Party findParty(ArrayList<Party> partyList) {
		Party newParty = null;
		
		partyList.sort(new Comparator<Party>() {
	        @Override
	        public int compare(Party o1, Party o2) {
	        	float thisNorm = dNorm(o1);
        		float thatNorm = dNorm(o2);
        		if (thisNorm > thatNorm) {
        			return 1;
        		} else if (thisNorm == thatNorm) {
        			return 0;
        		} else {
        			return -1;
        		}
        	}
	    });
		
		if (dNorm(partyList.get(0)) <= getAppRad()) {
			newParty = partyList.get(0);
		}
		
		return newParty;
	}

}
