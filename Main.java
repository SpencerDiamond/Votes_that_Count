
public class Main {

	public static void main(String[] args) {

	}

	//list methods
	public static Voter[] makeVoterList(int n) {
		Voter list[] = new Voter[n];
		for (int j=0; j<n; j++) {
			list[j] = new Voter();
		}
		return list;	
	}
	public static Candidate[] makeCandList(int n) {
		Candidate list[] = new Candidate[n];
		for (int j=0; j<n; j++) {
			list[j] = new Candidate();
		}
		return list;	
	}
	public static Party[] makePartyList(int n) {
		Party list[] = new Party[n];
		for (int j=0; j<n; j++) {
			list[j] = new Party();
		}
		return list;	
	}
	
}
