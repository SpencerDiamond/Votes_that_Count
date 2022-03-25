package thesis.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public abstract class VotingSystem implements VotingFunctions {
	//instance variables
	private ArrayList<Voter> mVoterList = new ArrayList<>();
	private ArrayList<Candidate> mCandList = new ArrayList<>();
	private ArrayList<Party> mPartyList = new ArrayList<>();
	private ArrayList<Candidate> mWinnerList = new ArrayList<>();
	protected Random r = new Random();
	
	//constructors
	public VotingSystem(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		setVoterList(vList);
		setCandList(cList);
		setPartyList(pList);
	}
	public VotingSystem(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList) {
		setVoterList(vList);
		setCandList(cList);
		setPartyList(pList);
		setWinList(wList);
	}
	
	//mutator methods
	public void setVoterList(ArrayList<Voter> vList) {
		mVoterList = vList;
	}
	public void setCandList(ArrayList<Candidate> cList) {
		mCandList = cList;
	}
	public void setPartyList(ArrayList<Party> pList) {
		mPartyList = pList;
	}
	public void setWinList(ArrayList<Candidate> wList) {
		mWinnerList = wList;
	}
	public void add(Candidate pWin) {
		mWinnerList.add(pWin);
	}
	
	//accessor methods
	public ArrayList<Voter> getVoterList() {
		return mVoterList;
	}
	public ArrayList<Candidate> getCandList() {
		return mCandList;
	}
	public ArrayList<Party> getPartyList() {
		return mPartyList;
	}
	public ArrayList<Candidate> getWinList() {
		return mWinnerList;
	}
	
	//Assigns Citizens to Parties and makes candLists for the Parties
	public void assignParty(Citizen cit){
		if (cit.getParty() == null) {
			cit.setParty(cit.findParty(getPartyList()));
		}
		if (cit instanceof Candidate) {
			cit.getParty().add((Candidate) cit);
		}
	}
	
	public Candidate findWin(ArrayList<Voter> vList, ArrayList<Candidate> cList) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		Candidate winner = null;
		
		if (ncList.size() == 1) {
			winner = ncList.get(0);
			return winner;
		}
		
		ncList.sort(new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate o1, Candidate o2) {
        		if (o1.getVotes() < o2.getVotes()) {
        			return 1;
        		} else if (o1.getVotes() == o2.getVotes()) {
        			return 0;
        		} else {
        			return -1;
        		}
        	}
	    });

		winner = ncList.get(0);
		
		while (ncList.get(0).getVotes() == ncList.get(1).getVotes()) {
			winner = breakTie(nvList, ncList, ncList.get(0).getVotes());
		}
		
		return winner;
	}
	
	public Candidate breakTie(ArrayList<Voter> vList, ArrayList<Candidate> cList, int tieVotes) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Voter> tvList = new ArrayList<>();
		ArrayList<Candidate> tcList = new ArrayList<>();
		Candidate winner = null;
		Voter aveVoter = null;
		float aveCiv = 0;
		float aveEcon = 0;
		float aveSoc = 0;
		
		for (Candidate c: ncList) {
			if (c.getVotes() == tieVotes) {
				tcList.add(c);
			}
			Voter newV = new Voter(c);
			newV.setAppRad(400);
			tvList.add(newV);
		}
		System.out.println(tcList);
		System.out.println(tvList);
		
		for (Voter v: tvList) {
			v.setPrefList(v.findPrefList(tcList));
		}
		
		giveVotes(tvList, tcList);
		
		ncList.sort(new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate o1, Candidate o2) {
        		if (o1.getVotes() < o2.getVotes()) {
        			return 1;
        		} else if (o1.getVotes() == o2.getVotes()) {
        			return 0;
        		} else {
        			return -1;
        		}
        	}
	    });
		
		winner = ncList.get(0);
		
		if (ncList.get(0).getVotes() == ncList.get(1).getVotes()) {//if there is still a tie after considering Candidates votes, create a virtual Voter that is the average of all other Voters
			for (Voter v: nvList) {
				aveCiv += v.getCiv();
				aveEcon += v.getEcon();
				aveSoc += v.getSoc();
			}
			aveCiv = aveCiv / nvList.size();
			aveEcon = aveEcon / nvList.size();
			aveSoc = aveSoc / nvList.size();
			aveVoter = new Voter(aveCiv, aveEcon, aveSoc, 400);
			
			winner = aveVoter.findPrefList(tcList).get(0);
			winner.addVote();
			System.out.println("Average Vote: "+ winner);
		}
		
		return winner;
	}
	
	public Candidate findWin(ArrayList<Voter> vList, ArrayList<Candidate> cList, boolean withoutTies) {//allows top candidate to be found while ignoring ties, specifically for use with Instant Runoff and Bucklin
		//ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		Candidate winner = null;
		
		if (ncList.size() == 1) {
			winner = ncList.get(0);
			return winner;
		}
		
		ncList.sort(new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate o1, Candidate o2) {
        		if (o1.getVotes() < o2.getVotes()) {
        			return 1;
        		} else if (o1.getVotes() == o2.getVotes()) {
        			return 0;
        		} else {
        			return -1;
        		}
        	}
	    });

		winner = ncList.get(0);
		
		return winner;
	}

}
