package cn.com.trying.certificate;

import org.springframework.util.ClassUtils;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
/**
* @Title:
* @Description:  证书相关操作
* @param
* @return
* @author huxx
* @date 2020/6/15 下午1:43
* @update
*/
public class CertUtil {
    public static void testCertificate()   {
        final String alias = "hchy";
        final String keystore_password = "hchy123456";//秘钥库密码
        final String ca_password = "hchy123456";//证书密码
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        try{
            //公钥证书的编码格式是x509  私钥通常是pkcs8
            /*从lisi.cer中提取公钥*/
            X509Certificate x509Certificate = null;
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            FileInputStream fis = new FileInputStream(path+"hchy.cer");
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(fis);
            fis.close();

            /*==========使用公钥加密============*/
            Key key = x509Certificate.getPublicKey();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal("helloworld".getBytes());
            System.out.println("加密结果: " + Base64.getEncoder().encodeToString(bytes));


            /*提取秘钥*/
            fis = new FileInputStream(path+"hchy.keystore");
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(fis, keystore_password.toCharArray());
            fis.close();

            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, ca_password.toCharArray());

            /*=======使用私钥解密=========*/
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            bytes = cipher.doFinal(bytes);
            System.out.println("解密： " + new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
