package calendarScreens;

import ports.usecases.calendar.goToSeekedDateUseCase.GoToSeekedDateOutputBoundary;
import ports.usecases.calendar.goToSeekedDateUseCase.GoToSeekedDateRequestModel;
import ports.usecases.calendar.goToSeekedDateUseCase.GoToSeekedDateResponseModel;

public class GoToSeekedDatePresenter implements GoToSeekedDateOutputBoundary {
    @Override
    public GoToSeekedDateResponseModel prepareSuccessView(GoToSeekedDateRequestModel goToSeekedDateRequestModel) {
        GoToSeekedDateResponseModel responseModel = new GoToSeekedDateResponseModel(goToSeekedDateRequestModel.getSeekedDate());
        return responseModel;
    }
}
