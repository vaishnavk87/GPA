package ports.usecases.gpaTrend;

import java.util.List;

public class TrendModel {
    // TODO: replace lists with arrays
    public List<String> xData;
    public List<Double> yData;

    public TrendModel() {}

    public TrendModel(List<String> xData, List<Double> yData) {
        this.xData = xData;
        this.yData = yData;
    }
}
