package ports.usecases.calendar.goToSeekedDateUseCase;

public class GoToSeekedDateInteractor implements GoToSeekedDateInputBoundary {
    GoToSeekedDateOutputBoundary goToSeekedDateOutputBoundary;

    public GoToSeekedDateInteractor(GoToSeekedDateOutputBoundary goToSeekedDateOutputBoundary) {
        this.goToSeekedDateOutputBoundary = goToSeekedDateOutputBoundary;
    }

    @Override
    public GoToSeekedDateResponseModel execute(GoToSeekedDateRequestModel requestModel) {
        return goToSeekedDateOutputBoundary.prepareSuccessView(requestModel);
    }
}
