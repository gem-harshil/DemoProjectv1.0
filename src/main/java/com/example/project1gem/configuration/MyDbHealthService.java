package com.example.project1gem.configuration;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class MyDbHealthService implements HealthIndicator {
    /**
     * This method check health of project.
     *
     * @return boolean
     */
    public boolean isHealthGood() {
        return true;
    }

    /**
     * Health check.
     *
     * @return Health
     */
    @Override
    public Health health() {
        if (isHealthGood()) {
            return Health.up()
                    .withDetail("Database Service", "is running up ")
                    .build();
        }
        return Health
                .down()
                .withDetail("Database Service", "is running down").build();
    }
}
