import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Comments")
public class Comments {
    //Todo make magic happen here

    private String userName;
    private String reply;
    private String comment;

    @XmlElement(name = "comment")
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @XmlElement(name = "reply")
    void setReply(String reply){
        this.reply = reply;
    }
    public String getReply() {
        return reply;
    }

    @XmlElement(name = "username")
    void setUserName(String username){
            this.userName = username;
    }
    String getUserName(){
        return this.userName;
    }


    public Comments(){}
    public Comments(String comment, String username){
        this.comment = comment;
        this.userName = username;
        this.reply = "";
    }
    public Comments (String comment, String reply, String userName){
        this.comment = comment;
        this.reply = reply;
        this.userName = userName;
    }

}
