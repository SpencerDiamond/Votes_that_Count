package thesis.model;

import java.util.ArrayList;

public class FirstPastThePost extends VotingSystem {

	public FirstPastThePost() {
		super();
	}
	public FirstPastThePost(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		super(vList, cList, pList);
	}

	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Party> npList = new ArrayList<>(pList);
		Candidate c = null;
		
		//int m=0;//ddddddddddddddddddddddddddddddd
		
		for (Voter v: nvList) {
			v.setPrefList(v.findPrefList(ncList));
			if (!v.getPrefList().isEmpty()) {
				c = v.getPrefList().get(0);
				c.addVote();
				//System.out.println(++m +" "+ c);
			}
		}
		
		giveFunding(nvList, npList);
	}

}
