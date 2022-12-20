package heig.vd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import static org.junit.jupiter.api.Assertions.*;

public class AwsMain
{
    public static void main( String[] args ) throws IOException {

        ///// list obj

        System.out.println("LIST OBJECT : ");
        HttpResponse<String> responseGetList = Unirest.get("http://localhost:8080/objets")
                .asString();

        assertEquals(200,  responseGetList.getStatus());
        System.out.println(responseGetList.getBody());

        ///// post obj

        System.out.println("CREATE OBJECT : ");
        HttpResponse<String> requestPost = Unirest.post("http://localhost:8080/objet")
                .field("file", new File("testBureau.jpg"))
                .field("objectName", "testBureau.jpg")
                .asString();
        assertEquals(201, requestPost.getStatus());

        System.out.println(requestPost.getBody());

        ///// get url obj

        System.out.println("GET URL OBJECT : ");
        HttpResponse<String> responseGetURL = Unirest.get("http://localhost:8080/objet/publie?nom=testBureau.jpg")
                .asString();
        assertEquals(200, responseGetURL.getStatus());
        System.out.println(responseGetURL.getBody());

        ///// analyser image dans s3

        System.out.println("ANALYSE URL OBJECT : ");
        HttpResponse<String> responseAnaylseS3 = Unirest.post("http://localhost:8081/analyse")
                .header("Content-Type", "application/json")
                .body("{\"source\" : "+responseGetURL.getBody()+"}")
                .asString();
        assertEquals(200, responseAnaylseS3.getStatus());
        System.out.println(responseAnaylseS3.getBody());

        ///// delete obj

        System.out.println("DELETE OBJECT : ");
        HttpResponse<String> responseDel = Unirest.delete("http://localhost:8080/objet/testBureau.jpg")
                .asString();

        assertEquals(200, responseDel.getStatus());
        System.out.println(responseDel.getBody());

        ///// upload result on bucket

        System.out.println("UPLOAD RESULT ANALYSE OBJECT : ");
        Path tempFile = Files.createTempFile("testBureau-result", ".json");
        try {
            Files.writeString(tempFile, responseAnaylseS3.getBody());
        } catch (IOException e) {
            Files.delete(tempFile);
            throw e;
        }

        HttpResponse<String> requestPostResult = Unirest.post("http://localhost:8080/objet")
                .field("file", new File(String.valueOf(tempFile)))
                .field("objectName", "testBureau-result.json")
                .asString();

        assertEquals(201, requestPostResult.getStatus());
        Files.delete(tempFile);
        System.out.println(requestPostResult.getBody());

        ///// delete result on bucket
        System.out.println("DELETE RESULT OBJECT : ");

        HttpResponse<String> responseDelResult = Unirest.delete("http://localhost:8080/objet/testBureau-result.json")
                .asString();

        assertEquals(200, responseDelResult.getStatus());
        System.out.println(responseDelResult.getBody());

    }
}
