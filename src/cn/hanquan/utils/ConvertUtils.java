package cn.hanquan.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("all")
public class ConvertUtils {
	/**
	 * 横向起始位置
	 */
	public static int QI_HENG;
	/**
	 * 竖向起止位置
	 */
	public static int QI_SHU;
	/**
	 * 图横
	 */
	public static int TU_HENG;
	/**
	 * 图竖
	 */
	public static int TU_SHU;
	/**
	 * 竖减
	 */
	public static int SHU_JIAN;
	/**
	 * 横增
	 */
	public static int HENG_ZENG;
	/**
	 * 横求余
	 */
	public static int HENG_QIUYU;
	/**
	 * 竖求余
	 */
	public static int SHU_QIUYU = 790;
	/**
	 * ppt文件存储路径名
	 */
	public static String PATH = PathManager.PATH;

	/**
	 * ppt2003转化为pdf
	 * 
	 * @param pptFileName ppt
	 * @param m           横向图片数量
	 * @param n           竖向图片数量
	 * @param scale       ppt画幅比例,0表示4:3,1表示16:9
	 * @throws IOException
	 */
	public static void ConvertPPT(String pptFileName, int m, int n, int scale) throws IOException {
		// 创建存储图片的文件夹
		String realPath = PATH + FileNameUtils.getShortFileName(pptFileName);
		FileNameUtils.createPicFolder(realPath);

		File file = new File(PATH + pptFileName);
		File imageFile = new File(PATH);

		// 计算ppt的页数
		HSLFSlideShow ppt = new HSLFSlideShow(new FileInputStream(file));
		List<HSLFSlide> slideList = ppt.getSlides();
		int pageNum = slideList.size();
		System.out.println("ppt的页数为：" + pageNum);

		// 生成png
		List<String> pathList = doPPT2003ToImage(new FileInputStream(file), imageFile, pptFileName);
		for (String s : pathList) {
			System.out.println(s);
		}

		// 生成pdf
		fillTemplate(imageFile, pptFileName, pageNum, m, n, scale);
	}

	/**
	 * 将PPTX2007转化为pdf
	 * 
	 * @param pptFileName ppt文件名(带后缀)
	 * @param m           横向图片数量
	 * @param n           竖向图片数量
	 * @param scale       ppt画幅比例,0表示4:3,1表示16:9
	 * @throws IOException
	 */
	public static void ConvertPPTX(String pptFileName, int m, int n, int scale) throws IOException {
		// 创建存储图片的文件夹
		String realPath = PATH + FileNameUtils.getShortFileName(pptFileName);
		FileNameUtils.createPicFolder(realPath);

		File pptFile = new File(PATH + pptFileName);
		File imageFile = new File(PATH);

		// 计算pptx的页数
		XMLSlideShow ppt = new XMLSlideShow(new FileInputStream(pptFile));
		List<XSLFSlide> slideList = ppt.getSlides();
		int pageNum = slideList.size();
		System.out.println("pptx的页数为：" + pageNum);

		// 生成png
		List<String> pathList = doPPT2007ToImage(new FileInputStream(pptFile), imageFile, pptFileName);

		for (String s : pathList) {
			System.out.println(s);
		}

		// 生成pdf
		fillTemplate(imageFile, pptFileName, pageNum, m, n, scale);
	}

