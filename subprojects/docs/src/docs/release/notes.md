## New and noteworthy

Here are the new features introduced in this Gradle release.

<!--
IMPORTANT: if this is a patch release, ensure that a prominent link is included in the foreword to all releases of the same minor stream.
Add-->

<!--
### Example new and noteworthy
-->

### More convenient testing of Gradle plugins

Gradle 2.6 introduced the “[Gradle TestKit](userguide/test_kit.html)” which made it easier to thoroughly test Gradle plugins.
The TestKit has improved and matured with subsequent Gradle releases.
Starting with Gradle 2.13, less manual build configuration is required in order to inject the implementation of your plugin into your tests.

When using the [Java Gradle Plugin Development Plugin](userguide/javaGradle_plugin.html),
the plugin being developed will now be implicitly made available to all builds under test,
removing the need for any manual configuration.

See the [TestKit chapter in the Gradle User Guide](userguide/test_kit.html#sub:test-kit-automatic-classpath-injection) for more about this new feature.

## Promoted features

Promoted features are features that were incubating in previous versions of Gradle but are now supported and subject to backwards compatibility.
See the User guide section on the “[Feature Lifecycle](userguide/feature_lifecycle.html)” for more information.

The following are the features that have been promoted in this Gradle release.

<!--
### Example promoted
-->

## Fixed issues

## Deprecations

Features that have become superseded or irrelevant due to the natural evolution of Gradle become *deprecated*, and scheduled to be removed
in the next major Gradle version (Gradle 3.0). See the User guide section on the “[Feature Lifecycle](userguide/feature_lifecycle.html)” for more information.

The following are the newly deprecated items in this Gradle release. If you have concerns about a deprecation, please raise it via the [Gradle Forums](http://discuss.gradle.org).

<!--
### Example deprecation
-->

## Potential breaking changes

<!--
### Example breaking change
-->

## External contributions

We would like to thank the following community members for making contributions to this release of Gradle.

* [Alexander Afanasyev](https://github.com/cawka) - allow configuring java.util.logging in tests ([GRADLE-2524](https://issues.gradle.org/browse/GRADLE-2524))
* [Evgeny Mandrikov](https://github.com/Godin) - upgrade default JaCoCo version to 0.7.6
* [Randall Becker](https://github.com/rsbecker) - bypass ulimit in NONSTOP os
* [Endre Fejese](htts://github.com/fejese) - fix a typo in a javadoc comment
* [Thomas Broyer](https://github.com/tbroyer) - add design doc for better/built-in Java annotation processing support

<!--
* [Some person](https://github.com/some-person) - fixed some issue (GRADLE-1234)
-->

We love getting contributions from the Gradle community. For information on contributing, please see [gradle.org/contribute](http://gradle.org/contribute).

## Known issues

Known issues are problems that were discovered post release that are directly related to changes made in this release.
