package thesis.model;

import java.util.ArrayList;

public class RepScore extends FairnessMeasure {

	//constructor
	public RepScore(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	
	public boolean makeMeasure() {
		double score;
		
		score = findScore(getVoterList(), getWinList().get(getWinList().size() - 1));
		System.out.println("Representation Score = "+ score +"%");
		
		if (score > 50) {
			return true;
		} else {
			return false;
		}
	}
	
	public double findScore(ArrayList<Voter> vList, Candidate winner) {
		double score=0;
		
		score = (((double) winner.getVotes()) / vList.size()) * 100;
		
		return score;
	}
}
