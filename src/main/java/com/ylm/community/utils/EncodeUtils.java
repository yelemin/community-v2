package com.ylm.community.utils;

import com.google.common.base.Strings;
import com.qcloud.cos.utils.StringUtils;
import com.ylm.community.constant.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author flyingwhale
 * @date 2022/10/5
 */
public final class EncodeUtils {

    public static final class Base64 {

        public static String encode(byte[] data) {
            return org.apache.commons.codec.binary.Base64.encodeBase64String(data);
        }

        public static byte[] decode(String encodeContent) {
            return org.apache.commons.codec.binary.Base64.decodeBase64(encodeContent);
        }

        public static byte[] decode(byte[] encodeContent) {
            return org.apache.commons.codec.binary.Base64.decodeBase64(encodeContent);
        }

    }

    public static final class AES {

        private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

        private static final String ALGORITHM = "AES";

        private AES() {
        }

        public static byte[] encode(byte[] data, String key) {
            if (data != null && data.length > 0 && !Strings.isNullOrEmpty(key)) {
                try {
                    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                    cipher.init(1, new SecretKeySpec(key.getBytes(CommonConstants.CHARSET), ALGORITHM));
                    return cipher.doFinal(data);
                } catch (Exception var4) {
                    throw new IllegalArgumentException("aes encrypt error", var4);
                }
            } else {
                return null;
            }
        }

        public static String encodeAndConvertBase64(byte[] data, String key) {
            return Base64.encode(encode(data, key));
        }

        public static byte[] decode(byte[] encodeData, String key) {
            if (encodeData != null && encodeData.length > 0 && !Strings.isNullOrEmpty(key)) {
                try {
                    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
                    cipher.init(2, new SecretKeySpec(key.getBytes(CommonConstants.CHARSET), ALGORITHM));
                    return cipher.doFinal(encodeData);
                } catch (Exception var4) {
                    throw new IllegalArgumentException("aes decrypt error", var4);
                }
            } else {
                return null;
            }
        }

        public static String decodeAndConvertBase64(byte[] encodeData, String key) {
            return Base64.encode(decode(encodeData, key));
        }

        public static byte[] encodeStringContent(String str, String key) {
            if (!Strings.isNullOrEmpty(str)) {
                try {
                    return encode(str.getBytes(CommonConstants.CHARSET), key);
                } catch (Exception var4) {
                    throw new IllegalArgumentException("aes encrypt error", var4);
                }
            } else {
                return null;
            }
        }

        public static String encodeStringContentAndConvertBase64(String str, String key) {
            return Base64.encode(encodeStringContent(str, key));
        }

        public static String decodeStringContent(byte[] data, String key) {
            if (!Strings.isNullOrEmpty(key)) {
                try {
                    return new String(decode(data, key), CommonConstants.CHARSET);
                } catch (Exception var4) {
                    throw new IllegalArgumentException("aes decrypt error", var4);
                }
            } else {
                return null;
            }
        }

        public static String decodeStringContent(String base64, String key) {
            return decodeStringContent(Base64.decode(base64), key);
        }

    }

    @Slf4j
    public static final class RSA {

