package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;

public class InstantRunoff extends VotingSystem {

	//instance variables
	private int mNumToBeat;
	
	public InstantRunoff() {
		super();
	}
	public InstantRunoff(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		super(vList, cList, pList);
		setNumToBeat((vList.size()/2) + 1); //50% plus 1
	}
	public InstantRunoff(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, int pNumToBeat) {
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
	
	public Candidate findLoser(ArrayList<Candidate> cList) {
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		Candidate loser = null;
		
		ncList.sort(new Comparator<Candidate>() {
	        public int compare(Candidate o1, Candidate o2) {
        		if (o1.getVotes() > o2.getVotes()) {
        			return 1;
        		} else if (o1.getVotes() == o2.getVotes()) {
        			return 0;
        		} else {
        			return -1;
        		}
        	}
	    });
		
		loser = ncList.get(0);
		
		return loser;
	}
	
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Party> npList = new ArrayList<>(pList);
		Candidate r = null;
		Candidate c = null;
		boolean e=false;
		
		//int m=0;//ddddddddddddddddddddddd
		
		for (Voter v: nvList) {
			v.setPrefList(v.findPrefList(ncList));
			if (!v.getPrefList().isEmpty()) {
				c = v.getPrefList().get(0);
				c.addVote();
				//System.out.println(++m +" "+ c);
			} else if (e) {
				lowerNumToBeat();
				e = false;
			} else {//only lowers the NumToBeat by one for every two Voters that didn't vote
				e = true;
			}
		}
		
		giveFunding(nvList, npList);
		
		while ((findWin(nvList, ncList, false).getVotes() < getNumToBeat()) && (ncList.size() > 1)) {
			//m=0;//ddddddddddddddddddd
			r = findLoser(ncList);
			//System.out.println("Removing "+ r);
			ncList.remove(r);
			for (Voter v: nvList) {
				if (v.getPrefList().contains(r)) {
					if (r == v.getPrefList().get(0)) {
						if (v.getPrefList().size() > 1) {
							c = v.getPrefList().get(1);
							c.addVote();
							//System.out.println(++m +" "+ c);
						}
					}
					v.getPrefList().remove(r);
				}
			}
		}
	}

}
