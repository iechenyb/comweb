package com.cyb.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 

public class Encryption {
	//是否是纯数字
	public static boolean isNumeric(String str){
		  for (int i = 0; i < str.length(); i++){
		  // System.out.println(str.charAt(i));
		   if (!Character.isDigit(str.charAt(i))){
		    return false;
		   }
		  }
		  return true;
		 }
	
	//是否是字母和数字的组合
	public static boolean is_zs(String alpha) {
		if(alpha==null) return false;
	    return alpha.matches("[0-9a-zA-Z]+");    
	}
	
	//是否只包含字母数字和汉字的组合
	public static boolean is_zsh(String chineseContent) {
		boolean flag ;
		if(chineseContent==null) return false;
		flag = chineseContent.matches("[a-zA-Z0-9\u4E00-\u9FA5]+");
		return flag;
	} 
	
	/**
     * 获取字符串的长度，对双字符（包括汉字）按两位计数
     * 
     * @param value
     * @return
     */
    public static boolean getUserLength(String value) {
    	boolean flag = false;
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        if(valueLength >= 4 && valueLength <= 16){
        	flag = true;
        }
        return flag;
    }
    
    /**
     * 检查密码长度
     * @param value
     * @return
     */
    public static boolean getPasswordLength(String value) {
    	boolean flag = false;
        int length = value.length();
        if( length >= 6 && length <= 16){
        	flag = true;
        }
        return flag;
    }
	
	//转乱码
	public static String covertString(String str){
		try {
			return new String(str.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String encryptionMD5(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
 
            int i;
 
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
 
            re_md5 = buf.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
	public static String encryptSHA1(String strSrc) {
	    MessageDigest md=null;
	    String strDes=null;
	 
	    byte[] bt=strSrc.getBytes();
	    try {
	 
	                 md=MessageDigest.getInstance("SHA-1");
	                 md.update(bt);
	                strDes=bytes2Hex(md.digest());  //to HexString
	                }catch (NoSuchAlgorithmException e) {
	                        System.out.println("Invalid algorithm.");
	                        return null;
	                        }
	        return strDes;
	         }
	 
	        public static String bytes2Hex(byte[]bts) {
	         String des="";
	         String tmp=null;
	         for (int i=0;i<bts.length;i++) {
	                    tmp=(Integer.toHexString(bts[i] & 0xFF));
	                    if (tmp.length()==1) {
	                        des+="0";
	                    }
	                    des+=tmp;
	                }
	                return des;
	        }
	        /**
	         * 把字符串转化成 Unicode Bytes.
	         *
	         * @param s String
	         * @return byte[]
	         */
	        public static byte [] stringBytes(String s) {
	                byte[] bytes = null;
	                if (s != null) {
	                        try {
	                                bytes = s.getBytes("utf-8");
	                        } catch (UnsupportedEncodingException e) {
	                                e.printStackTrace();
	                        }
	                }
	                return bytes;
	        }
	        
	        /**将二进制转换成16进制
	         * @param buf
	         * @return
	         */
	        public static String parseByte2HexStr(byte buf[]) {
	                StringBuffer sb = new StringBuffer();
	                for (int i = 0; i < buf.length; i++) {
	                        String hex = Integer.toHexString(buf[i] & 0xFF);
	                        if (hex.length() == 1) {
	                                hex = '0' + hex;
	                        }
	                        sb.append(hex.toUpperCase());
	                }
	                return sb.toString();
	        }
    	// 将 s 进行 BASE64 编码  
    	public static String getBASE64(String s) {  
    		//return s;
    		if (s == null) return null;  
    		return (new BASE64Encoder()).encode( s.getBytes() );  
    	}  
    	public static String getBASE64(byte[] s) {  
    		//return s;
    		return (new BASE64Encoder()).encode( s );  
    	}  
    	// 将 BASE64 编码的字符串 s 进行解码  
    	public static String getFromBASE64(String s) {  
    		//return s;
    		if (s == null) return null;  
    		BASE64Decoder decoder = new BASE64Decoder();  
    		try {  
    			byte[] b = decoder.decodeBuffer(s);  
    			return new String(b);  
    		} catch (Exception e) {  
    			return null;  
    		}  
    	} 
    	public static final String Key = "ABCDEFGHIJKLMNOPQRSTUVWXYZCESFINANCIAL";
        private static final String Algorithm = "DES";  //定义 加密算法,可用 DES,DESede,Blowfish
     
        // 加密字符串
        public static String encrypt3DESMode(String src) {
            DESedeKeySpec dks;
			try {
				dks = new DESedeKeySpec(Key.getBytes("UTF-8"));
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
				SecretKey securekey = keyFactory.generateSecret(dks);
				
				Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, securekey);
				byte[] b=cipher.doFinal(src.getBytes());
				
				BASE64Encoder encoder = new BASE64Encoder();
				return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
			return null;
        }
     
        // 解密字符串
        public static byte[] decrypt3DESMode(byte[] keybyte, byte[] src) {
            try { // 生成密钥
                SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 解密
                Cipher c1 = Cipher.getInstance(Algorithm);
                c1.init(Cipher.DECRYPT_MODE, deskey);
                return c1.doFinal(src);
            } catch (java.security.NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            } catch (javax.crypto.NoSuchPaddingException e2) {
                e2.printStackTrace();
            } catch (java.lang.Exception e3) {
                e3.printStackTrace();
            }
            return null;
        }
    public static byte[] desEncrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(message.getBytes("UTF-8"));
    }
    
    // 可逆的加密算法 
    public static String KL(String inStr) { 
     // String s = new String(inStr); 
     char[] a = inStr.toCharArray(); 
     for (int i = 0; i < a.length; i++) { 
      a[i] = (char) (a[i] ^ 't'); 
     } 
     String s = new String(a); 
     return s; 
    } 
    
 // 加密后解密 
    public static String JM(String inStr) { 
     char[] a = inStr.toCharArray(); 
     for (int i = 0; i < a.length; i++) { 
      a[i] = (char) (a[i] ^ 't'); 
     } 
     String k = new String(a); 
     return k; 
    } 
    
	public static void main(String [] args){
		//System.out.println(encryptSHA1("admin"));
	    Security.addProvider(new com.sun.crypto.provider.SunJCE());
	    String encoded;
		encoded = encrypt3DESMode("111111");
		System.out.println(encoded);
		System.out.println(getBASE64(encoded)); 
		System.out.println(encrypt3DESMode("1111"));
		System.out.println(KL("asasas111"));
		System.out.println(JM("=MTIxMg=="));
		System.out.println(is_zsh("1234测试"));
		System.out.println(getBASE64("12121212"));
		System.out.println(getFromBASE64("MTIxMjEyMTI="));
	}

}
