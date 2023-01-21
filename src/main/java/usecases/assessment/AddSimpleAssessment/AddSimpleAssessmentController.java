package usecases.assessment.AddSimpleAssessment;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.addSimpleAssessment.AddSimpleAssessmentRequest;
import ports.usecases.course.viewCourse.ViewCourseResponse;

import javax.swing.*;


public class AddSimpleAssessmentController {

    public AddSimpleAssessmentController(AddSimpleAssessmentRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        AddSimpleAssessmentUseCase usecase = new AddSimpleAssessmentUseCase(entityGateway, entityFactory, entityFactory, entityFactory);
        AddSimpleAssessmentPresenter presenter = new AddSimpleAssessmentPresenter(frame, entityGateway, entityFactory, parentFrame);

        try {
            ViewCourseResponse response = usecase.execute(request);
            presenter.presentSuccess(response);
        } catch (Throwable error) {
            presenter.presentError(error);
        }
    }
}
