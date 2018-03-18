
import javax.xml.bind.annotation.XmlElement;

public class Review {
    private String submitter;
    private String body;

    Review(){}

    @XmlElement(name="submitter")
    public String getSubmitter() { return this.submitter; }

    @XmlElement(name="body")
    public String getBody() { return this.body; }
}
