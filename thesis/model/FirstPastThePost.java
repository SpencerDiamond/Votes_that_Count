
public class FirstPastThePost extends VotingSystem {

	public FirstPastThePost(Voter[] vList, Candidate[] cList, Party[] pList) {
		super(vList, cList, pList);
	}

	@Override
	public void giveVotes(Voter[] vList) {
		float norm1=0;
		Candidate cand=null;
		for (Voter v : vList) {
			if ((norm1 <= v.getAppRad()) && (cand != null)) {
				cand.setVotes(cand.getVotes() + 1);
			}
		}
	}

}
