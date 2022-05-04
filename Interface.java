import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {
	
	int largurap = 820;
	int alturap = 640;

	BufferedImage imagem = null;
	
	static JTabbedPane painel = new JTabbedPane();
	

	
	public void ConfigurarFrame(JFrame frame) {

		frame.setTitle("Processamento digital de imagens");
		frame.setSize(largurap, alturap);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static BufferedImage fitImage(JLabel label, BufferedImage imagem) {
		
		int largura = label.getWidth();
		int altura = label.getHeight();
		
		if (largura < 1 || altura < 1) {
            return null;
        }
		
		BufferedImage redimensionarImage = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		Graphics2D graph = redimensionarImage.createGraphics();
		graph.drawImage(imagem, 0 ,0, largura, altura, null);
		graph.dispose();
		label.setIcon(new ImageIcon(redimensionarImage));
		return redimensionarImage;
		
		
	}
	
	public void conteudo() throws IOException{
		
		JFrame frame = new JFrame();
		
		JLabel originalLabel = new JLabel();
		JPanel original = new JPanel();
		Border blackline = BorderFactory.createTitledBorder("Imagem original");
		original.setLayout(null);
		original.setBorder(blackline);
		JButton escolher = new JButton("Escolher imagem");
		
		painel.add("Original", original);
		original.add(escolher);
		escolher.setBounds(230,25,150,30);
		originalLabel.setBounds(90,60,540,480);
		original.add(originalLabel);
		
		escolher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				painel.add("Original", original);
				original.removeAll();
				JFileChooser arquivo = new JFileChooser();
				arquivo.setDialogTitle("Escolha uma imagem:");
				arquivo.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Escolha uma imagem", "png", "jpg", "jpeg");
				arquivo.addChoosableFileFilter(filter);
				int res = arquivo.showSaveDialog(null);
				if(res == JFileChooser.APPROVE_OPTION) {
					File f = arquivo.getSelectedFile();
					arquivo.updateUI();
					
					try {
						imagem = ImageIO.read(f);
					}catch(IOException e1) {
						e1.printStackTrace();
					}
					
					
					fitImage(originalLabel, imagem);
					
					//passando os bufferedImage
					BufferedImage saidaR = filtros.bandaR(imagem);
					BufferedImage saidaG = filtros.bandaG(imagem);
					BufferedImage saidaB = filtros.bandaB(imagem);
					BufferedImage saidaGray = filtros.bandaGray(imagem);
					BufferedImage neg = filtros.bandaNegativada(imagem);
					BufferedImage negY = filtros.negativoYIQ(imagem);
					
					original.add(originalLabel);
					original.setVisible(true);

					JLabel negativoLabel = new JLabel();
					Border blackline0 = BorderFactory.createTitledBorder("Filtro negativo da imagem em RGB:");
					negativoLabel.setBorder(blackline0);
					negativoLabel.setBounds(40,60,540,480);
					fitImage(negativoLabel, neg);
					JPanel negativo = new JPanel();
					negativo.add(negativoLabel);
					negativo.setVisible(true);
					
					
					JLabel bandaGray = new JLabel();
					JLabel bandaGrayLabel = new JLabel();
					Border legenda = BorderFactory.createTitledBorder("Imagem com média de pixels como parâmetro para GrayScale");
					bandaGray.setBorder(legenda);
					bandaGrayLabel.setBounds(40,60,540,480);
					fitImage(bandaGrayLabel, saidaGray);
					bandaGray.add(bandaGrayLabel);
					bandaGray.setVisible(true);		
					
					JPanel grayPanel = new JPanel();
					JPanel grayRGBPanel = new JPanel();
					grayPanel.setLayout(null);
					Border legenda1 = BorderFactory.createTitledBorder("Selecione a banda para exibir o GrayScale individual da banda");
					grayPanel.setBorder(legenda1);
					grayRGBPanel.setBounds(40,60,540,480);
					
					JLabel grayRedLabel = new JLabel();
					grayRedLabel.setBounds(40,60,540,480);
					fitImage(grayRedLabel, saidaR);
					
					JLabel grayGreenLabel = new JLabel();
					grayGreenLabel.setBounds(40,60,540,480);
					fitImage(grayGreenLabel, saidaG);
					
					JLabel grayBlueLabel = new JLabel();
					grayBlueLabel.setBounds(40,60,540,480);
					fitImage(grayBlueLabel, saidaB);
					
					JButton butRed = new JButton("Banda RED");
					JButton butGreen = new JButton("Banda GREEN");
					JButton butBlue = new JButton("Banda Blue");
					butRed.setBounds(110,15,120,40);
					butGreen.setBounds(240,15,120,40);
					butBlue.setBounds(370,15,120,40);
					
					grayPanel.add(butRed);
					grayPanel.add(butGreen);
					grayPanel.add(butBlue);
					grayPanel.add(grayRGBPanel);
					
					butRed.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							grayRGBPanel.removeAll();
							grayRGBPanel.add(grayRedLabel);
							grayRGBPanel.updateUI();
						}
						
					});
					
					butGreen.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							grayRGBPanel.removeAll();
							grayRGBPanel.add(grayGreenLabel);
							grayRGBPanel.updateUI();
						}
						
					});
					
					butBlue.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							grayRGBPanel.removeAll();
							grayRGBPanel.add(grayBlueLabel);
							grayRGBPanel.updateUI();
						}
						
					});
					
					JPanel tomPanel = new JPanel();
					tomPanel.setLayout(null);
					Border legenda2 = BorderFactory.createTitledBorder("Digite um valor para alterar o tom e escolha uma banda (-255 até +255)");
					tomPanel.setBorder(legenda2);
					
					JLabel tomRedLabel = new JLabel();
					JLabel tomGreenLabel = new JLabel();
					JLabel tomBlueLabel = new JLabel();
					
					JPanel tomRGBPanel = new JPanel();
					
					JButton butRedTOM = new JButton("Banda RED");
					butRedTOM.setBounds(10,15,120,40);
					JButton butGreenTOM = new JButton("Banda GREEN");
					butGreenTOM.setBounds(135,15,120,40);
					JButton butBlueTOM = new JButton("Banda BLUE");
					butBlueTOM.setBounds(260,15,120,40);
					
					
					JTextField aumento = new JTextField(3);
					tomRGBPanel.setBounds(40,60,540,480);
					aumento.setBounds(420,15,120,40);
					aumento.setText("0");
					
					tomPanel.add(butRedTOM);
					tomPanel.add(butGreenTOM);
					tomPanel.add(butBlueTOM);
					tomPanel.add(tomRGBPanel);
					tomPanel.add(aumento);
					
					butRedTOM.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int aumentoINT = Integer.parseInt(aumento.getText());
							BufferedImage saidaREDTOM = filtros.TOMbandaR(imagem, aumentoINT);
							tomRedLabel.setBounds(40,60,540,480);
							fitImage(tomRedLabel, saidaREDTOM);
							tomRGBPanel.removeAll();
							tomRGBPanel.add(tomRedLabel);
							tomRGBPanel.updateUI();
						
							
						}
					});
					
					butGreenTOM.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int aumentoINT = Integer.parseInt(aumento.getText());
							BufferedImage saidaGreenTOM = filtros.TOMbandaG(imagem, aumentoINT);
							tomGreenLabel.setBounds(40,60,540,480);
							fitImage(tomGreenLabel, saidaGreenTOM);
							tomRGBPanel.removeAll();
							tomRGBPanel.add(tomGreenLabel);
							tomRGBPanel.updateUI();
							
							
						}
					});
					
					butBlueTOM.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int aumentoINT = Integer.parseInt(aumento.getText());
							BufferedImage saidaBlueTOM = filtros.TOMbandaB(imagem, aumentoINT);
							tomBlueLabel.setBounds(40,60,540,480);
							fitImage(tomBlueLabel, saidaBlueTOM);
							tomRGBPanel.removeAll();
							tomRGBPanel.add(tomBlueLabel);
							tomRGBPanel.updateUI();
							
							
						}
					});
					
					JPanel biPanelMenu = new JPanel();
					Border legenda3 = BorderFactory.createTitledBorder("Digite um parâmetro para binarizar a imagem (0 até 255)");
					biPanelMenu.setBorder(legenda3);
					JLabel biLabel = new JLabel();
					biPanelMenu.setLayout(null);
					JPanel biPanel = new JPanel();
					biPanel.setBounds(40,60,540,480);
					JTextField biValor = new JTextField(3);
					biValor.setBounds(340,15,40,40);
					JButton biBotao = new JButton("Binarizar");
					biBotao.setBounds(200,15,120,40);
					biPanelMenu.add(biValor);
					biValor.setText("128");
					biPanelMenu.add(biBotao);
					biPanelMenu.add(biPanel);
					biPanel.add(biLabel);
					biBotao.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int valor = Integer.parseInt(biValor.getText());
							BufferedImage saidaBi = filtros.binary(imagem, valor);
							biLabel.setBounds(40,60,540,480);
							fitImage(biLabel,saidaBi);
							biPanel.removeAll();
							biPanel.add(biLabel);
							biPanel.updateUI();
							
						}
						
					});
					
					JPanel menuYIQ = new JPanel();
					menuYIQ.setLayout(null);
					Border legenda4 = BorderFactory.createTitledBorder("Digite um valor para aplicar o brilho aditivo (-255 até 255)");
					menuYIQ.setBorder(legenda4);
					JPanel brilhoadt = new JPanel();
					brilhoadt.setBounds(40,60,540,480);
					JLabel adtLabel = new JLabel();
					JTextField valorBA = new JTextField(3);
					valorBA.setBounds(340,15,40,40);
					valorBA.setText("20");
					JButton adicionarBrilho = new JButton("Adicionar brilho");
					adicionarBrilho.setBounds(200,15,120,40);
					menuYIQ.add(valorBA);
					menuYIQ.add(adicionarBrilho);
					menuYIQ.add(brilhoadt);
					adicionarBrilho.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int valor = Integer.parseInt(valorBA.getText());
							BufferedImage brilhoadtv = filtros.brilhoY(imagem, valor);
							adtLabel.setBounds(40,60,540,480);
							fitImage(adtLabel,brilhoadtv);
							brilhoadt.removeAll();
							brilhoadt.add(adtLabel);
							brilhoadt.updateUI();
							
						}
						
					});	
					
					
					JPanel menuYIQmt = new JPanel();
					menuYIQmt.setLayout(null);
					Border legenda5 = BorderFactory.createTitledBorder("Digite um valor para multiplicar o brilho (Pode ser float)");
					menuYIQmt.setBorder(legenda5);
					JPanel brilhomt = new JPanel();
					brilhomt.setBounds(40,60,540,480);
					JLabel mtLabel = new JLabel();
					JTextField valorBM = new JTextField(3);
					valorBM.setBounds(340,15,40,40);
					valorBM.setText("1.25");
					JButton mtBrilho = new JButton("Adicionar brilho");
					mtBrilho.setBounds(200,15,120,40);
					menuYIQmt.add(valorBM);
					menuYIQmt.add(mtBrilho);
					menuYIQmt.add(brilhomt);
					mtBrilho.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							float valor = Float.parseFloat(valorBM.getText());
							System.out.println(valor);
							BufferedImage brilhomtv = filtros.brilhoYM(imagem, valor);
							mtLabel.setBounds(40,60,540,480);
							fitImage(mtLabel,brilhomtv);
							brilhomt.removeAll();
							brilhomt.add(mtLabel);
							brilhomt.updateUI();
							
						}
						
					});	
					JPanel negativoY = new JPanel();
					Border legenda6 = BorderFactory.createTitledBorder("Filtro negativo do YIQ da imagem");
					negativoY.setBorder(legenda6);
					JLabel negativoYLabel = new JLabel();
					negativoYLabel.setBounds(40,60,540,480);
					fitImage(negativoYLabel,negY);
					negativoY.add(negativoYLabel);
					
					
					painel.add("Tonalidade por banda",tomPanel);
					painel.add("Negativo RGB", negativo);
					painel.add("GrayScale por Banda", grayPanel);
					painel.add("GrayScale por Média", bandaGray);
					painel.add("Binarização", biPanelMenu);
					painel.add("Brilho YIQ Aditivo", menuYIQ);
					painel.add("Brilho YIQ Multiplicativo", menuYIQmt);
					painel.add("Negativo YIQ", negativoY);
					
					
				}
			}
			

		});
		
		frame.add(BorderLayout.CENTER, painel);
		ConfigurarFrame(frame);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
