/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exa_404_Euler59;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenovo
 */
public class XOR_Decryption_Worker implements Callable<Integer> {

    private String key;
    private static String[] commonWords
            = new String[]{"the", "of", "and", "a", "to",
                "in", "is", "you", "that", "it"};
    private int common_words;
    private String encrypted_text;
    private String decrypted_text;
    private Path path = Paths.get(System.getProperty("user.dir"), "src", "Exa_404_Euler59", "p059_cipher.txt");

    public XOR_Decryption_Worker(String key) {
        this.key = key;
    }

    public void XOR_Input() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(path.toFile()));
            while ((encrypted_text += br.readLine()) != null) {
                encrypted_text += br.readLine();
            }
            
            String[] ascii_tokens = encrypted_text.split(",");
            encrypted_text = "";
            int cnt = 0;
            for (String ascii_token : ascii_tokens) {
                encrypted_text += (char) ( Integer.parseInt(ascii_token) ^ key.charAt(cnt++));
                if(cnt%3==0)
                {
                    cnt = 0;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public Integer call() throws Exception {
        
        for (String commonWord : commonWords) {
            if(encrypted_text.contains(commonWord))
            {
                common_words++;
            }
        }
        return common_words;
    }

}
