package thesis.model;

import java.util.ArrayList;

public class IndepOfAlts extends FairnessMeasure {

	//constructor
	public IndepOfAlts(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	
	public boolean makeMeasure() {
		ArrayList<Candidate> nwList = new ArrayList<>();
		InstantRunoff findLoser = new InstantRunoff();//dummy InstantRunoff object used for findLoser method
		Candidate clone;
		Candidate loser;//Candidate with the lowest votes
		Candidate irrelAlt;//irrelevant alternative
		boolean pass=true;
		
		clone = new Candidate((getWinList().get(getWinList().size() - 1).getCiv() - 0.01), (getWinList().get(getWinList().size() - 1).getEcon() - 0.01), (getWinList().get(getWinList().size() - 1).getSoc() - 0.01), getWinList().get(getWinList().size() - 1).getParty());//clone of winner. not an exact clone to avoid redundant ties, but very, very close
		loser = findLoser.findLoser(getVoterList(), getCandList());
		irrelAlt = new Candidate((loser.getCiv() - 0.01), (loser.getEcon() - 0.01), (loser.getSoc() - 0.01), loser.getParty());//clone of loser. not an exact clone to avoid redundant ties, but very, very close
		
		nwList.add(checkWinner(getVotingSystem(), clone));
		nwList.add(checkWinner(getVotingSystem(), loser));
		nwList.add(checkWinner(getVotingSystem(), irrelAlt));

		for (Candidate win: nwList) {
			if (win.dNorm(getWinList().get(getWinList().size() - 1)) != 0) {
				pass = false;
//				if (nwList.indexOf(win) == 0) {
//					System.out.println("clone");
//				} else if (nwList.indexOf(win) == 1) {
//					System.out.println("loser");
//				} else {
//					System.out.println("irrel");
//				}
			}
		}
		
		if (pass) {
			System.out.println("Independence of Alternatives has been passed!");
		} else {
			System.out.println("Independence of Alternatives has been failed.");
		}
		return pass;
	}
	
	public Candidate checkWinner(VotingSystem election, Candidate pCand) {
		ArrayList<PolEntity> peList = new ArrayList<>(getCandList());
		Candidate nWinner=null;
		boolean inside=false;
		VotingSystem nElection;
		
		if (peList.contains(pCand)) {
			inside=true;
		}
		if (inside) {//removes pCand if it is the loser, but adds pCand if it is the clone or the irrelevant alternative
			peList.remove(pCand);
		} else {
			peList.add(pCand);
		}
		
		nElection = newElection(election, peList);
		nElection.reset();
		nWinner = nElection.makeElection(false);
		
		return nWinner;
	}

}
