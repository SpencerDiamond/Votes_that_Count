package thesis.model;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class RepScore extends FairnessMeasure {
	protected DecimalFormat myDF = new DecimalFormat("####.###");

	//constructor
	public RepScore(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	
	public boolean makeMeasure() {
		double score;
		
		score = findScore(getVoterList(), getWinList().get(getWinList().size() - 1));
		String scoreS = myDF.format(score);
		System.out.println("Representation Score = "+ scoreS +"%");
		
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
