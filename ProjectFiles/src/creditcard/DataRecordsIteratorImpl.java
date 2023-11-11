package creditcard;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.List;

public class DataRecordsIteratorImpl implements DataRecordsIterator {
    private List<String> csvRecords;
    private NodeList xmlRecords;
    private JSONArray jsonRecords;
    private int cursor;
    private int max;

    public DataRecordsIteratorImpl(JSONArray records, int size) {
        jsonRecords = records;
        cursor = 0;
        max = size;
    }

    public DataRecordsIteratorImpl(List<String> records) {
        csvRecords = records;
        cursor = 0;
        max = records.size();
    }

    public DataRecordsIteratorImpl(NodeList records) {
        xmlRecords = records;
        cursor = 0;
        max = records.getLength();
    }

    @Override
    public void next() {
        cursor++;
    }

    @Override
    public String currentString() {
        return csvRecords.get(cursor);
    }

    @Override
    public Node currentNode() {
        return xmlRecords.item(cursor);
    }

    @Override
    public JSONObject currentObject() {
        return (JSONObject) jsonRecords.get(cursor);
    }

    @Override
    public boolean isDone() {
        return cursor == max;
    }
}
