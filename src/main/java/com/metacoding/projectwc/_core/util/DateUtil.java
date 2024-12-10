package com.metacoding.projectwc._core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateUtil {
    public static String formatToyyyypMMpdd(Timestamp createdAt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return simpleDateFormat.format(createdAt);
    }
}