        public static void main(String[] args) {
            String priKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDBiV2SIhmBrgsUu9dzVyTMrcp9VRg5tAdh3HUpOhWg0APei6wGugOif6us8y/zZKlchPao500S05cHq7vFhyCZglskMaZRmcl9FwofPZ1F5Jtr41rQh/5CETMqX1Ff5mKPEjcdjQ5c/36Thn0rePwVkOs4t1DroD6uPIJ7qbnwYvgn/U+1rj5gEzcyqRMEJWS/ci6YFfUak2kEm+Wj/AueYRXfzQQ3zrkZyaONc1cw/hASCfWg+svGfHJMrTyIApocIIM7ZmmkYB0xw/+vDMB5c82OYJog4801sy5STzfsIRMALMWBCgNFA0MW0DkTsnfyKpXv6dLH0eVAzwGlKeiBAgMBAAECggEAF7mhMOLhQZZ2YUpNtSP/rN+5LPgbiidZqFOAQVePgzE86DEzG3f+LhN8li0RucKjSGY8XqRMPHyXzSbuU96mzmq+hYdYnjQ+EQmcAgxplUHbE7TBU0L4llzJau5YaWsytdtDymmaUyg2+hrIiWXBcVtcmo1ObDQuuulMDaDW3HLuyf34eDXQJAonjApqfSjut6tXZqIWqQNmZ+Q0jm784RaZ4v/tCbsCE3jhGk8RETVe7kgehuBe9aeoUhGN8aummU9ozNwDfxkdOjRxTOB67DsESRfioRQDMPHxW6OrM9lGYA/nHP8m0CIaNduRKBlXdqT1SulJKWJsh+x0pBTKYQKBgQDuLwJPpHdZuvyBt+81clgdlV0Rk7WNDgvgSnQJBuBI0lXNTnZY5ly6s3vCFjEdd+mSa4QOTw2JZXMvwQtExb/sS9LEHfCLCw131Wbha233jYSDxtvE8HA3PJJm+sFiiDzjIZ0OkKKHCGbcnWaAF1J4AITVhgn/2HGcFCtgrOqd5QKBgQDQA2hok8lU4GlAVsiykN1HJSxndBwszzhWVwmTfvqkxzebnpmluI694/KaLcYJiPNY48wLKvWtQIO/huuzuIXXRU9mZUHa7lFsui7+fCeuc41iP3Oifm9/oy9c7XncCI1heMbuKeEmeuisUpvoqneX8DtjwWtzxB4CnXjZObcWbQKBgAnmYk41I9DS+NPbdVHfYtI39xpX7V/jiZLVBWWqp3/fXjmeXUTsrkYEV8yFZ5018rtnRKHjn0hqsxL1DybYoVWNAUGtjOX6m6Czegc8k+GKPEvN5CyXYdKp7dvh6E/UIdO+/ewxDtBWBRB+alhAn+jk/KBzZc8AjBhUrkjiZw4RAoGBALI46UEItmxRZfxpHsXX1q85S2VMF0PRO8zF//9uwSNjSOK9rZlOmoZaAqdU5qOOwij76/v5j8skRn1YBSTTyWDbEp+BzD3fCMamctM53unaOV3fTZ0AxVlPoKn3EfyTG0dTKuYt3amRjz7t/IUJYTQ98TSUKRNkCe5lq4A6sgK1AoGADK3+CpezZWdFsA/IrDlK21G7Fhd7nH8R4HDeyo3D119lUimAVENPifSgQ3TJ4/FBDdIkIHFVOI+fffpvN5uNX2wU3W/8dqSe8ce2cFEBK9ytvX2N6Lf2PzGOh+LyBUlefaanKR2KqjJ2JAX+YHuIB1K3G0bhR3f8ISBrLM01Uns=";
            String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwYldkiIZga4LFLvXc1ckzK3KfVUYObQHYdx1KToVoNAD3ousBroDon+rrPMv82SpXIT2qOdNEtOXB6u7xYcgmYJbJDGmUZnJfRcKHz2dReSba+Na0If+QhEzKl9RX+ZijxI3HY0OXP9+k4Z9K3j8FZDrOLdQ66A+rjyCe6m58GL4J/1Pta4+YBM3MqkTBCVkv3IumBX1GpNpBJvlo/wLnmEV380EN865GcmjjXNXMP4QEgn1oPrLxnxyTK08iAKaHCCDO2ZppGAdMcP/rwzAeXPNjmCaIOPNNbMuUk837CETACzFgQoDRQNDFtA5E7J38iqV7+nSx9HlQM8BpSnogQIDAQAB";
            String algorithm = "SHA256withRSA";
            // encode/decode
            {
                String content = "hello,world.";
                byte[] data = content.getBytes();
                byte[] encodeData =
                        encodeByPrivateKey(data, priKey);
                byte[] decodeData =
                        decodeByPublicKey(encodeData, pubKey);
                System.out.println("pri-encode/pub-decode:" + new String(decodeData));
                encodeData =
                        encodeByPublicKey(data, pubKey);
                decodeData =
                        decodeByPrivateKey(encodeData, priKey);
                System.out.println("pub-encode/pri-decode:" + new String(decodeData));
            }

            // sign/check-sign
            {
                String content = "hello,world.";
                byte[] data = content.getBytes();
                byte[] signData =
                        signByPrivateKey(data, priKey);
                System.out.println(checkSignByPublicKey(data, signData, pubKey));
            }
        }

        public static String encodeBase64ByPrivateKey(String base64Content, String privateKey) {
            return Base64.encode(encodeByPrivateKey(Base64.decode(base64Content), privateKey));
        }

