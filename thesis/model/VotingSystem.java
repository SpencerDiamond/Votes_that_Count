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
	public VotingSystem() {}
	public VotingSystem(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) {
		ArrayList<Voter> nvList = new ArrayList<>();
		ArrayList<Candidate> ncList = new ArrayList<>();
		ArrayList<Party> npList = new ArrayList<>();
		
		for (Voter v: vList) {
			nvList.add(new Voter(v));
		}
		for (Candidate c: cList) {
			ncList.add(new Candidate(c));
		}
		for (Party p: pList) {
			npList.add(new Party(p));
		}
		
		setVoterList(nvList);
		setCandList(ncList);
		setPartyList(npList);
	}
	public VotingSystem(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList) {
		ArrayList<Voter> nvList = new ArrayList<>();
		ArrayList<Candidate> ncList = new ArrayList<>();
		ArrayList<Party> npList = new ArrayList<>();
		ArrayList<Candidate> nwList = new ArrayList<>();
		
		for (Voter v: vList) {
			nvList.add(new Voter(v));
		}
		for (Candidate c: cList) {
			ncList.add(new Candidate(c));
		}
		for (Party p: pList) {
			npList.add(new Party(p));
		}		
		for (Candidate w: wList) {
			nwList.add(new Candidate(w));
		}
		
		setVoterList(nvList);
		setCandList(ncList);
		setPartyList(npList);
		setWinList(nwList);
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
	public void addWin(Candidate pWin) {
		mWinnerList.add(new Candidate(pWin));
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

	//election methods
	public Candidate makeElection() {
		System.out.println(this);
		for (Candidate c: getCandList()) {
			 assignParty(c, getPartyList());
		}
		for (Candidate c: getCandList()) {
			if (c.getParty().getIndy() == true) {
				getPartyList().add(c.getParty());
			}
		}
		for (Voter v: getVoterList()) {
			 assignParty(v, getPartyList());
		}
		initFunding(getVoterList(), getPartyList());
		
		 giveVotes(getVoterList(), getCandList(), getPartyList());
		 addWin(findWin(getVoterList(), getCandList(), true));
		 findPerformance(getVoterList(), getWinList().get(getWinList().size() - 1));
		
		return getWinList().get(getWinList().size() - 1);
	}
	public void resetElection() {
		for (Party p: getPartyList()) {
			//System.out.print(p);
			p.distributeFunding();
		}
		 nudgeVoters(getVoterList(), getWinList().get(getWinList().size() - 1));
		 dropParties();
		 reset();
	}
	
	public void reset() {
		for (Voter v: getVoterList()) {
			v.reset();
		}
		for (Candidate c: getCandList()) {
			c.reset();
		}
		for (Party p: getPartyList()) {
			p.reset();
		}
	}
	
	//Assigns Citizens to Parties and makes candLists for the Parties
	public void assignParty(Citizen cit, ArrayList<Party> pList){
		ArrayList<Party> npList = new ArrayList<>(pList);
		
		if (cit.getParty() == null) {
			cit.setParty(cit.findParty(npList));
			if ((cit instanceof Candidate) && (!((Candidate) cit).getParty().getCandList().contains(cit))) {
				cit.getParty().addCand((Candidate) cit);
			}
		}
	}
	
	public Candidate findWin(ArrayList<Voter> vList, ArrayList<Candidate> cList, boolean withoutTies) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		Candidate winner = null;

		if (ncList.size() == 0) {
			return winner;
		}
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
		
		while ((ncList.get(0).getVotes() == ncList.get(1).getVotes()) && withoutTies) {//allows top candidate to be found while ignoring ties, specifically for use with Instant Runoff and Bucklin
			winner = breakTie(nvList, ncList, ncList.get(0).getVotes());
		}
		
		return winner;
	}
	
	public Candidate breakTie(ArrayList<Voter> vList, ArrayList<Candidate> cList, int tieVotes) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Party> tpList = new ArrayList<>();
		ArrayList<Voter> tvList = new ArrayList<>();
		ArrayList<Candidate> tcList = new ArrayList<>();
		Voter newNV = null;
		Voter newTV = null;
		Voter aveVoter = null;
		Candidate winner = null;
		double aveCiv = 0;
		double aveEcon = 0;
		double aveSoc = 0;
		
		for (Candidate c: ncList) {
			if (c.getVotes() == tieVotes) {
				c.reset();
				tcList.add(c);
				tpList.add(c.getParty());
			} else {
				newTV = new Voter(c);
				tvList.add(newTV);
			}
		}
		for (Voter nv: nvList) {
			newNV = new Voter(nv);
			newNV.reset();
			tvList.add(newNV);
		}
		if (ncList.size() > 2) {			
			for (Voter tv: tvList) {
				tv.setPrefList(tv.findPrefList(tcList, tpList));
			}
			
			giveVotes(tvList, tcList, tpList);
			
			ncList.sort(new Comparator<Candidate>() {
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
		}
		
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
			
			winner = aveVoter.findPrefList(tcList, tpList).get(0);
			winner.addVote();
			//System.out.println("Average Vote: "+ winner);
		}
		
		return winner;
	}
	
	public boolean giveFunding(ArrayList<Voter> vList, ArrayList<Party> pList) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Party> npList = new ArrayList<>(pList);
		int ballots = 0;
		int voterPercent = 0;
		int countPercent = 0;
		
		if (nvList.isEmpty() || npList.isEmpty()) {
			System.out.println("something wrong");
			return false;//this uses the return keyword to end the method before divide by zero/indexing error can occur
		}
		
		for (Party p: npList) {
			p.reset();
		}

		for (Voter v: nvList) {
			if (v.getParty() != null) {
				v.getParty().addVoter();
			}
			if (!v.getPrefList().isEmpty()) {
				v.getPrefList().get(0).getParty().addCount();
				ballots++;
			}
		}
		
		for (Party p: npList) {
			voterPercent = (int) ((((double) p.getVoterTotal()) / ballots) * 100);
			countPercent = (int) ((((double) p.getCountTotal()) / ballots) * 100);
			p.setFunding(voterPercent + (countPercent * 5));
		}
		
		return true;//returned number is not used, only here for error prevention
	}
	public void initFunding(ArrayList<Voter> vList, ArrayList<Party> pList) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Party> npList = new ArrayList<>(pList);
		int voterPercent = 0;
		
		if (nvList.isEmpty() || npList.isEmpty()) {
				throw new ArithmeticException("Empty List");
		}
		
		for (Party p: npList) {
			p.reset();
		}

		for (Voter v: nvList) {
			if (v.getParty() != null) {
				v.getParty().addVoter();
			}
		}
		
		for (Party p: npList) {
			voterPercent = (int) ((((double) p.getVoterTotal()) / nvList.size()) * 100);
			p.setFunding(voterPercent);
		}
	}
	
	public void dropParties(){
		ArrayList<Voter> nvList = new ArrayList<>(getVoterList());
		ArrayList<Candidate> ncList = new ArrayList<>(getCandList());
		ArrayList<Candidate> rcList = new ArrayList<>();
		ArrayList<Party> npList = new ArrayList<>(getPartyList());
		ArrayList<Party> ipList = new ArrayList<>();
		ArrayList<Party> rpList = new ArrayList<>();
		int topFunding;
		
		for (Candidate c: ncList) {
			if (c.getParty() == null) {
				rcList.add(c);
			}
		}
		for (Candidate rc: rcList) {
			ncList.remove(rc);
			nvList.add(new Voter(rc));
		}

		for (Party p: npList) {
			if (p.getIndy()) {
				ipList.add(p);
			}
		}
		if (!ipList.isEmpty()) {
			ipList.sort(new Comparator<Party>() {
		        public int compare(Party o1, Party o2) {
	        		if (o1.getFunding() < o2.getFunding()) {
	        			return 1;
	        		} else if (o1.getFunding() == o2.getFunding()) {
	        			return 0;
	        		} else {
	        			return -1;
	        		}
	        	}
		    });
			topFunding = ipList.get(0).getFunding();
			for (Party ip: ipList) {
				if (ip.getFunding() == topFunding) {
					ip.setIndy(false);
				} else {
					rpList.add(ip);
				}
			}
		}
		
		for (Party p: npList) {
			if (p.getCandList().isEmpty()) {
				rpList.add(p);
			}
		}
		for (Voter v: nvList) {
			if (rpList.contains(v.getParty())) {
				v.setParty(null);
			}
		}
		for (Party rp: rpList) {
			npList.remove(rp);
		}
		
		setVoterList(nvList);
		setCandList(ncList);
		setPartyList(npList);
	}
	
	//nudging methods
	public void findPerformance(ArrayList<Voter> vList, Candidate winner) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		ArrayList<Voter> rvList = new ArrayList<>();
		RepScore repScore = new RepScore(this);//dummy RepScore object for finding performance
		double rscore=0;//representation score
		double perf;//performance
		
		for (Voter v: nvList) {
			if (!v.getPrefList().isEmpty()) {
				rvList.add(v);
			}
		}
		if (!rvList.isEmpty()) {
			rscore = repScore.findScore(rvList, winner) - 50;//find Rep Score based only on Voters that cast at least one vote
		}
		perf = (r.nextGaussian() * 10) + rscore;
		//perf = rscore;
		
		winner.setPerf(perf);
	}
	public void findSatisfaction(ArrayList<Voter> vList, Candidate winner) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		double ascore;//agreement score
		double satisf;//satisfaction
		
		for (Voter v: nvList) {
			ascore = 100.353 - v.dNorm(winner);
			satisf = (r.nextGaussian() * 10) + ascore + winner.getPerf();
			//satisf = ascore + winner.getPerf();
			v.setSatisf(satisf);
		}
	}
	public void nudgeVoters(ArrayList<Voter> vList, Candidate winner) {
		ArrayList<Voter> nvList = new ArrayList<>(vList);
		Party dHat;//dummy Party to serve as unit difference vector
		double norm;
		
		findPerformance(nvList, winner);
		findSatisfaction(nvList, winner);
		
		for (Voter v: nvList) {
			norm = v.dNorm(winner);
			if (norm == 0) {
				dHat = new Party(0,0,0);
			} else {
				dHat = new Party((v.getCiv()/norm),(v.getEcon()/norm),(v.getSoc()/norm));
			}
			
			if (Math.abs(v.getSatisf()) > 56) {
				v.nudge(dHat);
			}
		}
		
	}
}







