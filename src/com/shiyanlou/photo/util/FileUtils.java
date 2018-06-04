package com.shiyanlou.photo.util;

import java.io.InputStream;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.api.rs.RSClient;

/**
 * ͼƬ�����ࣨʹ����ţ�ƴ洢����
 * @author JXS
 *
 */
public class FileUtils {
	private static final String ACCESS_KEY = "GKgmnS7twiTWZemer0C98gE5UujcA1sLS18AWf_N";
	private static final String SECRET_KEY = "E1sst6fzPQrgB9jzbYIbEfcB1A-FdnfTCvKLehXj";
	private static final String BUCKET_NAME = " shiyanlouphoto";
	
	/**
	 * �ϴ�ͼƬ����ţ�ƴ洢
	 * @param reader
	 * @param fileName
	 */
	public static void upload(InputStream reader,String fileName){
		String uptoken;
		Config.UP_HOST="http://up-z2.qiniu.com";
		try{
			Mac mac = new Mac(ACCESS_KEY,SECRET_KEY);
			PutPolicy putPolicy = new PutPolicy(BUCKET_NAME);
			uptoken = putPolicy.token(mac);
			IoApi.Put(uptoken, fileName, reader, null);
		}catch(AuthException e){
			e.printStackTrace();
		}catch(JSONException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ɾ����ţ�ƴ洢�ϵ�ͼƬ
	 * @param key
	 */
	public static void delete(String key){
		Mac mac = new Mac(ACCESS_KEY,SECRET_KEY);
		RSClient client = new RSClient(mac);
		client.delete(BUCKET_NAME, key);
	}
	
}
