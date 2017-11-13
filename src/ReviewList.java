import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Review.class)
class ReviewList extends ArrayList<Review> {

    ReviewList() {
    }

    @XmlElement
    List<Review> getReviews(){
        return this;
    }
}

class Review {
    private String text;
    private String provider;
    private int rating;

    Review(String text, String provider, int rating){
        this.text = text;
        this.provider = provider;
        this.rating = rating;
    }

    //getters & setters
    @XmlElement
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    @XmlElement
    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
    @XmlElement
    public void setRating(int rating) {
        this.rating = rating;
    }
    public int getRating() {
        return rating;
    }
}




