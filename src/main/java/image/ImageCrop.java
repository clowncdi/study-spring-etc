package image;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCrop {

	private static final int FIRST_HEIGHT = 1400;
	private static final int HEIGHT = 2364;
	private static int startNum = 2;
	private static int endNum = 35;

	public static void main(String[] args) {
		for (int i = startNum; i <= endNum; i++) {
			if (i < 10) {
				makeCropImage("0" + i);
			}
			makeCropImage(String.valueOf(i));
		}
	}

	private static void makeCropImage(String num) {
		try {
			BufferedImage originalImage = ImageIO.read(new File("/Users/yd/Downloads/webtoon/" + num + ".png"));
			int originalImageWidth = originalImage.getWidth();
			int originalImageHeight = originalImage.getHeight();
			int count = ((originalImageHeight - FIRST_HEIGHT) / HEIGHT) + 2;
			int cropHeight = FIRST_HEIGHT;

			saveCropImage(num, originalImage, originalImageWidth, cropHeight, 0);
			for (int i = 1; i < count; i++) {
				cropHeight = i == count - 1 ? originalImageHeight - FIRST_HEIGHT - ((i-1) * HEIGHT) : HEIGHT;
				if (cropHeight > 0) {
					saveCropImage(num, originalImage, originalImageWidth, cropHeight, i);
				}
			}
		} catch (IOException e) {
			System.out.println("Error cropping image: " + e.getMessage());
		}
	}

	private static void saveCropImage(String num, BufferedImage originalImage, int originalImageWidth, int cropHeight, int i) throws IOException {
		BufferedImage subImage = originalImage.getSubimage(0, i == 0 ? 0 : (i-1) * HEIGHT + 1400, originalImageWidth, cropHeight);
		BufferedImage croppedImage = new BufferedImage(originalImageWidth, cropHeight, originalImage.getType());

		// Draw the original image on the new BufferedImage object
		Graphics2D g = croppedImage.createGraphics();
		g.drawImage(subImage, 0, 0, originalImageWidth, cropHeight, null);
		g.dispose();

		// Save the cropped image as a JPEG file
		// make local directory
		File dir = new File("/Users/yd/Downloads/webtoon/" + num);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		ImageIO.write(croppedImage, "PNG", new File(dir.getAbsolutePath() + "/" + num + "-" + i + ".png"));
	}
}
