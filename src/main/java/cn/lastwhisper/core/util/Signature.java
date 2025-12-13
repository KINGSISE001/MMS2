package cn.lastwhisper.core.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;


public class Signature {
	private static final Logger log = LoggerFactory.getLogger(Signature.class);
    /**
     * 请求参数转Map
     *
     * @param paramMap
     * @return
     */
    public static TreeMap<String, Object> switchMap(Map<String, String[]> paramMap) {
        TreeMap<String, Object> resultMap = new TreeMap<>();
        for (Map.Entry<String, String[]> param : paramMap.entrySet()) {
            resultMap.put(param.getKey(), param.getValue()[0]);
        }
        return resultMap;
    }

    /**
     * 请求数据获取签名
     *
     * @param paramMap 请求参数
     * @param signKey  签名Key
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String getSign(TreeMap<String, Object> paramMap,String SignKey)  {
    	try {
    		StringBuilder sb = new StringBuilder();
        	paramMap.remove("sign");
        	//获得k1=v1&k2=v2 字符串
            for (Map.Entry entry : paramMap.entrySet()) {
                sb = sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            //添加Key
            sb.append("key").append("=").append(SignKey);
          
            //log.info("加密前[{}]:" , sb.toString());
            String md5Str = DigestUtils.md5DigestAsHex(sb.toString().getBytes(StandardCharsets.UTF_8)).toUpperCase();
            //log.info("MD5SIGN:[{}]" , md5Str);
            return md5Str;
		} catch (Exception e) {
			log.error("验签出错：{}",e.getLocalizedMessage());
		}
		return null;
    	
    }

    /**
     * 签名校验
     *
     * @param paramMap 请求参数
     * @param signKey  签名Key
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static boolean isSignEquals(TreeMap<String, Object> paramMap ,String SignKey) throws UnsupportedEncodingException {
        String sign = ((String) paramMap.get("sign")).toUpperCase();
       // System.err.println("传的sign:"+sign);
        String md5Str = getSign(paramMap,SignKey);
        System.err.println(md5Str);
        return sign.equals(md5Str);
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }
    /**
     * 获得k1=v1&k2=v2 字符串
     * @param map
     * @return
     */
    public static String getKeyAndValueStr(TreeMap<String, Object> map,String Sign) {
    	StringBuilder sb = new StringBuilder();
        try {
        	//获得k1=v1&k2=v2 字符串
            for (Map.Entry entry : map.entrySet()) {
                sb = sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            //添加Key
            sb.append("sign").append("=").append(Sign);
          
           // log.info("转化后：[{}]:" , sb.toString());
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return null;
        }
      
        return sb.toString().trim();
    }
    
    /**
	 * 将unicode码转化成字符串
	 * @author shuai.ding
	 * @param unicode
	 * @return
	 */
	public static String unicode2String(String unicode) {
		if (StringUtils.isBlank(unicode)) {
			return null;
		}
 
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;
 
		while ((i = unicode.indexOf("\\u", pos)) != -1) {
			sb.append(unicode, pos, i);
			if (i + 5 < unicode.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
			}
		}
		//如果pos位置后，有非中文字符，直接添加
		sb.append(unicode.substring(pos));
 
		return sb.toString();
	}
	/**
	 * 将字符串转化成unicode码
	 * @author shuai.ding
	 * @param string
	 * @return
	 */
	private String string2Unicode(String string) {
 
		if (StringUtils.isBlank(string)) {
			return null;
		}
 
		char[] bytes = string.toCharArray();
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			char c = bytes[i];
 
			// 标准ASCII范围内的字符，直接输出
			if (c >= 0 && c <= 127) {
				unicode.append(c);
				continue;
			}
			String hexString = Integer.toHexString(bytes[i]);
 
			unicode.append("\\u");
 
			// 不够四位进行补0操作
			if (hexString.length() < 4) {
				unicode.append("0000", hexString.length(), 4);
			}
			unicode.append(hexString);
		}
		return unicode.toString();
	}

}
