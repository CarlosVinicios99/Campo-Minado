 import modelo.Tabuleiro;
 import visao.TabuleiroConsole;

public class Aplicacao {
    public static void main(String[] args) {

        Tabuleiro tabuleiro = new Tabuleiro(20, 20, 10);  
        new TabuleiroConsole(tabuleiro);
        
    }
}
