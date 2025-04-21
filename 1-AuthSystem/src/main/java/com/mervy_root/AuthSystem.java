package com.mervy_root;

import org.mindrot.jbcrypt.BCrypt;
import java.util.HashMap;
import java.util.Scanner;

public class AuthSystem {
    static HashMap<String, String> utilisateurs = new HashMap<>(); // email -> mot de passe haché

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choixPourRecommencer;
        do {
            System.out.println("1. S'inscrire");
            System.out.println("2. Se connecter");
            System.out.println("Entrez votre choix : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                System.out.print("Email : ");
                String email = scanner.nextLine();
                System.out.print("Mot de passe : ");
                String password = scanner.nextLine();

                // Hachage
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                utilisateurs.put(email, hashed);
                System.out.println("Inscription réussie !");
            } else if (choix == 2) {
                System.out.print("Email : ");
                String email = scanner.nextLine();
                System.out.print("Mot de passe : ");
                String password = scanner.nextLine();

                String hashed = utilisateurs.get(email);

                if (hashed != null && BCrypt.checkpw(password, hashed)) {
                    System.out.println("Connexion réussie !");
                } else {
                    System.out.println("Email ou mot de passe incorrect !");
                }
            }

            System.out.println("Voulez-vous continuer : 1=oui && 0=non");
            choixPourRecommencer = scanner.nextInt();
            scanner.nextLine();

        }
        while (choixPourRecommencer == 1);

    }
}