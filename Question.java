package quiz_game;

public class Question {
private String text;
private String response;

public Question (String text, String response) {
	this.text=text;
	this.response=response;
}

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public String getResponse() {
	return response;
}

public void setResponse(String response) {
	this.response = response;
}

}

