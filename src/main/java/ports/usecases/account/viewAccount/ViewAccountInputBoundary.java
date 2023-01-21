package ports.usecases.account.viewAccount;

import ports.usecases.PathNotFoundError;
import ports.usecases.account.viewSemester.ViewSemesterResponse;

public interface ViewAccountInputBoundary {
    ViewSemesterResponse execute(ViewAccountRequest request) throws PathNotFoundError;
}
