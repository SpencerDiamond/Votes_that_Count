package thesis.model;

import java.util.Random;

public abstract class PolEntity {//implements Comparable<PolEntity> { //political entity
	//instance variables
	private float mCiv; //coordinate on Hierarchical/Individualistic (civil) axis
	private float mEcon; //coordinate on Socialist/Capitalist (economic) axis
	private float mSoc; //coordinate on the Right/Left (social) axis
	protected Random r = new Random();
	protected static final PolEntity origin = new Candidate(0,0,0,"s");
	
	//constructors
	public PolEntity() { //creates a PolEntity at a random point
		setCiv((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
		setEcon((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
		setSoc((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
	}
	public PolEntity(float pCiv, float pEcon, float pSoc) {
		setCiv(pCiv);
		setEcon(pEcon);
		setSoc(pSoc);
	}
	public PolEntity(PolEntity pe) {
		setCiv(pe.getCiv());
		setEcon(pe.getEcon());
		setSoc(pe.getSoc());
	}
	
	//mutator methods
	public void setCiv(float pCiv) {
		mCiv = pCiv;
	}
	public void setEcon(float pEcon) {
		mEcon = pEcon;
	}
	public void setSoc(float pSoc) {
		mSoc = pSoc;
	}
	
	//accessor methods
	public float getCiv() {
		return mCiv;
	}
	public float getEcon() {
		return mEcon;
	}
	public float getSoc() {
		return mSoc;
	}
	
	//print method
	public String toString() {
		return getCiv() +","+ getEcon() +","+ getSoc();
	}
	
	//Finds the magnitude of the separation between two PolEntities
	public float dNorm(PolEntity pe) { 
		float civ = pe.getCiv() - getCiv(); //
		float econ = pe.getEcon() - getEcon(); //These three lines find the difference vector between the two points
		float soc = pe.getSoc() - getSoc(); //
		
		float dot = civ*civ + econ*econ + soc*soc; //These two lines find the dot product of the difference vector
		float norm = (float) Math.sqrt(dot); //then find the root of the dot product to get the norm of the vector
		return norm;
	}

}
