package thesis;

import java.util.ArrayList;

import thesis.model.Candidate;
import thesis.model.Party;
import thesis.model.Voter;

public class MakeTestCase {

	public static void main(String[] args) {
		ArrayList<Voter> vList = new ArrayList<>();
		ArrayList<Candidate> cList = new ArrayList<>();
		ArrayList<Candidate> rcList = new ArrayList<>();
		ArrayList<Party> pList = new ArrayList<>();
		ArrayList<Party> rpList = new ArrayList<>();
		boolean cDupes=true;
		boolean pDupes=true;
		
		for (int j=0; j<300; j++) {
			vList.add(new Voter());
		}
		for (int j=0; j<30; j++) {
			cList.add(new Candidate());
		}
		while (cDupes) {//checks for Candidates that are too similar, if two Candidates are less than 1 unit length away from each other, one of them is removed and replaced by a new random Candidate
			cDupes = false;
			for (Candidate c1: cList) {
				for (Candidate c2: cList) {
					if (!c1.equals(c2) && c1.dNorm(c2) < 1) {
						rcList.add(c2);
						cDupes = true;
					}
				}
			}
			if (!rcList.isEmpty()) {
				for (Candidate rc: rcList) {
					cList.remove(rc);
					cList.add(new Candidate());
				}
			}
		}	
		for (int j=0; j<5; j++) {
			pList.add(new Party());
		}
		while (pDupes) {//checks for Parties that are too similar, if two Parties are less than 10 unit lengths away from each other, one of them is removed and replaced by a new random Party
			pDupes = false;
			for (Party p1: pList) {
				for (Party p2: pList) {
					if (!p1.equals(p2) && p1.dNorm(p2) < 1) {
						rpList.add(p2);
						pDupes = true;
					}
				}
			}
			if (!rpList.isEmpty()) {
				for (Party rp: rpList) {
					pList.remove(rp);
					pList.add(new Party());
				}
			}
		}
		
		System.out.println("ArrayList<Voter> vList = new ArrayList<>();");
		for (Voter v: vList) {
			System.out.println("vList.add(new Voter("+ v.getCiv() +","+ v.getEcon() +","+ v.getSoc() +","+ v.getAppRad() +"));");
		}
		System.out.println("ArrayList<Candidate> cList = new ArrayList<>();");
		for (Candidate c: cList) {
			System.out.println("cList.add(new Candidate("+ c.getCiv() +","+ c.getEcon() +","+ c.getSoc() +","+ c.getAppRad() +"));");
		}
		System.out.println("ArrayList<Party> pList = new ArrayList<>();");
		for (Party p: pList) {
			System.out.println("pList.add(new Party("+ p.getCiv() +","+ p.getEcon() +","+ p.getSoc() +"));");
		}
	}
}
