package thesis.model;

import java.util.ArrayList;
//import java.util.Comparator;

public class Party extends PolEntity{
	//instance variables
	private int mFunding;
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
			setIndy(((Party) pe).getIndy());
			setCandList(((Party) pe).getCandList());
		} else {
			setFunding(0);
			setIndy(false);
		}
	}
	public Party(float pCiv, float pEcon, float pSoc) {
		super(pCiv, pEcon, pSoc);
		setFunding(0);
		setIndy(false);
	}
	public Party(int pFunding) {
		super();
		setFunding(pFunding);
		setIndy(false);
	}
	public Party(float pCiv, float pEcon, float pSoc, int pFunding) {
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
	public Party(float pCiv, float pEcon, float pSoc, ArrayList<Candidate> pCandList) {
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
	public Party(float pCiv, float pEcon, float pSoc, int pFunding, ArrayList<Candidate> pCandList) {
		super(pCiv, pEcon, pSoc);
		setFunding(pFunding);
		setIndy(false);
		setCandList(pCandList);
	}
	public Party(float pCiv, float pEcon, float pSoc, boolean pIndy) {
		super(pCiv, pEcon, pSoc);
		setFunding(0);
		setIndy(pIndy);
	}
	
	//mutator methods
	public void setFunding(int pFunding) {
		mFunding = pFunding;
	}
	public void setIndy(boolean pIndy) {
		mIndy = pIndy;
	}
	public void setCandList(ArrayList<Candidate> pCandList) {
		mCandList = pCandList;
	}
	public void add(Candidate pCand) {
		mCandList.add(pCand);
	}
	public void drop(Candidate pCand) {
		mCandList.remove(pCand);
	}
	
	//accessor methods
	public int getFunding() {
		return mFunding;
	}
	public boolean getIndy() {
		return mIndy;
	}
	public ArrayList<Candidate> getCandList() {
		return mCandList;
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
