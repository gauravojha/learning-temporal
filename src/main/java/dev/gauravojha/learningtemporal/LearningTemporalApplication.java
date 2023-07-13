package dev.gauravojha.learningtemporal;

import com.uber.m3.tally.RootScopeBuilder;
import com.uber.m3.tally.Scope;
import com.uber.m3.tally.StatsReporter;
import com.uber.m3.util.ImmutableMap;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.temporal.common.converter.DataConverter;
import io.temporal.common.converter.DefaultDataConverter;
import io.temporal.common.reporter.MicrometerClientStatsReporter;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.spring.boot.TemporalOptionsCustomizer;
import java.util.Map;
import javax.annotation.Nonnull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LearningTemporalApplication {

  @Bean
  public DataConverter dataConverter() {
    return DefaultDataConverter.newDefaultInstance();
  }

  @Bean
  public TemporalOptionsCustomizer<WorkflowServiceStubsOptions.Builder>
      customServiceStubsOptions() {
    return new TemporalOptionsCustomizer<>() {
      @Nonnull
      @Override
      public WorkflowServiceStubsOptions.Builder customize(
          @Nonnull WorkflowServiceStubsOptions.Builder optionsBuilder) {
        PrometheusMeterRegistry registry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        StatsReporter reporter = new MicrometerClientStatsReporter(registry);
        Map<String, String> mp = ImmutableMap.of("ojha", "ojha");
        // set up a new scope, report every 1 seconds
        Scope scope =
            new RootScopeBuilder()
                .tags(mp)
                .reporter(reporter)
                .reportEvery(com.uber.m3.util.Duration.ofSeconds(1));
        optionsBuilder.setMetricsScope(scope);
        return optionsBuilder;
      }
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(LearningTemporalApplication.class, args).start();
  }
}
