
import java.util.*;
public class Booking {
    public ArrayList<Ticket> confirmationList;
    static int totalSeats=8;
    static int totalWaitingSeats = 2;
    static int totalStops = 5;
    public Queue<Ticket> waitingList;
    boolean[][] seatAvailable;
    boolean[][] waitingListSeats;
    public Booking(){
        confirmationList = new ArrayList<>();
        waitingList = new LinkedList<>();
        seatAvailable = new boolean[totalSeats][totalStops];
        for(int i=0;i<totalSeats;i++){
            for(int j=0;j<totalStops;j++){
                seatAvailable[i][j] = true;
            }
        }
        waitingListSeats = new boolean[totalWaitingSeats][totalStops];
        for(int i=0;i<totalWaitingSeats;i++){
            for(int j=0;j<totalStops;j++){
                waitingListSeats[i][j] = true;
            }
        }
    }

    public ArrayList<Ticket> getConfirmationList() {
        return confirmationList;
    }

    public void setConfirmationList(ArrayList<Ticket> confirmationList) {
        this.confirmationList = confirmationList;
    }

    public Queue<Ticket> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(LinkedList<Ticket> waitingList) {
        this.waitingList = waitingList;
    }
    public ArrayList<Integer> checkAvailable(int noOfSeats, String source, String destination){
        int seats=0;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i=0;i<totalSeats;i++){
            int flg = 0;
            for(int j=source.charAt(0)-'A';j<destination.charAt(0)-'A';j++){
                if(!seatAvailable[i][j]){
                    flg = 1;
                }
            }
            System.out.println();
            if(flg == 0){
                temp.add(i+1);
            }
        }
        if(temp.size() >= noOfSeats){
            return temp;
        }else{
            return new ArrayList<>();
        }
    }
    public ArrayList<Integer> checkAvailableInWaitingList(int noOfSeats, String source, String destination){
        int seats = 0;
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i=0;i<totalWaitingSeats;i++){
            int flg = 0;
            for(int j=source.charAt(0)-'A';j<destination.charAt(0)-'A';j++){
                if(!waitingListSeats[i][j]){
                    flg = 1;
                }
            }
            System.out.println();
            if(flg == 0){
                temp.add(i+1);
            }
        }
        if(temp.size() >= noOfSeats){
            return temp;
        }else {
            return new ArrayList<>();
        }
    }
    public void changeAvailablity(int noOfSeats, String source, String destination, ArrayList<Integer> seatNumbers){
        for(int i:seatNumbers){
            for(int j=source.charAt(0)-'A';j<destination.charAt(0)-'A';j++){
                seatAvailable[i-1][j] = false;
            }
        }
    }
    public void changeAvailablityReverse(int noOfSeats, String source, String destination, ArrayList<Integer> seatNumbers){
        for(int i:seatNumbers){
            for(int j=source.charAt(0)-'A';j<destination.charAt(0)-'A';j++){
                seatAvailable[i-1][j] = true;
            }
        }
    }

    public void changeAvailabilityWaiting(int noOfSeats, String source, String destination, ArrayList<Integer> seatNumbersWaiting){
        for(int i:seatNumbersWaiting){
            for(int j=source.charAt(0)-'A';j<destination.charAt(0)-'A';j++){
                waitingListSeats[i-1][j] = false;
            }
        }
    }
    public void changeAvailabilityWaitingReverse(int noOfSeats, String source, String destination, ArrayList<Integer> seatNumbersWaiting){
        for(int i:seatNumbersWaiting){
            for(int j=source.charAt(0)-'A';j<destination.charAt(0)-'A';j++){
                waitingListSeats[i-1][j] = true;
            }
        }
    }

    public boolean bookTicket(User user, int noOfSeats, String source, String destination){
        ArrayList<Integer> temp = checkAvailable(noOfSeats, source, destination);
        if(temp.size() > 0) {
            ArrayList<Integer> bookedSeats = new ArrayList<>();
            int ind = 0;
            for(int i=0;i<noOfSeats;i++){
                bookedSeats.add(temp.get(ind++));
            }
            Ticket newTicket = new Ticket(user, noOfSeats, source, destination, bookedSeats);
            confirmationList.add(newTicket);
            System.out.println(newTicket);
            System.out.println("Seat Booked Successfully");
            changeAvailablity(noOfSeats, source, destination, bookedSeats);
            printChart();
            return true;
        }
        else {
            if(noOfSeats<=2) {
                temp = checkAvailableInWaitingList(noOfSeats, source, destination);
                if (temp.size() > 0) {
                    ArrayList<Integer> bookedSeats = new ArrayList<>();
                    int ind = 0;
                    for(int i=0;i<noOfSeats;i++){
                        bookedSeats.add(temp.get(ind++));
                    }
                    Ticket newTicket = new Ticket(user, noOfSeats, source, destination, bookedSeats);
                    waitingList.add(newTicket);
                    changeAvailabilityWaiting(noOfSeats, source, destination, bookedSeats);
                    System.out.println("Seats not available\nBooked in Waiting List");
                    printChart();
                    return true;
                }
            }
        }
        System.out.println("No seats available");
        return false;
    }
    public void cancelTicketNoOfSeats(User user, int PNR, int noOfSeatsToCancel){
        int flg = 0;
        for(int i=0;i<confirmationList.size();i++){
            if(confirmationList.get(i).getPNR() == PNR){
                flg = 1;
                ArrayList<Integer> toCancel = new ArrayList<>();
                for(int j=0;j<noOfSeatsToCancel;j++){
                    toCancel.add(confirmationList.get(i).getSeatNumbers().get(j));
                }
                ArrayList<Integer> remaining = new ArrayList<>();
                for(int j=noOfSeatsToCancel;j<confirmationList.get(i).getSeatNumbers().size();j++){
                    remaining.add(confirmationList.get(i).getSeatNumbers().get(j));
                }
                changeAvailablityReverse(confirmationList.get(i).getNoOfSeats(), confirmationList.get(i).getSource(), confirmationList.get(i).getDestination(), toCancel);
                confirmationList.set(i, new Ticket(user, confirmationList.get(i).getNoOfSeats()-noOfSeatsToCancel, confirmationList.get(i).getSource(), confirmationList.get(i).getDestination(), remaining));
                System.out.println("Changed PNR"+confirmationList.get(i).getPNR());
            }
        }
        if(flg == 1){
            System.out.println("Ticket Canceled Successfully");
            printChart();
        }else{
            System.out.println("Can't able to Cancel tickets");
        }
        if(waitingList.size()>0){
            System.out.println("Booking Waiting Tickets");
            bookTicket(waitingList.peek().getUser(), waitingList.peek().getNoOfSeats(), waitingList.peek().getSource(), waitingList.peek().getDestination());
            changeAvailabilityWaitingReverse(waitingList.peek().getNoOfSeats(), waitingList.peek().getSource(), waitingList.peek().getDestination(), waitingList.peek().getSeatNumbers());
            waitingList.poll();
        }
    }
    public void cancelTicket(User user, int PNR){
        int flg = 0;
        for(int i=0;i<confirmationList.size();i++){
            if(confirmationList.get(i).getPNR() == PNR){
                flg = 1;
                changeAvailablityReverse(confirmationList.get(i).getNoOfSeats(), confirmationList.get(i).getSource(), confirmationList.get(i).getDestination(),confirmationList.get(i).getSeatNumbers());
                confirmationList.remove(i);
            }
        }
        if(flg == 1){
            System.out.println("Ticket Canceled Successfully");
            printChart();
        }else{
            System.out.println("Can't able to Cancel tickets");
        }
        if(waitingList.size()>0){
            System.out.println("Booking Waiting Tickets");
            bookTicket(waitingList.peek().getUser(), waitingList.peek().getNoOfSeats(), waitingList.peek().getSource(), waitingList.peek().getDestination());
            changeAvailabilityWaitingReverse(waitingList.peek().getNoOfSeats(), waitingList.peek().getSource(), waitingList.peek().getDestination(), waitingList.peek().getSeatNumbers());
            waitingList.poll();
        }
    }

    public void printChart(){

        char[][] seats = new char[8][5];
        for(Ticket t :confirmationList){
            for(int j=0;j<t.getNoOfSeats();j++) {
                for (int i = t.getSource().charAt(0)-'A'; i < t.getDestination().charAt(0)-'A'; i++) {
                    seats[j][i] = '*';
                }
            }
        }
        System.out.println("Confirm Tickets");
        System.out.println("\tA\tB\tC\tD\tE");
        for(int i=0;i<totalSeats;i++){
            System.out.print((i+1)+"\t");
            for(int j=0;j<totalStops;j++){
                if(!seatAvailable[i][j])
                    System.out.print("*\t");
                else
                    System.out.print(" \t");
            }
            System.out.println();
        }
        System.out.println("Waiting Tickets");
        System.out.println("\tA\tB\tC\tD\tE");
        for(int i=0;i<totalWaitingSeats;i++){
            System.out.print((i+1)+"\t");
            for(int j=0;j<totalStops;j++){
                if(!waitingListSeats[i][j])
                    System.out.print("*\t");
                else
                    System.out.print(" \t");
            }
            System.out.println();
        }
    }

}
