package thesis.model;

import java.util.ArrayList;

public class LaterNoHelpHarm extends FairnessMeasure {

	//constructor
	public LaterNoHelpHarm(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	
	public boolean makeMeasure() {
		ArrayList<Candidate> nwList = new ArrayList<>();
		boolean pass=true;
		
		for (Candidate cand: getCandList()) {
			nwList.add(checkWinner(getVotingSystem(), cand));
		}
		for (Candidate win: nwList) {
			if (win.dNorm(getWinList().get(getWinList().size() - 1)) != 0) {
				pass = false;
			}
		}
		
		if (pass) {
			System.out.println("Later No Help/Harm been passed!");
		} else {
			System.out.println("Later No Help/Harm been failed.");
		}
		return pass;
	}
	
	public Candidate checkWinner(VotingSystem election, Candidate pCand) {
		ArrayList<Voter> nvList = new ArrayList<>(getVoterList());
		ArrayList<PolEntity> peList = new ArrayList<>();
		Candidate nWinner=null;
		VotingSystem nElection;
		Voter nVoter;
		
		for (Voter v: nvList) {
			if (!v.getPrefList().isEmpty()) {
				if (v.getPrefList().get(0).equals(pCand)) {
					nVoter = new Voter(v);
					nVoter.setAppRad(400);
					peList.add(nVoter);
				} else {
					peList.add(v);
				}
			} else {
				peList.add(v);
			}
		}
		nElection = newElection(election, peList);
		nElection.reset();
		nWinner = nElection.makeElection();
		
		return nWinner;
	}

}