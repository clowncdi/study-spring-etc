package image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * @author clowncdi
 * 웹툰처럼 긴 이미지 정해진 사이즈로 일괄적으로 자르기
 * startNum				시작 파일번호
 * endNum					마지막 파일번호
 * firstHeight		표지 높이(1400)
 * height					자를 이미지 높이(2364)
 */
public class CropImage {
	private static final String PATH = "C:\\Users\\dev2\\Desktop\\test\\"; // Windows
//	private static final String PATH = "/Users/yd/Downloads/test/"; // Mac
	private static final String EXTENSION = ".png";
	private static int firstHeight;
	private static int height;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("시작 번호를 입력: ");
		int startNum = sc.nextInt();
		System.out.print("끝 번호를 입력: ");
		int endNum = sc.nextInt();
		System.out.print("표지 이미지 세로길이: ");
		firstHeight = sc.nextInt();
		System.out.print("자를 이미지 세로길이: ");
		height = sc.nextInt();

		for (int i = startNum; i <= endNum; i++) {
			String num = String.valueOf(i);
			try {
				BufferedImage originalImage = ImageIO.read(new File(PATH + num + EXTENSION));
				int originalImageHeight = originalImage.getHeight();
				int count = ((originalImageHeight - firstHeight) / height) + 2;
				int cropHeight = 0;

				for (int j = 0; j < count; j++) {
					if (j == 0) {
						cropHeight = firstHeight;
					} else if(j == count - 1) {
						cropHeight = originalImageHeight - firstHeight - ((j-1) * height);
					} else {
						cropHeight = height;
					}
					if (cropHeight > 0) {
						// 정해진 세로길이만큼 반복적으로 이미지 자르고 저장하기
						saveCropImage(num, originalImage, cropHeight, j);
					}
				}
			} catch (IOException e) {
				System.out.println("Error cropping image: " + e.getMessage());
			}
		}
	}

	private static void saveCropImage(String num, BufferedImage originalImage, int cropHeight, int i) throws IOException {
		// 원본 이미지에서 지정된 영역만큼 서브이미지를 리턴받는다.
		int originalImageWidth = originalImage.getWidth();
		BufferedImage subImage = originalImage.getSubimage(0, i == 0 ? 0 : (i-1) * height + firstHeight, originalImageWidth, cropHeight);

		// 새로운 BufferedImage 객체에 원본 이미지를 그린다.
		BufferedImage croppedImage = new BufferedImage(originalImageWidth, cropHeight, originalImage.getType());
		Graphics2D g = croppedImage.createGraphics();
		g.drawImage(subImage, 0, 0, originalImageWidth, cropHeight, null);
		g.dispose();

		// 파일명으로 디렉토리 만들기
		File dir = new File(PATH + num);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// 자른 이미지를 PNG 파일로 저장하기
		ImageIO.write(croppedImage, "PNG", new File(dir.getAbsolutePath() + "/" + num + "-" + i + EXTENSION));
	}
}
