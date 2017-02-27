package com.cyb.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;

public class FtpUploader {
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年10月13日下午5:02:08</br>
	 */
	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, 5);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

		if (isAjaxUpload) {
			upload.setHeaderEncoding("UTF-8");
		}
		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, 7);
			}

			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			String newName  = UUIDUtils.uuid()+suffix;
			savePath = savePath +newName;
			
			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, 8);
			}

			savePath = PathFormat.parse(savePath, newName);

			String remoteDir = "";

			int pos = savePath.lastIndexOf("/");
			if (pos > -1) {
				remoteDir = savePath.substring(0, pos + 1);
			}

			String physicalPath = (String) conf.get("rootPath") + savePath;

			boolean keepLocalFile = "false".equals(conf.get("keepLocalFile")) ? false
					: true;
			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFtpFileByInputStream(is,
					remoteDir, physicalPath, maxSize, keepLocalFile,newName);
			is.close();
			if (storageState.isSuccess()) {
				storageState.putInfo("url", savePath);
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (FileUploadException e) {
			e.printStackTrace();
			return new BaseState(false, 6);
		} catch (IOException localIOException) {
			localIOException.printStackTrace();
		}
		return new BaseState(false, 4);
	}
	 @SuppressWarnings("rawtypes")
	 private static boolean validType(String type, String[] allowTypes) {
	     List list = Arrays.asList(allowTypes);
	     return list.contains(type);
	 }
}
