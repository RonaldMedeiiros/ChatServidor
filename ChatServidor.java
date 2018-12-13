
package chatservidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ChatServidor {

    public static void main(String[] args) throws IOException {
        
        ArrayList<PrintStream> clientes = new ArrayList<>();
        
        int porta = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a porta do servidor: "));
        ServerSocket server = new ServerSocket(porta);
        Socket socket;
        
        while(true){
            socket = server.accept();
            JOptionPane.showMessageDialog(null, "Cliente conectado!");
            clientes.add(new PrintStream(socket.getOutputStream()));
            RecebeMensagem msg = new RecebeMensagem(socket, clientes);
            
        }
    }
    
}
