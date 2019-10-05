package src;

import java.util.Arrays;
import java.util.Random;

public class MainCode {

    public static void main(String[] args) {

        int[] lanternStatus = new int[32];
        for (int i = 0; i < lanternStatus.length; i++) {
            lanternStatus[i] = new Random().nextInt(2);
        }
        Arrays.stream(lanternStatus).forEach(System.out::print);
        System.out.println();
        getAllCodeByte(lanternStatus);

    }


    /**
     * 得到32位的
     *
     * @param lanternStatus 32位数组，数组元素是0代表关，1代表开
     * @return
     */
    public static byte[] getAllCodeByte(int[] lanternStatus) {
        if (lanternStatus == null || lanternStatus.length != 32 || !Arrays.stream(lanternStatus)
                .anyMatch(value -> value == 0 || value == 1)) {
            throw new RuntimeException("参数异常");
        }

        byte[] result = new byte[4];
        int resultIndex = 0;
        for (int i = 0; i < lanternStatus.length; i = i + 8) {
            StringBuilder bitStr = new StringBuilder();
            for (int j = i; j < i + 8; j++) {
                bitStr.append(lanternStatus[j] + "");
            }
            result[resultIndex++] = bitStringToByte(bitStr.toString());
        }

        return result;
    }


    /**
     * 位字符串转字节
     *
     * @param str
     * @return
     */
    public static byte bitStringToByte(String str) {
        if (null == str) {
            throw new RuntimeException("when bit string convert to byte, Object can not be null!");
        }
        if (8 != str.length()) {
            throw new RuntimeException("bit string'length must be 8");
        }
        try {
            //判断最高位，决定正负
            if (str.charAt(0) == '0') {
                return (byte) Integer.parseInt(str, 2);
            } else if (str.charAt(0) == '1') {
                return (byte) (Integer.parseInt(str, 2) - 256);
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("bit string convert to byte failed, byte String must only include 0 and 1!");
        }

        return 0;
    }

}
