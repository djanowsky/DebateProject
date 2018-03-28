package debate.core;

import java.util.ArrayList;
import java.util.List;

public class Team implements Comparable<Team> {

	private String teamID;
	private String school;
	private List<Team> played;
	private boolean[] isAffirmative;
	private boolean isPowerPaired;

	public Team(String school, String teamID) {// TeamID is the number team
		this.school = school;// at the school
		this.teamID = teamID; 
		this.played = new ArrayList<Team>();
		this.isAffirmative = new boolean[4];
		this.isPowerPaired = false;
	}

	public Team(Team t) {
		this.school = new String(t.school);
		this.teamID = new String(t.teamID);
		this.played = new ArrayList<Team>();
		
		for (Team te : t.played)
			this.played.add(new Team(te));
		
		this.isAffirmative = t.isAffirmative;
		this.isPowerPaired = false;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getTeamID() {
		return teamID;
	}
	
	public void setPlayed(List<Team> played) {
		this.played = played;
	}

	public List<Team> getPlayed() {
		return played;
	}
	
	public void addPlayed(Team t) {
		this.played.add(t);
	}

	public String getSchool() {
		return school;
	}

	public void setIsAffirmative(boolean isAff, int round) {// Round is 0, 1, 2, or 3, NOT 4
		this.isAffirmative[round] = isAff;
	}

	public boolean[] getIsAffirmative() {
		return isAffirmative;
	}
	
	public void setIsPowerPaired(boolean isPowerPaired) {
		this.isPowerPaired = isPowerPaired;
	}
	
	public boolean getIsPowerPaired() {
		return this.isPowerPaired;
	}

	public boolean canDebate(Team other) {
		if (this.played.size() == 0 && !this.school.equals(other.school))
			return true;
		return (!this.getSchool().equals(other.getSchool()) && !this.played.contains(other));
	}


	public boolean hasPlayedSchool(String school) {
		if (played.size() == 0)
			return false;
		for (Team t : played)
		{
			if (t.school.equals(school))
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(Team other) {
		return this.school.compareTo(other.school);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return teamID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return ((Team) obj).teamID.equals(teamID);
	}
}
