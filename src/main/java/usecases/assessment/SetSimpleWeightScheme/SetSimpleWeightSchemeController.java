package usecases.assessment.SetSimpleWeightScheme;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.setWeightScheme.SetSimpleWeightSchemeRequest;
import ports.usecases.assessment.setWeightScheme.SetSimpleWeightSchemeResponse;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;

import javax.swing.*;

public class SetSimpleWeightSchemeController {
    public SetSimpleWeightSchemeController(SetSimpleWeightSchemeRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame){
        SetSimpleWeightSchemeUseCase usecase = new SetSimpleWeightSchemeUseCase(entityGateway, entityFactory, entityFactory, entityFactory);
        SetSimpleWeightSchemePresenter presenter = new SetSimpleWeightSchemePresenter(frame, entityGateway, entityFactory, parentFrame);

        try {
            ViewAssessmentResponse response = usecase.execute(request);
            presenter.presentSuccess(response);
        } catch (Throwable error) {
            presenter.presentError(error);
        }
    }


}
