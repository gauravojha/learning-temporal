package dev.gauravojha.learningtemporal.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HelloWorldWorkflow {
  @WorkflowMethod
  String getGreeting(String name);
}
