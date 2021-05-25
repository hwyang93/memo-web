package memoWeb.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtils {
	public static String fileUpload(MultipartFile file) throws IOException {
		String extension = FilenameUtils.getExtension(file.getName());
		String newFileName = Long.toString(System.nanoTime()) + '.' + extension;
		File saveFile = new File("D:/memo-web/post/" + newFileName);
		file.transferTo(saveFile);
		return newFileName;
	}
}
