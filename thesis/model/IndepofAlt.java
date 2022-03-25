
public class SpoilerEff extends FairnessMeasure {

	//constructors
	public SpoilerEff(VotingSystem pElection) {
		super(pElection);
	}
	public SpoilerEff(Voter[] vList, Candidate[] cList, Party[] pList, VotingSystem pElection) {
		super(vList, cList, pList, pElection);
	}

}
