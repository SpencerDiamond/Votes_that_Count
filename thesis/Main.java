package thesis;

import java.util.ArrayList;
import thesis.model.*;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Voter> vList = new ArrayList<>();
		vList.add(new Voter(0,2,0,8));
		vList.add(new Voter(9,1,0,16));
		vList.add(new Voter(3,5,0,12));
		vList.add(new Voter(-2,10,0,12));
		vList.add(new Voter(3,-5,0,20));
		vList.add(new Voter(-8,2,0,7));
		vList.add(new Voter(1,2,-5,13));
		vList.add(new Voter(3,1,4,16));
		vList.add(new Voter(-2,5,-4,4));
		vList.add(new Voter(-2,-10,8,13));
		vList.add(new Voter(-3,-5,-6,20));
		vList.add(new Voter(-8,2,8,15));
		vList.add(new Voter(-5,6,10,14));
		vList.add(new Voter(-6,0,9,2));
		vList.add(new Voter(10,7,-5,3));
		vList.add(new Voter(1,2,-1,16));
		vList.add(new Voter(-6,4,-3,10));
		vList.add(new Voter(9,5,7,16));
		vList.add(new Voter(7,4,4,6));
		vList.add(new Voter(0,4,3,18));
		vList.add(new Voter(-1,-6,7,16));
		vList.add(new Voter(-6,-1,0,5));
		vList.add(new Voter(-4,5,8,1));
		vList.add(new Voter(5,7,8,30));
		ArrayList<Candidate> cList = new ArrayList<>();
		cList.add(new Candidate(3,-6,0,(float) 30));
		cList.add(new Candidate(-2,-1,0,(float) 3));
		cList.add(new Candidate(6,1,0,(float) 30));
		cList.add(new Candidate(-7,1,0,(float) 30));
		cList.add(new Candidate(7,5,2,(float) 30));
		cList.add(new Candidate(-2,1,7,(float) 3));
		ArrayList<Party> pList = new ArrayList<>();
		pList.add(new Party(5,2,0));
		pList.add(new Party(-3,-7,0));
		
		
		ArrayList<Candidate> winners = new ArrayList<>();
		FirstPastThePost election1 = new FirstPastThePost(vList, cList, pList);
		FlatApproval election2 = new FlatApproval(vList, cList, pList);
		Bucklin election3 = new Bucklin(vList, cList, pList);
		InstantRunoff election4 = new InstantRunoff(vList, cList, pList);
		//ddddddddddddddddddddddddd
		for (Candidate c: election1.getCandList()) {
			election1.assignParty(c);
		}
		for (Candidate c: election1.getCandList()) {
			if (c.getParty().getIndy() == true) {
				election1.getPartyList().add(c.getParty());
			}
		}
		for (Voter v: election1.getVoterList()) {
			election1.assignParty(v);
		}
		
		election1.giveVotes(election1.getVoterList(), election1.getCandList());
		election1.add(election1.findWin(election1.getVoterList(), election1.getCandList()));
		
//		for (Voter v: election1.getVoterList()) {
//			System.out.println(v);
//			for (Candidate c: v.getPrefList()) {
//				System.out.println(c);
//				System.out.println(" Norm: "+ v.dNorm(c));
//			}
//			System.out.println();
//		}
		winners.add(new Candidate(election1.getWinList().get(0)));
		for (Candidate c: election1.getCandList()) {
			c.setVotes(0);
		}
		//ddddddddddddddddddddddddd
		System.out.println();
		System.out.println();
		System.out.println();
		
		election2.giveVotes(election2.getVoterList(), election2.getCandList());
		election2.add(election2.findWin(election2.getVoterList(), election2.getCandList()));
		
//		for (Voter v: election2.getVoterList()) {
//			System.out.println(v);
//			for (Candidate c: v.getPrefList()) {
//				System.out.println(c);
//				System.out.println(" Norm: "+ v.dNorm(c));
//			}
//			System.out.println();
//		}
		winners.add(new Candidate(election2.getWinList().get(0)));
		for (Candidate c: election2.getCandList()) {
			c.setVotes(0);
		}
		//ddddddddddddddddddddddddd
		System.out.println();
		System.out.println();
		System.out.println();
		
		election3.giveVotes(election3.getVoterList(), election3.getCandList());
		election3.add(election3.findWin(election3.getVoterList(), election3.getCandList()));
		
//		for (Voter v: election3.getVoterList()) {
//			System.out.println(v);
//			for (Candidate c: v.getPrefList()) {
//				System.out.println(c);
//				System.out.println(" Norm: "+ v.dNorm(c));
//			}
//			System.out.println();
//		}
		winners.add(new Candidate(election3.getWinList().get(0)));
		for (Candidate c: election3.getCandList()) {
			c.setVotes(0);
		}
		//ddddddddddddddddddddddddd
		System.out.println();
		System.out.println();
		System.out.println();
		
		election4.giveVotes(election4.getVoterList(), election4.getCandList());
		election4.add(election4.findWin(election4.getVoterList(), election4.getCandList()));
		
//		for (Voter v: election4.getVoterList()) {
//			System.out.println(v);
//			for (Candidate c: v.getPrefList()) {
//				System.out.println(c);
//				System.out.println(" Norm: "+ v.dNorm(c));
//			}
//			System.out.println();
//		}
		System.out.println();
		System.out.println();
		System.out.println();
		for (Party p: election4.getPartyList()) {
			System.out.println(p);
		}
		winners.add(new Candidate(election4.getWinList().get(0)));
		for (Candidate c: election4.getCandList()) {
			c.setVotes(0);
		}
		System.out.println();
		System.out.println();
		System.out.println();
		for (Candidate w: winners) {
			System.out.println(w);
		}
	}

	//list generating methods
	public static ArrayList<Voter> makeVoterList(int n) {
		ArrayList<Voter> list = new ArrayList<>();
		for (int j=0; j<n; j++) {
			list.add(new Voter());
		}
		return list;	
	}
	public static ArrayList<Candidate> makeCandList(int n) {
		ArrayList<Candidate> list = new ArrayList<>();
		for (int j=0; j<n; j++) {
			list.add(new Candidate());
		}
		return list;	
	}
	public static ArrayList<Party> makePartyList(int n) {
		ArrayList<Party> list = new ArrayList<>();
		for (int j=0; j<n; j++) {
			list.add(new Party());
		}
		return list;	
	}
	
}
