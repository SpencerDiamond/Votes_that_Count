
public class Candidate extends Citizen{
	//instance variables
	private int mVotes; //number of votes the candidate has received
	
	//constructors
	public Candidate() {
		super();
		setVotes(0);
	}	
	public Candidate(int pVotes) {
		super();
		setVotes(pVotes);
	}	
	public Candidate(float pCiv, float pEcon, float pSoc) {
		super(pCiv, pEcon, pSoc);
		setVotes(0);
	}		
	public Candidate(float pCiv, float pEcon, float pSoc, String s) {
		super(pCiv, pEcon, pSoc);
	}	
	public Candidate(float pCiv, float pEcon, float pSoc, int pVotes) {
		super(pCiv, pEcon, pSoc);
		setVotes(pVotes);
	}
	public Candidate(Party pParty) {
		super(pParty);
		setVotes(0);
	}	
	public Candidate(int pVotes, Party pParty) {
		super(pParty);
		setVotes(pVotes);
	}	
	public Candidate(float pCiv, float pEcon, float pSoc, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setVotes(0);
	}
	public Candidate(float pCiv, float pEcon, float pSoc, int pVotes, Party pParty) {
		super(pCiv, pEcon, pSoc, pParty);
		setVotes(pVotes);
	}
	
	//mutator methods
	public void setVotes(int pVotes) {
		mVotes = pVotes;
	}
	
	//accessor methods
	public int getVotes() {
		return mVotes;
	}
	
	//print method
	public String toString() {
		return "Candidate: " + super.toString() +","+ getVotes();
	}
}
