package com.hunter.utils.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The type Time constants.
 */
public final class TimeConstants {

  /**
   * The constant MSEC.
   */
  public static final int MSEC = 1;
  /**
   * The constant SEC.
   */
  public static final int SEC = 1000;
  /**
   * The constant MIN.
   */
  public static final int MIN = 60000;
  /**
   * The constant HOUR.
   */
  public static final int HOUR = 3600000;
  /**
   * The constant DAY.
   */
  public static final int DAY = 86400000;

  /**
   * The interface Unit.
   */
  @IntDef({MSEC, SEC, MIN, HOUR, DAY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
