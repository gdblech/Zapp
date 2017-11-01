import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;


class ReviewList{
    ArrayList<Review> reviews;

    ReviewList(String user){
        //todo reviews = readReviews(user);

    }

    void addReview( String text, String provider, int rating){
        reviews.add(new Review(text, provider, rating, reviews.size()));
    }

    void removeReview(int reviewID){
        reviews.remove(reviewID);
    }

//    private static ArrayList readReviews(String user) {
//        ArrayList<Review> reviews = new ArrayList<>();
//        String fileName = "User\\reviews_" + user +".txt";
//        try {
//            FileReader fin = new FileReader(fileName);
//            Scanner reviewFile = new Scanner(fin);
//
//
//            while (reviewFile.hasNextLine()) {
//                String text  =  reviewFile.nextLine();
//                String provider =  reviewFile.nextLine();
//                int rating = Integer.parseInt( reviewFile.nextLine());
//                int reviewID = Integer.parseInt( reviewFile.nextLine());
//                Review rfile = new Review(text, provider, rating, reviewID);
//                reviews.add(rfile);
//
//            }
//        }catch (IOException e) {
//            try {
//                new FileWriter("User\\reviews_" + user + ".txt");
//            } catch(IOException e2){
//                System.out.println(e2);
//                return null;
//            }
//            return  reviews;
//        }
//        return reviews;
//    }
    //todo:
//    static int writeRewviews(ArrayList<Review>  reviews, String user){
//        try{
//            FileWriter fout = new FileWriter("User\\reviews_" + user + ".txt");
//            Iterator<Review> iter = reviews.iterator();
//            while(iter.hasNext()){
//                Review rev = iter.next();
//                fout.write(rev.getText() + "\n");
//                fout.write(rev.getProvider() + "\n");
//                fout.write(rev.getRating() + "\n");
//                fout.write(rev.getReviewID() + "\n");
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




