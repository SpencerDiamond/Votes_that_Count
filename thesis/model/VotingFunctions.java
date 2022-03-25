package thesis.model;

import java.util.ArrayList;

public interface VotingFunctions {
	
	//public int giveFunding(ArrayList<Voter> vList, ArrayList<Party> pList);
	public void assignParty(Citizen cit);
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList);
	public Candidate findWin(ArrayList<Voter> vList, ArrayList<Candidate> cList);
	public Candidate breakTie(ArrayList<Voter> vList, ArrayList<Candidate> cList, int tieVotes);
	
}
