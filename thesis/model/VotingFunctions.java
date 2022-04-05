package thesis.model;

import java.util.ArrayList;

public interface VotingFunctions {
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, boolean partyExclusive);
}
