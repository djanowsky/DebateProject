package debate.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tournament {

   private List<Team> teams;
   private List<Judge> judges;
   private List<Round> rounds;
   private int current;
   private Team extraTeam;
   private boolean isVarsity;
   
   public Tournament(List<Team> teams, List<Judge> judges, Team extra, boolean isVarsity) {
	   this.teams = teams;
	   this.judges = judges;
	   this.current = 0;
	   this.rounds = new ArrayList<Round>();
	   this.isVarsity = isVarsity;
	   this.extraTeam = extra;
	   generateNext(extra, null, null);
      
   }
   public void generateNext(Team extra, List<Team> ppTeams, List<Team> nTeams) {
	   if(!this.isVarsity)
		   rounds.add(generateNextHelperJV(extra));
	   else if (current<=1)
		   rounds.add(generateNextHelper12(extra));
	   else
		   rounds.add(generateNextHelper34(extra, ppTeams, nTeams));
   }
   
   private Round generateNextHelperJV(Team extra) {
	   List<Team> affs = new ArrayList<Team>();
	   List<Team> negs = new ArrayList<Team>();
	   Collections.sort(teams);
	   if(current==0) {
		   boolean isAff = true;
		   for(Team t: teams) {
			   if(isAff)
				   affs.add(t);
            
			   else
				   negs.add(t);
			   isAff=!isAff;
		   }
		   return new Round(affs, negs, extra, judges);
	   } else {
		   for(Team t: teams) {
			   if(t.getIsAffirmative()[current-1]) {
				   negs.add(t);
			   } else {
               affs.add(t);
			   }
		   }
		   return new Round(affs, negs, extra, judges);
	   }
   }
   
   private Round generateNextHelper12(Team extra) {
	   List<Team> affs = new ArrayList<Team>();
	   List<Team> negs = new ArrayList<Team>();
	   Collections.sort(teams);
	   if(current==0) {
		   boolean isAff = true;
		   for(Team t: teams) {
			   if(isAff)
				   affs.add(t);
            
			   else
				   negs.add(t);
			   isAff=!isAff;
		   }
		   return new Round(affs, negs, extra, judges);
	   } else {
		   for(Team t: teams) {
			   if(t.getIsAffirmative()[current-1]) {
				   negs.add(t);
			   } else {
				   affs.add(t);
			   }
		   }
		   return new Round(affs, negs, this.rounds.get(0).getExtraTeam(), judges);
	   }
   }
   
   private Round generateNextHelper34(Team extra, List<Team> ppTeams, List<Team> nTeams) {
	   Round powerPaired = generatePowerPairedHelper(extra, ppTeams);
	   List<Judge> unusedJudges = new ArrayList<Judge>();
	   unusedJudges.addAll(powerPaired.getExtraJudges());
	   Round normal = generateNormalHelper(extra, nTeams, unusedJudges);
	   List<Debate> allDebates = new ArrayList<Debate>();
	   allDebates.addAll(powerPaired.getDebates());
	   allDebates.addAll(normal.getDebates());
	   return new Round(allDebates, extra, normal.getExtraJudges());
   }
   
   private Round generatePowerPairedHelper(Team extra, List<Team> ppTeams) {
	   List<Team> affs = new ArrayList<Team>();
	   List<Team> negs = new ArrayList<Team>();
	   Collections.sort(ppTeams);
	   if(current==2) {
		   boolean isAff = true;
		   for(Team t: ppTeams) {
			   if(isAff)
				   affs.add(t);
            
			   else
				   negs.add(t);
			   isAff=!isAff;
		   }
		   return new Round(affs, negs, extra, judges);
	   } else {
		   for(Team t: ppTeams) {
			   if(t.getIsAffirmative()[current-1]) {
				   negs.add(t);
			   } else {
				   affs.add(t);
			   }
		   }
		   return new Round(affs, negs, this.rounds.get(0).getExtraTeam(), judges);
	   }
   }
   
   private Round generateNormalHelper(Team extra, List<Team> nTeams, List<Judge> nJudges) {
	   List<Team> affs = new ArrayList<Team>();
	   List<Team> negs = new ArrayList<Team>();
	   Collections.sort(nTeams);
	   if(current==2) {
		   boolean isAff = true;
		   for(Team t: nTeams) {
			   if(isAff)
				   affs.add(t);
            
			   else
				   negs.add(t);
			   isAff=!isAff;
		   }
		   return new Round(affs, negs, extra, judges);
	   } else {
		   for(Team t: nTeams) {
			   if(t.getIsAffirmative()[current-1]) {
				   negs.add(t);
			   } else {
				   affs.add(t);
			   }
		   }
		   return new Round(affs, negs, this.rounds.get(0).getExtraTeam(), judges);
	   }
   }
   
   public void finalizeCurrent() {
      getCurrentRound().finalize(current);
      current++;
   }
   
   public Round getCurrentRound() {
      return rounds.get(current);
   }
   
   public Team getExtraTeam() {
      return extraTeam;
   }
   public void setExtraTeam(Team extraTeam) {
      this.extraTeam = extraTeam;
   }
   public int getCurrent() {
	   return current;
   }
}