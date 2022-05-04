import java.awt.Color;
import java.awt.image.BufferedImage;

public class filtros {
	
	
//	->ESSE CÓDIGO AQUI PEGA POR BANDA
	public static BufferedImage bandaR (BufferedImage imagem) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemsaidaR = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int vermelho = cor.getRed();
			 		Color vermelhoApenas = new Color(vermelho, vermelho, vermelho);
			 		imagemsaidaR.setRGB(col, lin, vermelhoApenas.getRGB());
				 }
		}
		 
		 return imagemsaidaR;
	}
	
//	->ESSE CÓDIGO AQUI PEGA POR BANDA
		public static BufferedImage bandaG (BufferedImage imagem) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemsaidaG = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int verde = cor.getGreen();
			 		Color verdeApenas = new Color(verde, verde, verde);
			 		imagemsaidaG.setRGB(col, lin, verdeApenas.getRGB());
				 }
		}
		 
		 return imagemsaidaG;
	}
//	
//	->ESSE CÓDIGO AQUI PEGA POR BANDA
	public static BufferedImage bandaB (BufferedImage imagem) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemsaidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int azul = cor.getBlue();
			 		Color azulApenas = new Color(azul, azul, azul);
			 		imagemsaidaB.setRGB(col, lin, azulApenas.getRGB());
				 }
		}
		 
		 return imagemsaidaB;
	}
	
	//ESSE CODIGO AQUI É O COM MÉDIA
	public static BufferedImage bandaGray (BufferedImage imagem) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemsaidaGray = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int R = cor.getRed();
			 		int G = cor.getGreen();
			 		int B = cor.getBlue();
			 		int media = ((R + G + B) / 3);
			 		Color vermelhoApenas = new Color(media, media, media);
			 		imagemsaidaGray.setRGB(col, lin, vermelhoApenas.getRGB());
				 }
		}
		 
		 return imagemsaidaGray;
	}
	
	public static BufferedImage bandaNegativada (BufferedImage imagem) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemNegativada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int R = cor.getRed();
			 		int G = cor.getGreen();
			 		int B = cor.getBlue();
			 		Color vermelhoApenas = new Color((255-R), (255-G), (255-B));
			 		imagemNegativada.setRGB(col, lin, vermelhoApenas.getRGB());
				 }
		}
		 
		 return imagemNegativada;
	}
	
	public static BufferedImage TOMbandaR (BufferedImage imagem, int aumento) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemsaidaR = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int vermelho = cor.getRed();
			 		int tonalidade = aumentoTOM(aumento, vermelho);
			 		Color vermelhoApenas = new Color(tonalidade, 0, 0);
			 		imagemsaidaR.setRGB(col, lin, vermelhoApenas.getRGB());
				 }
		}
		 
		 return imagemsaidaR;
	}
	
	public static BufferedImage TOMbandaG (BufferedImage imagem, int aumento) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemsaidaG = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int verde = cor.getGreen();
			 		int tonalidade = aumentoTOM(aumento, verde);
			 		Color verdeApenas = new Color(0, tonalidade, 0);
			 		imagemsaidaG.setRGB(col, lin, verdeApenas.getRGB());
				 }
		}
		 
		 return imagemsaidaG;
	}
	
	
	public static BufferedImage TOMbandaB (BufferedImage imagem, int aumento) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage imagemsaidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int azul = cor.getBlue();
			 		int tonalidade = aumentoTOM(aumento, azul);
			 		Color azulApenas = new Color(0, 0, tonalidade);
			 		imagemsaidaB.setRGB(col, lin, azulApenas.getRGB());
				 }
		}
		 
		 return imagemsaidaB;
	}
	

	
	public static int aumentoTOM(int pixel, int aumento) {
		if(aumento + pixel <= 0) {
			aumento = 0;
		}
		else if(aumento + pixel >= 255) {
			aumento = 255;
		}
		else {
			aumento = pixel + aumento;
		}
		
		return aumento;
	}
	
	public static BufferedImage binary (BufferedImage imagem, int limiar) {
		 int altura = imagem.getHeight();
		 int largura = imagem.getWidth();
		 BufferedImage binaryFilter = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		 
		 for (int lin = 0; lin < altura; lin++) {
			 	for (int col = 0; col < largura; col++) {
			 		int rgb = imagem.getRGB(col, lin);
			 		Color cor = new Color(rgb);
			 		int red = cor.getRed();
			 		int green = cor.getGreen();
			 		int blue = cor.getBlue();
			 		int media = (red+green+blue)/3;
			 		int calculo = calcularLimiar(media, limiar);
			 		Color azulApenas = new Color(calculo, calculo, calculo);
			 		binaryFilter.setRGB(col, lin, azulApenas.getRGB());
				 }
		}
		 
		 return binaryFilter;
	 }
	
	public static int calcularLimiar (int atual, int limiar) {
		if (atual <= limiar)
			atual = 0;
		else if(atual > limiar)
			atual = 255;
		return atual;
	}
	
	public static BufferedImage brilhoY (BufferedImage image, int aumento) {
		
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				double y = 0.299 * red + 0.587 * green + 0.114 * blue;
				double i = 0.596 * red - 0.274 * green - 0.322 * blue;
				double q = 0.211 * red - 0.523 * green + 0.312 * blue;
				double aumento2 = aumento;
				double brilhoaum = aumento2+ y;
				
				double R = 1.000 * brilhoaum + 0.956 * i + 0.621 * q;
				double G = 1.000 * brilhoaum - 0.272 * i - 0.647 * q;
				double B = 1.000 * brilhoaum - 1.106 * i + 1.703 * q;
				
				int Ry = (int) R;
				int Gy = (int) G;
				int By = (int) B;
				Ry = brilhoadtregra(Ry);
				Gy = brilhoadtregra(Gy);
				By = brilhoadtregra(By);
				Color yiq = new Color(Ry,Gy,By);
				saidaB.setRGB(coluna,linha,yiq.getRGB());
								
			}
		}
		return saidaB;
		
	}
	
	public static BufferedImage brilhoYM (BufferedImage image, float aumento) {
		
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				double y = 0.299 * red + 0.587 * green + 0.114 * blue;
				double i = 0.596 * red - 0.274 * green - 0.322 * blue;
				double q = 0.211 * red - 0.523 * green + 0.312 * blue;
				double aumento2 = aumento;
				double brilhoaum = aumento2 * y;
				
				double R = 1.000 * brilhoaum + 0.956 * i + 0.621 * q;
				double G = 1.000 * brilhoaum - 0.272 * i - 0.647 * q;
				double B = 1.000 * brilhoaum - 1.106 * i + 1.703 * q;
				
				int Ry = (int) R;
				int Gy = (int) G;
				int By = (int) B;
				Ry = brilhoadtregra(Ry);
				Gy = brilhoadtregra(Gy);
				By = brilhoadtregra(By);
				Color yiq = new Color(Ry,Gy,By);
				saidaB.setRGB(coluna,linha,yiq.getRGB());
								
			}
		}
		return saidaB;
		
	}
	public static BufferedImage negativoYIQ (BufferedImage image) {
		
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				double y = 0.299 * red + 0.587 * green + 0.114 * blue;
				double yn = 255 - y;
				double i = 0.596 * red - 0.274 * green - 0.322 * blue;
				double q = 0.211 * red - 0.523 * green + 0.312 * blue;
				
				double R = 1.000 * yn + 0.956 * i + 0.621 * q;
				double G = 1.000 * yn - 0.272 * i - 0.647 * q;
				double B = 1.000 * yn - 1.106 * i + 1.703 * q;
				
				int Ry = (int) R;
				int Gy = (int) G;
				int By = (int) B;
				Ry = brilhoadtregra(Ry);
				Gy = brilhoadtregra(Gy);
				By = brilhoadtregra(By);
				Color yiq = new Color(Ry,Gy,By);
				saidaB.setRGB(coluna,linha,yiq.getRGB());
								
			}
		}
		return saidaB;
		
	}
	public static int brilhoadtregra (int pixel) {
		if (pixel < 0) {pixel = 0;}
		else if ( pixel > 255) {pixel = 255;}
		return pixel;
		
	};





}
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	