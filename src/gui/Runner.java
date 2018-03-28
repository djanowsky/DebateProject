package debate.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.swing.JOptionPane;

import debate.core.Judge;
import debate.core.Team;
import debate.core.Tournament;

public class Runner {

	public static void main(String[] args) {
		int exitProgram = JOptionPane.showConfirmDialog(null, "If you would like to close the program at any point, "
				+ "press Cancel on any message box.", "How to End Program", JOptionPane.OK_CANCEL_OPTION);
		if(exitProgram==JOptionPane.CANCEL_OPTION)
			return;
		
		int response = JOptionPane.showConfirmDialog(null, "Is this a Varsity Debate?", 
				"Varsity Debate?", JOptionPane.YES_NO_CANCEL_OPTION);
		
		if(response==JOptionPane.CANCEL_OPTION)
			return;
		
		//JV Debate
		if(response==JOptionPane.NO_OPTION) {
			boolean haveNumSchools = false;
			int numSchools = 0;
			attempt: while(!haveNumSchools) {
				String numberOfSchools = JOptionPane.showInputDialog("How many schools are at this debate? (Numbers please.)");
				if(numberOfSchools==null)
					return;
				try {
					numSchools = Integer.parseInt(numberOfSchools);
				} catch (NumberFormatException e) {
					continue attempt;
				}
				haveNumSchools=true;
			}
			List<String> schools = new ArrayList<String>();
			for(int i=0; i<numSchools; i++) {
				String schoolName = JOptionPane.showInputDialog("Which school is letter " + (char)(i+65) + "?");
				if(schoolName==null)
					return;
				schools.add(schoolName);
			}
			
			int[] schoolTeams = new int[numSchools];
			int[] schoolJudges = new int[numSchools];
			String teamNum;
			String judgeNum;
			
			teamsAndJudges: for(int i=0; i<numSchools; i++) {
				
				teamNum = JOptionPane.showInputDialog("How many teams does " + schools.get(i) + " have? (Numbers please.)");
				if(teamNum==null)
					return;
				try {
					schoolTeams[i] = Integer.parseInt(teamNum);
				} catch (NumberFormatException e) {
					i--;
					continue teamsAndJudges;
				}
				
				judgeNum = JOptionPane.showInputDialog("How many judges does " + schools.get(i) + " have? (Numbers please.)");
				if(judgeNum==null)
					return;
				try {
					schoolJudges[i] = Integer.parseInt(judgeNum);
				} catch (NumberFormatException e) {
					i--;
					continue teamsAndJudges;
				}
			}
			ArrayList<Team> aff = new ArrayList<Team>();
			ArrayList<Team> neg = new ArrayList<Team>();
			ArrayList<Team> teams = new ArrayList<Team>();
			List<Judge> judges = new ArrayList<Judge>();
			Team extraTeam = null;
			int asciiSchools = numSchools+64;
			String schoolLetter;
		   	boolean isAff = true;
		   	for(int i=65; i<=asciiSchools; i++) {
		   		schoolLetter = new String ("" + (char)i);
		   		for(int j=1; j<=schoolTeams[i-65]; j++) {
		   			if(isAff) {
		   				aff.add(new Team(schoolLetter, schoolLetter+j));
				   	} else {
				   		neg.add(new Team(schoolLetter, schoolLetter+j));
				   	}
				   	isAff=!isAff;
			   	}
			   	for(int k=1; k<=schoolJudges[i-65]; k++) {
			   		judges.add(new Judge(schoolLetter, "J"+schoolLetter+k, true));
			   	}
		   	}
		   	teams.addAll(aff);
	   		teams.addAll(neg);
	   		
	   		if(judges.size()<aff.size()) {
				JOptionPane.showMessageDialog(null, "There are not enough judges for this many teams.",
						"Not Enough Judges", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(teams.size()%2!=0) {
				String extraTeamID = JOptionPane.showInputDialog("There are an odd number of teams, please"
						+ " input a team to sit for the first half of the debate. Enter such as \"C3\" with all caps.");
				if(extraTeamID==null)
					return;
				for (Team t: aff) {
					if(t.getTeamID().equals(extraTeamID)) {
						extraTeam = t;
						aff.remove(t);
					}
				}
				for (Team t: neg) {
					if(t.getTeamID().equals(extraTeamID)) {
						extraTeam = t;
						neg.remove(t);
					}
				}
			}
			
			Tournament torn = new Tournament(teams, judges, extraTeam, false);
			TableMaker.createAndShowGUI(torn);
			torn.finalizeCurrent();
			exitProgram = JOptionPane.showConfirmDialog(null, "Are you ready for Round " + (torn.getCurrent()+1) + "? Press OK"
					+ " when you are ready.", "Ready for Round 2?", JOptionPane.OK_CANCEL_OPTION);
			if(exitProgram==JOptionPane.CANCEL_OPTION)
				return;
			
			if(teams.size()%2!=0) {
				String extraTeamID = JOptionPane.showInputDialog("There are an odd number of teams, please"
						+ " input a team to sit for the second half of the debate. Enter such as \"C3\" with all caps.");
				if(extraTeamID==null)
					return;
				if(extraTeam.getTeamID().equals(extraTeamID)) { }
				else {
					for (Team t: aff) {
						if(t.getTeamID().equals(extraTeamID)) {
							aff.add(extraTeam);
							extraTeam = t;
							aff.remove(t);
						}
					}
					for (Team t: neg) {
						if(t.getTeamID().equals(extraTeamID)) {
							neg.add(extraTeam);
							extraTeam = t;
							neg.remove(t);
						}
					}
				}
			}
			torn.generateNext(extraTeam, null, null);
			TableMaker.createAndShowGUI(torn);
			torn.finalizeCurrent();
		}
		
		
		
		//Varsity Debate
		else {
			boolean haveNumSchools = false;
			int numSchools = 0;
			attempt: while(!haveNumSchools) {
				String numberOfSchools = JOptionPane.showInputDialog("How many schools are at this debate? (Numbers please.)");
				if(numberOfSchools==null)
					return;
				try {
					numSchools = Integer.parseInt(numberOfSchools);
				} catch (NumberFormatException e) {
					continue attempt;
				}
				haveNumSchools=true;
			}
			List<String> schools = new ArrayList<String>();
			for(int i=0; i<numSchools; i++) {
				String schoolName = JOptionPane.showInputDialog("Which school is letter " + (char)(i+65) + "?");
				if(schoolName==null)
					return;
				schools.add(schoolName);
			}
			
			int[] schoolTeams = new int[numSchools];
			int[] schoolJudges = new int[numSchools];
			String teamNum;
			String judgeNum;
			
			teamsAndJudges: for(int i=0; i<numSchools; i++) {
				
				teamNum = JOptionPane.showInputDialog("How many teams does " + schools.get(i) + " have? (Numbers please.)");
				if(teamNum==null)
					return;
				try {
					schoolTeams[i] = Integer.parseInt(teamNum);
				} catch (NumberFormatException e) {
					i--;
					continue teamsAndJudges;
				}
				
				judgeNum = JOptionPane.showInputDialog("How many judges does " + schools.get(i) + " have? (Numbers please.)");
				if(judgeNum==null)
					return;
				try {
					schoolJudges[i] = Integer.parseInt(judgeNum);
				} catch (NumberFormatException e) {
					i--;
					continue teamsAndJudges;
				}
			}
			ArrayList<Team> aff = new ArrayList<Team>();
			ArrayList<Team> neg = new ArrayList<Team>();
			ArrayList<Team> teams = new ArrayList<Team>();
			List<Judge> judges = new ArrayList<Judge>();
			Team extraTeam = null;
			int asciiSchools = numSchools+64;
			String schoolLetter;
		   	boolean isAff = true;
		   	for(int i=65; i<=asciiSchools; i++) {
		   		schoolLetter = new String ("" + (char)i);
		   		for(int j=1; j<=schoolTeams[i-65]; j++) {
		   			if(isAff) {
		   				aff.add(new Team(schoolLetter, schoolLetter+j));
				   	} else {
				   		neg.add(new Team(schoolLetter, schoolLetter+j));
				   	}
				   	isAff=!isAff;
			   	}
			   	for(int k=1; k<=schoolJudges[i-65]; k++) {
			   		judges.add(new Judge(schoolLetter, "J"+schoolLetter+k, true));
			   	}
		   	}
		   	teams.addAll(aff);
	   		teams.addAll(neg);
			if(judges.size()<aff.size()) {
				JOptionPane.showMessageDialog(null, "There are not enough judges for this many teams.",
						"Not Enough Judges", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(teams.size()%2!=0) {
				String extraTeamID = JOptionPane.showInputDialog("There are an odd number of teams, please"
						+ " input a team to sit for the first half of the debate. Enter such as \"C3\" with all caps.");
				if(extraTeamID==null)
					return;
				for (Team t: aff) {
					if(t.getTeamID().equals(extraTeamID)) {
						extraTeam = t;
						aff.remove(t);
					}
				}
				for (Team t: neg) {
					if(t.getTeamID().equals(extraTeamID)) {
						extraTeam = t;
						neg.remove(t);
					}
				}
			}
			
			Tournament torn = new Tournament(teams, judges, extraTeam, true);
			TableMaker.createAndShowGUI(torn);
			torn.finalizeCurrent();
			exitProgram = JOptionPane.showConfirmDialog(null, "Are you ready for Round " + (torn.getCurrent()+1) + "? Press OK"
					+ " when you are ready.", "Ready for Round " + (torn.getCurrent()+1) + "?",
					JOptionPane.OK_CANCEL_OPTION);
			if(exitProgram==JOptionPane.CANCEL_OPTION)
				return;
			
			torn.generateNext(extraTeam, null, null);
			TableMaker.createAndShowGUI(torn);
			torn.finalizeCurrent();
			
			exitProgram = JOptionPane.showConfirmDialog(null, "Are you ready for Round " + (torn.getCurrent()+1) + "? Press OK"
					+ " when you are ready.", "Ready for Round " + (torn.getCurrent()+1) + "?",
					JOptionPane.OK_CANCEL_OPTION);
			if(exitProgram==JOptionPane.CANCEL_OPTION)
				return;
			
			List<Team> ppAff = new ArrayList<Team>();
			List<Team> ppNeg = new ArrayList<Team>();
			List<Team> ppTeams = new ArrayList<Team>();
			List<Team> nAff = new ArrayList<Team>();
			List<Team> nNeg = new ArrayList<Team>();
			List<Team> nTeams = new ArrayList<Team>();
			Collections.sort(teams);
			
			boolean pIsAff = true;
			boolean nIsAff = true;
			boolean isNotOkay=true;
			
			while(isNotOkay) {
				ppAff.clear();
				ppNeg.clear();
				nAff.clear();
				nNeg.clear();
				for(Team t: teams) {
					int powerPair = JOptionPane.showConfirmDialog(null, "Is team " + t.getTeamID() + " power paired?", 
							"Power Paired?", JOptionPane.YES_NO_CANCEL_OPTION);
					if(powerPair==JOptionPane.CANCEL_OPTION)
						return;
					if(powerPair==JOptionPane.YES_OPTION) {
						if(pIsAff)
							ppAff.add(t);
						else
							ppNeg.add(t);
						pIsAff = !pIsAff;
					
					} else {
						if(nIsAff)
							nAff.add(t);
						else
							nNeg.add(t);
						nIsAff = !nIsAff;
					}
				}
				int isNotOkayInt = JOptionPane.showConfirmDialog(null, "Were any mistakes made in power pairing teams?", "Mistakes?",
						JOptionPane.YES_NO_CANCEL_OPTION);
				isNotOkay=(isNotOkayInt==JOptionPane.YES_OPTION);
			}
			
			ppTeams.addAll(ppAff);
			ppTeams.addAll(ppNeg);
			nTeams.addAll(nAff);
			nTeams.addAll(nNeg);
			
			
			if(nTeams.size()%2!=0) {
				String extraTeamID = JOptionPane.showInputDialog("There are an odd number of teams, please"
						+ " input a team to sit for the second half of the debate. Enter such as \"C3\" with all caps.");
				if(extraTeamID==null)
					return;
				if(extraTeam.getTeamID().equals(extraTeamID)) { }
				else {
					for (Team t: nAff) {
						if(t.getTeamID().equals(extraTeamID)) {
							nAff.add(extraTeam);
							extraTeam = t;
							nAff.remove(t);
						}
					}
					for (Team t: nNeg) {
						if(t.getTeamID().equals(extraTeamID)) {
							nNeg.add(extraTeam);
							extraTeam = t;
							nNeg.remove(t);
						}
					}
				}
			}
			
			
			torn.generateNext(extraTeam, ppTeams, nTeams);
			TableMaker.createAndShowGUI(torn);
			torn.finalizeCurrent();
			
			exitProgram = JOptionPane.showConfirmDialog(null, "Are you ready for Round " + (torn.getCurrent()+1) + "? Press OK"
					+ " when you are ready.", "Ready for Round " + (torn.getCurrent()+1) + "?",
					JOptionPane.OK_CANCEL_OPTION);
			if(exitProgram==JOptionPane.CANCEL_OPTION)
				return;
			
			torn.generateNext(extraTeam, ppTeams, nTeams);
			TableMaker.createAndShowGUI(torn);
			torn.finalizeCurrent();
		}

	}
}
