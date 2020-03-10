/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exa_404_Euler59;

import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class XOR_Decryption_Launcher {

    public static void perform_XOR_Decryption() {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        CompletionService service = new ExecutorCompletionService(pool);

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                for (int k = 0; k < 26; k++) {
                    service.submit(new XOR_Decryption_Worker("" + ('a' + i) + "" + ('a' + j) + "" + ('a' + k)));
                }
            }
        }
        pool.shutdown();
        
        while(!pool.isTerminated())
        {
            try {
                Future<Integer> future = service.take();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(XOR_Decryption_Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }

    public static void main(String[] args) {
        perform_XOR_Decryption();
    }

}
