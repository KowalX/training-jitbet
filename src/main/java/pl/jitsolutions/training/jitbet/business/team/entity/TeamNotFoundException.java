package pl.jitsolutions.training.jitbet.business.team.entity;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class TeamNotFoundException extends RuntimeException {
}
