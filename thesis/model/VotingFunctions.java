package thesis.model;

import java.util.ArrayList;

public interface VotingFunctions {
	
	public boolean giveFunding(ArrayList<Voter> vList, ArrayList<Party> pList);
	public void assignParty(Citizen cit, ArrayList<Party> pList);
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList);
	public Candidate findWin(ArrayList<Voter> vList, ArrayList<Candidate> cList, boolean withoutTies);
	public Candidate breakTie(ArrayList<Voter> vList, ArrayList<Candidate> cList, int tieVotes);
	
}
