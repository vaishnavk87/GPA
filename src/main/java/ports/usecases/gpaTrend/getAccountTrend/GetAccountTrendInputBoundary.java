package ports.usecases.gpaTrend.getAccountTrend;

import ports.usecases.PathNotFoundError;
import ports.usecases.gpaTrend.TrendModel;

public interface GetAccountTrendInputBoundary {
    TrendModel execute(String username) throws PathNotFoundError;
}
