import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conexao {
    private DataInputStream in;
    private DataOutputStream out;
    private int messageId=0;
    private Message message = new Message();
    private Gson gson = new Gson();
    private String messageToSend;
    private String data = null;
    public Livro doOperation(Socket objectRef, int methodId, Livro livro) {
        message.setTipoMensagem(0);
        message.setIdRequisicao(messageId);
       // message.setReferenciaRemota(objectRef.getPort());
        message.setIdOperacao(methodId);
        message.setLivro(livro);

        messageToSend = gson.toJson(message);

        try{
            in = new DataInputStream( objectRef.getInputStream());
            out =new DataOutputStream( objectRef.getOutputStream());

            out.write(messageToSend.getBytes("UTF8"));
            byte[] buffer = new byte[1024];
            byte[] b =in.readAllBytes();
            data = new String(b);

            //byte[] a = imputStreamToByteArray(in);
            //data = new String(a);


            //System.out.println(data.substring(0,data.length()));

            	    // read a line of data from the stream
        }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("readline:"+e.getMessage());
        }finally {if(objectRef!=null) try {objectRef.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
        if(data != null){
            Message resposta = gson.fromJson(data,Message.class);
            if(resposta.getIdRequisicao() == messageId){
                messageId++;
                return resposta.getLivro();
            }
        }
        messageId++;
        return  null;
    }

    public byte[] imputStreamToByteArray(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        is.read(buffer);
        return buffer;
    }

}

