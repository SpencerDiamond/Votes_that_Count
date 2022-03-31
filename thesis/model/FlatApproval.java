package thesis.model;

import java.util.ArrayList;

public class FlatApproval extends VotingSystem {

	public FlatApproval() {
		super();
	}
	public FlatApproval(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		super(vList, cList, pList);
	}

	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, boolean partyExclusive) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Party> npList = new ArrayList<>(pList);
		
		//int m=0;//ddddddddddddddddddddd
		
		for (Voter v: nvList) {
			v.setPrefList(v.findPrefList(ncList, npList, partyExclusive));
			if (!v.getPrefList().isEmpty()) {
				for (Candidate c: v.getPrefList()) {
					c.addVote();
					//System.out.println(++m +" "+ c);
				}
			}
		}

		if (!partyExclusive) {
			giveFunding(nvList, npList);
		}
	}

}
