
public abstract class FairnessMeasure {
	//instance variables
	private Voter[] voterList;
	private Candidate[] candList;
	private Party[] partyList;
	private VotingSystem mElection;
	
	//constructors
	public FairnessMeasure(VotingSystem pElection) {
		setVoterList(pElection.getVoterList());
		setCandList(pElection.getCandList());
		setPartyList(pElection.getPartyList());
		setElection(pElection);
	}
	public FairnessMeasure(Voter[] vList, Candidate[] cList, Party[] pList, VotingSystem pElection) {
		setVoterList(vList);
		setCandList(cList);
		setPartyList(pList);
		setElection(pElection);
	}
	
	//mutator methods
	public void setVoterList(Voter[] vList) {
		voterList = vList;
	}
	public void setCandList(Candidate[] cList) {
		candList = cList;
	}
	public void setPartyList(Party[] pList) {
		partyList = pList;
	}
	public void setElection(VotingSystem pElection) {
		mElection = pElection;
	}
		
	//accessor methods
	public Voter[] getVoterList() {
		return voterList;
	}
	public Candidate[] getCandList() {
		return candList;
	}
	public Party[] getPartyList() {
		return partyList;
	}
	public VotingSystem getElection() {
		return mElection;
	}
}
