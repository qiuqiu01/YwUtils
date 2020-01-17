/*
 * Copyright (c) 2020.
 * Created by QiuQiu on 2020/01/09.
 * All Rights Reserved.
 */

package com.wuyou.utils;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * <pre>
 *     author: YanWen
 *     time  : 2020/01/09
 *     desc  : 设备工具类
 *  warning  : 部分功能需要先到Application类里实现初始化---- YwUtils.init(application);
 *  ******想要获取更多设备相关信息可参考《Android群英传》第9章的9.1节******
 * </pre>
 */
public final class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("U can't instantiate me...");
    }

    /**
     * 判断设备是否 root
     *
     * @return the boolean{@code true}: 是<br>{@code false}: 否
     */
    public static boolean isDeviceRooted() {
        String su = "su";
        String[] locations = {"/system/bin/", "/system/xbin/", "/sbin/", "/system/sd/xbin/",
                "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"};
        for (String location : locations) {
            if (new File(location + su).exists()) {
                return true;
            }
        }
        return false;
    }


    /**
     * 关机
     * <p>需要 root 权限或者系统权限 {@code <android:sharedUserId="android.uid.system" />}</p>
     */
    public static void shutdown() {
        ShellUtils.execCmd("reboot -p", true);
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra("android.intent.extra.KEY_CONFIRM", false);
        YwUtils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    /**
     * 重启
     * <p>需要 root 权限或者系统权限 {@code <android:sharedUserId="android.uid.system" />}</p>
     */
    public static void reboot() {
        ShellUtils.execCmd("reboot", true);
        Intent intent = new Intent(Intent.ACTION_REBOOT);
        intent.putExtra("nowait", 1);
        intent.putExtra("interval", 1);
        intent.putExtra("window", 0);
        YwUtils.getApp().sendBroadcast(intent);
    }

    /**
     * 重启
     * <p>需系统权限 {@code <android:sharedUserId="android.uid.system" />}</p>
     *
     * @param reason 传递给内核来请求特殊的引导模式，如"recovery"
     */
    public static void reboot(final String reason) {
        PowerManager mPowerManager =
                (PowerManager) YwUtils.getApp().getSystemService(Context.POWER_SERVICE);
        try {
            if (mPowerManager == null) return;
            mPowerManager.reboot(reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重启到 recovery
     * <p>需要 root 权限</p>
     */
    public static void reboot2Recovery() {
        ShellUtils.execCmd("reboot recovery", true);
    }

    /**
     * 重启到 bootloader
     * <p>需要 root 权限</p>
     */
    public static void reboot2Bootloader() {
        ShellUtils.execCmd("reboot bootloader", true);
    }

    /**
     * 获取设备 AndroidID
     *
     * @return AndroidID
     */
    public static String getAndroidID(Context ctx) {
        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备 IMEI 码(可通过手机拨号框“*#06#”查询)
     *
     * @return IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(Context ctx) {
        return ((TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * 获取设备 IMSI 码
     *
     * *** 国际移动用户识别码（英语：IMSI，International Mobile Subscriber Identity）***
     * *** 是用于区分蜂窝网络中不同用户的、在所有蜂窝网络中不重复的识别码 ***
     * @return IMSI
     */
    @SuppressLint("MissingPermission")
    public static String getIMSI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId() != null ? tm.getSubscriberId() : null;
    }

    /**
     * 获取设备连接Wifi时的 MAC 地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />}</p>
     * add : 这里只是简单获取MAC地址，更多获取方式请使用下面的getMacAddress()方法
     * @return MAC 地址
     */
    @SuppressWarnings("MissingPermission")
    public static String getWifiMacAddr(Context ctx) {
        String macAddr = "";
        try {
            WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
            macAddr = wifi.getConnectionInfo().getMacAddress();
            if (macAddr == null) {
                macAddr = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return macAddr;
    }

    /**
     * 获取网络IP地址(优先获取wifi地址)
     * @param ctx
     * @return
     */
    public static String getIP(Context ctx) {
        WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled() ? getWifiIP(wifiManager) : getGPRSIP();
    }

    /**
     * 获取WIFI连接下的ip地址
     * @param wifiManager
     * @return
     */
    public static String getWifiIP(WifiManager wifiManager) {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ip = intToIp(wifiInfo.getIpAddress());
        return ip != null ? ip : "";
    }

    /**
     * 获取GPRS连接下的ip地址
     * @return
     */
    public static String getGPRSIP() {
        String ip = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                for (Enumeration<InetAddress> enumIpAddr = en.nextElement().getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            ip = null;
        }
        return ip;
    }

    /**
     * 将int数据转化成ip地址
     * @return ip地址
     */
    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }


    /**
     * 获取设备序列号
     * @return
     */
    public static String getSerial() {
        return Build.SERIAL;
    }

    /**
     * 获取SIM卡序列号
     * @return
     */
    @SuppressLint({"MissingPermission"})
    public static String getSIMSerial(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

        return tm.getSimSerialNumber();
    }

    /**
     * 获取手机号码(未获取成功)
     * @return
     */
    @SuppressLint({"MissingPermission"})
    public static String getPhoneNumber(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getLine1Number();
    }

    /**
     * 获取网络运营商 46000,46002,46007 中国移动,46001 中国联通,46003 中国电信
     * @param ctx
     * @return
     */
    public static String getMNC(Context ctx) {
        String providersName = "";
        TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
            providersName = telephonyManager.getSimOperator();
            providersName = providersName == null ? "" : providersName;
        }
        return providersName;
    }

    /**
     * 获取网络运营商：中国电信,中国移动,中国联通
     * @param ctx
     * @return
     */
    public static String getCarrier(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getNetworkOperatorName().toLowerCase(Locale.getDefault());
    }

    /**
     * 获取硬件型号
     * <p>如 MI2SC</p>
     *
     * @return 设备型号
     */
    public static String getModel() {
        String model = Build.MODEL;
        if (model != null) {
            model = model.trim().replaceAll("\\s*", "");
        } else {
            model = "";
        }
        return model;
    }

    /**
     * 获取编译厂商-(手机品牌信息)
     *
     * @return 编译厂商
     */
    public static String getBuildBrand() {
        return Build.BRAND;
    }

    /**
     * 获取编译服务器主机
     *
     * @return 编译服务器主机
     */
    public static String getBuildHost() {
        return Build.HOST;
    }

    /**
     * 获取描述Build的标签
     *
     * @return 描述Build的标签
     */
    public static String getBuildTags() {
        return Build.TAGS;
    }

    /**
     * 获取系统编译时间
     *
     * @return 系统编译时间
     */
    public static long getBuildTime() {
        return Build.TIME;
    }

    /**
     * 获取系统编译作者
     *
     * @return 系统编译作者
     */
    public static String getBuildUser() {
        return Build.USER;
    }

    /**
     * 获取编译系统版本(5.1) - The user-visible version string.  E.g., "1.0" or "3.4b5".
     * @return 编译系统版本(5.1)
     */
    public static String getBuildVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取开发代号
     * The current development codename, or the string "REL" if this is
     * a release build.
     */
    public static String getBuildVersionCodename() {
        return Build.VERSION.CODENAME;
    }

    /**
     * 获取源码控制版本号
     * The internal value used by the underlying source control to
     * represent this build.  E.g., a perforce changelist number
     * or a git hash.
     */
    public static String getBuildVersionIncremental() {
        return Build.VERSION.INCREMENTAL;
    }

    /**
     * 获取编译的SDK版本号（Android版本号）
     *
     * @return 编译的SDK版本号（Android版本号）
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }

    /**
     * 获取修订版本列表(LMY47D)
     *
     * @return 修订版本列表
     */
    public static String getBuildID() {
        return Build.ID;
    }

    /**
     * CPU指令集
     * An ordered list of ABIs supported by this device. The most preferred ABI is the first
     * element in the list.
     */
    public static String[] getSupportedABIS() {
        String[] result = new String[]{"-"};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            result = Build.SUPPORTED_ABIS;
        }
        if (result == null || result.length == 0) {
            result = new String[]{"-"};
        }
        return result;
    }

    /**
     * 获取硬件制造厂商
     * <p>如 Xiaomi</p>
     *
     * @return 硬件制造厂商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取系统启动程序版本号
     * The system bootloader version number
     */
    public static String getBootloader() {
        return Build.BOOTLOADER;
    }

    /**
     * Gets the display id.
     * <p>
     * Each logical display has a unique id.
     * </p>
     */
    public static String getScreenDisplayID(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        return String.valueOf(wm.getDefaultDisplay().getDisplayId());
    }

    /**
     * 获取系统版本号
     * A build ID string meant for displaying to the user.
     */
    public static String getDisplayVersion() {
        return Build.DISPLAY;
    }

    /**
     * 获取语言
     * Returns the language code of this Locale.
     */
    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取sim卡的提供国家信息
     * Returns the ISO country code equivalent for the SIM provider's country code.
     */
    public static String getCountry(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        Locale locale = Locale.getDefault();
        return tm.getSimState() == TelephonyManager.SIM_STATE_READY ? tm.getSimCountryIso().toLowerCase(Locale.getDefault()) : locale.getCountry().toLowerCase(locale);
    }

    /**
     * 获取系统版本:5.1.1
     * 获取release版本信息 - The user-visible version string.  E.g., "1.0" or "3.4b5".
     * @return release版本信息
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取GSF序列号
     * <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
     */
    public static String getGSFID(Context context) {
        String result;
        final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
        final String ID_KEY = "android_id";
        String[] params = {ID_KEY};
        Cursor c = context.getContentResolver().query(URI, null, null, params, null);
        if (c == null || !c.moveToFirst() || c.getColumnCount() < 2) {
            return null;
        } else {
            result = Long.toHexString(Long.parseLong(c.getString(1)));
        }
        c.close();
        return result;
    }

    /**
     * 获取蓝牙的MAC地址
     * <uses-permission android:name="android.permission.BLUETOOTH"/>
     */
    @SuppressWarnings("MissingPermission")
    public static String getBluetoothMAC(Context context) {
        String result = null;
        try {
            if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH)
                    == PackageManager.PERMISSION_GRANTED) {
                BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
                result = bta.getAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Android设备物理唯一标识符
     * @return
     */
    public static String getPsuedoUniqueID() {
        String devIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            devIDShort += (Build.SUPPORTED_ABIS[0].length() % 10);
        } else {
            devIDShort += (Build.CPU_ABI.length() % 10);
        }
        devIDShort += (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
        String serial;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
            return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception e) {
            serial = "ESYDV000";
        }
        return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 获取构建标识
     * 包括brand,name,device,version.release,id,version.incremental,type,tags这些信息
     *
     * @return 构建标识
     */
    public static String getFingerprint() {
        return Build.FINGERPRINT;
    }

    /**
     * 获取硬件信息
     *
     * @return 硬件信息
     */
    public static String getHardware() {
        return Build.HARDWARE;
    }

    /**
     * 获取产品信息
     *
     * @return 产品信息
     */
    public static String getProduct() {
        return Build.PRODUCT;
    }

    /**
     * 获取设备信息
     *
     * @return 设备信息
     */
    public static String getDevice() {
        return Build.DEVICE;
    }

    /**
     * 获取手机主板信息
     *
     * @return 主板信息
     */
    public static String getBoard() {
        return Build.BOARD;
    }

    /**
     * 获取基带版本(无线电固件版本 Api14以上)
     * Returns the version string for the radio firmware.  May return
     * null (if, for instance, the radio is not currently on).
     */
    public static String getRadioVersion() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? Build.getRadioVersion() : "";
    }

    /**
     * 获取的浏览器指纹(User-Agent)
     * Returns the default User-Agent used by a WebView.
     */
    public static String getUA(Context ctx) {
        final String system_ua = System.getProperty("http.agent");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return new WebView(ctx).getSettings().getDefaultUserAgent(ctx) + "__" + system_ua;
        } else {
            return new WebView(ctx).getSettings().getUserAgentString() + "__" + system_ua;
        }
    }

    /**
     * 获取得屏幕密度.
     *
     * @return 设备密度信息对应的字符串
     */
    public static String getDensity(Context ctx) {
        String densityStr = null;
        final int density = ctx.getResources().getDisplayMetrics().densityDpi;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                densityStr = "LDPI";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                densityStr = "MDPI";
                break;
            case DisplayMetrics.DENSITY_TV:
                densityStr = "TVDPI";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                densityStr = "HDPI";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                densityStr = "XHDPI";
                break;
            case DisplayMetrics.DENSITY_400:
                densityStr = "XMHDPI";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                densityStr = "XXHDPI";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                densityStr = "XXXHDPI";
                break;
        }
        return densityStr;
    }

    /**
     * 获取谷歌账户（国内手机一般用不到）
     * <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
     */
    @SuppressWarnings("MissingPermission")
    public static String[] getGoogleAccounts(Context ctx) {
        if (ctx.checkCallingOrSelfPermission(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
            Account[] accounts = AccountManager.get(ctx).getAccountsByType("com.google");
            String[] result = new String[accounts.length];
            for (int i = 0; i < accounts.length; i++) {
                result[i] = accounts[i].name;
            }
            return result;
        }
        return null;
    }

    /** ===========================================================================================
     * 以下代码参考：implementation 'com.blankj:utilcode:1.10.0'
     * 需要先在App中初始化YwUtils.init（app）
     * =============================================================================================
     */

    /**
     * 获取设备 AndroidID
     *
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID() {
        return Settings.Secure.getString(YwUtils.getApp().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    /**
     * 获取设备 MAC 地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @return MAC 地址
     */
    public static String getMacAddress() {
        String macAddress = getMacAddressByWifiInfo();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByNetworkInterface();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        macAddress = getMacAddressByFile();
        if (!"02:00:00:00:00:00".equals(macAddress)) {
            return macAddress;
        }
        return "please open wifi";
    }

    /**
     * 获取设备 MAC 地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />}</p>
     *
     * @return MAC 地址
     */
    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static String getMacAddressByWifiInfo() {
        try {
            @SuppressLint("WifiManagerLeak")
            WifiManager wifi = (WifiManager) YwUtils.getApp().getSystemService(Context.WIFI_SERVICE);
            if (wifi != null) {
                WifiInfo info = wifi.getConnectionInfo();
                if (info != null)
                    return info.getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备 MAC 地址
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.INTERNET" />}</p>
     *
     * @return MAC 地址
     */
    private static String getMacAddressByNetworkInterface() {
        try {
            List<NetworkInterface> nis = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface ni : nis) {
                if (!ni.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = ni.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02x:", b));
                    }
                    return res1.deleteCharAt(res1.length() - 1).toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    /**
     * 获取设备 MAC 地址
     *
     * @return MAC 地址
     */
    private static String getMacAddressByFile() {
        ShellUtils.CommandResult result = ShellUtils.execCmd("getprop wifi.interface", false);
        if (result.result == 0) {
            String name = result.successMsg;
            if (name != null) {
                result = ShellUtils.execCmd("cat /sys/class/net/" + name + "/address", false);
                if (result.result == 0) {
                    if (result.successMsg != null) {
                        return result.successMsg;
                    }
                }
            }
        }
        return "02:00:00:00:00:00";
    }
}
