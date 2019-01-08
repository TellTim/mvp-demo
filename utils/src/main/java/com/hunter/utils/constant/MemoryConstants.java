package com.hunter.utils.constant;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The type Memory constants.
 */
public final class MemoryConstants {

  /**
   * The constant BYTE.
   */
  public static final int BYTE = 1;
  /**
   * The constant KB.
   */
  public static final int KB = 1024;
  /**
   * The constant MB.
   */
  public static final int MB = 1048576;
  /**
   * The constant GB.
   */
  public static final int GB = 1073741824;

  /**
   * The interface Unit.
   */
  @IntDef({BYTE, KB, MB, GB})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Unit {
    }
}