        public static byte[] encodeByPrivateKey(byte[] content, String privateKey) {
            try {
                PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, priKey);
                return cipher.doFinal(content);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static String encodeBase64ByPublicKey(String base64Content, String publicKey) {
            return Base64.encode(encodeByPublicKey(Base64.decode(base64Content), publicKey));
        }

        public static byte[] encodeByPublicKey(byte[] content, String publicKey) {
            try {
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKey.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, pubKey);
                return cipher.doFinal(content);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static String decodeBase64ByPublicKey(String base64Content, String publicKey) {
            return Base64.encode(decodeByPublicKey(Base64.decode(base64Content), publicKey));
        }

        public static String decodeBase64ByPrivateKey(String base64Content, String privateKey) {
            return Base64.encode(decodeByPrivateKey(Base64.decode(base64Content), privateKey));
        }

        public static byte[] decodeByPublicKey(byte[] content, String publicKey) {
            try {
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(publicKey.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey pubKey = keyFactory.generatePublic(x509EncodedKeySpec);
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, pubKey);
                return cipher.doFinal(content);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static byte[] decodeByPrivateKey(byte[] content, String privateKey) {
            try {
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey priKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, priKey);
                return cipher.doFinal(content);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static byte[] signByPrivateKey(byte[] content, String privateKey) {
            return signByPrivateKey(content, "SHA256withRSA", privateKey);
        }

        public static byte[] signByPrivateKey(byte[] content, String algorithm, String privateKey) {
            try {
                PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey.getBytes()));
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey priKey = keyFactory.generatePrivate(priPKCS8);
                Signature signature = Signature.getInstance(algorithm);
                signature.initSign(priKey);
                signature.update(content);
                return signature.sign();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public static boolean checkSignByPublicKey(byte[] content, byte[] sign, String publicKey) {
            return checkSignByPublicKey(content, sign, "SHA256withRSA", publicKey);
        }

        public static boolean checkSignByPublicKey(byte[] content, byte[] sign, String algorithm, String publicKey) {
            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                byte[] encodedKey = Base64.decode(publicKey);
                PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
                Signature signature = Signature.getInstance(algorithm);
                signature.initVerify(pubKey);
                signature.update(content);
                return signature.verify(sign);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static final class Md5 {

        private Md5() {
        }

        public static String md5Hex(File file) {
            return Hex.encodeHexString(computeMD5Hash(file));
        }

        public static String md5Hex(String utf8Content) {
            return Hex.encodeHexString(computeMD5Hash(utf8Content.getBytes(StringUtils.UTF8)));
        }

        public static String md5Hex(byte[] input) {
            return Hex.encodeHexString(computeMD5Hash(input));
        }

        public static byte[] computeMD5Hash(InputStream is) {
            assert is != null;
            MessageDigest digest = getDigest();
            byte[] buffer = new byte[1024];
            try {
                for (int read = is.read(buffer, 0, 1024); read > -1; read = is.read(buffer, 0, 1024)) {
                    digest.update(buffer, 0, read);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
            return digest.digest();
        }

        public static byte[] computeMD5Hash(byte[] input) {
            return getDigest().digest(input);
        }

        public static byte[] computeMD5Hash(File file) {
            FileInputStream input = null;
            byte[] md5Hash;
            try {
                input = new FileInputStream(file);
                md5Hash = computeMD5Hash(input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception ignored) {
                    }
                }
            }
            return md5Hash;
        }

        private static MessageDigest getDigest() {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException var2) {
                throw new IllegalArgumentException(var2);
            }
        }

    }

    public static final class Sha256 {

        private Sha256() {
        }

        public static String sha256Hex(File file) {
            return Hex.encodeHexString(computeSha256Hash(file));
        }

        public static String sha256Hex(String utf8Content) {
            return Hex.encodeHexString(computeSha256Hash(utf8Content.getBytes(StringUtils.UTF8)));
        }

        public static String sha256Hex(byte[] input) {
            return Hex.encodeHexString(computeSha256Hash(input));
        }

        public static byte[] computeSha256Hash(InputStream is) {
            assert is != null;
            MessageDigest digest = getDigest();
            byte[] buffer = new byte[1024];
            try {
                for (int read = is.read(buffer, 0, 1024); read > -1; read = is.read(buffer, 0, 1024)) {
                    digest.update(buffer, 0, read);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    is.close();
                } catch (Exception ignored) {
                }
            }
            return digest.digest();
        }

        public static byte[] computeSha256Hash(byte[] input) {
            return getDigest().digest(input);
        }

        public static byte[] computeSha256Hash(File file) {
            FileInputStream input = null;
            byte[] md5Hash;
            try {
                input = new FileInputStream(file);
                md5Hash = computeSha256Hash(input);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception ignored) {
                    }
                }
            }
            return md5Hash;
        }

        private static MessageDigest getDigest() {
            try {
                return MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException var2) {
                throw new IllegalArgumentException(var2);
            }
        }

    }

}