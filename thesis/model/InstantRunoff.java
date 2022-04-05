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
	
	public Candidate findLoser(ArrayList<Voter> vList, ArrayList<Candidate> cList) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Party> tpList = new ArrayList<>();
		ArrayList<Candidate> tcList = new ArrayList<>();
		Candidate loser = null;
		Voter aveVoter = null;
		double aveCiv = 0;
		double aveEcon = 0;
		double aveSoc = 0;
		int tieVotes;
		
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
		
		if (ncList.get(0).getVotes() == ncList.get(1).getVotes()) {//if there is a tie 
			tieVotes = ncList.get(0).getVotes();
			for (Candidate c: ncList) {
				if (c.getVotes() == tieVotes) {
					c.reset();
					tcList.add(c);
					tpList.add(c.getParty());
				}
			}
			
			for (Voter v: nvList) {
				aveCiv += v.getCiv();
				aveEcon += v.getEcon();
				aveSoc += v.getSoc();
			}
			aveCiv = aveCiv / nvList.size();
			aveEcon = aveEcon / nvList.size();
			aveSoc = aveSoc / nvList.size();
			aveVoter = new Voter(aveCiv, aveEcon, aveSoc, 400);
			
			aveVoter.setPrefList(aveVoter.findPrefList(tcList, tpList, false));
			loser = aveVoter.getPrefList().get(aveVoter.getPrefList().size() - 1);
		}
		
		return loser;
	}
	
	public void giveVotes(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, boolean partyExclusive) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Party> npList = new ArrayList<>(pList);
		Candidate r = null;
		Candidate c = null;
		boolean e=false;
		
		//int m=0;//ddddddddddddddddddddddd
		
		for (Voter v: nvList) {
			v.setPrefList(v.findPrefList(ncList, npList, partyExclusive));
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
		
		if (!partyExclusive) {
			giveFunding(nvList, npList);
		}
		
		while ((findWin(nvList, ncList, false).getVotes() < getNumToBeat()) && (ncList.size() > 1)) {
			//m=0;//ddddddddddddddddddd
			r = findLoser(nvList, ncList);
			//System.out.println("Removing "+ getCandList().indexOf(r));
			ncList.remove(r);
			for (Voter v: nvList) {
				if (v.getPrefList().contains(r)) {
					if (r == v.getPrefList().get(0)) {
						if (v.getPrefList().size() > 1) {
							c = v.getPrefList().get(1);
							c.addVote();
//							System.out.print(v);
//							for (Candidate p: v.getPrefList()) {
//								System.out.print("  "+ getCandList().indexOf(p));
//							}
//							System.out.println("");
							//System.out.println(++m +" "+ c);
						}
					}
					v.getPrefList().remove(r);
				}
			}
//			for (Candidate nc: ncList) {
//				System.out.println(getCandList().indexOf(nc) +": "+ nc.getVotes());
//			}
//			System.out.println("");
		}
	}

}
