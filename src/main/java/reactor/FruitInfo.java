package reactor;

import java.util.List;
import java.util.Map;

public class FruitInfo {
    private final List<String> distinctFruits;
    private final Map<String, Long> countFruits;

    public FruitInfo(List<String> distinctFruits, Map<String, Long> countFruits) {
        this.distinctFruits = distinctFruits;
        this.countFruits = countFruits;
    }

    @Override
    public int hashCode() {
        int result = distinctFruits != null ? distinctFruits.hashCode() : 0;
        result = 31 * result + (countFruits != null ? countFruits.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        FruitInfo fruitInfo = (FruitInfo) obj;

        if (distinctFruits != null ? !distinctFruits.equals(fruitInfo.distinctFruits) : fruitInfo.distinctFruits != null)
            return false;
        return countFruits != null ? countFruits.equals(fruitInfo.countFruits) : fruitInfo.countFruits == null;
    }

    @Override
    public String toString() {
        return "FruitInfo{" +
                "distinctFruits=" + distinctFruits +
                ", countFruits=" + countFruits +
                '}';
    }
}