	/**
	 * 将图片填充到PDF
	 * 
	 * @param imageFile
	 * @param fileName
	 * @param pageNum
	 * @param m         横向图片数量
	 * @param n         竖向图片数量
	 * @param scale     ppt画幅比例,0表示4:3,1表示16:9
	 */
	public static void fillTemplate(File imageFile, String fileName, int pageNum, int m, int n, int scale) {
		// 删除文件名后缀
		String shortFileName = FileNameUtils.getShortFileName(fileName);

		try {
			// Step 1—Create a Document.
			Document document = new Document();
			// Step 2—Get a PdfWriter instance.
			PdfWriter.getInstance(document, new FileOutputStream(PATH + shortFileName + "/" + shortFileName + ".pdf"));
			// Step 3—Open the Document.
			document.open();

			// 判断一共应生成几页pdf
			int k0 = m * n;// 一页pdf上有几张ppt
			int pdfNum;
			if (pageNum % k0 == 0)
				pdfNum = pageNum / k0;
			else
				pdfNum = pageNum / k0 + 1;
			System.out.println("一共有" + pdfNum + "页pdf");

			if (scale == 0) {
				// ppt比例为4:3——用逻辑值 '0' 表示
				if (m == 1) {
					switch (n) {
					case 2: {
						QI_HENG = 50;
						QI_SHU = 430;
						TU_HENG = 495;
						TU_SHU = 370;
						SHU_JIAN = 380;
						HENG_ZENG = 540;
						HENG_QIUYU = 540;
						break;
					}
					case 3: {
						QI_HENG = 125;
						QI_SHU = 557;
						TU_HENG = 540;
						TU_SHU = 263;
						SHU_JIAN = 263;
						HENG_ZENG = 532;
						HENG_QIUYU = 532;
						break;
					}
					}
				} else if (m == 2) {
					switch (n) {
					case 4: {
						QI_HENG = 25;
						QI_SHU = 622;
						TU_HENG = 270;
						TU_SHU = 197;
						SHU_JIAN = 197;
						HENG_ZENG = 275;
						HENG_QIUYU = 550;
						break;
					}
					case 5: {
						QI_HENG = 50;
						QI_SHU = 662;
						TU_HENG = 270;
						TU_SHU = 158;
						SHU_JIAN = 162;
						HENG_ZENG = 275;
						HENG_QIUYU = 550;
						break;
					}
					}
				} else {
					switch (n) {
					case 7: {
						QI_HENG = 40;
						QI_SHU = 712;
						TU_HENG = 180;
						TU_SHU = 112;
						SHU_JIAN = 115;
						HENG_ZENG = 183;
						HENG_QIUYU = 549;
						break;
					}
					case 8: {
						QI_HENG = 50;
						QI_SHU = 720;
						TU_HENG = 170;
						TU_SHU = 95;
						SHU_JIAN = 98;
						HENG_ZENG = 180;
						HENG_QIUYU = 540;
						break;
					}
					}
				}
			} else {
				// ppt比例为16:9 ——用逻辑值 '1'表示
				if (m == 1) {
					switch (n) {
					case 2: {
						QI_HENG = 50;
						QI_SHU = 450;
						TU_HENG = 495;
						TU_SHU = 370;
						SHU_JIAN = 360;
						HENG_ZENG = 540;
						HENG_QIUYU = 540;
						break;
					}
					case 3: {
						QI_HENG = 65;
						QI_SHU = 557;
						TU_HENG = 540;
						TU_SHU = 263;
						SHU_JIAN = 268;
						HENG_ZENG = 532;
						HENG_QIUYU = 532;
						break;
					}
					}
				} else if (m == 2) {
					switch (n) {
					case 4: {
						QI_HENG = 25;
						QI_SHU = 642;
						TU_HENG = 270;
						TU_SHU = 197;
						SHU_JIAN = 197;
						HENG_ZENG = 275;
						HENG_QIUYU = 550;
						break;
					}
					case 5: {
						QI_HENG = 25;
						QI_SHU = 662;
						TU_HENG = 270;
						TU_SHU = 158;
						SHU_JIAN = 158;
						HENG_ZENG = 275;
						HENG_QIUYU = 550;
						break;
					}
					}
				} else {
					switch (n) {
					case 7: {
						QI_HENG = 25;
						QI_SHU = 712;
						TU_HENG = 180;
						TU_SHU = 112;
						SHU_JIAN = 115;
						HENG_ZENG = 183;
						HENG_QIUYU = 549;
						break;
					}
					case 8: {
						QI_HENG = 33;
						QI_SHU = 720;
						TU_HENG = 170;
						TU_SHU = 95;
						SHU_JIAN = 98;
						HENG_ZENG = 180;
						HENG_QIUYU = 540;
						break;
					}
					}
				}
			}

//开始生成pdf的每一页			
			// Step 4—Add content.
			int i = 0;// i记录当前是第几页pdf
			Image[] image = new Image[pageNum];
			// 前面的每一页
			for (i = 0; i < pdfNum - 1; i++) // 前面的，除了最后一页pdf
			{
				System.out.println("这是第" + (i + 1) + "页pdf");
				document.newPage();
				System.out.println("QI_HENG=" + QI_HENG + "QI_SHU" + QI_SHU);
				int heng = QI_HENG, shu = QI_SHU;
				int p = 1;
				for (int k = m * n * i; k < m * n * (i + 1); k++) {
					System.out.println("k=" + k);
					System.out.println("heng=" + heng + "shu=" + shu);
					// 读图片
					image[k] = Image.getInstance(imageFile.getAbsolutePath() + "/" + shortFileName + "/" + shortFileName
							+ "pic" + k + ".png");
					image[k].scaleToFit(TU_HENG, TU_SHU);
					// image[k].setAbsolutePosition(-700+k*20, -700+juli*200);
					image[k].setAbsolutePosition(heng, shu);
					// 添加图片
					document.add(image[k]);
					heng += HENG_ZENG;
					System.out.println("HENG_QIUYU=" + HENG_QIUYU);
					heng %= HENG_QIUYU;

					if (p % m == 0)
						shu -= SHU_JIAN;
					p++;
					shu %= SHU_QIUYU;
				}
			}

			// 最后一页pdf
			document.newPage();
			int heng = QI_HENG, shu = QI_SHU;
			int p = 1;
			for (int k = m * n * i; k < pageNum; k++) {
				System.out.println("k=" + k);
				System.out.println("这是第" + (i + 1) + "页pdf");
				// 读图片
				image[k] = Image.getInstance(
						imageFile.getAbsolutePath() + "/" + shortFileName + "/" + shortFileName + "pic" + k + ".png");
				image[k].scaleToFit(TU_HENG, TU_SHU);
				image[k].setAbsolutePosition(heng, shu);
				// 添加图片
				document.add(image[k]);

				heng += HENG_ZENG;
				heng %= HENG_QIUYU;

				if (p % m == 0)
					shu -= SHU_JIAN;
				p++;

				shu %= SHU_QIUYU;
			}

			// Step 5—Close the Document.
			document.close();

		} catch (IOException e) {
			System.out.println("请检查是否文件已经打开/图片是否存在" + e);// 请检查是否文件已经打开
		} catch (DocumentException e) {
			System.out.println(e);
		}
	}

