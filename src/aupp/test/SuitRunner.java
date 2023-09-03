package aupp.test;

import java.util.stream.Collectors;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class SuitRunner {

	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(AllTests.class);

		result
			.getFailures()
			.stream()
			.collect(Collectors.toMap(Failure::getTestHeader, Failure::getMessage))
			.forEach((key, value) -> System.out.println(key + " - " + value));

		System.out.println("\nSuccess test count: " + (result.getRunCount() - result.getFailureCount()));
		System.out.println("Failed test count: " + result.getFailureCount());
	}
}