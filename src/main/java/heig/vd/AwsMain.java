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


        ///// demande analyse avec image externe

        HttpResponse<String> responseAnaylse = Unirest.post("http://localhost:8082/analyse")
                .header("Content-Type", "application/json")
                .body("{\"source\" : \"https://plus.unsplash.com/premium_photo-1663134281768-37fa467e9f5b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80\",\n    \"maxLabels\" : 20,\n    \"minConfidence\" : 70\n}")
                .asString();

        System.out.println(responseAnaylse.getBody());



        /////image dans S3

        HttpResponse<String> responseAnaylseS3 = Unirest.post("http://localhost:8082/analyse")
                .header("Content-Type", "application/json")
                .body("{\"source\" : "+responseGetURL.getBody()+",\n    \"maxLabels\" : 10}")
                .asString();

        System.out.println(responseAnaylseS3.getBody());


        ///// delete obj

        HttpResponse<String> responseDel = Unirest.delete("http://localhost:8080/objet/testsac.jpg")
                .asString();


        System.out.println(responseDel.getBody());


    }
}
