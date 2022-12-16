package heig.vd;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;


public class AwsMain
{
    public static void main( String[] args ) throws IOException, InterruptedException {
        String nom = "testsac.jpg";


        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest requestPost = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/objet?objectName="+nom))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("file.json")))
                .build();

        HttpResponse<String> responsePost = httpClient.send(requestPost, HttpResponse.BodyHandlers.ofString());
        System.out.println(responsePost.statusCode());
        System.out.println(responsePost.body());

        HttpRequest requestGet = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/objet/publie?nom="+nom))
                .setHeader("Accept", "application/json").build();

        HttpResponse<String> responseGet = httpClient.send(requestGet, HttpResponse.BodyHandlers.ofString());
        System.out.println(responseGet.statusCode());
        System.out.println(responseGet.body());

    }
}
