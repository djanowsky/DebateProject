package debate.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import debate.client.Client;


public class JVTester {

	/***
    *
    * Notes
    * @throws Exception 
    *
    */

   public static void main(String[] args) throws Exception {
	   int numSchools = 8;
	   //A is [Ramapo] with [1] team(s) and [3] judge(s)
	   //B is [Northern Highlands] with [11] team(s) and [3] judge(s)
	   //C is [Paramus] with [2] team(s) and [0] judge(s)
	   //D is [Pascack Hills] with [7] team(s) and [7] judge(s)
	   //E is [Pascack Valley] with [1] team(s) and [0] judge(s)
	   //F is [Glen Rock] with [0] team(s) and [0] judge(s)
	   //G is [Frisch] with [8] team(s) and [0] judge(s)
	   //H is [Ramsey] with [6] team(s) and [4] judge(s)
//	   int asciiSchools = numSchools+64;
//	   int[] numTeams = {1,11,2,7,1,0,8,6};
//	   int[] numJudges = {3,4,0,7,0,0,0,4};
//	   ArrayList<Team> aff = new ArrayList<Team>();
//	   ArrayList<Team> neg = new ArrayList<Team>();
//	   ArrayList<Team> teams = new ArrayList<Team>();
//	   List<Judge> judges = new ArrayList<Judge>();
//	   String school;
//	   boolean isAff = true;
//	   for(int i=65; i<=asciiSchools; i++) {
//		   school = new String ("" + (char)i);
//		   for(int j=1; j<=numTeams[i-65]; j++) {
//			   if(isAff) {
//				   aff.add(new Team(school, school+j));
//			   } else {
//				   neg.add(new Team(school, school+j));
//			   }
//			   isAff=!isAff;
//		   }
//		   for(int k=1; k<=numJudges[i-65]; k++) {
//			   judges.add(new Judge(school, "J"+school+k, true));
//		   }
//	   }
//	   teams.addAll(aff);
//	   teams.addAll(neg);
//	   System.out.println(aff.size());
//	   System.out.println(judges.size());
//	   if(judges.size()<aff.size()) {
//		   System.out.println("Not enough judges");
//		   throw new IllegalArgumentException();
//	   }
//	   //For a JV debate (while still in testing), use null as extraTeam and simply manually switch the
//	   //two teams after output
//	   
//	   Tournament torn = new Tournament(teams, judges, null, false);
//	   System.out.println("About to print round 1\n\n");
//	   System.out.println(torn.getCurrentRound());
//	   
//	   System.out.println("\n\nAbout to finalize round 1");
//	   torn.finalizeCurrent();
//	   
//	   System.out.println("\nAbout to generate Next.");
//	   torn.generateNext(null, null, null);
//	   System.out.println("About to print round 2\n\n");
//	   System.out.println(torn.getCurrentRound());
//	   
//	   
//	   System.out.println("\n\nAbout to finalize round 2");
//	   torn.finalizeCurrent();

	   
	   
	   //   System.out.println("About to generate Next.");
//   torn.generateNext(new Team(new Debater("",""), new Debater("",""), ""));
//   System.out.println("About to print round 3");
//   System.out.println(torn.getCurrentRound());
//   System.out.println("About to finalize round 3");
//   torn.finalizeCurrent();
//   
//   System.out.println("About to generate Next.");
//   torn.generateNext(new Team(new Debater("",""), new Debater("",""), ""));
//   System.out.println("About to print round 4");
//   System.out.println(torn.getCurrentRound());
//   System.out.println("About to finalize round 4");
//   torn.finalizeCurrent();
//   

	   
	   
	}

}
