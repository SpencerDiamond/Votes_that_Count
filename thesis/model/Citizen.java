
public abstract class Citizen extends PolEntity {
	//instance variables
	private Party mParty;
	
	public Citizen() {
		super();
	}
	public Citizen(float pCiv, float pEcon, float pSoc) {
		super(pCiv, pEcon, pSoc);
	}
	public Citizen(Party pParty) {
		super();
		setParty(pParty);
	}
	public Citizen(float pCiv, float pEcon, float pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc);
		setParty(pParty);
	}
	
	//mutator methods
	public void setParty(Party pParty) {
		mParty = pParty;
	}
	
	//accessor methods
	public Party getParty(){
		return mParty;
	}
	
	//print method
	@Override
	public String toString() {
		return super.toString();
	}
}
