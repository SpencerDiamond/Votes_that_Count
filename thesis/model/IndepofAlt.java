package thesis.model;

import java.util.ArrayList;

public class IndepofAlt extends FairnessMeasure {

	//constructors
	public IndepofAlt(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	public IndepofAlt(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList, VotingSystem pVotingSystem) {
		super(vList, cList, pList, wList, pVotingSystem);
	}

}
