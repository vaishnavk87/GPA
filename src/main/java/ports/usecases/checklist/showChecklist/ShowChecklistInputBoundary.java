package ports.usecases.checklist.showChecklist;

import ports.usecases.PathNotFoundError;

public interface ShowChecklistInputBoundary {
    ShowChecklistResponse execute(ShowChecklistRequest request) throws PathNotFoundError;
}
