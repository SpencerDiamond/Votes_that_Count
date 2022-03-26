package thesis.model;

import java.util.ArrayList;

public abstract class FairnessMeasure {
	//instance variables
	private ArrayList<Voter> mVoterList;
	private ArrayList<Candidate> mCandList;
	private ArrayList<Party> mPartyList;
	private ArrayList<Candidate> mWinnerList;
	private VotingSystem mVotingSystem;
	
	//constructors
	public FairnessMeasure(VotingSystem pVotingSystem) {
		setVoterList(pVotingSystem.getVoterList());
		setCandList(pVotingSystem.getCandList());
		setPartyList(pVotingSystem.getPartyList());
		setWinList(pVotingSystem.getWinList());
		setVotingSystem(pVotingSystem);
	}
	public FairnessMeasure(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList, VotingSystem pVotingSystem) {
		setVoterList(vList);
		setCandList(cList);
		setPartyList(pList);
		setWinList(wList);
		setVotingSystem(pVotingSystem);
	}
	
	//mutator methods
	public void setVoterList(ArrayList<Voter> vList) {
		mVoterList = vList;
	}
	public void setCandList(ArrayList<Candidate> cList) {
		mCandList = cList;
	}
	public void setPartyList(ArrayList<Party> pList) {
		mPartyList = pList;
	}
	public void setWinList(ArrayList<Candidate> wList) {
		mWinnerList = wList;
	}
	public void setVotingSystem(VotingSystem pVotingSystem) {
		mVotingSystem = pVotingSystem;
	}
		
	//accessor methods
	public ArrayList<Voter> getVoterList() {
		return mVoterList;
	}
	public ArrayList<Candidate> getCandList() {
		return mCandList;
	}
	public ArrayList<Party> getPartyList() {
		return mPartyList;
	}
	public ArrayList<Candidate> getWinList() {
		return mWinnerList;
	}
	public VotingSystem getVotingSystem() {
		return mVotingSystem;
	}
}
