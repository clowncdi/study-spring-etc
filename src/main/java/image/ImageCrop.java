import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCrop {
	public static void main(String[] args) {
		try {
			// Load the image file
			BufferedImage originalImage = ImageIO.read(new File("D:\\temp\\1.jpg"));

			int cropHeight = 2400;
			int originalImageWidth = originalImage.getWidth();
			int originalImageHeight = originalImage.getHeight();
			int count = (originalImageHeight / cropHeight) + 1;

			for (int i = 0; i < count; i++) {
				int endCropHeight = cropHeight;
				if (i == count - 1) {
					endCropHeight = originalImageHeight - (i * cropHeight);
				}
				BufferedImage subImage = originalImage.getSubimage(0, i * cropHeight, originalImageWidth, endCropHeight);
				BufferedImage croppedImage = new BufferedImage(originalImageWidth, endCropHeight, originalImage.getType());

				// Draw the original image on the new BufferedImage object
				Graphics2D g = croppedImage.createGraphics();
				g.drawImage(subImage, 0, 0, originalImageWidth, endCropHeight, null);
				g.dispose();

				// Save the cropped image as a JPEG file
				ImageIO.write(croppedImage, "jpg", new File("D:\\temp\\cropped-" + i + ".jpg"));
			}
		} catch (IOException e) {
			System.out.println("Error cropping image: " + e.getMessage());
		}

	}
}
