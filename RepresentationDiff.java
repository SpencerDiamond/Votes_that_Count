
public class RepresentationDiff extends FairnessMeasure {

	//constructors
	public RepresentationDiff(VotingSystem pElection) {
		super(pElection);
	}
	public RepresentationDiff(Voter[] vList, Candidate[] cList, Party[] pList, VotingSystem pElection) {
		super(vList, cList, pList, pElection);
	}
	
}
