package dev.gauravojha.learningtemporal.controller;

import dev.gauravojha.learningtemporal.workflows.HelloWorldWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(HelloWorldController.CONTROLLER_PATH)
public class HelloWorldController {

  @Autowired private WorkflowClient workflowClient;

  public static final String CONTROLLER_PATH = "/hello";

  WorkflowOptions options =
      WorkflowOptions.newBuilder()
          .setWorkflowId("HelloWorldWorkflowID")
          .setTaskQueue("HelloWorldTaskQueue")
          .build();

  @GetMapping
  public ResponseEntity<String> test() {
    HelloWorldWorkflow workflow = workflowClient.newWorkflowStub(HelloWorldWorkflow.class, options);
    String greeting = workflow.getGreeting("World");
    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }
}
