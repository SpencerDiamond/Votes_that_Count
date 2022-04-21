package thesis.model;

import java.util.ArrayList;

public class CondTest extends FairnessMeasure {

	//constructor
	public CondTest(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}

	public boolean makeMeasure() {
		ArrayList<Candidate> pwList = new ArrayList<>();
		Candidate winner = getVotingSystem().findWin(getVoterList(), getCandList(), true);
		boolean pass;
		
		if (getWinList().get(getWinList().size() - 1).dNorm(winner) !=0) {
			throw new ArithmeticException("Winner doesn't match");
		}
		
		if (getVotingSystem() instanceof InstantRunoff) {
			for (Voter v: getVoterList()) {
				v.setPrefList(v.findPrefList(getCandList(), getPartyList(), false));
			}
		}
		
		pwList = getPWWinners(winner, getCandList());
		//System.out.println(pwList);
		pass = checkPWWinner(winner, pwList, getCandList());
		if (pass) {
			System.out.println("Condorcet Test has been passed!");
		} else {
			System.out.println("Condorcet Test has been failed.");
		}
		return pass;
	}
	
	public ArrayList<Candidate> getPWWinners(Candidate pCand, ArrayList<Candidate> cList){
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Candidate> wList = new ArrayList<>();
		boolean lose;
		
		for (Candidate c: ncList) {
			if (c.dNorm(pCand) > 0) {
				lose = !pairwiseWinner(pCand, c);
				if (lose) {
					wList.add(c);
					//System.out.println("Pairwise winner of "+ pCand +" and "+ c +" is "+ c);
				} else {
					//System.out.println("Pairwise winner of "+ pCand +" and "+ c +" is "+ pCand);
				}
			}
		}
		
		return wList;
	}
	public boolean checkPWWinner(Candidate pWinner, ArrayList<Candidate> wList, ArrayList<Candidate> cList){
		ArrayList<Candidate> ncList = new ArrayList<>(cList);
		ArrayList<Candidate> nwList = new ArrayList<>(wList);
		ArrayList<Candidate> npwList = new ArrayList<>();
		boolean nPass=false;
		boolean pass=true;
		ncList.remove(pWinner);
		
		if (!nwList.isEmpty()) {
			for (Candidate w: nwList) {
				if (w.dNorm(pWinner) > 0) {
//					System.out.println(w);
					npwList = getPWWinners(w, ncList);
					nPass = (checkPWWinner(w, npwList, ncList));
				}
				if (nPass) {
//					System.out.println("fail "+ w);
					pass = false;
				}
			}
		}
		
		return pass;
	}
	public boolean pairwiseWinner(Candidate pWinner, Candidate pCand) {
		ArrayList<Voter> nvList = new ArrayList<>(getVoterList());
		ArrayList<Candidate> ncList = new ArrayList<>();
		Candidate nWinner = new Candidate(pWinner);
		Candidate nCand = new Candidate(pCand);
		
		
		nWinner.reset();
		nCand.reset();
		ncList.add(nWinner);
		ncList.add(nCand);
		
		for (Voter v: nvList) {
			if (!v.getPrefList().isEmpty()) {
				if (v.getPrefList().contains(pWinner) && v.getPrefList().contains(pCand)) {
					if (v.getPrefList().indexOf(pWinner) < v.getPrefList().indexOf(pCand)) {
						nWinner.addVote();
					} else {
						nCand.addVote();
					}
				} else if (v.getPrefList().contains(pWinner) && !v.getPrefList().contains(pCand)) {
					nWinner.addVote();					
				} else if (!v.getPrefList().contains(pWinner) && v.getPrefList().contains(pCand)) {
					nCand.addVote();					
				}
			}
		}
		//System.out.println(nWinner +"   "+ nCand);
		if (getVotingSystem().findWin(nvList, ncList, true).dNorm(nWinner) == 0) {
			return true;
		} else {
			return false;
		}
	}
}
