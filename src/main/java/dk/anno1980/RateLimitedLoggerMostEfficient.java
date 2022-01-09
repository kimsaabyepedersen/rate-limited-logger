package dk.anno1980;

import com.swrve.ratelimitedlogger.Level;
import com.swrve.ratelimitedlogger.LogWithPatternAndLevel;
import com.swrve.ratelimitedlogger.RateLimitedLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;

public class RateLimitedLoggerMostEfficient {
  private static final Logger logger = LoggerFactory.getLogger(RateLimitedLoggerMostEfficient.class);

  private static final RateLimitedLog rateLimitedLog =
      RateLimitedLog.withRateLimit(logger).maxRate(3).every(Duration.ofSeconds(3)).build();

  public static void main(String[] args) {

    // Get the logger for the pattern and level
    LogWithPatternAndLevel log = rateLimitedLog.get("The time is: {}", Level.INFO);
    // Use it
    log.log(LocalTime.now());
    log.log(LocalTime.now());
    log.log(LocalTime.now());
    // The following gets does _not_ get emitted
    // The logger is the same as the one in the "log" variable
    // But the logger is looked up in a concurrent map (by pattern) and then in an array (by index)
    rateLimitedLog.warn("The time is: {}", LocalTime.now());

  }
}
