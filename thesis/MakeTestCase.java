package thesis;

import java.util.Random;

public class MakeTestCase {

	public static void main(String[] args) {
		Random r = new Random();
		
		System.out.println("ArrayList<Voter> vList = new ArrayList<>();");
		for (int j=0; j<300; j++) {
			System.out.println("vList.add(new Voter("+ ((r.nextFloat() * 200) - 100) +","+ ((r.nextFloat() * 200) - 100) +","+ ((r.nextFloat() * 200) - 100) +","+ (r.nextFloat() * 200) +"));");
		}
		System.out.println("ArrayList<Candidate> cList = new ArrayList<>();");
		for (int j=0; j<30; j++) {
			System.out.println("cList.add(new Candidate("+ ((r.nextFloat() * 200) - 100) +","+ ((r.nextFloat() * 200) - 100) +","+ ((r.nextFloat() * 200) - 100) +","+ (r.nextFloat() * 200) +"));");
		}
		System.out.println("ArrayList<Party> pList = new ArrayList<>();");
		for (int j=0; j<5; j++) {
			System.out.println("pList.add(new Party("+ ((r.nextFloat() * 200) - 100) +","+ ((r.nextFloat() * 200) - 100) +","+ ((r.nextFloat() * 200) - 100) +"));");
		}
	}
}
