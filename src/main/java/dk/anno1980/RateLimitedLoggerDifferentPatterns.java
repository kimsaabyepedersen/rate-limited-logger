package dk.anno1980;

import com.swrve.ratelimitedlogger.RateLimitedLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;

public class RateLimitedLoggerDifferentPatterns {
  private static final Logger logger = LoggerFactory.getLogger(RateLimitedLoggerDifferentPatterns.class);

  private static final RateLimitedLog rateLimitedLog =
      RateLimitedLog.withRateLimit(logger).maxRate(3).every(Duration.ofSeconds(3)).build();

  public static void main(String[] args) {
    rateLimitedLog.info("The time is 1: {}", LocalTime.now());
    rateLimitedLog.info("The time is 1: {}", LocalTime.now());
    rateLimitedLog.info("The time is 1: {}", LocalTime.now());
    // The following gets does get emitted
    // Because the patterns are different, and the rate limited applies per pattern and level
    rateLimitedLog.info("The time is 2: {}", LocalTime.now());
  }
}
