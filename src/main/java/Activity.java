import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.ScatteringByteChannel;

import com.google.gson.Gson;

public class Activity {
    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("Please enter a username...");
            return;
        }

        String username = args[0];

        String url = "https://api.github.com/users/" + username + "/events";

        try {
            HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {

            Gson gson = new Gson();
            Event[] events = gson.fromJson(response.body(), Event[].class);

            for (Event event : events) {
                switch (event.type) {
                    case "PushEvent":
                        System.out.println("- Pushed to " + event.repo.name);
                        break;

                    case "WatchEvent":
                        System.out.println("- Starred " + event.repo.name);
                        break;

                    case "CreateEvent":
                        System.out.println("- Created repository " + event.repo.name);
                        break;

                    case "IssuesEvent":
                        System.out.println("- Opened an issue in " + event.repo.name);
                        break;

                    default:
                        System.out.println("- " + event.type + " on " + event.repo.name);
                }
            }

        } else if (response.statusCode() == 404) {
            System.out.println("User not found!");
        } else {
            System.out.println("API error: " + response.statusCode());
        }
    } catch (Exception e) {
            System.out.println("Unable to connect to Github. Please check your internet connection.");
        }
    }
}