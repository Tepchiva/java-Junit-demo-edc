import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        HouseholdConsumerTest.class,
        HouseholdConsumerNegativeTest.class,
        BusinessConsumerTest.class,
        BusinessConsumerNegativeTest.class,
        IndustrialConsumerTest.class
})
public class AllTests {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AllTests.class);
    }
}
