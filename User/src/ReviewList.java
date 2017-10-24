import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class ReviewList{
    ArrayList<Review> reviews;
    String user;

    ReviewList(String user){
        reviews = new ArrayList<>();
        this.user = user;
    }

    void addReview( String text, String provider, int rating){
        reviews.add(new Review(text, provider, rating, reviews.size()));
    }

    void removeReview(int reviewID){
        reviews.remove(reviewID);
    }

    static ArrayList readRewviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        String fileName = "User\\reviews.txt";
        try {
            FileReader fin = new FileReader(fileName);
            Scanner reviewFile = new Scanner(fin);

            String line;
            while (reviewFile.hasNextLine()) {
                line = reviewFile.nextLine();
                StringTokenizer st = new StringTokenizer(line);

                try {
                    if (st.countTokens() != 5) {
                        System.err.println("Skipping ill-formatted line " + line);
                        continue;
                    }
                    String text  = st.nextToken();
                    String provider = st.nextToken();
                    int rating = Integer.parseInt(st.nextToken());
                    int reviewID = Integer.parseInt(st.nextToken());
                    Review rfile = new Review(text, provider, rating, reviewID);
                    reviews.add(rfile);
                } catch (NumberFormatException e) {
                    System.err.println("Skipping ill-formatted line " + line);
                }
            }
        }catch (IOException e) {
            System.err.println(e);
            return null;
        }
        return reviews;
    }
    //todo:
//    static int writeRewviews(ArrayList<Profile>  prof){
//        try{
//            FileWriter fout = new FileWriter("User\\profiles.txt");
//            Iterator<Profile> iter = prof.iterator();
//            while(iter.hasNext()){
//                Profile pro = iter.next();
//                fout.write(pro.getWriterOut());
//            }
//            fout.flush();
//            fout.close();
//            return 0;
//        }catch(IOException e) {
//            System.err.println(e);
//            return 1;
//        }
//
//    }

}




class Review {
    String text;
    String provider;
    int rating;
    int reviewID;

    Review(String text, String provider, int rating, int reviewID){
        this.text = text;
        this.provider = provider;
        this.rating = rating;
        this.reviewID = reviewID;
    }

    //gettters
    public String getProvider() {
        return provider;
    }
    public String getText() {
        return text;
    }
    public int getRating() {
        return rating;
    }
    public int getReviewID() {
        return reviewID;
    }

    //setters
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public void setText(String text) {
        this.text = text;
    }

}




