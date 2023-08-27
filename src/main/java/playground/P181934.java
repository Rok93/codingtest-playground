package playground;

import java.util.Arrays;
import java.util.function.BiFunction;

public class P181934 {

    public int solution(String ineq, String eq, int n, int m) {
        ComparisonOperator comparisonOperator = ComparisonOperator.of(ineq, eq);
        return comparisonOperator.operate(n, m) ? 1 : 0;
    }

    /**
     * 비교 연산자
     */
    enum ComparisonOperator {
        RIGHT_BIGGER_CURSOR("<", "!", (n, m) -> n < m),
        RIGHT_EQUAL_AND_BIGGER_CURSOR("<", "=", (n, m) -> n <= m),
        LEFT_BIGGER_CURSOR(">", "!", (n, m) -> n > m),
        LEFT_EUQAL_AND_BIGGER_CURSOR(">", "=", (n, m) -> n >= m)
        ;


        private final String ineq;
        private final String eq;
        private final BiFunction<Integer, Integer, Boolean> operate;

        ComparisonOperator(String ineq, String eq, BiFunction<Integer, Integer, Boolean> operate) {
            this.ineq = ineq;
            this.eq = eq;
            this.operate = operate;
        }

        public boolean operate(int n, int m) {
            return operate.apply(n, m);
        }

        public static ComparisonOperator of(String ineq, String eq) {
            return Arrays.stream(values())
                .filter(it -> it.ineq.equals(ineq) && it.eq.equals(eq))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 symbol입니다. symbol:" + ineq));
        }
    }
}
