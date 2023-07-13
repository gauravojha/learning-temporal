package dev.gauravojha.learningtemporal.activities;

import io.temporal.spring.boot.ActivityImpl;
import org.springframework.stereotype.Component;

@Component
@ActivityImpl(taskQueues = "HelloWorldTaskQueue")
public class HelloWorldActivitiesImpl implements HelloWorldActivities {
  @Override
  public String composeGreeting(String name) {
    return "Hello " + name + "!";
  }
}
