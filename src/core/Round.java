package debate.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Round
{

	private List<Debate> debates;
	private Team extraTeam;
	private List<Judge> extraJudges;
	private boolean finalized;

	public Round(List<Team> aff, List<Team> neg, Team extraTeam, List<Judge> judges) {
		this.debates = new ArrayList<Debate>();
		this.extraJudges = new ArrayList<Judge>();
		this.extraTeam = extraTeam;
		this.debates = addJudges(createRound(aff, neg, false), judges);
		Collections.shuffle(this.debates);
		this.extraJudges.removeAll(this.getJudges());
		this.finalized = false;
	}

	public Round(List<Team> aff, List<Team> neg, List<Team> affPP, List<Team> negPP, Team extraTeam,
			List<Judge> judges) {
		this.debates = new ArrayList<Debate>();
		this.extraJudges = new ArrayList<Judge>();
		this.extraJudges.addAll(judges);
		this.extraTeam = extraTeam;
		List<Debate> debatesPP = addJudges(createRound(affPP, negPP, true), extraJudges);
		for (Debate d : debatesPP) {
			extraJudges.remove(d.getJudge());
		}
		List<Debate> debatesN = addJudges(createRound(aff, neg, false), extraJudges);
		for (Debate d : debatesN) {
			extraJudges.remove(d.getJudge());
		}
		this.debates.addAll(debatesPP);
		this.debates.addAll(debatesN);
		Collections.shuffle(this.debates);
		this.finalized = false;
	}
	
	public Round(List<Debate> debates, Team extraTeam, List<Judge> extraJudges) {
		this.debates = debates;
		this.extraTeam = extraTeam;
		this.extraJudges = extraJudges;
		this.finalized = false;
	}
	
	private List<Debate> createRound(List<Team> aff, List<Team> neg, boolean power) {
		Collections.shuffle(aff);
		Collections.shuffle(neg);
		List<Debate> result = new ArrayList<Debate>();
		List<List<Team>> affSchools = new ArrayList<List<Team>>();
		List<List<Team>> negSchools = new ArrayList<List<Team>>();
		List<String> schools = new ArrayList<String>();
		List<List<String>> matchUps = new ArrayList<List<String>>();
		for (Team t : aff) {
			if (!schools.contains(t.getSchool())) {
				schools.add(t.getSchool());
				affSchools.add(new ArrayList<Team>());
				negSchools.add(new ArrayList<Team>());
				matchUps.add(new ArrayList<String>());
			}
		}
		for (Team t : neg) {
			if (!schools.contains(t.getSchool())) {
				schools.add(t.getSchool());
				affSchools.add(new ArrayList<Team>());
				negSchools.add(new ArrayList<Team>());
				matchUps.add(new ArrayList<String>());
			}
		}

		int rating = 1000;
		int attemptNum = 0;
		int maxRating = 0;
		attempt: while (rating > maxRating) {
			maxRating++;
			attemptNum++;
			result.clear();
			rating = 0;
			Collections.shuffle(neg);
			for (List<String> l : matchUps)
				l.clear();

			for (List<Team> l : negSchools)
				l.clear();

			for (List<Team> l : affSchools)
				l.clear();

			for (Team t : neg)
				negSchools.get(schools.indexOf(t.getSchool())).add(t);

			for (Team t : aff)
				affSchools.get(schools.indexOf(t.getSchool())).add(t);

			for (int i = 0; i < affSchools.size(); i++) {
				int largestIndex = i;
				for (int j = i + 1; j < affSchools.size(); j++) {
					if (affSchools.get(largestIndex).size() < affSchools.get(j).size())
						largestIndex = j;
				}
				Collections.swap(affSchools, i, largestIndex);
			}

			Collections.shuffle(negSchools);

			int negIndex = 0, negIndexInitial = 0;
			for (List<Team> affs : affSchools) {
				for (int i = affs.size() - 1; i >= 0; i--) {
					Team teamAff = affs.get(i);
					negIndexInitial = negIndex;
					search: while (true) {
						for (int j = negSchools.get(negIndex).size() - 1; j >= 0; j--) {
							Team teamNeg = negSchools.get(negIndex).get(j);
							if (teamAff.canDebate(teamNeg)) {
								result.add(new Debate(teamAff, teamNeg));
								affs.remove(teamAff);
								negSchools.get(negIndex).remove(teamNeg);
								negIndex = (negIndex + 1) % negSchools.size();
								if (teamAff.hasPlayedSchool(teamNeg.getSchool())
										|| teamNeg.hasPlayedSchool(teamAff.getSchool())) {
									rating += 25;
								}
								if (!(matchUps.get(schools.indexOf(teamAff.getSchool())).contains(teamNeg.getSchool()))) {
									matchUps.get(schools.indexOf(teamAff.getSchool())).add(teamNeg.getSchool());
									matchUps.get(schools.indexOf(teamNeg.getSchool())).add(teamAff.getSchool());
								} else {
									rating += 100;
								}

								break search;
							}
						}
						negIndex = (negIndex + 1) % negSchools.size();
						if (negIndex == negIndexInitial) {
							rating = 1000;
							continue attempt;
						}
					}
				}
			}
		}
		for (Debate d : result) {
			d.setPowerPair(power);
		}
		return result;
	}

	private List<Debate> addJudges(List<Debate> debates, List<Judge> judges) {
		List<Debate> tempDebates = new ArrayList<Debate>();
		List<Judge> tempJudges = new ArrayList<Judge>();
		List<String> schools = new ArrayList<String>();
		List<List<String>> matchUps = new ArrayList<List<String>>();
		int round = 0;
		for (Judge j : judges) {
			if (!schools.contains(j.getSchool())) {
				schools.add(j.getSchool());
				matchUps.add(new ArrayList<String>());
			}
			if (j.getRoundsJudged() > round) {
				round = j.getRoundsJudged();
			}
		}

		int rating = 1000;
		int attemptNum = 0;
		int maxRating = 0;

		attempt: while (rating > maxRating) {
			maxRating++;
			attemptNum++;
			rating = 0;
			this.extraJudges.clear();
			this.extraJudges.addAll(judges);
			for (List<String> l : matchUps)
				l.clear();
			tempJudges.clear();
			tempJudges.addAll(judges);
			Collections.shuffle(tempJudges);
			tempDebates.clear();
			tempDebates.addAll(debates);
			Collections.shuffle(tempDebates);
			search: for (Debate d : tempDebates) {
				for (int i = 0; i < tempJudges.size(); i++) {
					if (tempJudges.get(i).canJudge(d) && tempJudges.get(i).getRoundsJudged() < round) {
						d.setJudge(tempJudges.get(i));
						for (Team t : tempJudges.get(i).getTeamsJudged()) {
							if (d.getAffTeam().getSchool() == t.getSchool())
								rating += 25;
							if (d.getNegTeam().getSchool() == t.getSchool())
								rating += 25;
						}
						if (matchUps.get(schools.indexOf(tempJudges.get(i).getSchool())).contains(
								d.getAffTeam().getSchool())) {
							rating += 25;
						} else {
							matchUps.get(schools.indexOf(tempJudges.get(i).getSchool()))
									.add(d.getNegTeam().getSchool());
						}
						if (matchUps.get(schools.indexOf(tempJudges.get(i).getSchool())).contains(
								d.getNegTeam().getSchool())) {
							rating += 25;
						} else {
							matchUps.get(schools.indexOf(tempJudges.get(i).getSchool()))
									.add(d.getAffTeam().getSchool());
						}
						this.extraJudges.remove(tempJudges.get(i));
						tempJudges.remove(i);
						continue search;
					}
					if (tempJudges.get(i).canJudge(d)) {
						d.setJudge(tempJudges.get(i));
						for (Team t : tempJudges.get(i).getTeamsJudged()) {
							if (d.getAffTeam().getSchool() == t.getSchool())
								rating += 25;
							if (d.getNegTeam().getSchool() == t.getSchool())
								rating += 25;
						}
						if (matchUps.get(schools.indexOf(tempJudges.get(i).getSchool())).contains(
								d.getAffTeam().getSchool())) {
							rating += 25;
						} else {
							matchUps.get(schools.indexOf(tempJudges.get(i).getSchool()))
									.add(d.getNegTeam().getSchool());
						}
						if (matchUps.get(schools.indexOf(tempJudges.get(i).getSchool())).contains(
								d.getNegTeam().getSchool())) {
							rating += 25;
						} else {
							matchUps.get(schools.indexOf(tempJudges.get(i).getSchool()))
									.add(d.getAffTeam().getSchool());
						}
						this.extraJudges.remove(tempJudges.get(i));
						tempJudges.remove(i);
						continue search;
					}
				}
				rating = 1000;
				continue attempt;
			}
		}
		return tempDebates;
	}

	public List<Debate> getDebates() {
		return this.debates;
	}


	public List<Judge> getJudges() {
		List<Judge> result = new ArrayList<Judge>();
		for (Debate d : debates) {
			result.add(d.getJudge());
		}
		return result;
	}

	public void finalize(int round) {
		for (Debate d : debates) {
			d.finalize(round);
		}
		this.finalized = true;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public String toString() {
		String result = "";
		for (Debate d : debates)
			result += d.toString() + "\n";
		return result;
	}

	public void setExtraTeam(Team extraTeam) {
		this.extraTeam = extraTeam;
	}

	public Team getExtraTeam() {
		return extraTeam;
	}

	public void setExtraJudges(List<Judge> extraJudges) {
		this.extraJudges = extraJudges;
	}

	public List<Judge> getExtraJudges() {
		return extraJudges;
	}

}