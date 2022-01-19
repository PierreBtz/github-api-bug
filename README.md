# github-api-bug Project

Minimal repro for an issue encountered when building a native image with the 
[quarkus-github-api](https://github.com/quarkiverse/quarkus-github-api) project.

## Reproduction steps:

* clone
* run `mvn -DskipTests -Pnative`
* Optional: in case you want to run the tests/play with the project, add a `.env` file at the root with
`demo.github.pat=<a valid github personal access token>`.

Observed stack using GraalVM 21.3.0 (with java 11), gu --version GraalVM Updater 21.3.0

```
Error: com.oracle.graal.pointsto.constraints.UnresolvedElementException: Discovered unresolved type during parsing: okhttp3.OkHttpClient$Builder. To diagnose the issue you can use the --allow-incomplete-classpath option. The missing type is then reported at run time when it is accessed the first time.
Detailed message:
Trace:
	at parsing org.kohsuke.github.internal.DefaultGitHubConnector.create(DefaultGitHubConnector.java:45)
Call path from entry point to org.kohsuke.github.internal.DefaultGitHubConnector.create(String):
	no path found from entry point to target method


com.oracle.svm.core.util.UserError$UserException: com.oracle.graal.pointsto.constraints.UnresolvedElementException: Discovered unresolved type during parsing: okhttp3.OkHttpClient$Builder. To diagnose the issue you can use the --allow-incomplete-classpath option. The missing type is then reported at run time when it is accessed the first time.
Detailed message:
Trace:
	at parsing org.kohsuke.github.internal.DefaultGitHubConnector.create(DefaultGitHubConnector.java:45)
Call path from entry point to org.kohsuke.github.internal.DefaultGitHubConnector.create(String):
	no path found from entry point to target method


	at com.oracle.svm.core.util.UserError.abort(UserError.java:87)
	at com.oracle.svm.hosted.FallbackFeature.reportAsFallback(FallbackFeature.java:233)
	at com.oracle.svm.hosted.NativeImageGenerator.runPointsToAnalysis(NativeImageGenerator.java:759)
	at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:529)
	at com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:488)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:403)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:569)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.main(NativeImageGeneratorRunner.java:122)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner$JDK9Plus.main(NativeImageGeneratorRunner.java:599)
Caused by: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: com.oracle.graal.pointsto.constraints.UnresolvedElementException: Discovered unresolved type during parsing: okhttp3.OkHttpClient$Builder. To diagnose the issue you can use the --allow-incomplete-classpath option. The missing type is then reported at run time when it is accessed the first time.
Detailed message:
Trace:
	at parsing org.kohsuke.github.internal.DefaultGitHubConnector.create(DefaultGitHubConnector.java:45)
Call path from entry point to org.kohsuke.github.internal.DefaultGitHubConnector.create(String):
	no path found from entry point to target method


	at com.oracle.graal.pointsto.constraints.UnsupportedFeatures.report(UnsupportedFeatures.java:126)
	at com.oracle.svm.hosted.NativeImageGenerator.runPointsToAnalysis(NativeImageGenerator.java:756)
	... 6 more
Caused by: com.oracle.graal.pointsto.constraints.UnresolvedElementException: Discovered unresolved type during parsing: okhttp3.OkHttpClient$Builder. To diagnose the issue you can use the --allow-incomplete-classpath option. The missing type is then reported at run time when it is accessed the first time.
	at com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.reportUnresolvedElement(SharedGraphBuilderPhase.java:307)
	at com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.handleUnresolvedType(SharedGraphBuilderPhase.java:263)
	at com.oracle.svm.hosted.phases.SharedGraphBuilderPhase$SharedBytecodeParser.handleUnresolvedNewInstance(SharedGraphBuilderPhase.java:207)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genNewInstance(BytecodeParser.java:4624)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.genNewInstance(BytecodeParser.java:4617)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBytecode(BytecodeParser.java:5422)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.iterateBytecodesForBlock(BytecodeParser.java:3477)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.handleBytecodeBlock(BytecodeParser.java:3437)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.processBlock(BytecodeParser.java:3282)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.build(BytecodeParser.java:1145)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.BytecodeParser.buildRootMethod(BytecodeParser.java:1030)
	at jdk.internal.vm.compiler/org.graalvm.compiler.java.GraphBuilderPhase$Instance.run(GraphBuilderPhase.java:84)
	at com.oracle.svm.hosted.phases.SharedGraphBuilderPhase.run(SharedGraphBuilderPhase.java:81)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.run(Phase.java:49)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.BasePhase.apply(BasePhase.java:212)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:42)
	at jdk.internal.vm.compiler/org.graalvm.compiler.phases.Phase.apply(Phase.java:38)
	at com.oracle.graal.pointsto.flow.AnalysisParsedGraph.parseBytecode(AnalysisParsedGraph.java:132)
	at com.oracle.graal.pointsto.meta.AnalysisMethod.ensureGraphParsed(AnalysisMethod.java:621)
	at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.parse(MethodTypeFlowBuilder.java:163)
	at com.oracle.graal.pointsto.flow.MethodTypeFlowBuilder.apply(MethodTypeFlowBuilder.java:321)
	at com.oracle.graal.pointsto.flow.MethodTypeFlow.createTypeFlow(MethodTypeFlow.java:293)
	at com.oracle.graal.pointsto.flow.MethodTypeFlow.ensureTypeFlowCreated(MethodTypeFlow.java:282)
	at com.oracle.graal.pointsto.flow.MethodTypeFlow.addContext(MethodTypeFlow.java:103)
	at com.oracle.graal.pointsto.flow.StaticInvokeTypeFlow.update(InvokeTypeFlow.java:420)
	at com.oracle.graal.pointsto.PointsToAnalysis$2.run(PointsToAnalysis.java:595)
	at com.oracle.graal.pointsto.util.CompletionExecutor.executeCommand(CompletionExecutor.java:188)
	at com.oracle.graal.pointsto.util.CompletionExecutor.lambda$executeService$0(CompletionExecutor.java:172)
	at java.base/java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1426)
	at java.base/java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:290)
	at java.base/java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1020)
	at java.base/java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1656)
	at java.base/java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1594)
	at java.base/java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:183)
```
----------

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/github-api-bug-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
