import java.util.ArrayList;
import java.util.Comparator;

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
		setAppRad((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
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
		setAppRad((r.nextFloat() * 200) - 100); //generates a random number in [-100, 100]
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
		ArrayList<Candidate[]> cList = new ArrayList<>();
		float x,y,z;
		for (Candidate c: candList) {
			x = c.getCiv() - this.getCiv();
			y = c.getEcon() - this.getEcon();
			z = c.getSoc() - this.getSoc();
			Candidate[] cand = new Candidate[2];
			cand[0] = c;
			cand[1] = new Candidate(x,y,z);
			cList.add(cand);
		}

		cList.sort(new Comparator<Candidate[]>() {
	        @Override
	        public int compare(Candidate[] o1, Candidate[] o2) {
	            return o1[1].compareTo(o2[1]);
	        }
	    });
		
		Candidate[] pList = new Candidate[candList.length];
		for (int n=0; n < pList.length; n++) {
			pList[n] = cList.get(n)[0];
		}
		
		this.setPrefList(pList);
		return pList;
	}
	
	//print method
	@Override
	public String toString() {
		return "Voter: " + super.toString() +","+ getAppRad();
	}
}