	/**
	 * 将ppt2007转化为图片
	 * 
	 * @param inputStream
	 * @param imageFile
	 * @param fileName
	 * @return
	 */
	public static List<String> doPPT2007ToImage(FileInputStream inputStream, File imageFile, String fileName) {// PPTx-->image
		System.out.println("(doPPT2007ToImage)");
		List<String> pathList = new ArrayList<String>();
		try {
			XMLSlideShow ppt = new XMLSlideShow(inputStream);
			Dimension pgSize = ppt.getPageSize();
			List<XSLFSlide> slideList = ppt.getSlides();

			for (int i = 0; i < slideList.size(); i++) {

				for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
					if (shape instanceof XSLFTextShape) {
						XSLFTextShape tsh = (XSLFTextShape) shape;
						for (XSLFTextParagraph p : tsh) {
							for (XSLFTextRun r : p) {
								r.setFontFamily("宋体");
							}
						}
					}
				}

				BufferedImage image = new BufferedImage(pgSize.width, pgSize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = image.createGraphics();

				// 高清
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// 用最高的精确度和视觉质量执行颜色转换计算。
				graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
						RenderingHints.VALUE_COLOR_RENDER_QUALITY);
				// 控制偏向于处理速递还是质量-偏向于质量
				graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
						RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle(0, 0, pgSize.width, pgSize.height));
				slideList.get(i).draw(graphics);

				if (!imageFile.exists()) {
					imageFile.mkdirs();
				}

				String shortFileName = fileName.substring(0, fileName.length() - 5);// 去除后缀的文件名

				pathList.add(
						imageFile.getAbsolutePath() + "\\" + shortFileName + "\\" + shortFileName + "pic" + i + ".png");

				FileOutputStream outputStream = new FileOutputStream(new File(imageFile.getAbsolutePath() + "\\"
						+ shortFileName + "\\" + shortFileName + "pic" + i + ".png"));

				ImageIO.write(image, "png", outputStream);
				outputStream.close();
			}
			ppt.close();
			return pathList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将ppt2003转化为图片
	 * 
	 * @param inputStream ppt文件
	 * @param imageFile   图片文件名+路径
	 * @param fileName
	 * @return
	 */
	public static List<String> doPPT2003ToImage(FileInputStream inputStream, File imageFile, String fileName) {// PPT-->image
		System.out.println("(doPPT2003ToImage)");
		List<String> pathList = new ArrayList<String>();
		try {
			HSLFSlideShow ppt = new HSLFSlideShow(inputStream);
			Dimension pgSize = ppt.getPageSize();
			List<HSLFSlide> slideList = ppt.getSlides();

			for (int i = 0; i < slideList.size(); i++) {
				for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
					if (shape instanceof HSLFTextShape) {
						HSLFTextShape tsh = (HSLFTextShape) shape;
						for (HSLFTextParagraph p : tsh) {
							for (HSLFTextRun r : p) {
								r.setFontFamily("宋体");
							}
						}
					}
				}

				BufferedImage image = new BufferedImage(pgSize.width, pgSize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = image.createGraphics();

				// 高清
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				// 用最高的精确度和视觉质量执行颜色转换计算。
				graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
						RenderingHints.VALUE_COLOR_RENDER_QUALITY);
				// 控制偏向于处理速递还是质量-偏向于质量
				graphics.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
						RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle(0, 0, pgSize.width, pgSize.height));
				slideList.get(i).draw(graphics);

				String shortFileName = fileName.substring(0, fileName.length() - 4);// 去除后缀的文件名

				if (!imageFile.exists()) {
					imageFile.mkdirs();
				}

				pathList.add(
						imageFile.getAbsolutePath() + "\\" + shortFileName + "\\" + shortFileName + "pic" + i + ".png");

				FileOutputStream outputStream = new FileOutputStream(new File(imageFile.getAbsolutePath() + "\\"
						+ shortFileName + "\\" + shortFileName + "pic" + i + ".png"));

				ImageIO.write(image, "png", outputStream);
				outputStream.close();
			}
			ppt.close();
			return pathList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
