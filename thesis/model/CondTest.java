package thesis.model;

import java.util.ArrayList;

public class CondTest extends FairnessMeasure {

	//constructor
	public CondTest(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}

	public boolean makeMeasure() {
		ArrayList<Candidate> ncList = new ArrayList<>(getCandList());
		ArrayList<Candidate> nwList = new ArrayList<>();
		Candidate winner = getWinList().get(getWinList().size() - 1);
		Candidate pwWinner;
		boolean pass=true;
		
		for (Candidate c: ncList) {
			if (c.dNorm(winner) > 0) {
				pwWinner = pairwiseWinner(winner, c);
				nwList.add(pwWinner);
				System.out.println("Pairwise winner of "+ winner +" and "+ c +" is "+ pwWinner);
			}
		}
		
		for (Candidate w: nwList) {
			if (!w.equals(winner)) {
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
