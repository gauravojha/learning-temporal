package dev.gauravojha.learningtemporal.activities;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface HelloWorldActivities {
  String composeGreeting(String name);
}
