package methods;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AplicarFiltroDeBordas {

    public static void AplicarFiltros() {
        try {
            BufferedImage imagemOriginal = ImageIO.read(new File("C:\\Users\\kaiof\\OneDrive\\Documentos\\Pdi-Exercicios\\PDI-Filtros\\src\\Images\\paisagem.png"));

            // Aplique os filtros
            int[] filtro1 = {1, 1, 1, 0, 0, 0, -1, -1, -1};
            int[] filtro2 = {1, 0, -1, 1, 0, -1, 1, 0, -1};
            int[] filtro3 = {-1, -1, -1, -1, 8, -1, -1, -1, -1};
            int[] filtro4 = {0, -1, 0, -1, 5, -1, 0, -1, 0};
            int[] filtro5 = {0, 0, 0, -1, 1, 0, 0, 0, 0};
            int[] filtro6 = {0, 1, 0, 1, -4, 1, 0, 1, 0};
            int[] filtro8 = {-2, -1, 0, -1, 1, 1, 0, 1, 2};

            BufferedImage imagemFiltrada1 = aplicarFiltro(imagemOriginal, filtro1);
            BufferedImage imagemFiltrada2 = aplicarFiltro(imagemOriginal, filtro2);
            BufferedImage imagemFiltrada3 = aplicarFiltro(imagemOriginal, filtro3);
            BufferedImage imagemFiltrada4 = aplicarFiltro(imagemOriginal, filtro4);
            BufferedImage imagemFiltrada5 = aplicarFiltro(imagemOriginal, filtro5);
            BufferedImage imagemFiltrada6 = aplicarFiltro(imagemOriginal, filtro6);
            BufferedImage imagemFiltrada8 = aplicarFiltro(imagemOriginal, filtro8);

            // Salve as imagens
            ImageIO.write(imagemFiltrada1, "jpg", new File("imagem_filtrada1.jpg"));
            ImageIO.write(imagemFiltrada2, "jpg", new File("imagem_filtrada2.jpg"));
            ImageIO.write(imagemFiltrada3, "jpg", new File("imagem_filtrada3.jpg"));
            ImageIO.write(imagemFiltrada4, "jpg", new File("imagem_filtrada4.jpg"));
            ImageIO.write(imagemFiltrada5, "jpg", new File("imagem_filtrada5.jpg"));
            ImageIO.write(imagemFiltrada6, "jpg", new File("imagem_filtrada6.jpg"));
            ImageIO.write(imagemFiltrada8, "jpg", new File("imagem_filtrada8.jpg"));

            System.out.println("Filtros aplicados e imagens salvas com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage aplicarFiltro(BufferedImage imagem, int[] filtro) {
        int largura = imagem.getWidth();
        int altura = imagem.getHeight();
        BufferedImage imagemFiltrada = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);

        for (int y = 1; y < altura - 1; y++) {
            for (int x = 1; x < largura - 1; x++) {
                int pixel = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int rgb = imagem.getRGB(x + i, y + j);
                        int cinza = (int) (0.299 * ((rgb >> 16) & 0xFF) + 0.587 * ((rgb >> 8) & 0xFF) + 0.114 * (rgb & 0xFF));
                        pixel += filtro[(i + 1) * 3 + (j + 1)] * cinza;
                    }
                }
                pixel = Math.min(255, Math.max(0, pixel));
                imagemFiltrada.setRGB(x, y, (pixel << 16) | (pixel << 8) | pixel);
            }
        }

        return imagemFiltrada;
    }
}
