package pl.jitsolutions.training.jitbet.business.team.entity;

import java.io.Serializable;
import java.time.Year;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Year established;

    private int pointsSum;

    private int matchesWon;

    private int matchesLost;

    private int matchesDrawn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getEstablished() {
        return established;
    }

    public void setEstablished(Year established) {
        this.established = established;
    }

    public int getPointsSum() {
        return pointsSum;
    }

    public void setPointsSum(int pointsSum) {
        this.pointsSum = pointsSum;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        return matchesLost;
    }

    public void setMatchesLost(int matchesLost) {
        this.matchesLost = matchesLost;
    }

    public int getMatchesDrawn() {
        return matchesDrawn;
    }

    public void setMatchesDrawn(int matchesDrawn) {
        this.matchesDrawn = matchesDrawn;
    }

//    public List<Match> getMatches() {
//        return matches;
//    }
//
//    public void setMatches(List<Match> matches) {
//        this.matches = matches;
//    }
}
