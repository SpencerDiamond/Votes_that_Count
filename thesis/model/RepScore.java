package thesis.model;

import java.util.ArrayList;

public class RepScore extends FairnessMeasure {

	//constructors
	public RepScore(VotingSystem pVotingSystem) {
		super(pVotingSystem);
	}
	public RepScore(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList, ArrayList<Candidate> wList, VotingSystem pVotingSystem) {
		super(vList, cList, pList, wList, pVotingSystem);
	}
	
	public void makeMeasure() {
		System.out.println("Representation Score = "+ findScore() +"%");
	}
	
	public double findScore() {
		double score=0;
		
		score = (((double) getWinList().get(getWinList().size() - 1).getVotes()) / getVoterList().size()) * 100;
		
		return score;
	}
}
