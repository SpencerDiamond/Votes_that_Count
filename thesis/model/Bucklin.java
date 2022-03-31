package thesis.model;

import java.util.ArrayList;

public class Bucklin extends VotingSystem {

	//instance variables
	private int mNumToBeat;
	
	public Bucklin() {
		super();
	}
	public Bucklin(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		super(vList, cList, pList);
		setNumToBeat((vList.size()/2) + 1); //50% plus 1
	}
	public Bucklin(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, int pNumToBeat) {
		super(vList, cList, pList);
		setNumToBeat(pNumToBeat);
	}

	//mutator method
	public void setNumToBeat(int pNumToBeat) {
		mNumToBeat = pNumToBeat;
	}
	
	//accessor method
	public int getNumToBeat() {
		return mNumToBeat;
	}
	
	public void lowerNumToBeat() {
		mNumToBeat--;
	}
	
	@Override
	public void reset() {
		super.reset();
		setNumToBeat((getVoterList().size()/2) + 1);
	}
	
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, boolean partyExclusive) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Party> npList = new ArrayList<>(pList);
		Candidate c = null;
		boolean e=false;
		int n=0;
		
		//int m=0;//dddddddddddddddddddd

		//System.out.println(getNumToBeat() +","+ getVoterList().size() +","+ getCandList().size());
		//System.out.println("Level 1");
		for (Voter v: nvList) {
			v.setPrefList(v.findPrefList(ncList, npList, partyExclusive));
			if (!v.getPrefList().isEmpty()) {
				c = v.getPrefList().get(0);
				c.addVote();
				//System.out.println(++m +" "+ c);
			} else if (e) {
				lowerNumToBeat();
				e = false;
			} else {
				e = true;
			}
		}
		
		if (!partyExclusive) {
			giveFunding(nvList, npList);
		}
		
		while (findWin(nvList, ncList, false).getVotes() < getNumToBeat() && ncList.size() > n) {
			//System.out.println("Level "+ (n+2));
			giveVotes(nvList, ncList, ++n);
		}
	}
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, int n) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		Candidate c = null;
		
		//int m=0;//ddddddddddddddddddddddddd
		
		for (Voter v: nvList) {
			if (v.getPrefList().size() > n) {
				c = v.getPrefList().get(n);
				c.addVote();
				//System.out.println(++m +" "+ c);
			}
		}
	}

}
