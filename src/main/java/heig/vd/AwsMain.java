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

        HttpResponse<String> responseGetList = Unirest.get("http://localhost:8080/objets")
                .asString();


        System.out.println("code status: " + responseGetList.getStatus()+ " reponse: " + responseGetList.getBody());
        /////////////////////post obj


        HttpResponse<String> requestPost = Unirest.post("http://localhost:8080/objet")
                .field("file", new File("testsac.jpg"))
                .field("objectName", "testsac.jpg")
                .asString();


        System.out.println(requestPost.getBody());

        ////////get url obj

        HttpResponse<String> responseGetURL = Unirest.get("http://localhost:8080/objet/publie?nom=testsac.jpg")
                .asString();

        System.out.println(responseGetURL.getBody());


        ///// delete obj

        HttpResponse<String> responseDel = Unirest.delete("http://localhost:8080/objet/testsac.jpg")
                .asString();


        System.out.println(responseDel.getBody());


    }
}
