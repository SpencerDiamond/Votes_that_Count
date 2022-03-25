import java.util.Random;

public abstract class PolEntity implements Comparable<PolEntity> { //political entity
	//instance variables
	private float mCiv; //coordinate on Authoritarian/Libertarian (civil) axis
	private float mEcon; //coordinate on Collectivist/Capitalist (economic) axis
	private float mSoc; //coordinate on the Right/Left (social) axis	
	
	private static PolEntity origin = new Candidate(0,0,0,"s");
	
	Random r = new Random();
	
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
	
	@Override
	public int compareTo(PolEntity pe) {
		float thisNorm = VotingSystem.dNorm(this, origin);
		float thatNorm = VotingSystem.dNorm(pe, origin);
		
		if (thisNorm > thatNorm) {
			return 1;
		} else if (thisNorm == thatNorm) {
			return 0;
		} else {
			return -1;
		}
	}
	
	//print method
	public String toString() {
		return getCiv() +","+ getEcon() +","+ getSoc();
	}
}
