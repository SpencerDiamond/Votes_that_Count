import java.util.Random;

public abstract class VotingSystem implements VotingFunctions {
	//instance variables
	private Voter[] voterList;
	private Candidate[] candList;
	private Party[] partyList;

	Random r = new Random();
	
	//constructors
	public VotingSystem(Voter[] vList, Candidate[] cList, Party[] pList) {
		setVoterList(vList);
		setCandList(cList);
		setPartyList(pList);
	}
	
	//mutator methods
	public void setVoterList(Voter[] vList) {
		voterList = vList;
	}
	public void setCandList(Candidate[] cList) {
		candList = cList;
	}
	public void setPartyList(Party[] pList) {
		partyList = pList;
	}
	
	//accessor methods
	public Voter[] getVoterList() {
		return voterList;
	}
	public Candidate[] getCandList() {
		return candList;
	}
	public Party[] getPartyList() {
		return partyList;
	}
	
	//Finds the magnitude of the separation between two PolEntities
	public static float dNorm(PolEntity pe1, PolEntity pe2) { 
		float civ = pe1.getCiv() - pe2.getCiv(); //
		float econ = pe1.getEcon() - pe2.getEcon(); //These three lines find the difference vector between the two points
		float soc = pe1.getSoc() - pe2.getSoc(); //
		
		float dot = civ*civ + econ*econ + soc*soc; //These two lines find the dot product of the difference vector
		float norm = (float) Math.sqrt(dot); //then find the root of the dot product to get the norm of the vector
		return norm;
	}
	
	public void assignParty(Citizen[] cList) { }

	public int giveFunding(Candidate[] canList) {
		return 0;
	}
		
}
