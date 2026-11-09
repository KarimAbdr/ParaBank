package com.parabank.uat.bdd;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

/**
 * Entry point that lets Maven/JUnit run the .feature files from src/test/resources/features.
 * Other Cucumber settings (glue package, tag filter, report) are in junit-platform.properties.
 */
@Suite(failIfNoTests = false) // do not fail when a tag filter (-Dgroups=...) excludes all scenarios
@IncludeEngines("cucumber")
@SelectPackages("features")
public class RunCucumberTest {
}
