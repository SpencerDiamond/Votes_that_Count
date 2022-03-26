package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Party extends PolEntity{
	//instance variables
	private int mFunding;
	private int mVoterTotal;
	private int mCountTotal;
	private boolean mIndy;
	private ArrayList<Candidate> mCandList = new ArrayList<>();
	
	//constructors
	public Party() {
		super();
		setFunding(0);
		setIndy(false);
	}
	public Party(PolEntity pe) {
		super(pe);
		if (pe instanceof Party) {
			setFunding(((Party) pe).getFunding());
			setVoterTotal(((Party) pe).getVoterTotal());
			setCountTotal(((Party) pe).getCountTotal());
			setIndy(((Party) pe).getIndy());
			setCandList(new ArrayList<>(((Party) pe).getCandList()));
		} else {
			setFunding(0);
			setIndy(false);
		}
	}
	public Party(double pCiv, double pEcon, double pSoc) {
		super(pCiv, pEcon, pSoc);
		setFunding(0);
		setIndy(false);
	}
	public Party(int pFunding) {
		super();
		setFunding(pFunding);
		setIndy(false);
	}
	public Party(double pCiv, double pEcon, double pSoc, int pFunding) {
		super(pCiv, pEcon, pSoc);
		setFunding(pFunding);
		setIndy(false);
	}
	public Party(ArrayList<Candidate> pCandList) {
		super();
		setFunding(0);
		setIndy(false);
		setCandList(pCandList);
	}
	public Party(double pCiv, double pEcon, double pSoc, ArrayList<Candidate> pCandList) {
		super(pCiv, pEcon, pSoc);
		setFunding(0);
		setIndy(false);
		setCandList(pCandList);
	}
	public Party(int pFunding, ArrayList<Candidate> pCandList) {
		super();
		setFunding(pFunding);
		setIndy(false);
		setCandList(pCandList);
	}
	public Party(double pCiv, double pEcon, double pSoc, int pFunding, ArrayList<Candidate> pCandList) {
		super(pCiv, pEcon, pSoc);
		setFunding(pFunding);
		setIndy(false);
		setCandList(pCandList);
	}
	public Party(double pCiv, double pEcon, double pSoc, boolean pIndy) {
		super(pCiv, pEcon, pSoc);
		setFunding(0);
		setIndy(pIndy);
	}
	
	//mutator methods
	public void setFunding(int pFunding) {
		mFunding = pFunding;
	}
	public void setVoterTotal(int pVoterTotal) {
		mVoterTotal = pVoterTotal;
	}
	public void setCountTotal(int pCountTotal) {
		mCountTotal = pCountTotal;
	}
	public void setIndy(boolean pIndy) {
		mIndy = pIndy;
	}
	public void setCandList(ArrayList<Candidate> pCandList) {
		mCandList = pCandList;
	}
	
	//accessor methods
	public int getFunding() {
		return mFunding;
	}
	public int getVoterTotal() {
		return mVoterTotal;
	}
	public int getCountTotal() {
		return mCountTotal;
	}
	public boolean getIndy() {
		return mIndy;
	}
	public ArrayList<Candidate> getCandList() {
		return mCandList;
	}
	
	//other instance variable methods
	public void add(Candidate pCand) {
		mCandList.add(pCand);
	}
	public void drop(Candidate pCand) {
		mCandList.remove(pCand);
		pCand.setParty(null);
	}
	public void addVoter() {
		mVoterTotal++;
	}
	public void addCount() {
		mCountTotal++;
	}
	public void reset() {
		setFunding(0);
		setVoterTotal(0);
		setCountTotal(0);
	}
	
	public void distributeFunding() {
		ArrayList<Candidate> ncList = new ArrayList<>(getCandList());
		int numAfford;
		
		numAfford = (int) (((double) getFunding()) / 25);
		System.out.println(" -> "+ numAfford);
		ncList.sort(new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate o1, Candidate o2) {
        		if (o1.getVotes() < o2.getVotes()) {
        			return 1;
        		} else if (o1.getVotes() == o2.getVotes()) {
        			return 0;
        		} else {
        			return -1;
        		}
        	}
	    });//sort by number of votes
		
		for (Candidate c: ncList) {
			if (ncList.indexOf(c) >= numAfford) {
				drop(c);
			}
		}
	}
	
	//print method
	@Override
	public String toString() {
		if (getIndy()) {
			return "Indy Party: " + super.toString() +","+ getFunding() +','+ getCandList().size();
		} else {
			return "Party: " + super.toString() +","+ getFunding() +','+ getCandList().size();
		}
	}
	
}
