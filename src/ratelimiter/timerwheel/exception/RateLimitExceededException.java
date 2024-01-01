package ratelimiter.timerwheel.exception;

public class RateLimitExceededException extends IllegalStateException {
    public RateLimitExceededException() {
        super("Rate limit exceeded");
    }
}
