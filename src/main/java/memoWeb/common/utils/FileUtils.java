package memoWeb.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Component
public class FileUtils {
	public static String fileUpload(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getName());
		String newFileName = Long.toString(System.nanoTime()) + '.' + extension;
		File saveFile = new File("D:/memo-web/post/" + newFileName);
		file.transferTo(saveFile);
		return newFileName;
	}

	public static byte[] convertFileContentToBlob(String filePath) {
		byte[] result = null;
		try {
			result = org.apache.commons.io.FileUtils.readFileToByteArray(new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String convertBlobToBase64(byte[] blob) {
		return new String(Base64.getEncoder().encode(blob));
	}

	public static String getFileContent(String filePath) {
		byte[] filebyte = convertFileContentToBlob(filePath);
		return convertBlobToBase64(filebyte);
	}
}
