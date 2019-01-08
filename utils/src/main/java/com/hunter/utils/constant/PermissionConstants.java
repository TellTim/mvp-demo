package com.hunter.utils.constant;

import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

/**
 * The type Permission constants.
 */
@SuppressLint("InlinedApi")
public final class PermissionConstants {

  /**
   * The constant CALENDAR.
   */
  public static final String CALENDAR = Manifest.permission_group.CALENDAR;
  /**
   * The constant CAMERA.
   */
  public static final String CAMERA = Manifest.permission_group.CAMERA;
  /**
   * The constant CONTACTS.
   */
  public static final String CONTACTS = Manifest.permission_group.CONTACTS;
  /**
   * The constant LOCATION.
   */
  public static final String LOCATION = Manifest.permission_group.LOCATION;
  /**
   * The constant MICROPHONE.
   */
  public static final String MICROPHONE = Manifest.permission_group.MICROPHONE;
  /**
   * The constant PHONE.
   */
  public static final String PHONE = Manifest.permission_group.PHONE;
  /**
   * The constant SENSORS.
   */
  public static final String SENSORS = Manifest.permission_group.SENSORS;
  /**
   * The constant SMS.
   */
  public static final String SMS = Manifest.permission_group.SMS;
  /**
   * The constant STORAGE.
   */
  public static final String STORAGE = Manifest.permission_group.STORAGE;

    private static final String[] GROUP_CALENDAR = {
            permission.READ_CALENDAR, permission.WRITE_CALENDAR
    };
    private static final String[] GROUP_CAMERA = {
            permission.CAMERA
    };
    private static final String[] GROUP_CONTACTS = {
            permission.READ_CONTACTS, permission.WRITE_CONTACTS, permission.GET_ACCOUNTS
    };
    private static final String[] GROUP_LOCATION = {
            permission.ACCESS_FINE_LOCATION, permission.ACCESS_COARSE_LOCATION
    };
    private static final String[] GROUP_MICROPHONE = {
            permission.RECORD_AUDIO
    };
    private static final String[] GROUP_PHONE = {
            permission.READ_PHONE_STATE, permission.READ_PHONE_NUMBERS, permission.CALL_PHONE,
            permission.READ_CALL_LOG, permission.WRITE_CALL_LOG, permission.ADD_VOICEMAIL,
            permission.USE_SIP, permission.PROCESS_OUTGOING_CALLS, permission.ANSWER_PHONE_CALLS
    };
    private static final String[] GROUP_PHONE_BELOW_O = Arrays.copyOf(
            GROUP_PHONE, GROUP_PHONE.length - 1
    );
    private static final String[] GROUP_SENSORS = {
            permission.BODY_SENSORS
    };
    private static final String[] GROUP_SMS = {
            permission.SEND_SMS, permission.RECEIVE_SMS, permission.READ_SMS,
            permission.RECEIVE_WAP_PUSH, permission.RECEIVE_MMS,
    };
    private static final String[] GROUP_STORAGE = {
            permission.READ_EXTERNAL_STORAGE, permission.WRITE_EXTERNAL_STORAGE
    };

  /**
   * The interface Permission.
   */
  @StringDef({CALENDAR, CAMERA, CONTACTS, LOCATION, MICROPHONE, PHONE, SENSORS, SMS, STORAGE,})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Permission {
    }

  /**
   * Get permissions string [ ].
   *
   * @param permission the permission
   * @return the string [ ]
   */
  public static String[] getPermissions(@Permission final String permission) {
        switch (permission) {
            case CALENDAR:
                return GROUP_CALENDAR;
            case CAMERA:
                return GROUP_CAMERA;
            case CONTACTS:
                return GROUP_CONTACTS;
            case LOCATION:
                return GROUP_LOCATION;
            case MICROPHONE:
                return GROUP_MICROPHONE;
            case PHONE:
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                    return GROUP_PHONE_BELOW_O;
                } else {
                    return GROUP_PHONE;
                }
            case SENSORS:
                return GROUP_SENSORS;
            case SMS:
                return GROUP_SMS;
            case STORAGE:
                return GROUP_STORAGE;
        }
        return new String[]{permission};
    }
}
