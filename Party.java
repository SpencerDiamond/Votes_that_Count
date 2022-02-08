
public class Party extends PolEntity{
	//instance variables
	private int mFunding;
	
	//constructors
	public Party() {
		super();
	}
	public Party(int pFunding) {
		super();
		setFunding(pFunding);
	}
	public Party(float pCiv, float pEcon, float pSoc, int pFunding) {
		super(pCiv, pEcon, pSoc);
		setFunding(pFunding);
	}
	
	//mutator methods
	public void setFunding(int pFunding) {
		mFunding = pFunding;
	}
	
	//accessor methods
	public int getFunding() {
		return mFunding;
	}
	
	//print method
	public String toString() {
		return "Party: " + super.toString() +","+ getFunding();
	}
}
