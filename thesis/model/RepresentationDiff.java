package thesis.model;

import java.util.ArrayList;

public class RepresentationDiff extends FairnessMeasure {

	//constructors
	public RepresentationDiff(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	public RepresentationDiff(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList, VotingSystem pVotingSystem) {
		super(vList, cList, pList, wList, pVotingSystem);
	}
	
}
