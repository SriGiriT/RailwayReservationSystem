import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRegistered = false;
        User user;
        if(!isRegistered){
            System.out.println("Please create a User Login to Access our system");
            System.out.println("Enter User name, mail Id, password");
            String name = sc.next();
            String mailId = sc.next();
            String password = sc.next();
            user = new User(name, mailId, password);
            Booking booking = new Booking();
            boolean isRunning = true;
            while(isRunning) {
                    System.out.println("1. Book ticket\n2.Cancel ticket\n3.show chart\n4. Exit");
                    int n = sc.nextInt();sc.nextLine();
                    switch (n) {
                        case 1:
                            System.out.println("Enter Source Destination and no of seats with space between");
                            String[] inp = sc.nextLine().split(" ");
                            String source = inp[0];
                            String destination = inp[1];
                            int noOfSeats = Integer.parseInt(inp[2]);

                            booking.bookTicket(user, noOfSeats, source, destination);
                            break;
                        case 2:
                            System.out.println("Enter the PNR no to cancel the ticket");
                            int pnr = sc.nextInt();
                            System.out.println("Do you want to cancel all tickets y/n");
                            String y = sc.next();
                            if (y.equals("y")) {
                                booking.cancelTicket(user, pnr);
                            } else {
                                System.out.println("Enter the no of seats to get canceled");
                                int noOfSeatToCancel = sc.nextInt();
                                booking.cancelTicketNoOfSeats(user, pnr, noOfSeatToCancel);
                            }
                            break;
                        case 3:
                            System.out.println("Chart");
                            booking.printChart();
                            break;
                        case 4:
                            isRunning = false;
                            System.out.println("Exited the System!!!");
                            break;
                    }
            }
        }
    }
}