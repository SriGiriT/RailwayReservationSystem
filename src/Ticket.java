import java.util.*;
public class Ticket {
    private int PNR;
    static int PNRGenerator = 0;
    private User user;
    private int noOfSeats;
    private String source;
    private String Destination;

    public ArrayList<Integer> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(ArrayList<Integer> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    private ArrayList<Integer> seatNumbers;

    public int getPNR() {
        return PNR;
    }

    public void setPNR(int PNR) {
        this.PNR = PNR;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "PNR=" + PNR +
                ", user=" + user +
                ", noOfSeats=" + noOfSeats +
                ", source='" + source + '\'' +
                ", Destination='" + Destination + '\'' +
                '}';
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public Ticket(User user, int noOfSeats, String source, String destination, ArrayList<Integer> seatNumbers) {
        this.PNR = ++PNRGenerator;
        this.user = user;
        this.noOfSeats = noOfSeats;
        this.source = source;
        Destination = destination;
        this.seatNumbers = seatNumbers;
    }
}
