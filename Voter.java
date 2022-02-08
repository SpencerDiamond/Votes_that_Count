import java.util.ArrayList;

public class Voter extends Citizen{
	//instance variables
	private float mAppRad; //approval radius
	//private ArrayList<Pair> mPrefList;
	private Candidate[] mPrefList;
	
	//constructors
	public Voter() { 
		super();
		setAppRad((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
	}	
	public Voter(float pAppRad) { 
		super();
		setAppRad(pAppRad);
	}	
	public Voter(float pCiv, float pEcon, float pSoc) {
		super(pCiv, pEcon, pSoc);
		setAppRad((r.nextInt() * 200) - 100); //generates a random number in [-100, 100]
	}	
	public Voter(float pCiv, float pEcon, float pSoc, float pAppRad) {
		super(pCiv, pEcon, pSoc);
		setAppRad(pAppRad);
	}
	public Voter(Party pParty) { 
		super(pParty);
		setAppRad((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
	}	
	public Voter(float pAppRad, Party pParty) { 
		super(pParty);
		setAppRad(pAppRad);
	}	
	public Voter(float pCiv, float pEcon, float pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setAppRad((r.nextInt() * 200) - 100); //generates a random number in [-100, 100]
	}	
	public Voter(float pCiv, float pEcon, float pSoc, float pAppRad, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setAppRad(pAppRad);
	}
	
	//mutator methods
	public void setAppRad(float pAppRad) {
		mAppRad = pAppRad;
	}
	public void setPrefList(Candidate[] pPrefList) {
		mPrefList = pPrefList;
	}
	
	//accessor method
	public float getAppRad() {
		return mAppRad;
	}
	public Candidate[] getPrefList() {
		return mPrefList;
	}
	
	public Candidate[] findPrefList(Candidate[] candList) {
		ArrayList<Candidate> cList = new ArrayList<Candidate>();
		Candidate cand;
		float x,y,z;
		for (Candidate c: candList) {
			x = c.getCiv() - this.getCiv();
			y = c.getEcon() - this.getEcon();
			z = c.getSoc() - this.getSoc();
			cand = new Candidate(x,y,z);
			cList.add(cand);
		}
		
		cList.sort(null);
		Candidate[] pList = cList.toArray(new Candidate[cList.size()]);
		
		this.setPrefList(pList);
		return pList;
	}
	
	//print method
	public String toString() {
		return "Voter: " + super.toString() +","+ getAppRad();
	}
}
