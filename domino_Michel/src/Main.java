import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random random = new Random();
    static Scanner l = new Scanner(System.in);
    static ArrayList<Peca> pedras = new ArrayList<>();
    static List pecasPlayer = new List();
    static List pecasBot = new List();
    static List jogo = new List();
    static int travado = 0;

    public static void main(String[] args) throws Exception {
        System.out.println("***** JOGO DE DOMINÓ *****");
        criarPedras(); // usei arraylist pra gerar todas pedras do jogo e depois distribuir pros
                       // jogadores (listas das pecas dos jogadores)
        distribuirPecasBot();
        distribuirPecasP();
        while (true) {
            System.out.println("Jogada do Bot: ");
            botPlay();
            if (pecasBot.size == 0) {
                System.out.println("PEÇAS DO BOT ACABARAM - VITÓRIA DO COMPUTADOR");
                break;
            }
            System.out.println("**********         PEÇAS JOGADAS         ********** ");
            jogo.imprimirLista();
            System.out.println("Número da peça da Ponta: " + jogo.p1);
            System.out.println("Número da peça da outra Ponta: " + jogo.p2);
            playerPlay();
            if (pecasPlayer.size == 0) {
                System.out.println("SUAS PEÇAS ACABARAM - VITÓRIA");
                break;
            }
            if(travado >= 6){
                System.out.println("O jogo teve 6 rodadas passadas, será finalizado!");
                contagemPecas();
                break;
            }
        }
    }

    public static void contagemPecas(){
        if(pecasBot.size > pecasPlayer.size){
            System.out.println("VITÓRIA - VOCÊ TEM MENOS PEÇAS QUE O COMPUTADOR!");
        }else if(pecasBot.size < pecasPlayer.size){
            System.out.println("VITÓRIA DO COMPUTADOR - NÚMERO DE PEÇAS MENOR!");
        }else{
            System.out.println("Número de peças igual - EMPATE!");
        }
    }

    public static void botPlay() {
        if (jogo.size == 0) {
            int x = random.nextInt(pecasBot.size);
            Node no = pecasBot.returnNode(x);
            jogo.insertHead(no.peca);
            System.out.println("Primeira Peça jogada: " + no.peca.n1 + "  /  " + no.peca.n2);
            pecasBot.removePeca(x);
            jogo.p1 = no.peca.n1;
            jogo.p2 = no.peca.n2;
        } else {
            int x = 0;
            Node no = pecasBot.head;
            while (no != null) {
                if (jogo.p1 == no.peca.n1) {
                    jogo.p1 = no.peca.n2;
                    jogo.insertHead(no.peca);
                    pecasBot.removePeca(x);
                    x = -1;
                    break;
                } else if (jogo.p1 == no.peca.n2) {
                    jogo.p1 = no.peca.n1;
                    jogo.insertHead(no.peca);
                    pecasBot.removePeca(x);
                    x = -1;
                    break;
                } else if (jogo.p2 == no.peca.n1) {
                    jogo.p2 = no.peca.n2;
                    jogo.insertLast(no.peca);
                    pecasBot.removePeca(x);
                    x = -1;
                    break;
                } else if (jogo.p2 == no.peca.n2) {
                    jogo.p2 = no.peca.n1;
                    jogo.insertLast(no.peca);
                    pecasBot.removePeca(x);
                    x = -1;
                    break;
                }
                no = no.next;
                x++;
            }

            if (x == -1) {
                System.out.println("Peça jogada: " + no.peca.n1 + "  /  " + no.peca.n2);
            } else {
                System.out.println("Passou a Vez!");
                travado++;
            }
        }
    }

    public static void playerPlay() {
        System.out.println("");
        System.out.println("---     Peças do PLAYER");
        pecasPlayer.imprimirLista();
        System.out.println("Escolha qual peça deseja jogar: Digite o número correspondente");
        System.out.println("Para passar a vez DIGITE -1 ");
        int e = readInt();
        if (e == -1) {
            System.out.println("Passou a Vez!");
            travado++;
        } else {
            Node no = pecasPlayer.returnNode(e);
            pecasPlayer.removePeca(e);
            System.out.println("Peça jogada: " + no.peca.n1 + "  /  " + no.peca.n2);
            if (jogo.p1 == no.peca.n1) {
                jogo.p1 = no.peca.n2;
                jogo.insertHead(no.peca);
            } else if (jogo.p1 == no.peca.n2) {
                jogo.p1 = no.peca.n1;
                jogo.insertHead(no.peca);
            } else if (jogo.p2 == no.peca.n1) {
                jogo.p2 = no.peca.n2;
                jogo.insertLast(no.peca);
            } else if (jogo.p2 == no.peca.n2) {
                jogo.p2 = no.peca.n1;
                jogo.insertLast(no.peca);
            }
        }
    }

    public static void criarPedras() {
        int c = 0;
        for (int x = 6; x >= 0; x--) {
            for (int y = 0; y <= 6 - c; y++) {
                Peca p = new Peca();
                p.n1 = x;
                p.n2 = y;
                pedras.add(p);
            }
            c++;
        }
    }

    public static void distribuirPecasBot() {
        for (int i = 0; i < 14; i++) {
            int x = random.nextInt(pedras.size());
            Peca p = pedras.get(x);
            pecasBot.insertHead(p);
            pedras.remove(x);
        }
    }

    public static void distribuirPecasP() {
        for (int i = 0; i < 14; i++) {
            int x = random.nextInt(pedras.size());
            Peca p = pedras.get(x);
            pecasPlayer.insertHead(p);
            pedras.remove(x);
        }
    }

    public static int readInt() {
        int value = l.nextInt();
        l.nextLine();
        return value;
    }
}
