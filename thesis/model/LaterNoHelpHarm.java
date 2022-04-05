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
			nwList.add(checkWide(getVotingSystem(), cand));
		}
		for (Candidate win: nwList) {
			if (win.dNorm(getWinList().get(getWinList().size() - 1)) != 0) {
				pass = false;
				//System.out.println(nwList.indexOf(win) +"  ,  "+ win);
			}
		}
		
		if (pass) {
			System.out.println("Later No Help/Harm has been passed!");
		} else {
			System.out.println("Later No Help/Harm has been failed.");
		}
		return pass;
	}
	
	public Candidate checkWide(VotingSystem election, Candidate pCand) {
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
		nWinner = nElection.makeElection(false);
		
		return nWinner;
	}
	public Candidate checkNarrow(VotingSystem election, Candidate pCand) {//currently unused
		ArrayList<Voter> nvList = new ArrayList<>(getVoterList());
		ArrayList<PolEntity> peList = new ArrayList<>();
		Candidate nWinner=null;
		VotingSystem nElection;
		Voter nVoter;
		
		for (Voter v: nvList) {
			if (!v.getPrefList().isEmpty()) {
				if (v.getPrefList().get(0).equals(pCand)) {
					if (v.getPrefList().size() > 1) {
						nVoter = new Voter(v);
						nVoter.getPrefList().remove(v.getPrefList().get(v.getPrefList().size() - 1));
						peList.add(nVoter);					
					} else {
						peList.add(v);
					}
				} else {
					peList.add(v);
				}
			} else {
				peList.add(v);
			}
		}
		nElection = newElection(election, peList);
		nElection.reset();
		nWinner = nElection.makeElection(false);
		
		return nWinner;
	}
}