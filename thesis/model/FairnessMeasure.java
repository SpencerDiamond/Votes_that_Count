package thesis.model;

import java.util.ArrayList;

public abstract class FairnessMeasure {
	//instance variable
	private VotingSystem mVotingSystem;
	
	//constructor
	public FairnessMeasure(VotingSystem pVotingSystem) {
		setVotingSystem(pVotingSystem);
	}
	
	//mutator method
	public void setVotingSystem(VotingSystem pVotingSystem) {
		mVotingSystem = pVotingSystem;
	}
		
	//accessor methods
	public ArrayList<Voter> getVoterList() {
		return mVotingSystem.getVoterList();
	}
	public ArrayList<Candidate> getCandList() {
		return mVotingSystem.getCandList();
	}
	public ArrayList<Party> getPartyList() {
		return mVotingSystem.getPartyList();
	}
	public ArrayList<Candidate> getWinList() {
		return mVotingSystem.getWinList();
	}
	public VotingSystem getVotingSystem() {
		return mVotingSystem;
	}
}
