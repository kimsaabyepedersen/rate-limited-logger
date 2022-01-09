package dk.anno1980;

import com.swrve.ratelimitedlogger.RateLimitedLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;

public class RateLimitedLoggerDemo {
  private static final Logger logger = LoggerFactory.getLogger(RateLimitedLoggerDemo.class);

  private static final RateLimitedLog rateLimitedLog =
      RateLimitedLog.withRateLimit(logger).maxRate(3).every(Duration.ofSeconds(3)).build();

  public static void main(String[] args) throws InterruptedException {
    rateLimitedLog.info("The time is: {}", LocalTime.now());
    rateLimitedLog.info("The time is: {}", LocalTime.now());
    rateLimitedLog.info("The time is: {}", LocalTime.now());
    // The following gets does _not_ get emitted
    rateLimitedLog.info("The time is: {}", LocalTime.now());
    Thread.sleep(3000);
    // Time span has elapsed, it will again log
    rateLimitedLog.info("After sleep");
    rateLimitedLog.info("The time is: {}", LocalTime.now());
  }
}
