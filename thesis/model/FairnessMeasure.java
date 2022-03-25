package thesis.model;

import java.util.ArrayList;

public abstract class FairnessMeasure {
	//instance variables
	private ArrayList<Voter> voterList;
	private ArrayList<Candidate> candList;
	private ArrayList<Party> partyList;
	private ArrayList<Candidate> winnerList;
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
		voterList = vList;
	}
	public void setCandList(ArrayList<Candidate> cList) {
		candList = cList;
	}
	public void setPartyList(ArrayList<Party> pList) {
		partyList = pList;
	}
	public void setWinList(ArrayList<Candidate> wList) {
		winnerList = wList;
	}
	public void setVotingSystem(VotingSystem pVotingSystem) {
		mVotingSystem = pVotingSystem;
	}
		
	//accessor methods
	public ArrayList<Voter> getVoterList() {
		return voterList;
	}
	public ArrayList<Candidate> getCandList() {
		return candList;
	}
	public ArrayList<Party> getPartyList() {
		return partyList;
	}
	public ArrayList<Candidate> getWinList() {
		return winnerList;
	}
	public VotingSystem getVotingSystem() {
		return mVotingSystem;
	}
}
