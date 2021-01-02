package Beans.arquivos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.primefaces.model.file.UploadedFile;

public class LoadImage {
	
	public static String[] getImagem(UploadedFile upf ,byte[] imagemByte) {
		
		try {
			BufferedImage bi = ImageIO.read(new ByteArrayInputStream(imagemByte));
			int type = bi.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bi.getType();
			int largura = 200;
			int altura = 200;
			
			BufferedImage resizedImage = new BufferedImage(largura, altura, type);
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(bi, 0, 0, largura, altura, null);
			g.dispose();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			String extensao = upf.getContentType().split("\\/")[1];		
			ImageIO.write(resizedImage, extensao, baos);
			
			StringBuilder miniImagem = new StringBuilder();
			miniImagem.append("data:").append(upf.getContentType())
			.append(";base64,")
			.append(DatatypeConverter.printBase64Binary(baos.toByteArray()));
			
			String[] imagem = new String[] {miniImagem.toString(), extensao};
			
			return imagem;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
			
	}
}
