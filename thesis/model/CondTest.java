package thesis.model;

import java.util.ArrayList;

public class CondorcetTest extends FairnessMeasure {

	//constructors
	public CondorcetTest(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	public CondorcetTest(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList, VotingSystem pVotingSystem) {
		super(vList, cList, pList, wList, pVotingSystem);
	}

}
