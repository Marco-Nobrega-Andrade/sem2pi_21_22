package app.domain.model.Exceptions;

public class NameNotFoundException extends Exception {
        public NameNotFoundException(){
            super("there's not founded vaccine by the specify name");
        }
        public NameNotFoundException(String msg){
            super(msg);
        }
    }
