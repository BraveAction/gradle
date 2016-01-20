/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.performance

import org.gradle.performance.categories.Experiment
import org.gradle.performance.categories.JavaPerformanceTest
import org.junit.experimental.categories.Category
import spock.lang.Unroll

import static org.gradle.performance.measure.DataAmount.kbytes
import static org.gradle.performance.measure.DataAmount.mbytes
import static org.gradle.performance.measure.Duration.millis

@Category([Experiment, JavaPerformanceTest])
class JavaFullBuildDaemonPerformanceTest extends AbstractCrossVersionPerformanceTest {
    @Unroll("full build Java build - #testProject")
    def "full build Java build"() {
        given:
        runner.testId = "full build Java build $testProject (daemon)"
        runner.previousTestIds = ["daemon clean build $testProject"]
        runner.testProject = testProject
        runner.useDaemon = true
        runner.tasksToRun = ['clean', 'build']
        runner.maxExecutionTimeRegression = maxTimeReg
        runner.maxMemoryRegression = maxMemReg
        runner.targetVersions = ['1.0', '2.0', '2.8', '2.10', 'last']
        runner.gradleOpts = ["-Xms1g", "-Xmx1g", "-XX:MaxPermSize=256m"]

        when:
        def result = runner.run()

        then:
        result.assertCurrentVersionHasNotRegressed()

        where:
        testProject | maxTimeReg   | maxMemReg
        "small"     | millis(500)  | kbytes(1500)
        "multi"     | millis(1000) | mbytes(10)
    }
}