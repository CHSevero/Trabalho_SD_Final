


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args){
        boolean continuar = true;
        int opcao;
        Scanner scanner = new Scanner(System.in);
        Socket s = null;

        try{
            s = new Socket("localhost", 2111, InetAddress.getLocalHost(), 50001);
        }catch (IOException e){System.out.println("Socket:"+e.getMessage()); }



        while (continuar){
            System.out.println("Digite:");
            System.out.println("1 para upload");
            System.out.println("2 para download");
            System.out.println("3 para sair");
            opcao = Integer.valueOf(scanner.nextLine());


            Conexao c = new Conexao();
            Livro resposta = null;
            switch (opcao){
                case 1:
                    Livro l = new Livro();
                    System.out.println("Nome: ");
                    l.setNome(scanner.nextLine());
                    System.out.println("Digite o caminho do livro: ");
                    String caminho=scanner.nextLine();

                    File file = new File(caminho);
                    l.setConteudo(readFileToByteArray(file));

                    resposta = c.doOperation(s,1,l);
                    if(resposta != null){
                        System.out.println("Cadastro realizado com sucesso");
                    }
                    break;
                case 2:
                    Livro l1 = new Livro();
                    System.out.println("Nome: ");
                    l1.setNome(scanner.nextLine());
                    l1.setConteudo("vazio");
                    resposta=c.doOperation(s,2,l1);
                    if(resposta != null){

                        byte[] bytes = Base64.getDecoder().decode(resposta.getConteudo());
                        Path path= Paths.get("/home/chsevero/Desktop/Distributed Systems/Trabalho_SD_4/src/main/java/livros/"+resposta.getNome()+".pdf");
                        try{
                            Files.write(path,bytes);
                        }catch (IOException e){System.out.println(e);}

                        System.out.println("Download realizado com sucesso");
                    }

                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção invalida");
                    break;
            }
        }

    }

    public static String readFileToByteArray(File file){
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();

        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return new String(Base64.getEncoder().encodeToString(bArray));
    }

}
