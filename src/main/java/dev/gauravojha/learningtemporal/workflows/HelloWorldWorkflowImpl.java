package dev.gauravojha.learningtemporal.workflows;

import dev.gauravojha.learningtemporal.activities.HelloWorldActivities;
import io.temporal.activity.ActivityOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import java.time.Duration;

@WorkflowImpl(taskQueues = "HelloWorldTaskQueue")
public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

  ActivityOptions options =
      ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(60)).build();

  private final HelloWorldActivities activity =
      Workflow.newActivityStub(HelloWorldActivities.class, options);

  @Override
  public String getGreeting(String name) {
    return activity.composeGreeting(name);
  }
}
