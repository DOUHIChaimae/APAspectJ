package ma.enset.test;

import ma.enset.metier.Compte;
import ma.enset.metier.IMetierBanque;
import ma.enset.metier.IMetierBanqueImpl;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        new Application().start();
    }

    public void start() {
        System.out.println("Démarrage de l'application...");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez saisir le code du compte:");
        Long code = scanner.nextLong();

        System.out.println("Veuillez saisir le solde initial du compte:");
        double solde = scanner.nextDouble();

        IMetierBanque metier = new IMetierBanqueImpl();
        metier.addCompte(new Compte(code, solde));

        while (true) {
            System.out.println("Veuillez saisir l'opération à effectuer:");
            System.out.println("1- Verser");
            System.out.println("2- Retirer");
            System.out.println("3- Consulter");
            System.out.println("4- Quitter");
            int choix = scanner.nextInt();

            try {
                switch (choix) {
                    case 1:
                        System.out.println("Veuillez saisir le montant à verser:");
                        double montant = scanner.nextDouble();
                        metier.verser(code, montant);
                        break;
                    case 2:
                        System.out.println("Veuillez saisir le montant à retirer:");
                        montant = scanner.nextDouble();
                        metier.retirer(code, montant);
                        break;
                    case 3:
                        System.out.println(metier.consulter(code));
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choix invalide");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }
}