package com.wxm.test.main;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-04-07 17:14:21
 */
@Slf4j
public class NetUtil {
    // Declare a method to generate a unique ID based on the computer's IPv6 address
    public static String generateUniqueID() throws UnknownHostException, SocketException {
        // Get the computer's IPv6 address
        InetAddress ip = InetAddress.getLocalHost();
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        byte[] mac = network.getHardwareAddress();

        // Concatenate the bytes of the IPv6 address and the MAC address
        ByteBuffer buffer = ByteBuffer.wrap(new byte[mac.length + ip.getAddress().length]);
        buffer.put(mac);
        buffer.put(ip.getAddress());

        // Convert the concatenated bytes to a string and return it
        return new String(buffer.array());
    }

    public static void main(String[] args) throws IOException {
        String uniqueID = generateUniqueID();
        log.info(">>>>>>>>>>>>>>id为：{}",uniqueID);
    }
    // Call the generateUniqueID() method to get the unique ID

}