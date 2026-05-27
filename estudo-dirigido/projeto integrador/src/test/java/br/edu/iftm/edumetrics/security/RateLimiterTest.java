package br.edu.iftm.edumetrics.security;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class RateLimiterTest {

    @Test
    void shouldAllowRequestsUnderLimit() {
        RateLimiter rateLimiter = new RateLimiter(3, 1000);

        assertTrue(rateLimiter.permitir("user1"));
        assertTrue(rateLimiter.permitir("user1"));
        assertTrue(rateLimiter.permitir("user1"));
    }

    @Test
    void shouldRejectRequestsOverLimitWithinWindow() {
        RateLimiter rateLimiter = new RateLimiter(2, 500);

        assertTrue(rateLimiter.permitir("user2"));
        assertTrue(rateLimiter.permitir("user2"));
        assertFalse(rateLimiter.permitir("user2"));
    }

    @Test
    void shouldAllowRequestsAfterWindowExpires() throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(1, 200);

        assertTrue(rateLimiter.permitir("user3"));
        assertFalse(rateLimiter.permitir("user3"));

        Thread.sleep(250);

        assertTrue(rateLimiter.permitir("user3"));
    }
}
