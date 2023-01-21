package ports.usecases.assessment.setMark;

import ports.usecases.PathNotFoundError;

public interface SetMarkWindowInputBoundary {

    SetMarkWindowResponse execute(SetMarkWindowRequest request) throws PathNotFoundError;

}
