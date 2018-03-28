package debate.core;

public class Debate {

    private Team affTeam, negTeam;
    private Judge judge;
    private boolean powerPaired;

    public Debate() {
       
    }
    
    public Debate(Team aff, Team neg) {
    	this.affTeam = aff;
    	this.negTeam = neg;
    	this.powerPaired = false;
    }
    
    public Debate(Team aff, Team neg, Judge j) {
        this.affTeam = aff;
        this.negTeam = neg;
        this.judge = j;
        this.powerPaired = false;
    }

    public Team getAffTeam() {
        return affTeam;
    }

    public void setAffTeam(Team t1) {
        this.affTeam = t1;
    }

    public Team getNegTeam() {
        return negTeam;
    }

    public void setNegTeam(Team t2) {
        this.negTeam = t2;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge j) {
        this.judge = j;
    }

    public boolean isPowerPaired() {
    	return powerPaired;
    }

    public void setPowerPair(boolean powerPair) {
    	this.powerPaired = powerPair;
    }

    public void finalize(int round) {
    	this.affTeam.addPlayed(negTeam);
    	this.affTeam.setIsAffirmative(true, round);
    	this.negTeam.addPlayed(affTeam);
    	this.negTeam.setIsAffirmative(false, round);
    	this.judge.addTeamsJudged(negTeam);
    	this.judge.addTeamsJudged(affTeam);
    	this.judge.incrementRoundsJudged();
    }
    
    public String toString() {
    	if(this.judge==null)
    		return this.affTeam.getTeamID() + " - " + this.negTeam.getTeamID() + "  " + this.judge;
    	return this.affTeam.getTeamID() + " - " + this.negTeam.getTeamID() + "  Judge: " + this.judge.getJudgeID();
    }
}