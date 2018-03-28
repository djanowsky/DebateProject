package debate.core;
//
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import debate.client.Client;


public class Tester {

   /***
    *
    * Notes
    * @throws Exception 
    *
    */

   public static void main(String[] args) throws Exception 
   {
	   //Tournament t = TournamentLoader.initialize("DebateTestFile.csv", "JudgesList.csv");
	   //Client c = new Client(t);
	   
     //HashMap<String, ArrayList<Debater> > map = FileInput.loadFromFile("DebateTestFile.csv");
     //System.out.println("Done");
     
     //FileOutput.writeSchool("DebateTestWriter.csv", "PHHS", map);
     
   Team a1 = new Team("a", "a1");
   Team a2 = new Team("a", "a2");
   Team a3 = new Team("a", "a3");
   Team a4 = new Team("a", "a4");
   Team a5 = new Team("a", "a5");
   Team b1 = new Team("b", "b1");
   Team b2 = new Team("b", "b2");
   Team b3 = new Team("b", "b3");
   Team b4 = new Team("b", "b4");
   Team b5 = new Team("b", "b5");
   Team b6 = new Team("b", "b6");
   Team b7 = new Team("b", "b7");
   Team b8 = new Team("b", "b8");
   Team b9 = new Team("b", "b9");
   Team b10 = new Team("b", "b10");
   Team c1 = new Team("c", "c1");
   Team d1 = new Team("d", "d1");
   Team d2 = new Team("d", "d2");
   Team d3 = new Team("d", "d3");
   Team d4 = new Team("d", "d4");
   Team d5 = new Team("d", "d5");
   Team d6 = new Team("d", "d6");
   Team e1 = new Team("e", "e1");
   Team e2 = new Team("e", "e2");
   Team e3 = new Team("e", "e3");
   Team e4 = new Team("e", "e4");
   Team e5 = new Team("e", "e5");
   Team e6 = new Team("e", "e6");
   Team e7 = new Team("e", "e7");
   Team f1 = new Team("f", "f1");
   Team f2 = new Team("f", "f2");
   Team f3 = new Team("f", "f3");
   Team g1 = new Team("g", "g1");
   Team g2 = new Team("g", "g2");
   Team g3 = new Team("g", "g3");
   Team g4 = new Team("g", "g4");
   Team g5 = new Team("g", "g5");
   Team g6 = new Team("g", "g6");
   
   
   Team testExtra = new Team("a", "extra");
   
   
   
   ArrayList<Team> aff = new ArrayList<Team>();//3a, 6b, 1c, 3d, 3e, 1f, 3g 
   ArrayList<Team> neg = new ArrayList<Team>();//3a, 6b, 0c, 3d, 4e, 1f, 3g
   
   
   
   aff.add(a1); aff.add(a3); aff.add(a5); aff.add(b2);
   aff.add(b4); aff.add(b6); aff.add(b8); aff.add(b10);
   aff.add(d1); aff.add(d3); aff.add(d5); aff.add(e1);
   aff.add(e3); aff.add(e5); aff.add(e7); aff.add(f2);
   aff.add(g1); aff.add(g3); aff.add(g5);

   neg.add(a2); neg.add(a4); neg.add(b1); neg.add(b3);
   neg.add(b5); neg.add(b7); neg.add(b9); neg.add(c1);
   neg.add(d2); neg.add(d4); neg.add(d6); neg.add(e2);
   neg.add(e4); neg.add(e6); neg.add(f1); neg.add(f3);
   neg.add(g2); neg.add(g4); neg.add(g6);
   
   
   List<Team> teams = new ArrayList<Team>();
   teams.addAll(aff);
   teams.addAll(neg);
   
   
   
   Judge ja1 = (new Judge("a", "a1", true));
   
   Judge jb1 = (new Judge("b", "b1", true));
   
   Judge jb2 = (new Judge("b", "b2", true));
   
   Judge jb3 = (new Judge("b", "b3", true));
   
   Judge jb4 = (new Judge("b", "b4", true));
   
   Judge jb5 = (new Judge("b", "b5", true));
   
   Judge jb6 = (new Judge("b", "b6", true));
   
   Judge jc1 = (new Judge("c", "c1", true));
   
   Judge jc2 = (new Judge("c", "c2", true));
   
   Judge jc3 = (new Judge("c", "c3", true));
   
   Judge jc4 = (new Judge("c", "c4", true));
   
   Judge jd1 = (new Judge("d", "d1", true));
   
   Judge jd2 = (new Judge("d", "d2", true));
   
   Judge jd3 = (new Judge("d", "d3", true));
   
   Judge je1 = (new Judge("e", "e1", true));
   
   Judge je2 = (new Judge("e", "e2", true));
   
   Judge je3 = (new Judge("e", "e3", true));
   
   Judge je4 = (new Judge("e", "e4", true));
   
   Judge je5 = (new Judge("e", "e5", true));
   
   Judge je6 = (new Judge("e", "e6", true));
   
   Judge je7 = (new Judge("e", "e7", true));
   
   Judge jf1 = (new Judge("f", "f1", true));
   
   Judge jf2 = (new Judge("f", "f2", true));
   
   Judge jf3 = (new Judge("f", "f3", true));
   
   
   List<Judge> judges = new ArrayList<Judge>();
   //Insert Judges Here
   judges.add(ja1); judges.add(jb1); judges.add(jb2);
   judges.add(jb3); judges.add(jb4); judges.add(jb5);
   judges.add(jb6); judges.add(jc1); judges.add(jc2);
   judges.add(jc3); judges.add(jc4); judges.add(jd1);
   judges.add(jd2); judges.add(jd3); judges.add(je1);
   judges.add(je2); judges.add(je3); judges.add(je4);
   judges.add(je5); judges.add(je6); judges.add(je7);
   judges.add(jf1); judges.add(jf2); judges.add(jf3);
   
   //Uncomment "placeJudges(judges)" in Round constructor
   //Test if Round.placeJudges(List<Judge> judges) works
   
   
   Random rand = new Random();
   


   
   

   Tournament torn = new Tournament(teams, judges, testExtra, true);
   System.out.println("About to generate Next.");
   System.out.println("About to print round 1");
   
   
   
   Client cl = new Client(torn);
   
   System.out.println(torn.getCurrentRound());
   for(Debate debate: torn.getCurrentRound().getDebates())
   {
      if(rand.nextBoolean()) 
      {
         debate.getAffTeam().addLoss();
         debate.getAffTeam().addScores(20);
         debate.getNegTeam().addWin();
         debate.getNegTeam().addScores(30);
      }
      else
      {
         debate.getAffTeam().addWin();
         debate.getAffTeam().addScores(30);
         debate.getNegTeam().addLoss();
         debate.getNegTeam().addScores(20);
      }

   }
   System.out.println("About to finalize round 1");
   torn.finalizeCurrent();
   System.out.println("About to generate Next.");
   torn.generateNext(testExtra, null, null);
   System.out.println("About to print round 2");
   System.out.println(torn.getCurrentRound());
   for(Debate debate: torn.getCurrentRound().getDebates())
   {
      if(rand.nextBoolean()) 
      {
         debate.getAffTeam().addLoss();
         debate.getAffTeam().addScores(20);
         debate.getNegTeam().addWin();
         debate.getNegTeam().addScores(30);
      }
      else
      {
         debate.getAffTeam().addWin();
         debate.getAffTeam().addScores(30);
         debate.getNegTeam().addLoss();
         debate.getNegTeam().addScores(20);
      }
   }
   System.out.println("About to finalize round 2");
   torn.finalizeCurrent();
   
   System.out.println("About to generate Next.");
   torn.generateNext(testExtra, null, null);//TODO Change from null
   System.out.println("About to print round 3");
   System.out.println(torn.getCurrentRound());
   System.out.println("About to finalize round 3");
   torn.finalizeCurrent();
   
   System.out.println("About to generate Next.");
   torn.generateNext(testExtra, null, null);//TODO Change from null
   System.out.println("About to print round 4");
   System.out.println(torn.getCurrentRound());
   System.out.println("About to finalize round 4");
   torn.finalizeCurrent();
   
   
   }
}

