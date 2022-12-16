package heig.vd;



import java.io.File;
import java.io.IOException;
import java.net.URI;



import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class AwsMain
{
    public static void main( String[] args ){

        //////list obj

        HttpResponse<String> responseGetList = Unirest.get("localhost:8080/objets")
                .asString();


        /////////////////////post obj


        HttpResponse<String> requestPost = Unirest.post("http://localhost:8080/objet")
                .field("file", new File("testsac.jpg"))
                .field("objectName", "testsac.jpg")
                .asString();



        ////////get url obj

        HttpResponse<String> responseGetURL = Unirest.get("localhost:8080/objet/publie?nom=testsac.jpg")
                .asString();


        ///// delete obj

        HttpResponse<String> responseDel = Unirest.delete("localhost:8080/objet/testsac.jpg")
                .asString();



    }
}
