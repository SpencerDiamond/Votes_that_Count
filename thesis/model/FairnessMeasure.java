package thesis.model;

import java.util.ArrayList;
import java.util.Random;

public abstract class FairnessMeasure {
	//instance variable
	private VotingSystem mVotingSystem;
	protected Random r = new Random();
	
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
	
	public VotingSystem newElection(VotingSystem pElection, ArrayList<PolEntity> peList) {
		ArrayList<PolEntity> npeList = new ArrayList<>(peList);
		ArrayList<Voter> nvList = new ArrayList<>();
		ArrayList<Candidate> ncList = new ArrayList<>();
		ArrayList<Party> npList = new ArrayList<>();
		VotingSystem vs=null;
		
		if (!npeList.isEmpty()) {
			if (npeList.get(0) instanceof Voter) {
				for (PolEntity pe: npeList) {
					nvList.add(new Voter(pe));
				}
				ncList = new ArrayList<>(pElection.getCandList());
				npList = new ArrayList<>(pElection.getPartyList());
			} else if (npeList.get(0) instanceof Candidate) {
				for (PolEntity pe: npeList) {
					ncList.add(new Candidate(pe));
				}
				nvList = new ArrayList<>(pElection.getVoterList());
				npList = new ArrayList<>(pElection.getPartyList());
			} else if (npeList.get(0) instanceof Party) {
				for (PolEntity pe: npeList) {
					npList.add(new Party(pe));
				}
				nvList = new ArrayList<>(pElection.getVoterList());
				ncList = new ArrayList<>(pElection.getCandList());
			}
		}
		
		if (pElection instanceof FirstPastThePost) {
			vs = new FirstPastThePost(nvList, ncList, npList);
		} else if (pElection instanceof FlatApproval) {
			vs = new FlatApproval(nvList, ncList, npList);
		} else if (pElection instanceof Bucklin) {
			vs = new Bucklin(nvList, ncList, npList);
		} else if (pElection instanceof InstantRunoff) {
			vs = new InstantRunoff(nvList, ncList, npList);
		}
		
		return vs;
	}
}
