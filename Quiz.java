package quiz_game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
private boolean playAgain;
private int score;
private long timeElapsed; 
private boolean done = false;
private static final int NBRE_DE_QUESTION = 5;
Scanner clavier = new Scanner(System.in);

public void start() {
try {
	long startTime = System.currentTimeMillis(); 

ArrayList<Question> questions = generate(NBRE_DE_QUESTION);
for(Question question: questions )
{
System.out.println(question.getText());
String userAnswer =clavier.nextLine();
if(userAnswer.equalsIgnoreCase(question.getResponse()))
{
score++;
System.out.println("Bonne Réponse");
}else {
	score--;
	System.out.println("Oops! Mauvaise Réponse");
	System.out.println("La bonne reponse etait:" +question.getResponse());
}
}
done = true ;
long endTime = System.currentTimeMillis();
timeElapsed=endTime-startTime;
}catch(IllegalArgumentException e) {
	done = false;
	System.out.println(e.getMessage());}
}

public void displayResults() {
if (done == true) {
System.out.printf("Votre score final est de  %d / %d.\n", score,NBRE_DE_QUESTION);	
System.out.printf("Il vous a fallu environ %d secondes pour repondre aux %d questions\n", getTimeElapsedInSeconds(timeElapsed),NBRE_DE_QUESTION);	
}	
}

public ArrayList<Question> generate(int nbreQuestions){
	String [][] data=getData();
	if(nbreQuestions>data.length) {
		throw new IllegalArgumentException("On peut generer que  "+ data.length+" questions maximum.");
	}
	ArrayList<Question> questions= new ArrayList<>();
	int index;
	ArrayList<Integer> indexAlreadyTaken = new ArrayList<>();
	indexAlreadyTaken.clear();
	for(int i=0;i<nbreQuestions; i++) {
		do {
		Random random=new Random();
		index=random.nextInt(data.length);
		}while(indexAlreadyTaken.contains(index));
		indexAlreadyTaken.add(index);
		String pays= data[index][0];
		String capitale=data[index][1];
		String questionText=String.format("Quelle est la capitale de ce pays: %s?",pays);
		questions.add(new Question(questionText,capitale));
	}
	
	return questions;
}
private int getTimeElapsedInSeconds(long timeInMilliSeconds) 
{
	return (int) (timeInMilliSeconds/1000);
}
public String[][] getData(){
	String [][] data = {
			{"Senegal","Dakar"},
			{"France","Paris"},
			{"Nigeria","Lagos"},
			{"Gabon","Libreville"},
			{"Allemagne","Berlin"},
			{"Italie","Rome"},
			{"Monaco","Monaco"},
			{"Liberia","Monrovia"},
			{"Perou","Lima"},
			{"Tunisie","Tunis"},
			{"Japon","Tokio"},
			{"Libye","Tripoli"},
			{"Liban","Beyrouth"},
			{"Mali","Bamako"},
			{"Ukraine","Kiev"},
			{"Inde","New Delhi"},
			{"Palastine","Kods"},
			{"Egypte","Le Caire"},
			{"Espagne","Madrid"},
			{"Chine","Pekin"},
			{"Afghanistan","Kaboul"},
			{"Algerie","Alger"},
			{"Etats Unis","Washington"},
			{"Grece","Athenes"},
			{"Bulgarie","Sofia"},
			{"Arabie Saoudite","Riyad"}
	};
	return data;
}

public void run() {
	do {
		this.start();
		this.displayResults();
		System.out.println("Voulez vous continuer ? (oui/non)");
		String answerPlayAgain = clavier.nextLine();
		if (answerPlayAgain.equalsIgnoreCase("oui")) {
			this.playAgain = true;
		}
		else {
			this.playAgain = false;
		}
	} while (this.playAgain);
	System.out.println("Merci d'avoir joué ! Fin du jeu...");
}
}
