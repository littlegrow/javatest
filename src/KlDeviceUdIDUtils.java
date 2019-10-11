
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class KlDeviceUdIDUtils {

    public static void main(String[] args) {

        // 1. 根据版本号&灰度比例获range
        // 2. 根据range.isInRange 入参为udid 出参为boolean, true标识命中了灰度
        float versionRatio = 0.3f;
        int verison = 40180000;
        UdIDRange range = getRange(verison, versionRatio);
        System.out.println("" + range);
        int count = 1000000;
        int inCount = 0;
        for (int i = 0; i < count; i++) {
            String sha = randomeSha();
            boolean isIn = range.isInRange(sha);
            System.out.println(sha + " " + isIn);
            if (isIn) {
                inCount++;
            }
        }
        float realRatio = inCount * 1.0f / count;
        System.out.println("versionRatio:" + versionRatio + ",realRatio:" + realRatio);

    }


    //delete
    @Deprecated
    private static String randomeSha() {
        try {
            Random r = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int val = r.nextInt(256);
                if (val < 0) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
                sb.append(":");
            }
            sb.deleteCharAt(sb.length() - 1);
            String mac = sb.toString();

            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.update(mac.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                if (toHexString.length() < 2) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(toHexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static UdIDRange getRange(int version, float ratio) {
        ratio = Math.min(1.0f, Math.max(0, ratio));
        int versionWithoutBeta = version / 100;
        byte offset = (byte) (versionWithoutBeta % 256);
        int step = (int) (ratio * 256);
        byte offsetWithStep = (byte) (step + offset);
        boolean isReverseRange = offsetWithStep < offset;
        byte start = offset;
        byte end = offsetWithStep;
        if (isReverseRange) {
            start = offsetWithStep;
            end = offset;
        }
        return new UdIDRange(offset, start, end, isReverseRange, ratio);
    }

    public static class UdIDRange {
        final byte offset;
        final byte start;
        final byte end;
        final boolean isReverseRange;
        final float ratio;

        /**
         * @param shaStr 一般是udid
         * @return true:命中灰度
         */
        public boolean isInRange(String shaStr) {
            if (shaStr == null || shaStr.length() == 0) {
                return false;
            }
            if (shaStr.length() < 2) {
                return false;
            }
            boolean ret = false;
            String subStr = shaStr.substring(shaStr.length() - 2);
            try {
                byte byteVal = (byte) Integer.parseInt(subStr, 16);
                ret = isInRange(byteVal);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            return ret;

        }

        public boolean isInRange(byte val) {
            if (ratio == 1.0f) {
                return true;
            }
            boolean isIn = isReverseRange ? (val > start && val < end) : (val >= start && val <= end);
            return isReverseRange ^ isIn;
//            if (!isReverseRange) {
//                return isInRange;
//            } else {
//                return !isInRange;
//            }
        }

        public UdIDRange(byte offset, byte start, byte end, boolean isInclude, float ratio) {
            this.offset = offset;
            this.start = start;
            this.end = end;
            this.isReverseRange = isInclude;
            this.ratio = ratio;
        }

        private String getHexString(byte val) {
            String str = Integer.toHexString(val);
            if (str.length() > 2) {
                return str.substring(str.length() - 2, str.length());
            }
            return str;
        }

        @Override
        public String toString() {
            return "UdIDRange{" +
                    "offset=" + getHexString(offset) +
                    ", start=" + getHexString(start) +
                    ", end=" + getHexString(end) +
                    ", isReverseRange=" + isReverseRange +
                    '}';
        }
    }

}
