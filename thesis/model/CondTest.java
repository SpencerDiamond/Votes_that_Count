package thesis.model;

import java.util.ArrayList;

public class CondTest extends FairnessMeasure {

	//constructor
	public CondTest(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}

	public boolean makeMeasure() {
		ArrayList<Candidate> pwList = new ArrayList<>();
		Candidate winner = getWinList().get(getWinList().size() - 1);
		boolean pass;
		
		pwList = getPWWinners(winner);
		pass = checkPWWinner(winner, pwList);

		if (pass) {
			System.out.println("Condorcet Test has been passed!");
		} else {
			System.out.println("Condorcet Test has been failed.");
		}
		return pass;
	}
	
	public ArrayList<Candidate> getPWWinners(Candidate pCand){
		ArrayList<Candidate> ncList = new ArrayList<>(getCandList());
		ArrayList<Candidate> wList = new ArrayList<>();
		Candidate pwWinner;
		
		for (Candidate c: ncList) {
			if (c.dNorm(pCand) > 0) {
				pwWinner = pairwiseWinner(pCand, c);
				wList.add(pwWinner);
				//System.out.println("Pairwise winner of "+ winner +" and "+ c +" is "+ pwWinner);
			}
		}
		
		return wList;
	}
	public boolean checkPWWinner(Candidate pWinner, ArrayList<Candidate> wList) {
		ArrayList<Candidate> nwList = new ArrayList<>(wList);
		ArrayList<Candidate> npwList = new ArrayList<>();
		boolean nPass=false;
		boolean pass=true;
		
		for (Candidate w: nwList) {
			if (!w.equals(pWinner)) {
				//System.out.println(w);
				npwList = getPWWinners(w);
				nPass = (checkPWWinner(w, npwList));
			}
			if (nPass) {
				//System.out.println("fail "+ w);
				pass = false;
			}
		}
		
		return pass;
	}
	public Candidate pairwiseWinner(Candidate pWinner, Candidate pCand) {
		ArrayList<Voter> nvList = new ArrayList<>(getVoterList());
		Candidate nWinner = new Candidate(pWinner);
		Candidate nCand = new Candidate(pCand);
		ArrayList<Candidate> ncList = new ArrayList<>();
		
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
				} else if (v.getPrefList().contains(pWinner)) {
					nWinner.addVote();					
				} else if (v.getPrefList().contains(pCand)) {
					nCand.addVote();					
				}
			}
		}
		
		if (getVotingSystem().findWin(nvList, ncList, true).equals(nWinner)) {
			return pWinner;
		} else {
			return pCand;
		}
	}
}
