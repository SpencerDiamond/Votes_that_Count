package thesis.model;

import java.util.ArrayList;

public class LaterNoHarm extends FairnessMeasure {

	//constructors
	public LaterNoHarm(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	public LaterNoHarm(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList, VotingSystem pVotingSystem) {
		super(vList, cList, pList, wList, pVotingSystem);
	}

}