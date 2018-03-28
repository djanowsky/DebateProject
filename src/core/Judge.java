package debate.core;

import java.util.ArrayList;
import java.util.List;

public class Judge implements Comparable<Judge> {

	private String school;
	private String judgeID;
	private List<Team> teamsJudged;
	private boolean isExperienced;
	private int roundsJudged;

	public Judge(String school, String judgeID, boolean isExperienced) {
		
		this.school = school;
		this.judgeID = judgeID;
		this.isExperienced = isExperienced;
		this.teamsJudged = new ArrayList<Team>();
		this.roundsJudged = 0;
	}
	
	public Judge(String school, String judgeID) {
		
		this.school = school;
		this.judgeID = judgeID;
		this.isExperienced = false;
		this.teamsJudged = new ArrayList<Team>();
		this.roundsJudged = 0;
	}

	public int getRoundsJudged() {
		return roundsJudged;
	}

	public void incrementRoundsJudged() {
		this.roundsJudged++;
	}

	public void setJudgeID(String judgeID) {
		this.judgeID = judgeID;
	}

	public String getJudgeID() {
		return judgeID;
	}

	public List<Team> getTeamsJudged() {
		return teamsJudged;
	}

	public void setTeamsJudged(List<Team> teamsJudged) {
		this.teamsJudged = teamsJudged;
	}

	public void addTeamsJudged(Team t) {
		this.teamsJudged.add(t);
	}

	public boolean isExperienced() {
		return isExperienced;
	}

	public void setExperienced(boolean isExperienced) {
		this.isExperienced = isExperienced;
	}
	
	public void setSchool(String school) {
		this.school = school;
	}
	
	public String getSchool() {
		return this.school;
	}

	public boolean canJudge(Debate d) {
		if (teamsJudged.size() == 0 && !(this.school.equals(d.getAffTeam().getSchool()))
				&& !(this.school.equals(d.getNegTeam().getSchool())))
			return (!d.isPowerPaired() || this.isExperienced);
		return (!(this.teamsJudged.contains(d.getAffTeam())) && !(this.teamsJudged.contains(d.getNegTeam()))
				&& !(this.school.equals(d.getAffTeam().getSchool()))
				&& !(this.school.equals(d.getNegTeam().getSchool())) && (!d.isPowerPaired() || this.isExperienced));
	}

	public boolean hasJudgedSchool(String school) {
		if (teamsJudged.size() == 0)
			return true;
		for (Team t : teamsJudged)
		{
			if (t.getSchool().equals(school))
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(Judge other) {
		return this.judgeID.compareTo(other.judgeID);
	}

	@Override
	public String toString() {
		return this.judgeID;
	}

}