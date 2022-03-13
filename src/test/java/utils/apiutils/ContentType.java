package utils.apiutils;

public enum ContentType {
    Json("application/Json"),
    Xml("application/xml");

    String bodyContentType;

    ContentType(String s) {
        this.bodyContentType = s;
    }
    public String getValue(){return bodyContentType;}
}
