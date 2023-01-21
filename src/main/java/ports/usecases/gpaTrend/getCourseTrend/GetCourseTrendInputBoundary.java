package ports.usecases.gpaTrend.getCourseTrend;

import ports.usecases.PathNotFoundError;
import ports.usecases.gpaTrend.TrendModel;

public interface GetCourseTrendInputBoundary {
    TrendModel execute(String username, String courseCode) throws PathNotFoundError;
}
