package thesis.model;

import java.util.ArrayList;

public class LaterNoHelpHarm extends FairnessMeasure {

	//constructor
	public LaterNoHelpHarm(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	
	public boolean makeMeasure() {
		ArrayList<Voter> nvList = new ArrayList<>(getVoterList());
		ArrayList<Candidate> ncList = new ArrayList<>(getCandList());
		ArrayList<Party> npList = new ArrayList<>(getPartyList());
		
		
		
		return true;
	}
	
	public Candidate checkWinner(VotingSystem election, Candidate pCand) {
		
		
		return pCand;
	}

}