package com.lakala.cheatservice.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrUtils {
	
//	  private static Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
	
	private static Pattern emoji = Pattern.compile ("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
	
	  
	  public static boolean ismatch(String str){
		  boolean flag = false;
		  Matcher emojiMatcher =  emoji.matcher(str);
		  if(emojiMatcher.find()){
			  flag = true;
		  }
		  return flag;
		  
	  }
	  
	  
	  // 转义时标识
	    private static final char unicode_separator = '&';
	    private static final char unicode_prefix = 'u';
	    private static final char separator = ':';

	    private static boolean isEmojiCharacter(int codePoint) {
	        return (codePoint >= 0x2600 && codePoint <= 0x27BF) // 杂项符号与符号字体
	                || codePoint == 0x303D
	                || codePoint == 0x2049
	                || codePoint == 0x203C
	                || (codePoint >= 0x2000 && codePoint <= 0x200F)//
	                || (codePoint >= 0x2028 && codePoint <= 0x202F)//
	                || codePoint == 0x205F //
	                || (codePoint >= 0x2065 && codePoint <= 0x206F)//
	                /* 标点符号占用区域 */
	                || (codePoint >= 0x2100 && codePoint <= 0x214F)// 字母符号
	                || (codePoint >= 0x2300 && codePoint <= 0x23FF)// 各种技术符号
	                || (codePoint >= 0x2B00 && codePoint <= 0x2BFF)// 箭头A
	                || (codePoint >= 0x2900 && codePoint <= 0x297F)// 箭头B
	                || (codePoint >= 0x3200 && codePoint <= 0x32FF)// 中文符号
	                || (codePoint >= 0xD800 && codePoint <= 0xDFFF)// 高低位替代符保留区域
	                || (codePoint >= 0xE000 && codePoint <= 0xF8FF)// 私有保留区域
	                || (codePoint >= 0xFE00 && codePoint <= 0xFE0F)// 变异选择器
	                || codePoint >= 0x10000; // Plane在第二平面以上的，char都不可以存，全部都转
	    }

	    /**
	     * 将带有emoji字符的字符串转换成可见字符标识
	     */
	    public static String escape(String src) {
	        if (src == null) {
	            return null;
	        }
	        int cpCount = src.codePointCount(0, src.length());
	        int firCodeIndex = src.offsetByCodePoints(0, 0);
	        int lstCodeIndex = src.offsetByCodePoints(0, cpCount - 1);
	        StringBuilder sb = new StringBuilder(src.length());
	        for (int index = firCodeIndex; index <= lstCodeIndex;) {
	            int codepoint = src.codePointAt(index);
	            if (isEmojiCharacter(codepoint)) {
	                String hash = Integer.toHexString(codepoint);
	                sb.append(unicode_separator).append(hash.length()).append(unicode_prefix).append(separator).append(hash);
	            } else {
	                sb.append((char) codepoint);
	            }
	        }
	        return sb.toString();
	    }

	    /** 解析可见字符标识字符串 */
	    public static String reverse(String src) {
	        // 查找对应编码的标识位
	        if (src == null) {
	            return null;
	        }
	        StringBuilder sb = new StringBuilder(src.length());
	        char[] sourceChar = src.toCharArray();
	        int index = 0;
	        while (index < sourceChar.length) {
	            if (sourceChar[index] == unicode_separator) {
	                if (index + 6 >= sourceChar.length) {
	                    sb.append(sourceChar[index]);
	                    index++;
	                    continue;
	                }
	                // 自已的格式，与通用unicode格式不能互转
	                if (sourceChar[index + 1] >= '4' && sourceChar[index + 1] <= '6' && sourceChar[index + 2] == unicode_prefix && sourceChar[index + 3] == separator) {
	                    int length = Integer.parseInt(String.valueOf(sourceChar[index + 1]));
	                    char[] hexchars = new char[length]; // 创建一个4至六位的数组，来存储uncode码的HEX值
	                    for (int j = 0; j < length; j++) {
	                        char ch = sourceChar[index + 4 + j];// 4位识别码
	                        if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f')) {
	                            hexchars[j] = ch;

	                        } else { // 字符范围不对
	                            sb.append(sourceChar[index]);
	                            index++;
	                            break;
	                        }
	                    }
	                    sb.append(Character.toChars(Integer.parseInt(new String(hexchars), 16)));
	                    index += (4 + length);// 4位前缀+4-6位字符码
	                } else if (sourceChar[index + 1] == unicode_prefix) { // 通用字符的反转
	                    // 因为第二平面之上的，已经采用了我们自己转码格式，所以这里是固定的长度4
	                    char[] hexchars = new char[4];
	                    for (int j = 0; j < 4; j++) {
	                        char ch = sourceChar[index + 2 + j]; // 两位识别码要去掉
	                        if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'f')) {
	                            hexchars[j] = ch; // 4位识别码
	                        } else { // 字符范围不对
	                            sb.append(sourceChar[index]);
	                            index++;
	                            break;
	                        }
	                        sb.append(Character.toChars(Integer.parseInt(String.valueOf(hexchars), 16)));
	                        index += (2 + 4);// 2位前缀+4位字符码
	                    }
	                } else {
	                    sb.append(sourceChar[index]);
	                    index++;
	                    continue;
	                }
	            } else {
	                sb.append(sourceChar[index]);
	                index++;
	                continue;
	            }
	        }

	        return sb.toString();
	    }

	    public static String filter(String src) {
	        if (src == null) {
	            return null;
	        }
	        int cpCount = src.codePointCount(0, src.length());
	        int firCodeIndex = src.offsetByCodePoints(0, 0);
	        int lstCodeIndex = src.offsetByCodePoints(0, cpCount - 1);
	        StringBuilder sb = new StringBuilder(src.length());
	        for (int index = firCodeIndex; index <= lstCodeIndex;) {
	            int codepoint = src.codePointAt(index);
	            if (!isEmojiCharacter(codepoint)) {
	                System.err.println("codepoint:" + Integer.toHexString(codepoint));
	                sb.append((char) codepoint);
	            }
	            index += ((Character.isSupplementaryCodePoint(codepoint)) ? 2 : 1);

	        }
	        return sb.toString();
	    }
	  
	   public static boolean requiresMb4(String s) {
		    int len = s.length();
		    return len != s.codePointCount(0, len);
		}
	   
	   
	   static public String filterOffUtf8Mb42(String text) throws UnsupportedEncodingException {
	        byte[] bytes = text.getBytes("utf-8");
	        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
	        int i = 0;
	        while (i < bytes.length) {
	            short b = bytes[i];
	            if (b > 0) {
	                buffer.put(bytes[i++]);
	                continue;
	            }
	            b += 256;
	            if ((b ^ 0xC0) >> 4 == 0) {
	                buffer.put(bytes, i, 2);
	                i += 2;
	            }
	            else if ((b ^ 0xE0) >> 4 == 0) {
	                buffer.put(bytes, i, 3);
	                i += 3;
	            }
	            else if ((b ^ 0xF0) >> 4 == 0) {
	                i += 4;
	            }
	        }
	        buffer.flip();
	        return new String(buffer.array(), "utf-8");
	    }
	   
	   
	   public static String filterOffUtf8Mb43(String text) throws UnsupportedEncodingException { 
	        byte[] bytes = text.getBytes("utf-8"); 
	        ByteBuffer buffer = ByteBuffer.allocate(bytes.length); 
	        int i = 0; 
	        while (i < bytes.length) { 
	            short b = bytes[i]; 
	            if (b > 0) { 
	                buffer.put(bytes[i++]); 
	                continue; 
	            } 


	            b += 256; // 去掉符号位 


	            if (((b >> 5) ^ 0x6) == 0) { 
	                buffer.put(bytes, i, 2); 
	                i += 2; 
	            } else if (((b >> 4) ^ 0xE) == 0) { 
	            buffer.put(bytes, i, 3); 
	                i += 3; 
	            } else if (((b >> 3) ^ 0x1E) == 0) { 
	                i += 4; 
	            } else if (((b >> 2) ^ 0x3E) == 0) { 
	                i += 5; 
	            } else if (((b >> 1) ^ 0x7E) == 0) { 
	                i += 6; 
	            } else { 
	                buffer.put(bytes[i++]); 
	            } 
	        } 
	        buffer.flip(); 
	        return new String(buffer.array(), 0, buffer.limit(), "utf-8"); 
	    } 
	   
	   public static  String filterOffUtf8Mb4(String text) throws UnsupportedEncodingException {
	        byte[] bytes = text.getBytes("utf-8");
	        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
	        int i = 0;
	        while (i < bytes.length) {
	            short b = bytes[i];
	            if (b > 0) {
	                buffer.put(bytes[i++]);
	                continue;
	            }
	            b += 256;
	            if ((b ^ 0xC0) >> 4 == 0) {
	                buffer.put(bytes, i, 2);
	                i += 2;
	            }
	            else if ((b ^ 0xE0) >> 4 == 0) {
	                buffer.put(bytes, i, 3);
	                i += 3;
	            }
	            else if ((b ^ 0xF0) >> 4 == 0) {
	                i += 4;
	            }
	        }
	        buffer.flip();
	        return new String(buffer.array(), 0, buffer.limit(), "utf-8"); 
	    }
	    
	   // 判断一个字符是否是中文
	   public static boolean isChinese(char c) {
	         return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
	   }
	   // 判断一个字符串是否含有中文
	   public static boolean isChinese(String str) {
	       if (str == null) return false;
	       for (char c : str.toCharArray()) {
	           if (isChinese(c)) return true;// 有一个中文字符就返回
	       }
	       return false;
	   }
	   public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	        return dest;
	    }
	   public static boolean isContainsStr(String phone) {  
	        String regex=".*[a-zA-Z]+.*";  
	        Matcher m=Pattern.compile(regex).matcher(phone);  
	        return m.matches();  
	    }  
	   
	  public static void main(String[] args) throws UnsupportedEncodingException{
//		  System.out.println(".↙aaaa".replaceAll("/[\u4E00-\u9FB0]{2}", ""));
		  
		
//		  System.out.println(ismatch(".↙aaaa"));
//		  String str = "🍉🍉";
//		  str = escape(".↙🍉🍉");
//		  System.out.println(str);
		  String str = "adfdf\nsdfsdf\tdfs";
		  System.out.println(str);
		  System.out.println(replaceBlank(str));
	  }
}
