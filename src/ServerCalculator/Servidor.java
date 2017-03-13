package ServerCalculator;

import java.io.*;
import java.net.*;

public class Servidor {
    static ServerSocket serversocket;
    static Socket client_socket;
    static Conexao c;
    static String msg;

    public Servidor() {

        try {
            serversocket = new ServerSocket(9600);
            System.out.println("Criando o Server Socket");
        } catch (Exception e) {
            System.err.println("Nao criei o Server Socket...");
        }
    }


    public static void main(String args[]) {
        Requisicao requisicao = new Requisicao();
        Resposta resp = new Resposta();
        new Servidor();
        if (connect()) {          
                requisicao = (Requisicao)c.receive(client_socket);
                float op1 = requisicao.getNum1();
                float op2 = requisicao.getNum2();
                char op = requisicao.getOp();
                
                switch(op){
                    case '+':
                        resp.setResult(op1+op2);
                        resp.setStatus(0);
                        break;
                    case '-':
                        resp.setResult(op1-op2);  
                        resp.setStatus(0);
                        break;
                    case '*':
                        resp.setResult(op1*op2);
                        resp.setStatus(0);
                        break;
                    case '/':
                        if(op1 == 0 || op2 == 0){
                            resp.setStatus(2);
                            break;
                        }
                        resp.setResult(op1/op2);
                        resp.setStatus(0);
                        break;
                    default:
                        resp.setStatus(1);
                        break;
                }
                c.send(client_socket, resp);
            
            try {
                client_socket.close();
                serversocket.close();           // fase de desconexão
            } catch (Exception e) {
                System.err.println("Não encerrou a conexão corretamente" + e.getMessage());
            }
        }
    }

    static boolean connect() {
        boolean ret;
        try {
            client_socket = serversocket.accept();  // fase de conexão
            ret = true;
        } catch (Exception e) {
            System.err.println("Não fez conexão" + e.getMessage());
            ret = false;
        }
        return ret;
    }
}
