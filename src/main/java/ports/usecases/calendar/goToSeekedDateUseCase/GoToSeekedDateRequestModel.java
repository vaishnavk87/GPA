package ports.usecases.calendar.goToSeekedDateUseCase;

import java.time.*;

public class GoToSeekedDateRequestModel {
    private LocalDate seekedDate;

    public GoToSeekedDateRequestModel(LocalDate seekedDate) {
        this.seekedDate = seekedDate;
    }

    void setSeekedDate(LocalDate seekedDate) {
        this.seekedDate = seekedDate;
    }

    public LocalDate getSeekedDate() {
        return seekedDate;
    }
}
