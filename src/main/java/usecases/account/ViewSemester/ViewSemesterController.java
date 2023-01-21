package usecases.account.ViewSemester;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.viewSemester.ViewSemesterRequest;
import ports.usecases.account.viewSemester.ViewSemesterResponse;

import javax.swing.*;

public class ViewSemesterController {

    public ViewSemesterController(ViewSemesterRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        ViewSemesterUseCase usecase = new ViewSemesterUseCase(entityGateway);
        ViewSemesterPresenter presenter = new ViewSemesterPresenter(frame, entityGateway, entityFactory, parentFrame);

        ViewSemesterResponse response = usecase.execute(request);
        presenter.present(response);
    }
}
