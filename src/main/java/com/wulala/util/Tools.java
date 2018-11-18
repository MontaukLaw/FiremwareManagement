package com.wulala.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	/**
	 * 随机生成六位数验证码
	 * 
	 * @return
	 */
	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;// (Math.random()*(999999-100000)+100000)
	}

	public static int getJsonInt(String ori, String key) {
		ori = ori.replaceAll(" ", "");
		int keyStartIndex = ori.indexOf(key);
		String subString = ori.substring(keyStartIndex);
		int stopIndex = subString.indexOf(",");
		if (stopIndex < 0) {
			stopIndex = subString.length() - 1;
		} else {
			stopIndex = stopIndex;
		}
		int startIndex = subString.indexOf(":") + 1;

		// System.out.println(startIndex + " " + stopIndex);
		System.out.println(subString.substring(startIndex, stopIndex));
		return Integer.valueOf(subString.substring(startIndex, stopIndex));
	}

	public static Boolean getJsonBool(String ori, String key) {
		ori = ori.replaceAll(" ", "");
		int keyStartIndex = ori.indexOf(key);
		String subString = ori.substring(keyStartIndex);
		int stopIndex = subString.indexOf(",");
		if (stopIndex < 0) {
			stopIndex = subString.length() - 1;
		} else {
			stopIndex = stopIndex;
		}
		int startIndex = subString.indexOf(":") + 1;

		// System.out.println(startIndex + " " + stopIndex);
		int i = Integer.valueOf(subString.substring(startIndex, stopIndex));
		if (i == 1) {
			return true;
		}
		return false;
	}

	public static String getJsonString(String ori, String key) {
		String result = "";
		ori = ori.replaceAll(" ", "");
		int keyStartIndex = ori.indexOf(key);
		String subString = ori.substring(keyStartIndex);
		int stopIndex = subString.indexOf(",");
		if (stopIndex < 0) {
			stopIndex = subString.length() - 2;
		} else {
			stopIndex = stopIndex - 1;
		}
		int startIndex = subString.indexOf(":") + 2;

		// System.out.println(startIndex + " " + stopIndex);
		System.out.println(subString.substring(startIndex, stopIndex));
		return subString.substring(startIndex, stopIndex);

	}

	public static String sortStringAndEncrypt(String strA, String strB, String strC) {
		String result = "";

		ArrayList list = new ArrayList();
		list.add(strA);
		list.add(strB);
		list.add(strC);
		Collections.sort(list, new SpellComparator());
		for (int i = 0; i < list.size(); i++) {
			// System.out.println(list.get(i));
			result = result + list.get(i);
		}
		return SHA1(result);

	}

	public static String SHA1(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1"); // 选择SHA-1，也可以选择MD5
			byte[] digest = md.digest(inStr.getBytes()); // 返回的是byet[]，要转化为String存储比较方便
			outStr = bytetoString(digest);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return outStr;
	}

	public static String bytetoString(byte[] digest) {
		String str = "";
		String tempStr = "";

		for (int i = 1; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				str = str + "0" + tempStr;
			} else {
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}

	public static String getStringRandom(int length) {

		String val = "";
		Random random = new Random();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < length; i++) {

			// String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			// if ("char".equalsIgnoreCase(charOrNum)) {
			// 输出是大写字母还是小写字母
			int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
			val += (char) (random.nextInt(26) + temp);
			// } else if ("num".equalsIgnoreCase(charOrNum)) {
			// val += String.valueOf(random.nextInt(10));
			// }
		}
		return val;
	}

	/**
	 * 检测字符串是否不为空(null,"","null")
	 * 
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	public static boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 * 
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	/**
	 * 字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitRegex
	 *            分隔符
	 * @return
	 */
	public static String[] str2StrArray(String str, String splitRegex) {
		if (isEmpty(str)) {
			return null;
		}
		return str.split(splitRegex);
	}

	/**
	 * 用默认的分隔符(,)将字符串转换为字符串数组
	 * 
	 * @param str
	 *            字符串
	 * @return
	 */
	public static String[] str2StrArray(String str) {
		return str2StrArray(str, ",\\s*");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date str2Date(String date) {
		if (notEmpty(date)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return new Date();
		} else {
			return null;
		}
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 把时间根据时、分、秒转换为时间段
	 * 
	 * @param StrDate
	 */
	public static String getTimes(String StrDate) {
		String resultTimes = "";

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date now;

		try {
			now = new Date();
			java.util.Date date = df.parse(StrDate);
			long times = now.getTime() - date.getTime();
			long day = times / (24 * 60 * 60 * 1000);
			long hour = (times / (60 * 60 * 1000) - day * 24);
			long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
			long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			StringBuffer sb = new StringBuffer();
			// sb.append("发表于：");
			if (hour > 0) {
				sb.append(hour + "小时前");
			} else if (min > 0) {
				sb.append(min + "分钟前");
			} else {
				sb.append(sec + "秒前");
			}

			resultTimes = sb.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return resultTimes;
	}

	/**
	 * 写txt里的单行内容
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            写入的内容
	 */
	public static void writeFile(String fileP, String content) {
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if (filePath.indexOf(":") != 1) {
			filePath = File.separator + filePath;
		}
		try {
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(content);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
			Matcher matcher = regex.matcher(mobileNumber);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 检测KEY是否正确
	 * 
	 * @param paraname
	 *            传入参数
	 * @param FKEY
	 *            接收的 KEY
	 * @return 为空则返回true，不否则返回false
	 */
	public static boolean checkKey(String paraname, String FKEY) {
		paraname = (null == paraname) ? "" : paraname;
		return MD5.md5(paraname + "-NowOrNever-" + DateUtil.getDays()).equals(FKEY);
	}

	public static boolean checkKey(String FKEY) {
		return true;
		// return MD5.md5("-NowOrNever-" + DateUtil.getDays() +
		// "-NowOrNever-").equals(FKEY);
	}

	/**
	 * 读取txt里的单行内容
	 * 
	 * @param filePath
	 *            文件路径
	 */
	public static String readTxtFile(String fileP) {
		try {

			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../"; // 项目路径
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if (filePath.indexOf(":") != 1) {
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding); // 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
		}
		return "";
	}

	public static void main(String[] args) {
		// System.out.println("-NowOrNever-" + DateUtil.getDays() +
		// "-NowOrNever-");
		System.out.println(MD5.md5("-NowOrNever-" + DateUtil.getDays() + "-NowOrNever-"));
		// System.out.println(UUID.randomUUID().toString());

		// 测试
		for (int i = 0; i < 100000; i++) {
			System.out.println(getStringRandom(8));
			// System.out.println(10 / 6);
		}
	}
}

class SpellComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		try {
			// 取得比较对象的汉字编码，并将其转换成字符串
			String s1 = new String(o1.toString());
			String s2 = new String(o2.toString());
			// 运用String类的 compareTo（）方法对两对象进行比较
			return s1.compareTo(s2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}