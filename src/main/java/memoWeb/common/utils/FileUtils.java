package memoWeb.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Component
public class FileUtils {

	public static final String FILE_ROOT_PATH = "D:/memo-web/";

	public static String fileUpload(MultipartFile file, String filePath) throws IOException {
		String extension = FilenameUtils.getExtension(file.getName());
		String newFileName = Long.toString(System.nanoTime()) + '.' + extension;
		File saveFile = new File(FILE_ROOT_PATH +filePath + File.separator + newFileName);
		file.transferTo(saveFile);
		resizeImg(saveFile);

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
		byte[] fileByte = convertFileContentToBlob(filePath);
		return convertBlobToBase64(fileByte);
	}

	public static void resizeImg(File file) {
		Image image;
		try {
			image = ImageIO.read(file);

			Image resizeImage = image.getScaledInstance(720, 450, Image.SCALE_SMOOTH);
			BufferedImage newImage = new BufferedImage(720, 450, BufferedImage.TYPE_INT_RGB);
			Graphics g = newImage.getGraphics();
			g.drawImage(resizeImage, 0, 0, null);
			g.dispose();
			ImageIO.write(newImage, FilenameUtils.getExtension(file.getName()), file);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

}
