import java.text.DecimalFormat;
import java.util.Scanner;

public class Vertejums {
	static String[] studenti = null;
	static Scanner scan = new Scanner(System.in);
	static String[] studentii(){
		int studSk;
		do {
			System.out.println("Cik studentiem aprēķināsi gala vērtējumu?");
			studSk = scan.nextInt();
		}while(studSk<1);
		
		studenti = new String[studSk];
		
		for(int i=0; i<studenti.length; i++) {
			System.out.println("Ievadi "+(i+1)+". studentu");
			studenti[i] = scan.next();
		}
		
		return studenti;
	}
	public static void main(String[] args) {
		int studSk, kritSk;
		DecimalFormat df = new DecimalFormat("0.#");
		String izvele;

		
		
		do{
			System.out.println("1 - izveidot studentu masīvu | stop - apturēt programmu");
			izvele=scan.next();
		
		switch(izvele){
		case "1":
			studentii();
			break;
		case "2":
			break;
		case "stop": System.out.println("Programma apturēta!"); break;
			default: System.out.println("Darbības nepastāv!");
		}
		}while(!izvele.equalsIgnoreCase("stop"));
		
		
		
		
		do {
			System.out.println("Kāds būs kritēriju skaits?");
			kritSk = scan.nextInt();
		}while(kritSk<1);
		String[] kriteriji = new String[kritSk];
		int[] kriterijaSvars = new int[kritSk];
		int[][] kriterijaVertejums = new int[studenti.length][kritSk];
		double[] semestraVertejums = new double[studenti.length];
		
		
		//Definē kritērijus
		int maxSvars = 100;
		for(int i=0; i<kriteriji.length; i++) {
			System.out.println("Ievadi "+(i+1)+". kritēriju");
			kriteriji[i] = scan.next();
			//Norāda katra kritērija svaru
			do {
				System.out.println("Ievadi "+(i+1)+". kritērija svaru");
				kriterijaSvars[i] = scan.nextInt();
			}while(kriterijaSvars[i]>maxSvars || 
					kriterijaSvars[i]<1 || 
					(kriterijaSvars[0]==100 && kritSk > 1));
			maxSvars -= kriterijaSvars[i];
		}
		
		//Norāda vērtējumu kādu ieguvis katrs students par katru kritēriju
		for(int i=0; i<kriterijaVertejums.length; i++) {
			for(int j=0; j<kriterijaVertejums[i].length; j++) {
				do {
					System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
					kriterijaVertejums[i][j] = scan.nextInt();
				}while(kriterijaVertejums[i][j]<0 || kriterijaVertejums[i][j]>10);
			}
		}
		
		double rezultats;
		for(int i=0; i<studenti.length; i++) {
			rezultats=0;
			for(int j=0; j<kriteriji.length; j++) {
				rezultats += ((double) kriterijaSvars[j]/100)*kriterijaVertejums[i][j];
			}
			semestraVertejums[i] = rezultats;
		}
		
		for(int i=0; i<studenti.length; i++) {	
			for(int j=0; j<kriteriji.length; j++) {
				System.out.println("Studenta "+studenti[i]+" vērtējums par kritēriju "+kriteriji[j]+" ir "+kriterijaVertejums[i][j]+", kura svars ir "+kriterijaSvars[j]);
			}
			System.out.println("Semestra vērtējums ir "+df.format(semestraVertejums[i])+"\n");
		}
		scan.close();
	}
}
