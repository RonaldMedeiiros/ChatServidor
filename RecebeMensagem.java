package chatservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecebeMensagem {
    
    private Socket s;
    private ArrayList<PrintStream> clientes;

    
    public void setClientes(ArrayList<PrintStream> clientes) {
        this.clientes = clientes;
    }
    
    
    public RecebeMensagem (Socket s, ArrayList<PrintStream> clientes) throws IOException{
        
        this.s = s;
        this.clientes = clientes;
        
        Thread();
    }
    
    private void Thread() throws IOException{
        
        Thread t = new Thread(new Runnable(){
        
            @Override
            public void run(){
            
                String msg = "";
        
        InputStreamReader isr;
                try {
                    isr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    while((msg = br.readLine()) != null){
                        enviarMsg(msg);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(RecebeMensagem.class.getName()).log(Level.SEVERE, null, ex);
                }
        
        
       
                
            }
        });
        t.start();
        
    }
    
    private void enviarMsg(String mensagem){
        
        for(int a =0; a< clientes.size(); a++){
        
            clientes.get(a).println(mensagem);
            clientes.get(a).flush();
            
        }
        
    }
}
