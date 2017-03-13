package ServerCalculator;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    static Conexao c;
    static Socket socket;

    public Cliente() {
        try {
            socket = new Socket("localhost", 9600); // fase de conexão
        } catch (Exception e) {
            System.err.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]) {
        Requisicao req = new Requisicao();
        Resposta resp = new Resposta();
        new Cliente();
        Scanner entrada = new Scanner(System.in);
        System.out.println("Qual a operação desejada? (+,-,* ou /)");
        req.setOp(entrada.next().charAt(0));
        System.out.println("Qual é o primeiro número da operação");
        req.setNum1(entrada.nextFloat());
        System.out.println("Qual é o segundo número da operação");
        req.setNum2(entrada.nextFloat());
        c.send(socket, req);
            
            resp = (Resposta)c.receive(socket);      // fase de dados
            switch(resp.getStatus()){
                case 0:
                    System.out.println("Operação realizada com sucesso");
                    break;
                case 1:
                    System.out.println("Operador inválido");
                    break;
                case 2:
                    System.out.println("Divisão por 0");
                    break;
            }
            System.out.println("O resultado é: " + resp.getResult());
        try {
            socket.close();                 // fase de desconexão
        } catch (IOException e) {
            System.err.println("Não encerrou a conexão corretamente" + e.getMessage());
        }
    }
}
