package pl.jitsolutions.training.jitbet.business.team.control;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pl.jitsolutions.training.jitbet.business.team.entity.TeamNotFoundException;

@Provider
public class TeamNotFoundExceptionMapper implements ExceptionMapper<TeamNotFoundException> {
    @Override
    public Response toResponse(TeamNotFoundException exception) {



        return Response.status(Response.Status.NOT_FOUND).entity(exception.getMessage()).build();
    }
}
