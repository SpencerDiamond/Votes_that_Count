package thesis.model;

import java.util.Random;
import java.text.DecimalFormat;

public abstract class PolEntity {//implements Comparable<PolEntity> { //political entity
	//instance variables
	private double mCiv; //coordinate on Hierarchical/Individualistic (civil) axis
	private double mEcon; //coordinate on Socialist/Capitalist (economic) axis
	private double mSoc; //coordinate on the Right/Left (social) axis
	protected Random r = new Random();
	protected DecimalFormat myDF = new DecimalFormat("####.###");
	
	//constructors
	public PolEntity() { //creates a PolEntity at a random point
		setCiv((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
		setEcon((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
		setSoc((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
	}
	public PolEntity(double pCiv, double pEcon, double pSoc) {
		if (Double.isNaN(pCiv) || Double.isNaN(pEcon) || Double.isNaN(pSoc) || Double.isInfinite(pCiv) || Double.isInfinite(pEcon) || Double.isInfinite(pSoc)) {
			throw new ArithmeticException("PolEntity with NaN value");
		}
		setCiv(pCiv);
		setEcon(pEcon);
		setSoc(pSoc);
	}
	public PolEntity(PolEntity pe) {
		if (Double.isNaN(pe.getCiv()) || Double.isNaN(pe.getEcon()) || Double.isNaN(pe.getSoc()) || Double.isInfinite(pe.getCiv()) || Double.isInfinite(pe.getEcon()) || Double.isInfinite(pe.getSoc())) {
			throw new ArithmeticException("PolEntity with NaN value");
		}
		setCiv(pe.getCiv());
		setEcon(pe.getEcon());
		setSoc(pe.getSoc());
	}
	
	//mutator methods
	public void setCiv(double pCiv) {
		mCiv = pCiv;
	}
	public void setEcon(double pEcon) {
		mEcon = pEcon;
	}
	public void setSoc(double pSoc) {
		mSoc = pSoc;
	}
	
	//accessor methods
	public double getCiv() {
		return mCiv;
	}
	public double getEcon() {
		return mEcon;
	}
	public double getSoc() {
		return mSoc;
	}
	
	//print method
    @Override
	public String toString() {
    	String civS = myDF.format(getCiv());
    	String econS = myDF.format(getEcon());
    	String socS = myDF.format(getSoc());
		return civS +", "+ econS +", "+ socS;
	}
	
	//Finds the magnitude of the separation between two PolEntities
	public double dNorm(PolEntity pe) { 
		double civ = pe.getCiv() - getCiv(); //
		double econ = pe.getEcon() - getEcon(); //These three lines find the difference vector between the two points
		double soc = pe.getSoc() - getSoc(); //
		
		double dot = civ*civ + econ*econ + soc*soc; //These two lines find the dot product of the difference vector
		double norm = (double) Math.sqrt(dot); //then find the root of the dot product to get the norm of the vector
		return norm;
	}

}
