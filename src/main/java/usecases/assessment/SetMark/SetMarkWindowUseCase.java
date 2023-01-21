package usecases.assessment.SetMark;

import ports.database.EntityGateway;
import ports.usecases.assessment.setMark.SetMarkWindowInputBoundary;
import ports.usecases.assessment.setMark.SetMarkWindowRequest;
import ports.usecases.assessment.setMark.SetMarkWindowResponse;

public class SetMarkWindowUseCase implements SetMarkWindowInputBoundary {

    public final EntityGateway entityGateway;

    public SetMarkWindowUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public SetMarkWindowResponse execute(SetMarkWindowRequest request) {
        return createResponse(request, request.username);
    }

    private SetMarkWindowResponse createResponse(SetMarkWindowRequest request, String username) {
        SetMarkWindowResponse response = new SetMarkWindowResponse();
        response.username = username;
        response.instanceName = request.instanceName;
        response.courseCode = request.courseCode;
        response.assessmentTitle = request.assessmentTitle;
        response.instanceNumber = request.instanceNumber;
        return response;
    }
}
