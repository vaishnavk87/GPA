package ports.usecases.account.viewSemester;

import ports.usecases.PathNotFoundError;

public interface ViewSemesterInputBoundary {
    ViewSemesterResponse execute(ViewSemesterRequest request) throws PathNotFoundError;
}
