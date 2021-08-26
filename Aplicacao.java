 import java.util.Scanner;

import modelo.Tabuleiro;
 import visao.TabuleiroConsole;

public class Aplicacao {
    
    static void executarJogo(int linhas, int colunas, int minas){
         Tabuleiro tabuleiro = new Tabuleiro(linhas, colunas, minas);
         new TabuleiroConsole(tabuleiro);
    }
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        System.out.println("Dificuldade\n");

        System.out.println("(1)Facil 8x8 -> 16 minas\n(2)Normal 16x16 -> 40 minas\n(3)Dificil 30x16 -> 99 minas");
        int opcao = entrada.nextInt();
        
        if(opcao == 1){
            executarJogo(8, 8, 16);  

        }
        else if(opcao == 2){
            executarJogo(16, 16, 40);  
         
        }
        else{
            executarJogo(30, 16, 99);
        }
        entrada.close(); 
    }
}
