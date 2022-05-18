/*
* %W% %E% KienNT
*
* Copyright (c) 2019 SMAC Microsystems, Inc. All Rights Reserved. 
*
* This software is the confidential and proprietary information of SMAC 
* Microsystems, Inc. ("Confidential Information"). You shall not
* disclose such Confidential Information and shall use it only in
* accordance with the terms of the license agreement you entered into 
* with SMAC.
*
* SMAC MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF 
* THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
* TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
* PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SMAC SHALL NOT BE LIABLE FOR 
* ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR 
* DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/
package com.examonline.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;

/**
 * The Class DataUtils.
 *
 * @author HungND
 * @version 3.0
 */
public class DataUtils {
	private static final String PHONE_PATTERN = "^[0-9]*$";
	public static final String ID_SEPARATE_DOT = " . ";
	public static final String ID_SEPARATE_DASH = " - ";
	public static final String ID_SEPARATE_STAR = " * ";
	public static final String ID_SEPARATE_SEMILOCON = ";";
	public static final String ID_SEPARATE_COMMA = ",";
	public static final String ID_SEPARATE_BREAK_LINE = "\n";

	public final static Logger logger = LogManager.getLogger(DataUtils.class);
	private static String ipAddress;
	static {
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			ipAddress = "UNKNOW";
		}
	}

	@SuppressWarnings({ "viettel-java:Random" })
	public static String generateCharacter(int length) {
		StringBuilder result = new StringBuilder();

		Random r = new Random();
		String alphabet = "123456789";
		for (int i = 0; i < length; i++) {
			result.append(alphabet.charAt(r.nextInt(alphabet.length())));
		}

		return result.toString();
	}

	public static String getIpAddress() {
		return ipAddress;
	}

	public static String addZeroToString(String input, int strLength) {
		String result = input;
		for (int i = 1; i <= strLength - input.length(); i++) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * Copy du lieu tu bean sang bean moi Luu y chi copy duoc cac doi tuong o ngoai
	 * cung, list se duoc copy theo tham chieu
	 * <p>
	 * Chi dung duoc cho cac bean java, khong dung duoc voi cac doi tuong dang nhu
	 * String, Integer, Long...
	 *
	 * @param source
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T cloneBean(T source) {
		try {
			if (source == null) {
				return null;
			}
			T dto = (T) source.getClass().getConstructor().newInstance();
			BeanUtils.copyProperties(source, dto);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Clone an object completely
	 *
	 * @param source
	 * @param <T>
	 * @return
	 * @author KhuongDV
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deepCloneObject(T source) {
		try {
			if (source == null) {
				return null;
			}
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(source);
			out.flush();
			out.close();

			ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
			T dto = (T) in.readObject();
			in.close();
			return dto;
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
	}

	/**
	 * forward page
	 *
	 * @return
	 * @author ThanhNT
	 */
	public static String forwardPage(String pageName) {
		if (!DataUtils.isNullOrEmpty(pageName)) {
			return "pretty:" + pageName.trim();
		}
		return "";
	}

	/*
	 * Kiem tra Long bi null hoac zero
	 *
	 * @param value
	 * 
	 * @return
	 */
	public static boolean isNullOrZero(Long value) {
		return (value == null || value.equals(0L));
	}

	/*
	 * Kiem tra Long bi null hoac zero
	 *
	 * @param value
	 * 
	 * @return
	 */
	public static boolean isNullOrOneNavigate(Long value) {
		return (value == null || value.equals(-1L));
	}

	/**
	 * Kiem tra Bigdecimal bi null hoac zero
	 *
	 * @param value
	 * @return
	 */
	public static boolean isNullOrZero(BigDecimal value) {
		return (value == null || value.compareTo(BigDecimal.ZERO) == 0);
	}

	/**
	 * Lay ten phuong thuc getter
	 *
	 * @param columnName
	 * @return
	 */
	public static String getHibernateName(String columnName) {
		String temp = columnName.toLowerCase();
		String[] arrs = temp.split("_");
		String method = "";
		for (String arr : arrs) {
			method += DataUtils.upperFirstChar(arr);
		}
		return method;
	}

	/**
	 * Upper first character
	 *
	 * @param input
	 * @return
	 */
	public static String upperFirstChar(String input) {
		if (DataUtils.isNullOrEmpty(input)) {
			return input;
		}
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	/**
	 * Lower first characater
	 *
	 * @param input
	 * @return
	 */
	public static String lowerFirstChar(String input) {
		if (DataUtils.isNullOrEmpty(input)) {
			return input;
		}
		return input.substring(0, 1).toLowerCase() + input.substring(1);
	}

	public static Long safeToLong(Object obj1, Long defaultValue) {
		Long result = defaultValue;
		if (obj1 != null) {
			if (obj1 instanceof BigDecimal) {
				return ((BigDecimal) obj1).longValue();
			}
			if (obj1 instanceof BigInteger) {
				return ((BigInteger) obj1).longValue();
			}
			try {
				result = Long.parseLong(obj1.toString());
			} catch (Exception ignored) {
			}
		}

		return result;
	}

	/**
	 * @param obj1 Object
	 * @return Long
	 */
	public static Long safeToLong(Object obj1) {
		return safeToLong(obj1, 0L);
	}

	public static Double safeToDouble(Object obj1, Double defaultValue) {
		Double result = defaultValue;
		if (obj1 != null) {
			try {
				result = Double.parseDouble(obj1.toString());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		return result;
	}


	public static Double safeToDouble(Object obj1) {
		return safeToDouble(obj1, 0.0);
	}

	public static String customFormat(String pattern, double value) {
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value);
	}

	public static Short safeToShort(Object obj1, Short defaultValue) {
		Short result = defaultValue;
		if (obj1 != null) {
			try {
				result = Short.parseShort(obj1.toString());
			} catch (Exception ignored) {
			}
		}

		return result;
	}

	public static Short safeToShort(Object obj1) {
		return safeToShort(obj1, (short) 0);
	}

	/**
	 * @param obj1
	 * @param defaultValue
	 * @return
	 * @author phuvk
	 */
	public static int safeToInt(Object obj1, int defaultValue) {
		int result = defaultValue;
		if (obj1 != null) {
			try {
				result = Integer.parseInt(obj1.toString());
			} catch (Exception ignored) {
			}
		}

		return result;
	}

	/**
	 * @param obj1 Object
	 * @return int
	 */
	public static int safeToInt(Object obj1) {
		return safeToInt(obj1, 0);
	}

	/**
	 * @param obj1 Object
	 * @return String
	 */
	public static String safeToString(Object obj1, String defaultValue) {
		if (obj1 == null) {
			return defaultValue;
		}

		return obj1.toString();
	}

	/**
	 * @param obj1 Object
	 * @return String
	 */
	public static String safeToString(Object obj1) {
		return safeToString(obj1, "");
	}

	/**
	 * safe equal
	 *
	 * @param obj1 Long
	 * @param obj2 Long
	 * @return boolean
	 */
	public static boolean safeEqual(Long obj1, Long obj2) {
		if (obj1 == obj2)
			return true;
		return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
	}

	/**
	 * safe equal
	 *
	 * @param obj1 Long
	 * @param obj2 Long
	 * @return boolean
	 */
	public static boolean safeEqual(BigInteger obj1, BigInteger obj2) {
		if (obj1 == obj2)
			return true;
		return (obj1 != null) && (obj2 != null) && obj1.equals(obj2);
	}

	/**
	 * @param obj1
	 * @param obj2
	 * @return
	 * @date 09-12-2015 17:43:20
	 * @author TuyenLT18
	 * @description
	 */
	public static boolean safeEqual(Short obj1, Short obj2) {
		if (obj1 == obj2)
			return true;
		return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
	}

	/**
	 * safe equal
	 *
	 * @param obj1 String
	 * @param obj2 String
	 * @return boolean
	 */
	public static boolean safeEqual(String obj1, String obj2) {
		if (obj1 == null && obj2 == null)
			return true;
		else if (obj1 == obj2)
			return true;
		return ((obj1 != null) && (obj2 != null) && obj1.equals(obj2));
	}

	/**
	 * check null or empty Su dung ma nguon cua thu vien StringUtils trong apache
	 * common lang
	 *
	 * @param cs String
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNullOrEmpty(final Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNullOrEmpty(final Object[] collection) {
		return collection == null || collection.length == 0;
	}

	public static boolean isNullOrEmpty(final Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * Tra ve doi tuong default neu object la null, neu khong thi tra object
	 *
	 * @param object
	 * @param defaultValue
	 * @param <T>
	 * @return
	 */
	public static <T> T defaultIfNull(final T object, final T defaultValue) {
		return object != null ? object : defaultValue;
	}

	/**
	 * Ham nay mac du nhan tham so truyen vao la object nhung gan nhu chi hoat dong
	 * cho doi tuong la string Chuyen sang dung isNullOrEmpty thay the
	 *
	 * @param obj1
	 * @return
	 */
	@Deprecated
	public static boolean isStringNullOrEmpty(Object obj1) {
		return obj1 == null || obj1.toString().trim().equals("");
	}

	/**
	 * @param obj1 Object
	 * @return BigDecimal
	 */
	public static BigDecimal safeToBigDecimal(Object obj1) {
		BigDecimal result = BigDecimal.ZERO;
		if (obj1 == null) {
			return result;
		}
		try {
			result = new BigDecimal(obj1.toString());
		} catch (Exception ignored) {
		}

		return result;
	}

	public static BigDecimal safeToBigDecimal(Object obj1, BigDecimal defaultvalue) {
		BigDecimal result = defaultvalue;
		if (obj1 == null) {
			return result;
		}
		try {
			result = new BigDecimal(obj1.toString());
		} catch (Exception ignored) {
		}

		return result;
	}

	public static BigInteger safeToBigInteger(Object obj1, BigInteger defaultValue) {
		BigInteger result = defaultValue;
		if (obj1 == null) {
			return result;
		}
		try {
			result = new BigInteger(obj1.toString());
		} catch (Exception ignored) {
		}

		return result;
	}

	public static BigInteger safeToBigInteger(Object obj1) {
		BigInteger result = BigInteger.ZERO;
		try {
			if (obj1 instanceof BigInteger) {
				result = (BigInteger) obj1;
			} else {
				result = new BigInteger(obj1.toString());
			}

		} catch (Exception ignored) {
		}

		return result;
	}

	public static BigInteger length(BigInteger from, BigInteger to) {
		return to.subtract(from).add(BigInteger.ONE);
	}

	/**
	 * add
	 *
	 * @param obj1 BigDecimal
	 * @param obj2 BigDecimal
	 * @return BigDecimal
	 */
	public static BigInteger add(BigInteger obj1, BigInteger obj2) {
		if (obj1 == null) {
			return obj2;
		} else if (obj2 == null) {
			return obj1;
		}

		return obj1.add(obj2);
	}

	/**
	 * @param number
	 * @param pattern
	 * @return
	 * @author KhuongDV Ham format so thuc ve dang co max la 4 chu so thap phan.
	 *         Trim() so 0 vo nghia
	 */
	public static String getFormattedString4Digits(String number, String pattern) {
		double amount = 0;
		try {
			amount = Double.parseDouble(number);
			DecimalFormat formatter = new DecimalFormat(pattern);
			return formatter.format(amount);
		} catch (Exception ex) {
			return number;
		}
	}

	public static Character safeToCharacter(Object value) {
		return safeToCharacter(value, '0');
	}

	public static Character safeToCharacter(Object value, Character defaulValue) {
		if (value == null)
			return defaulValue;
		return String.valueOf(value).charAt(0);
	}

	public static Collection<Long> objLstToLongLst(List<Object> list) {
		Collection<Long> result = new ArrayList<>();
		if (!list.isEmpty()) {
			result.addAll(list.stream().map(DataUtils::safeToLong).collect(Collectors.toList()));
		}
		return result;
	}

	public static Collection<Short> objLstToShortLst(List<Object> list) {
		Collection<Short> result = new ArrayList<>();
		if (!list.isEmpty()) {
			result.addAll(list.stream().map(DataUtils::safeToShort).collect(Collectors.toList()));
		}
		return result;
	}

	public static Collection<BigDecimal> objLstToBigDecimalLst(List<Object> list) {
		Collection<BigDecimal> result = new ArrayList<>();
		if (!list.isEmpty()) {
			result.addAll(list.stream().map(DataUtils::safeToBigDecimal).collect(Collectors.toList()));
		}
		return result;
	}

	public static Collection<Double> objLstToDoubleLst(List<Object> list) {
		Collection<Double> result = new ArrayList<>();
		if (!list.isEmpty()) {
			result.addAll(list.stream().map(DataUtils::safeToDouble).collect(Collectors.toList()));
		}
		return result;
	}

	public static Collection<Character> objLstToCharLst(List<Object> list) {
		Collection<Character> result = new ArrayList<>();
		if (!list.isEmpty()) {
			result.addAll(list.stream().map(item -> item.toString().charAt(0)).collect(Collectors.toList()));
		}

		return result;
	}

	public static Collection<String> objLstToStringLst(List<Object> list) {
		Collection<String> result = new ArrayList<>();
		if (!list.isEmpty()) {
			result.addAll(list.stream().map(DataUtils::safeToString).collect(Collectors.toList()));
		}

		return result;
	}

	/**
	 * Check an object is active
	 *
	 * @param status   status of object
	 * @param isDelete isdetete status of object
	 * @return
	 */
	public static boolean isActive(Character status, Character isDelete) {
		return Objects.equals(status, '1') && (isDelete == null || isDelete.equals('0'));
	}

	public static boolean isNullObject(Object obj1) {
		if (obj1 == null) {
			return true;
		}
		if (obj1 instanceof String) {
			return isNullOrEmpty(obj1.toString());
		}
		if (obj1 instanceof List) {
			return ((List) obj1).isEmpty();
		}
		return false;
	}

	/**
	 * tinh gia tri that cua param ccua tong dai tra ve theo cong thuc =
	 * param/operator
	 *
	 * @param param
	 * @return
	 */
	public static String getProvisionParam(String param, int operator) {
		try {
			String result = "";
			if (isNullOrEmpty(param)) {
				return "";
			}
			Long lmoney = Long.parseLong(param);
			lmoney = lmoney / operator;

			result = lmoney.toString();
			return result;
		} catch (Exception ex) {
			return param;
		}
	}

	public static String toUpper(String input) {
		if (isNullOrEmpty(input)) {
			return input;
		}
		return input.toUpperCase();
	}

	/**
	 * Validate Data theo Pattern
	 *
	 * @author khuongdv
	 */
	public static boolean validateStringByPattern(String value, String regex) {
		if (isNullOrEmpty(regex) || isNullOrEmpty(value)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	/**
	 * Bien cac ki tu dac biet ve dang ascii
	 *
	 * @param input
	 * @return
	 */
	public static String convertCharacter(String input) {
		if (input == null) {
			return "";
		}
		String[] aList = { "à", "á", "ả", "ã", "ạ", "â", "ầ", "ấ", "ẩ", "ẫ", "ậ", "ă", "ằ", "ắ", "ẳ", "ẵ", "ặ" };
		String[] eList = { "è", "é", "ẻ", "ẽ", "ẹ", "ê", "ề", "ế", "ể", "ễ", "ệ" };
		String[] iList = { "ì", "í", "ỉ", "ĩ", "ị" };
		String[] oList = { "ò", "ó", "ỏ", "õ", "ọ", "ô", "ồ", "ố", "ổ", "ỗ", "ộ", "ơ", "ờ", "ớ", "ở", "ỡ", "ợ" };
		String[] uList = { "ù", "ú", "ủ", "ũ", "ụ", "ư", "ừ", "ứ", "ử", "ữ", "ự" };
		String[] yList = { "ý", "ỳ", "ỷ", "ỹ", "ỵ" };
		String[] AList = { "À", "Á", "Ả", "Ã", "Ạ", "Â", "Ầ", "Ấ", "Ẩ", "Ẫ", "Ậ", "Ă", "Ằ", "Ắ", "Ẳ", "Ẵ", "Ặ" };
		String[] EList = { "È", "É", "Ẻ", "Ẽ", "Ẹ", "Ê", "Ề", "Ế", "Ể", "Ễ", "Ệ" };
		String[] IList = { "Ì", "Í", "Ỉ", "Ĩ", "Ị" };
		String[] OList = { "Ò", "Ó", "Ỏ", "Õ", "Ọ", "Ô", "Ồ", "Ố", "Ổ", "Ỗ", "Ộ", "Ơ", "Ờ", "Ớ", "Ở", "Ỡ", "Ợ" };
		String[] UList = { "Ù", "Ú", "Ủ", "Ũ", "Ụ", "Ư", "Ừ", "Ứ", "Ử", "Ữ", "Ự" };
		String[] YList = { "Ỳ", "Ý", "Ỷ", "Ỹ", "Ỵ" };
		for (String s : aList) {
			input = input.replace(s, "a");
		}
		for (String s : AList) {
			input = input.replace(s, "A");
		}
		for (String s : eList) {
			input = input.replace(s, "e");
		}
		for (String s : EList) {
			input = input.replace(s, "E");
		}
		for (String s : iList) {
			input = input.replace(s, "i");
		}
		for (String s : IList) {
			input = input.replace(s, "I");
		}
		for (String s : oList) {
			input = input.replace(s, "o");
		}
		for (String s : OList) {
			input = input.replace(s, "O");
		}
		for (String s : uList) {
			input = input.replace(s, "u");
		}
		for (String s : UList) {
			input = input.replace(s, "U");
		}
		for (String s : yList) {
			input = input.replace(s, "y");
		}
		for (String s : YList) {
			input = input.replace(s, "Y");
		}
		input = input.replace("đ", "d");
		input = input.replace("Đ", "D");
		return input;
	}

	public static Map<String, String> convertStringToMap(String temp, String regex, String regexToMap) {
		if (isNullOrEmpty(temp)) {
			return null;
		}
		String[] q = temp.split(regex);
		Map<String, String> lstParam = new HashMap<>();
		for (int i = 0; i < q.length; i++) {
			String a = q[i];
			String key = a.substring(0, (a.indexOf(regexToMap) < 0 ? 1 : a.indexOf(regexToMap)));
			String value = a.substring(a.indexOf(regexToMap) + 1);
			lstParam.put(key.trim(), value.trim());
		}
		return lstParam;
	}

	/*
	 * toanld2 ham xu li khoang trang giua xau
	 **/
	public static String replaceSpaceSolr(String inputLocation) {
		if (inputLocation == null || inputLocation.trim().isEmpty()) {
			return "";
		}
		String[] arr = inputLocation.split(" ");
		String result = "";
		for (String s : arr) {
			if (!"".equals(s.trim())) {
				if (!"".equals(result)) {
					result += "\\ ";
				}
				result += s.trim();
			}
		}
		return result;
	}

	public static boolean isNumber(String string) {
		return !isNullOrEmpty(string) && string.trim().matches("^\\d+$");
	}

	public static String addIsdn84(String isdn) {
		if (isNullOrEmpty(isdn) || isdn.length() < 2) {
			return isdn;
		}
		if (!"84".equals(isdn.substring(0, 2))) {
			isdn = "0".equals(isdn.substring(0, 1)) ? "84" + isdn.substring(1) : "84" + isdn;
		}
		return isdn;
	}

	public static boolean isValidFraction(String str) {
		if (str != null) {
			try {
				String tmp[] = str.split("/");
				if (tmp.length == 2) {
					if (safeToLong(tmp[0]) < safeToLong(tmp[1])) {
						return true;
					}
				}
			} catch (Exception e) {

			}
		}
		return false;

	}

	/**
	 * Tim nhung phan tu co o collection a ma khong co o collection b
	 *
	 * @param a
	 * @param b
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> subtract(Collection<T> a, Collection<T> b) {
		if (a == null || b == null) {
			return new ArrayList<>();
		}
		List<T> list = new ArrayList<>(a);
		list.removeAll(b);
		return list;
	}

	public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {
		if (a == null || b == null) {
			return new ArrayList<>();
		}
		List<T> list = new ArrayList<>(a);
		list.retainAll(b);
		return list;
	}


	/**
	 * Trim string
	 *
	 * @param str
	 * @param alt: sau thay the khi str null
	 * @return
	 */
	public static String trim(String str, String alt) {
		if (str == null) {
			return alt;
		}
		return str.trim();
	}

	public static BigDecimal defaultIfSmallerThanZero(BigDecimal value) {
		return defaultIfSmallerThanZero(value, BigDecimal.ZERO);
	}

	public static BigDecimal defaultIfSmallerThanZero(BigDecimal value, BigDecimal defaultValue) {
		if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
			return defaultValue;
		}
		return value;
	}

	private static String convertUnicode2Nosign(String org) {

		char arrChar[] = org.toCharArray();
		char result[] = new char[arrChar.length];
		for (int i = 0; i < arrChar.length; i++) {
			switch (arrChar[i]) {
			case '\u00E1':
			case '\u00E0':
			case '\u1EA3':
			case '\u00E3':
			case '\u1EA1':
			case '\u0103':
			case '\u1EAF':
			case '\u1EB1':
			case '\u1EB3':
			case '\u1EB5':
			case '\u1EB7':
			case '\u00E2':
			case '\u1EA5':
			case '\u1EA7':
			case '\u1EA9':
			case '\u1EAB':
			case '\u1EAD':
			case '\u0203':
			case '\u01CE': {
				result[i] = 'a';
				break;
			}
			case '\u00E9':
			case '\u00E8':
			case '\u1EBB':
			case '\u1EBD':
			case '\u1EB9':
			case '\u00EA':
			case '\u1EBF':
			case '\u1EC1':
			case '\u1EC3':
			case '\u1EC5':
			case '\u1EC7':
			case '\u0207': {
				result[i] = 'e';
				break;
			}
			case '\u00ED':
			case '\u00EC':
			case '\u1EC9':
			case '\u0129':
			case '\u1ECB': {
				result[i] = 'i';
				break;
			}
			case '\u00F3':
			case '\u00F2':
			case '\u1ECF':
			case '\u00F5':
			case '\u1ECD':
			case '\u00F4':
			case '\u1ED1':
			case '\u1ED3':
			case '\u1ED5':
			case '\u1ED7':
			case '\u1ED9':
			case '\u01A1':
			case '\u1EDB':
			case '\u1EDD':
			case '\u1EDF':
			case '\u1EE1':
			case '\u1EE3':
			case '\u020F': {
				result[i] = 'o';
				break;
			}
			case '\u00FA':
			case '\u00F9':
			case '\u1EE7':
			case '\u0169':
			case '\u1EE5':
			case '\u01B0':
			case '\u1EE9':
			case '\u1EEB':
			case '\u1EED':
			case '\u1EEF':
			case '\u1EF1': {
				result[i] = 'u';
				break;
			}
			case '\u00FD':
			case '\u1EF3':
			case '\u1EF7':
			case '\u1EF9':
			case '\u1EF5': {
				result[i] = 'y';
				break;
			}
			case '\u0111': {
				result[i] = 'd';
				break;
			}
			case '\u00C1':
			case '\u00C0':
			case '\u1EA2':
			case '\u00C3':
			case '\u1EA0':
			case '\u0102':
			case '\u1EAE':
			case '\u1EB0':
			case '\u1EB2':
			case '\u1EB4':
			case '\u1EB6':
			case '\u00C2':
			case '\u1EA4':
			case '\u1EA6':
			case '\u1EA8':
			case '\u1EAA':
			case '\u1EAC':
			case '\u0202':
			case '\u01CD': {
				result[i] = 'A';
				break;
			}
			case '\u00C9':
			case '\u00C8':
			case '\u1EBA':
			case '\u1EBC':
			case '\u1EB8':
			case '\u00CA':
			case '\u1EBE':
			case '\u1EC0':
			case '\u1EC2':
			case '\u1EC4':
			case '\u1EC6':
			case '\u0206': {
				result[i] = 'E';
				break;
			}
			case '\u00CD':
			case '\u00CC':
			case '\u1EC8':
			case '\u0128':
			case '\u1ECA': {
				result[i] = 'I';
				break;
			}
			case '\u00D3':
			case '\u00D2':
			case '\u1ECE':
			case '\u00D5':
			case '\u1ECC':
			case '\u00D4':
			case '\u1ED0':
			case '\u1ED2':
			case '\u1ED4':
			case '\u1ED6':
			case '\u1ED8':
			case '\u01A0':
			case '\u1EDA':
			case '\u1EDC':
			case '\u1EDE':
			case '\u1EE0':
			case '\u1EE2':
			case '\u020E': {
				result[i] = 'O';
				break;
			}
			case '\u00DA':
			case '\u00D9':
			case '\u1EE6':
			case '\u0168':
			case '\u1EE4':
			case '\u01AF':
			case '\u1EE8':
			case '\u1EEA':
			case '\u1EEC':
			case '\u1EEE':
			case '\u1EF0': {
				result[i] = 'U';
				break;
			}
			case '\u00DD':
			case '\u1EF2':
			case '\u1EF6':
			case '\u1EF8':
			case '\u1EF4': {
				result[i] = 'Y';
				break;
			}

			case '\u0110':
			case '\u00D0':
			case '\u0089': {
				result[i] = 'D';
				break;
			}
			default:
				result[i] = arrChar[i];
			}
		}
		return new String(result);
	}

	public static Object convertNullToEmpty(Object value) {
		return value == null ? "" : value;
	}

	public static boolean safeEqual(Object obj1, Object obj2) {
		return ((obj1 != null) && (obj2 != null) && obj2.toString().equals(obj1.toString()));
	}

	// thiendn1: format cho don vi tien te khi in hoa don
	public static Object convertCommaToDot(Object value) {
		if (!(value instanceof Number)) {
			return value;
		}
		DecimalFormat formatter = new DecimalFormat("###,###");
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		formatter.setDecimalFormatSymbols(symbols);
		return formatter.format(value);
	}

	public static String formatSerial(int serialLength, BigInteger serial) {
		int prefix = serialLength - serial.toString().length();
		String serialFormat = prefix == 0 ? "%d" : ("%0" + String.valueOf(serialLength) + "d");
		return String.format(serialFormat, serial);
	}

	public static String getMimeType(String fileExtension) {
		switch (fileExtension) {
		case "pdf":
			return "application/pdf";
		case "png":
			return "image/png";
		case "jpg":
			return "image/jpeg";
		case "bmp":
			return "image/bmp";
		case "gif":
			return "image/gif";
		case "jpe":
			return "image/jpeg";
		case "jpeg":
			return "image/jpeg";
		default:
			return "";
		}
	}

	public static boolean checkPhone(String input) {
		boolean isOk = true;
		if (isNullOrEmpty(input)) {
			return isOk;
		}
		return validateStringByPattern(input, PHONE_PATTERN);
	}

	/**
	 * ham compare 2 object Model, chi dung voi cac thuoc tinh kieu nguyen thuy
	 * (primitive type)
	 *
	 * @param oldObj
	 * @param newObj
	 * @return
	 */
	public static boolean compareTwoObj(Object oldObj, Object newObj) {
		try {
			if ((oldObj == null && newObj != null) || (oldObj != null && newObj == null)) {
				return false;
			}
			if (oldObj == null) {
				return true;
			}
			if (!safeEqual(oldObj.getClass().getName(), newObj.getClass().getName())) {
				return false;
			}
			Method[] arrMethod = oldObj.getClass().getDeclaredMethods();
			Method tempMethod;
			for (int i = 0; i < arrMethod.length; i++) {
				tempMethod = arrMethod[i];
				if (!tempMethod.getName().startsWith("get")) {
					continue;
				}
				Object oldBO = null;
				oldBO = tempMethod.invoke(oldObj);
				Object newBO = tempMethod.invoke(newObj);
				String oldValue = "";
				if (oldBO != null) {
					if (oldBO instanceof Date) {
						SimpleDateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						oldValue = yyyyMMdd.format(oldBO);
					} else {
						oldValue = oldBO.toString();
					}
				}
				String newValue = "";
				if (newBO != null) {
					if (newBO instanceof Date) {
						SimpleDateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
						newValue = yyyyMMdd.format(newBO);
					} else {
						newValue = newBO.toString();
					}
				}
				if (!oldValue.equals(newValue)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static String getEndPoint() {
		try {
			MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
			Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"),
					Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
			String hostname = InetAddress.getLocalHost().getHostName();
			InetAddress addresses = InetAddress.getByName(hostname);
			for (ObjectName obj : objs) {
				String port = obj.getKeyProperty("port");
				String host = addresses.getHostAddress();
				return host + ":" + port;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return "";
	}

	public static Short convertBooleanToShort(boolean isCheck) {
		if (isCheck) {
			return Short.valueOf("1");
		} else {
			return Short.valueOf("0");
		}
	}

	public static boolean convertShortToBoolean(Short isCheck) {
		return isCheck == 1;
	}

	public static Object getIdFromFileInput(String cellValue, Class expectClass) {
		if (DataUtils.isNullOrEmpty(cellValue)) {
			return null;
		}
		String id = cellValue.split(ID_SEPARATE_DASH)[0];
		if (expectClass != null) {
			if (expectClass.equals(Long.class)) {
				return Long.valueOf(id);
			}
			if (expectClass.equals(Short.class)) {
				return Short.valueOf(id);
			}
		}
		return id;
	}

	public static String escapeSpecialCharacterToSearch(String value) {
		return value.replaceAll("\\_", "\\$_").replaceAll("\\%", "\\$%");
	}
	private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z private
	private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z private static
	private static final String digits = "0123456789"; // 0-9
	private static final String specials = "~=+%^*/()[]{}/!@#$?|";
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static final String ALL = alpha + alphaUpperCase + digits + specials;
	private static Random generator = new Random();

	/** * Random string with a-zA-Z0-9, not included special characters */
	public static String randomAlphaNumeric(int numberOfCharactor) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfCharactor; i++) {
			int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
			char ch = ALPHA_NUMERIC.charAt(number);
			sb.append(ch);
		}
		return sb.toString();
	}

	public static int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}
	
	public static String covertConnectorCodeToSubNodeCode(String connectorCode) {
		String[] parts = connectorCode.split("-");
		if (parts.length == 4) {
			return parts[0] + "-" + parts[1] + "-" + parts[2];
		}
		return "";
	}
	
	public static String covertReasonNameToPromotionReasonName(String reasonName, Double prepaidMoneyPromotion) {
		String[] parts = reasonName.split("/");
		if (parts.length == 2) {
			Locale locale = new Locale("en", "EN");
		    String pattern = "###,###.##";
		    DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		    dcf.applyPattern(pattern);
		    
		    String prepaidMoneyFormat = dcf.format(prepaidMoneyPromotion);
		    
			return parts[0] + "/" + prepaidMoneyFormat;
		}
		return "";
	}
	
	private Long objectToDate(Object o) {
		try {
			if (o != null)
				return ((Date) o).getTime();
			else
				return null;
		} catch (Exception e) {
			return null;
		}
	}

	
}
