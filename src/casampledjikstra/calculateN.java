/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casampledjikstra;

import java.util.Random;

/**
 *
 * @author Alex.Selby
 */
public class calculateN {

    public static void main(String[] args) {
        final int E=4;
        int R=0,N=0;
        
        Random rand = new Random();

        R = rand.nextInt(50 - 10)+10;
        System.out.println("R = "+R);
        N=R+E;
        System.out.println("R + E = "+N);
        System.out.println("N = "+N);
    }
}
