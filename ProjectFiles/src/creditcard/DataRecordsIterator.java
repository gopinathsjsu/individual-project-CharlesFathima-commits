package creditcard;

import org.json.simple.JSONObject;
import org.w3c.dom.Node;

public interface DataRecordsIterator {
    boolean isDone();
    void next();
    String currentString();
    Node currentNode();
    JSONObject currentObject();
}
