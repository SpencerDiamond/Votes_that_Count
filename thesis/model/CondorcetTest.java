
public class CondorcetTest extends FairnessMeasure {

	//constructors
	public CondorcetTest(VotingSystem pElection) {
		super(pElection);
	}
	public CondorcetTest(Voter[] vList, Candidate[] cList, Party[] pList, VotingSystem pElection) {
		super(vList, cList, pList, pElection);
	}

}
