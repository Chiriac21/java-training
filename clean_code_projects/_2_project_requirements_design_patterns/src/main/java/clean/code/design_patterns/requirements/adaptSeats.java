package clean.code.design_patterns.requirements;

public class adaptSeats extends Seats implements SeatsAdapter {
    private int seatsNumber;

    public adaptSeats(){
        this.seatsNumber = 0;
    }

    public adaptSeats setSeatsNumber(int seats){
        this.seatsNumber = seats;
        return this;
    }

    public People adaptNumberOfSeatsAndPeople(People p){
        if(p.getPeopleAtShow() > this.seatsNumber){
            int persons = p.getPeopleAtShow();

            System.out.println("\nThere are less seats (" + this.seatsNumber + ") than people (" + persons + ") at the show!" +
                    "\nAdjusting the number of seats and people...\n");

            persons /= 2;
            this.seatsNumber += (persons-this.seatsNumber);
            p.setPeople(persons);

            System.out.println("After the adjust:\nSeats: " + this.seatsNumber + "\nParticipants: " + persons);
        }else{
            System.out.println("\nThere are enough seats!\nSeats: " + this.seatsNumber + "\nParticipants: " + p.getPeopleAtShow());
        }
        return p;
    }
    @Override
    public People resizeNumberOfPeopleAndSeats(){
        return adaptNumberOfSeatsAndPeople(getPeopleAtShow());
    }
}
