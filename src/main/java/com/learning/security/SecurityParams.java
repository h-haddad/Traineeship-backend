package com.learning.security;

public interface SecurityParams {
  public static final String HEADER_NAME = "Authorization";
  public static final String SECRET = "secret";
  public static final String HEADER_PREFIX = "Bearer ";
  public static final long EXPIRATION = 10 * 24 * 3600 * 1000; // 10 Days
}
