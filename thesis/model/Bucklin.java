package thesis.model;

import java.util.ArrayList;

public class Bucklin extends VotingSystem {

	//instance variables
	private int mNumToBeat;
	
	public Bucklin(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		super(vList, cList, pList);
		setNumToBeat(vList.size()/2 + 1); //50% plus 1
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
	
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		Candidate c = null;
		int n=0;
		
		int m=0;//dddddddddddddddddddd

		System.out.println("Level 1");
		for (Voter v: nvList) {
			v.setPrefList(v.findPrefList(ncList));
			if (!v.getPrefList().isEmpty()) {
				c = v.getPrefList().get(0);
				c.addVote();
				System.out.println(++m +" "+ c);
			}
		}
		while (findWin(nvList, ncList, true).getVotes() < getNumToBeat() && cList.size() > n) {
			System.out.println("Level "+ (n+2));
			giveVotes(nvList, ncList, ++n);
		}
	}
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, int n) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		Candidate c = null;
		
		int m=0;//ddddddddddddddddddddddddd
		
		for (Voter v: nvList) {
			if (v.getPrefList().size() > n) {
				c = v.getPrefList().get(n);
				c.addVote();
				System.out.println(++m +" "+ c);
			}
		}
	}

}
