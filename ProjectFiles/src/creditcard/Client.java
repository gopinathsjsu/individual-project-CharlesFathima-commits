package creditcard;

public class  Client {

    public static void main(String args[]){
        try {
            FileProcessingContext fc = new FileProcessingContext();
            String inputFilename = "ProjectFiles/src/inputs/"+args[0];
            String outputFilename = "ProjectFiles/src/outputs/"+args[1];
            fc.parse(inputFilename, outputFilename);
        }catch(Exception e){
            System.out.print("Error: "+e);
        }
    }
}
