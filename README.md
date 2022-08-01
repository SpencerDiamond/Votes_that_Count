# Votes_that_Count

  This is Spencer Diamond's Barrett, the Honors College thesis/creative project. 

   The goal of this project was to develop a prototype for an educational tool that will help users understand how the voting system deployed by a government can affect the outcomes of elections. This tool was developed in Java SE, consisting of a model for the simulation of elections capable of supporting various voting systems, along with a variety of fairness measures, and educational and explanatory material. While a completed version of this tool would ideally be fully self-contained, easily accessible in-browser, and provide detailed visualizations of the simulated elections, the current prototype version consists of a GitHub repository containing the code, with the educational material and explanations contained within the thesis paper. Ultimately, the goal of this project was to be a stepping stone on the path to create a tool that will instill a measure of systemic skepticism in the user; to give them cause to question why our systems are built the way they are, and reasons to believe that they could be changed for the better. In undertaking this project, I hope to help in providing people with the political education needed to make informed decisions about how they want the government to function.
   
   This repository has both the code of the tool and the paper (with the LaTeX code and other files needed for compiling, for completeness). To run the program, first create ArrayLists of Voters, Candidates, and Parties to serve as the initial conditions. This can be done randomly for every run with the make[PolEntityType]List() methods, or alternatively, run MakeTestCase class, and copy paste the output into the Main class to have a consistent set of initial conditions. Two sets of initial conditions are implemented, one generated by MakeTestCase, and the other being the case for the examples in the thesis paper. It is also helpful to create another ArrayList of Candidates to serve as a list of the winners, as I have done. 
   
   Next, make your VotingSystem object with the VotingSystem(ArrayList<Voter> vList, ArrayList<Candidate> cList, ArrayList<Party> pList) constructors. Create any FairnessMeasure objects with the FairnessMeasure(VotingSystem pVotingSystem) constructor. 
   
   To run an election, use the VotingSystem.makeElection(boolean withPrimary) method. If withPrimary=true, there will be a primary election with Voters restricted to voting within their Party before the actual election; and if withPrimary=false, no primary is held. The returned value of VotingSystem.makeElection is a Candidate, so running listOfWinners.add(VotingSystem.makeElection(withPrimary)) is most convenient. After this, run any Fairness Measures with FairnessMeasure.makeMeasure(). After each election, you must run the VotingSystem.resetEletion() method, otherwise the tool will not work as desired. 
  
  The output is currently only lightly formated, with Candidates printing as "Candidate: civil, economic, social - votes, performance"; Voters printing as "Voter: civil, economic, social - approval radius, satisfaction"; Parties printing as "[Indy] Party: civil, economic, social - funding, num of Candidates"; and all other objects just as their internal identifier. The output of all .makeMeasure methods is "[FairnessMeasure] has been passed!/failed." 
