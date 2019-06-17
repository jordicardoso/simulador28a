package firestore;

import com.google.api.client.util.ArrayMap;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.io.InputStream;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class putVots implements Processor{

    public void process(Exchange exchange) throws Exception {

        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("service-key.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        FirestoreOptions foptions =
                FirestoreOptions.newBuilder()
                        .setTimestampsInSnapshotsEnabled(true)
                        .setCredentials(credentials)
                        .setProjectId("tuvotestucomptes-2ecb8")
                        .build();
        Firestore db = foptions.getService();

        ApiFuture<DocumentReference> addedDocRef = db.collection("vots").add((HashMap)exchange.getIn().getBody());

        exchange.getOut().setBody("Added document with ID: " + addedDocRef.get().getId());
    }
}