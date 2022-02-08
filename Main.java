
public class Main {

	public static void main(String[] args) {
		
		Voter[] vList = {new Voter(0,2,0), new Voter(9,1,0), new Voter(3,5,0)};
		Candidate[] cList = {new Candidate(3,-6,0), new Candidate(-2,-1,0),  new Candidate(-9,1,0)};
		
		for (Voter v: vList) {
			System.out.println(v);
			v.findPrefList(cList);
		}
		
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
